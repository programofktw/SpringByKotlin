package com.example.socialbyjava.etc.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    String name;

    String provider;

}
