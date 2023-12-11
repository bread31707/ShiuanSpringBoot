package com.wuw.service.request;

import com.wuw.core.model.SvcReqModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisTestAddReq extends SvcReqModel {
    private String key;
    private String value;
}
