package com.example.dobee.domain.member.service.command;

import com.example.dobee.domain.member.converter.MemberConverter;
import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.domain.member.entity.Food;
import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.entity.mapping.MemberFood;
import com.example.dobee.domain.member.exception.FoodException;
import com.example.dobee.domain.member.exception.code.FoodErrorCode;
import com.example.dobee.domain.member.repository.FoodRepository;
import com.example.dobee.domain.member.repository.MemberFoodRepository;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDto.MemberJoinResDto signup(MemberDto.MemberJoinReqDto dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword, Role.ROLE_USER);

        memberRepository.save(member);

        if (dto.preferredFoods() != null && !dto.preferredFoods().isEmpty()) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            for (Long id : dto.preferredFoods()) {
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));

                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberFoodList.add(memberFood);
            }

            memberFoodRepository.saveAll(memberFoodList);
        }
        return MemberConverter.toJoinDto(member);
    }
}
