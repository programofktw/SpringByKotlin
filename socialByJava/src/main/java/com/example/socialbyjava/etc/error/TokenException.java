package com.example.socialbyjava.etc.error;

import com.example.socialbyjava.etc.enums.TokenErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenException extends RuntimeException {
    private final TokenErrorResult tokenErrorResult;

    @Override
    public String getMessage() {
        return tokenErrorResult.getMessage();
    }
}
