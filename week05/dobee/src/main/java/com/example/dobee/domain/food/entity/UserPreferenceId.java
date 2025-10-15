package com.example.dobee.domain.food.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class UserPreferenceId implements Serializable {
    private Long userId;
    private Long categoryId;
}
