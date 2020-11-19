package chaoking.java.allinone.utils.common;

import okhttp3.*;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class JavaBeanConfiguration {

    @Bean
    public OkHttpClient sysOkHttpClient() {



        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        return builder.build();
    }
}
