package test.okhttp.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        System.out.println(String.format("test.com.ivollo.eicp.http Request: %s", request.url().toString()));
        Response response = chain.proceed(request);
        System.out.println(String.format("test.com.ivollo.eicp.http Response: %s", response.body()));
        return response;
    }
}
