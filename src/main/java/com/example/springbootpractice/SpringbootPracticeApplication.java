package com.example.springbootpractice;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPracticeApplication.class, args);
    }

    /*
    * api의 response에 entity를 직접 노출할때 생기는 문제를 막기 위해 아래 모듈 추가
    * */
    @Bean
    Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        //강제 지연 로딩 설정
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5Module;
    }
}
