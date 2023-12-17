package com.wuw.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuw.core.model.SvcInfo;
import com.wuw.core.model.SvcReqModel;
import com.wuw.core.model.SvcResModel;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSvcLogic<Req extends SvcReqModel, Res extends SvcResModel> {

    @Autowired
    protected SvcInfo svcInfo;

    public abstract Result<Res> doSvc(Req reqModel, Res resModel) throws JsonProcessingException;

}
