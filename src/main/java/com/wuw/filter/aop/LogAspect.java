//// 可以給 Action, DB 使用, svc 的部分使用 filter 會比較好
//
//package com.wuw.filter.aop;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.WebUtils;
//import java.io.UnsupportedEncodingException;
//
//@Aspect
//@Component
//public class LogAspect {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Pointcut("bean(*Controller)") // 抓取所有Controller，可依實際需求調整
//    public void logPointCut() { }
//
//    @Around("logPointCut()")
//    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 僅取到Controller名字，去掉前面package的路徑。實務上同一專案不同路徑卻同一命名機會應該頗低
//        // 若仍想印完整，拿掉`lastPointIndex`，直接印`joinPoint.getSignature().getDeclaringTypeName()`即可
//        int lastPointIndex = joinPoint.getSignature().getDeclaringTypeName().lastIndexOf(".") + 1;
//        String class_method = joinPoint.getSignature().getDeclaringTypeName().substring(lastPointIndex) + "." +
//                joinPoint.getSignature().getName() + "()";
//
//        Object responseBody = joinPoint.proceed();
//        logger.info("------------------------------------------");
//        // [IP] [方法(GET/POST)] [API網址] [controller.method()] [執行時間]
//        logger.info("[{}] [{}] [{}] [{}] [{} ms]", request.getRemoteAddr(), request.getMethod(), request.getRequestURL(), class_method, System.currentTimeMillis() - startTime);
//        logger.info("-----------------REQUEST------------------");
//        logger.info("REQUEST parameter－: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getParameterMap())); // 若想要一行呈現，拿掉`writerWithDefaultPrettyPrinter()`，寫成`mapper.writeValueAsString(參數)`即可
//        logger.info("REQUEST body: {}", getPayload(request));
//        logger.info("-----------------RESPONSE-----------------");
//        logger.info("RESPONSE: {}", this.mapper.writeValueAsString(responseBody)); // / 若想要一行呈現，拿掉`writerWithDefaultPrettyPrinter()`，寫成`mapper.writeValueAsString(參數)`即可
//        logger.info("------------------------------------------");
//
//        return responseBody;
//    }
//        private String getPayload(HttpServletRequest request) {
//            ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
//            if (wrapper != null) {
//                byte[] buf = wrapper.getContentAsByteArray();
//                if (buf.length > 0) {
//                    try {
//                        int length = Math.min(buf.length, 1024);// 最多只印出1024長度
//                        return new String(buf, 0, length, wrapper.getCharacterEncoding());
//                    } catch (UnsupportedEncodingException ex) {
//                        return "[unknown]";
//                    }
//                }
//            }
//            return "";
//        }
//}
