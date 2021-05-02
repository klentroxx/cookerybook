package hu.cookerybook.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CookerybookWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CookerybookWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CookerybookWebApplication.class, args);
        System.out.println("The application is available here: http://localhost:8099/");
    }

}
