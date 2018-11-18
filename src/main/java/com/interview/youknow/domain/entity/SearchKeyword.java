package com.interview.youknow.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
public class SearchKeyword {

    @Id
    @Column(name="keyword")
    private String keyword;

    @Column(name="search_count")
    private int searchCount;

    public SearchKeyword() {}

    public SearchKeyword(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }
}
