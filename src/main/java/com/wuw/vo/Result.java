package com.wuw.vo;

import com.wuw.enums.ResultFields;
import com.wuw.core.model.SvcResModel;
import lombok.Data;

@Data
public class Result<Res extends SvcResModel>{

    private String code;
    private String msg;
    private Res resModel;

    public Result(ResultFields resultFields, Res data){
        setCode(resultFields.getCode());
        setMsg(resultFields.getMsg());
        setResModel(data);
    }

}
