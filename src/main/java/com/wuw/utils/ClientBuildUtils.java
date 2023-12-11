package com.wuw.utils;

import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class ClientBuildUtils {

    private OkHttpClient.Builder builder;

    public ClientBuildUtils() {
        this.builder = new OkHttpClient.Builder();
    }

    public ClientBuildUtils clientLife(int connectTimeOut, int readTimeOut) {
        builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS);
        return this;
    }

    public ClientBuildUtils ignoerSSL() {
        try {
            TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagers[0])
                    .hostnameVerifier((hostname, session) -> true);

            return this;

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new AppException(ResultFields.FAIL_TO_SSL_CONNECTION);
        }
    }
}