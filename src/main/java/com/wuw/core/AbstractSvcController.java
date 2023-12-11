package com.wuw.core;

import com.wuw.core.model.SvcReqModel;
import com.wuw.core.model.SvcResModel;
import com.wuw.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractSvcController<Req extends SvcReqModel, Res extends SvcResModel> {

    public abstract Result<Res> service(@RequestBody Req request);

}
