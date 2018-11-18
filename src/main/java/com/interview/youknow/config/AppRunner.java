package com.interview.youknow.config;

import com.interview.youknow.domain.entity.ProjectUser;
import com.interview.youknow.domain.repository.ProjectUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final ProjectUserRepository projectUserRepository;

    @Autowired
    public AppRunner(ProjectUserRepository projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String userPassword = "password";

        String encPassword = DigestUtils.sha256Hex(userPassword);

        projectUserRepository.save(ProjectUser.builder().userId("youknow")
                                                        .userPw(encPassword).build());
    }
}
