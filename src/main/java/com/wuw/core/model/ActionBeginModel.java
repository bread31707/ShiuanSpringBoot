package com.wuw.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionBeginModel {

    private String svcUuid;
    private String uuid;
    private String startDateTime;
    private String requestBody;

}
