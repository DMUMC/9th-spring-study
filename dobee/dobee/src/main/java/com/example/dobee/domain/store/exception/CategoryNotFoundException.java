package com.example.dobee.domain.store.exception;

import com.example.dobee.domain.store.enums.StoreErrorCode;
import com.example.dobee.global.exception.CustomException;

public class CategoryNotFoundException extends CustomException {
    public CategoryNotFoundException() {
        super(StoreErrorCode.CATEGORY_NOT_FOUND);
    }
}
