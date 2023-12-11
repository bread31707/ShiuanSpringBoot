package com.wuw.handler;

import com.wuw.core.model.SvcResModel;
import com.wuw.enums.ResultFields;
import com.wuw.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({AppException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<SvcResModel> handlerApplicationException(AppException e){
        log.error(e.getMessage(), e);
        return new Result<>(e.getResultFields(), new SvcResModel());
    }

//    @ExceptionHandler({RuntimeException.class})
//    @ResponseStatus(HttpStatus.OK)
//    public Result<SvcResModel> handlerRuntimeException(RuntimeException e){
//        logger.error(e.fillInStackTrace().toString());
//        return new Result<>(ResultFields.FAIL, new SvcResModel());
//    }
}
