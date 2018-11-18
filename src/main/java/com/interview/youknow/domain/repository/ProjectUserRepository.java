package com.interview.youknow.domain.repository;

import com.interview.youknow.domain.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, String> {
    ProjectUser findByUserIdAndUserPw (@Param("userId") String userId, @Param("userPw") String userPw);
}
