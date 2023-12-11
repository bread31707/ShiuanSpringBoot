package com.wuw.enums;

import lombok.Getter;

@Getter
public enum ResultFields {

    SUCCEDD("0000", "交易成功"),
    FAIL("WS01", "有錯就是有錯!"),
    FAIL_SIGNATURE_VERIFICATION("WS02", "TOKEN驗證失敗"),
    FAIL_TOKEN_EXPIRED("WS03", "TOKEN失效"),
    FAIL_ALGORITHM_MISMATCH("WS04", "加密算法不匹配"),
    FAIL_JWT_DECODE("WS05", "TOKEN解析失敗"),
    FAIL_TO_CONNECTION("WS06", "ACTION連線失敗"),
    FAIL_TO_SSL_CONNECTION("WS06", "ACTION_SSL連線失敗");


    private String code;
    private String msg;

    ResultFields(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
