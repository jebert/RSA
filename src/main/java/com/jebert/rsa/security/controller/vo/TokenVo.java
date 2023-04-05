package com.jebert.rsa.security.controller.vo;

import java.util.Date;

public record TokenVo (String username, boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken){
}
