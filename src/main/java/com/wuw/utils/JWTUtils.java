package com.wuw.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class JWTUtils {

    private static final String SIGN = "!@QHd";

    public static String getToken(int calendar, int amount, Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(calendar, amount);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }

    public static String getToken(Map<String, String> map) {
        return getToken(Calendar.MINUTE, 30, map); // 預設 30 分鐘
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

}