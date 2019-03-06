package test.com.ivollo.eicp.http;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@Component
public class WebTestClientBuilder {

    private WebTestClient.ResponseSpec responseSpec;

    @Autowired
    private WebTestClient testClient;

    public EntityExchangeResult<String> returnResult(String exceptBody){
        return responseSpec.expectBody(String.class).isEqualTo(exceptBody).returnResult();
    }

    public EntityExchangeResult<String> returnResult(){
        return responseSpec.expectBody(String.class).returnResult();
    }

    public WebTestClientBuilder post(String uri, MediaType mediaType, Publisher publisher, Class elementClass){
        responseSpec =  testClient.post().uri(uri)
                .contentType(mediaType)
                .body(publisher, elementClass)
                .exchange().expectStatus().isOk();
        return this;
    }


    public WebTestClientBuilder post(String uri, MediaType mediaType){
        responseSpec =  testClient.post().uri(uri)
                .accept(mediaType)
                .exchange().expectStatus().isOk();
        return this;
    }
}
