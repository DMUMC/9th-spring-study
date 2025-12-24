package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.global.annotation.CheckPage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionQueryService {
    Page<Mission> getMissionListByStoreId(
            @PathVariable Long storeId,
            @CheckPage Integer page
    );

    Page<MemberMission> getMyChallengingMissions(
            Long memberId,
            Integer page
    );
}
