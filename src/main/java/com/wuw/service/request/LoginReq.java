package com.wuw.service.request;

import com.wuw.core.model.SvcReqModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq extends SvcReqModel {

    private String userId;
    private String userName;

}
