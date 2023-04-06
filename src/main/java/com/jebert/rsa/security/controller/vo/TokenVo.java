package com.jebert.rsa.security.controller.vo;

import java.util.Date;

public record TokenVo (String username, boolean authenticated, Date createdAt, Date expiration, String accessToken, String refreshToken){
}
