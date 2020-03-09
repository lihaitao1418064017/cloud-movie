package org.lht.boot.lang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LangApplication {

    public static void main(String[] args) {
        SpringApplication.run(LangApplication.class, args);
    }

}
