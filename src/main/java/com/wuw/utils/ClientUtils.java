package com.wuw.utils;

import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import okhttp3.*;

import java.io.IOException;

public class ClientUtils {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8");

    private OkHttpClient client;

    public ClientUtils() {
        this.client = new OkHttpClient();
    }

    public ClientUtils(OkHttpClient.Builder builder) {
        this.client = builder.build();
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
