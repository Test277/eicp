package test.okhttp;

import test.okhttp.interceptor.RequestInterceptor;
import test.util.JsonUtil;
import lombok.NonNull;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class HttpClientTemplate {
    private Logger logger = LoggerFactory.getLogger(HttpClientTemplate.class);
    private Request.Builder requestBuilder;
    private OkHttpClient httpClient;
    private HttpUrl.Builder httpUrlBuilder;

    @Value("${okhttp.connecttimeout}")
    private int connectTimeout;

    @Value("${okhttp.readtimeout}")
    private int readTimeout;

    @Value("${okhttp.writetimeout}")
    private int writeTimeout;

    @Value("${okhttp.host}")
    private String host;

    @Value("${okhttp.port}")
    private int port;

    public HttpClientTemplate build(){
         httpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();
         httpUrlBuilder = new HttpUrl.Builder().scheme("http").host(host).port(port);
         return this;
    }

    public HttpClientTemplate url(){
        requestBuilder = new Request.Builder().url(httpUrlBuilder.build());
        return this;
    }

    public HttpClientTemplate post(RequestBody body){
        requestBuilder.post(body);
        return this;
    }

    public HttpClientTemplate pathSegment(String pathSegment){
        httpUrlBuilder.addEncodedPathSegments(pathSegment);
        return this;
    }

    public HttpClientTemplate queryParameter(@NonNull Map<String, String> params){
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public IResponse send() throws IOException {

        Request request = requestBuilder.build();
        logger.info(String.format("Sending request to: %s", request.url()));
        Response response = httpClient.newCall(request).execute();
        if(response.isSuccessful()) {
            String responseBody = response.body().string();
            logger.info(String.format("Received response body: %s", responseBody));
            IResponse response1 = new JsonUtil().parseJsonToObject(responseBody, IResponse.class);
            return response1;
        }else {
            logger.error(String.format("Request failed. Response code: %s, ", response.code(), response.message()));
            return null;
        }
    }
}
