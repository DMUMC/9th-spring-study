// com.example.umc9th.domain.mission.service.MissionQueryService

package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.mission.dto.MyMissionPageResponse;
import com.example.umc9th.domain.mission.dto.StoreMissionPageResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private static final String STATUS_IN_PROGRESS = "IN_PROGRESS";

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;

    public StoreMissionPageResponse getStoreMissions(Long storeId, Integer page) {

        if (page == null || page < 1) {
            throw new IllegalArgumentException("page는 1 이상의 정수여야 합니다.");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("STORE_NOT_FOUND"));

        PageRequest pageRequest = PageRequest.of(
                page - 1,
                10,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toStoreMissionPageResponse(result);
    }

    public MyMissionPageResponse getMyOngoingMissions(Long memberId, Integer page) {

        if (page == null || page < 1) {
            throw new IllegalArgumentException("page는 1 이상의 정수여야 합니다.");
        }

        PageRequest pageRequest = PageRequest.of(
                page - 1,
                10,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<UserMission> result = userMissionRepository
                .findAllByMemberIdAndStatus(memberId, STATUS_IN_PROGRESS, pageRequest);

        return UserMissionConverter.toMyMissionPageResponse(result);
    }
}
