package com.syg.domain.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class JWTUser {
    private String userName;
    private String password;
}
