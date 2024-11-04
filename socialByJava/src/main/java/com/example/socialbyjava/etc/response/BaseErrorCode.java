package com.example.socialbyjava.etc.response;

public interface BaseErrorCode {
    public ErrorReasonDto getReason();

    public ErrorReasonDto getReasonHttpStatus();
}