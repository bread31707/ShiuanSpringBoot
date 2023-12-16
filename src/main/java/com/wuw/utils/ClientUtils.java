package com.wuw.utils;

import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import okhttp3.*;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class ClientUtils {

    private final OkHttpClient client;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8");


    public ClientUtils(){
        this.client = new OkHttpClient();
    }
    public ClientUtils(int connectTimeOut, int readTimeOut){
        this.client = new OkHttpClient().newBuilder()
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .build();
    }

    // type = TLS
    public OkHttpClient ignoerSSL(String type) {
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
            SSLContext sslContext = SSLContext.getInstance(type);
            sslContext.init(null, trustManagers, new SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return client.newBuilder().sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagers[0])
                    .hostnameVerifier((hostname, session) -> true).build();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new AppException(ResultFields.FAIL_TO_SSL_CONNECTION);
        }
    }

    public String sendPostRequest(String url, String json, Request.Builder builder) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = builder.url(url).post(body).build();
        return executeRequest(request);
    }

    public String sendPostRequestForXml(String url, String xml, Request.Builder builder) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_XML, xml);
        Request request = builder.url(url).post(body).build();
        return executeRequest(request);
    }

    private String executeRequest(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new AppException(ResultFields.FAIL_TO_CONNECTION);
        }
    }


}
