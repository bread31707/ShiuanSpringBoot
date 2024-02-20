package com.wuw.action.response;

import com.wuw.core.model.ActionResModel;
import com.wuw.service.response.ActionTestRes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestActionRes extends ActionResModel {

    private List<ActionTestRes.AemInfo> aemInfoList = new ArrayList<>();

    @Setter
    @Getter
    public static class AemInfo{
        private String uuid;
        private String title;
        private String content;
    }

}
