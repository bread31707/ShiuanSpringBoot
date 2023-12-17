package com.wuw.service.response;

import com.wuw.core.model.SvcResModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActionTestRes extends SvcResModel {

    private List<AemInfo> aemInfoList = new ArrayList<>();

    @Setter
    @Getter
    public static class AemInfo{
        private String uuid;
        private String title;
        private String content;
    }

}
