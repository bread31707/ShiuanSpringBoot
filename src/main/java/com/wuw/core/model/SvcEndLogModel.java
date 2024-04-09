package com.wuw.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SvcEndLogModel {

    private String uuid;
    private String endDateTime;
    private String resCode;
    private String resBody;
    private String runTime;

}
