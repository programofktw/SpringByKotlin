package com.example.socialbyjava.etc.error;
import com.example.socialbyjava.etc.enums.UserErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorResult userErrorResult;

    @Override
    public String getMessage() {
        return userErrorResult.getMessage();
    }
}