package com.wuw.filter.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import com.wuw.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        try {

            JWTUtils.verify(token);

            return true;
        } catch (SignatureVerificationException e) {
            throw new AppException(ResultFields.FAIL_SIGNATURE_VERIFICATION);
        } catch (TokenExpiredException e) {
            throw new AppException(ResultFields.FAIL_TOKEN_EXPIRED);
        } catch (AlgorithmMismatchException e) {
            throw new AppException(ResultFields.FAIL_ALGORITHM_MISMATCH);
        } catch (JWTDecodeException e) {
            throw new AppException(ResultFields.FAIL_JWT_DECODE);
        }

    }
}
