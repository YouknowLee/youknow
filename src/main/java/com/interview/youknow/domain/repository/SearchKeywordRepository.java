package com.interview.youknow.domain.repository;

import com.interview.youknow.domain.entity.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, String> {
}
