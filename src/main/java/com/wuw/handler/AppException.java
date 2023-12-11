package com.wuw.handler;

import com.wuw.enums.ResultFields;
import lombok.Data;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private ResultFields resultFields;

    public AppException(ResultFields resultFields) {
        super(resultFields.getMsg());
        this.resultFields = resultFields;
    }

}
