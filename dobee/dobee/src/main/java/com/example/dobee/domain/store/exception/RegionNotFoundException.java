package com.example.dobee.domain.store.exception;

import com.example.dobee.domain.store.enums.StoreErrorCode;
import com.example.dobee.global.exception.CustomException;

public class RegionNotFoundException extends CustomException {
    public RegionNotFoundException() {
        super(StoreErrorCode.REGION_NOT_FOUND);
    }
}
