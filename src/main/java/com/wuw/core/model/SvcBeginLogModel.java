package com.wuw.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SvcBeginLogModel {

    private String uuid;
    private String startDateTime;
    private String method;
    private String reqBody;
    private String remoto;
    private String url;

}
