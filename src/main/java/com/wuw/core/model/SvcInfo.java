package com.wuw.core.model;

import com.wuw.utils.DateUtils;
import com.wuw.utils.UuidUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.time.LocalDateTime;

@Data
@Component
@RequestScope // 作用為：在處理 HTTP 請求時，每個請求都會創建一個新的 bean 實例。
public class SvcInfo {

    private final String uuid = UuidUtils.generateUuid();
    private final LocalDateTime startDateTime = DateUtils.getNowLocalDateTime();

}
