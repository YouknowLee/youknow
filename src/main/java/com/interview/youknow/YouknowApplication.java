package com.interview.youknow;

import com.interview.youknow.domain.entity.ProjectUser;
import com.interview.youknow.domain.repository.ProjectUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class YouknowApplication {

    @Autowired
    private ProjectUserRepository projectUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(YouknowApplication.class, args);
    }

    @Bean
    InitializingBean createUserData () {
        return () -> {
            String userPassword = "password";

            String encPassword = DigestUtils.sha256Hex(userPassword);

            projectUserRepository.save(new ProjectUser("youknow", encPassword));
        };
    }
}
