package com.example.dobee.domain.review.enums;
import com.example.dobee.global.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ResponseCode {

    REVIEW_NOT_FOUND("RV001", "Review not found"),
    REVIEW_CREATION_FAILED("RV002", "Review creation failed"),
    INVALID_RATING("RV003", "Invalid rating value"),
    MEMBER_NOT_FOUND("RV004", "Member not found"),
    STORE_NOT_FOUND("RV005", "Store not found"),
    REVIEW_CONTENT_EMPTY("RV006", "Review content is empty"),
    REVIEW_DELETE_FORBIDDEN("RV007", "You do not have permission to delete this review"),
    REVIEW_UPDATE_FORBIDDEN("RV008", "You do not have permission to update this review");

    private final String statusCode;
    private final String message;
}
