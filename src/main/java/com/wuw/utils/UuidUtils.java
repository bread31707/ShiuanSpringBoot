package com.wuw.utils;

import java.util.UUID;

public class UuidUtils {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
