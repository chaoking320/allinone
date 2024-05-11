package allinone.vi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@ServletComponentScan
public class ViApplication  {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(ViApplication.class, args);

        System.setProperty("java.awt.headless", "false");
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:9090/@in"));
    }

}
