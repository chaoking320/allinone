package chaoking.java.allinone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * 使用外置的tomcat启动：继承 SpringBootServletInitializer 重写configure()方法
 * 部署到外部的tomcat时必须采用这种方式 而且 要war包形式
 */
@SpringBootApplication
public class TomcatApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TomcatApplication.class);
    }

}
