package com.umc.service;

import com.umc.converter.MissionConverter;
import com.umc.domain.Mission;
import com.umc.dto.response.MissionResponseDTO;
import com.umc.repository.MissionRepository;
import com.umc.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    
    /**
     * 특정 가게의 미션 목록 (페이징)
     */
    public MissionResponseDTO.MissionPreviewListDTO getStoreMissionsPaged(Long storeId, Integer page) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다. ID: " + storeId));
        
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Mission> missionPage = missionRepository.findByStoreId(storeId, pageRequest);
        
        return MissionConverter.toMissionPreviewListDTO(missionPage);
    }
}
