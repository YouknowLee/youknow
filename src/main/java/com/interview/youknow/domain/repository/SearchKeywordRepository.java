package com.interview.youknow.domain.repository;

import com.interview.youknow.domain.entity.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, String> {
}
