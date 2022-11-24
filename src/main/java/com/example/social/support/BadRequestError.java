package com.example.social.support;

import com.hmc.common.exception.ResponseError;

public enum BadRequestError implements ResponseError {

    REDDIT_NOT_FOUND(40006001, "Reddit not found"),

    REDDIT_GROUP_NOT_FOUND(40006002, "Reddit not found"),

    REDDIT_USER_ONLY_ONE(4006002, "You have only one reddit")
    ;

    private final Integer code;

    private final String message;

    BadRequestError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return 400;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
