package com.wuw.core;

import com.wuw.core.model.ActionReqModel;
import com.wuw.core.model.ActionResModel;

public abstract class AbstractActionLogic<Req extends ActionReqModel, Res extends ActionResModel> {
    public abstract Res doAction(Req reqModel);
}
