package com.wuw.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuw.core.model.SvcReqModel;
import com.wuw.core.model.SvcResModel;
import com.wuw.vo.Result;

public abstract class AbstractSvcLogic<Req extends SvcReqModel, Res extends SvcResModel> {

    public abstract Result<Res> doSvc(Req reqModel, Res resModel) throws JsonProcessingException;

}
