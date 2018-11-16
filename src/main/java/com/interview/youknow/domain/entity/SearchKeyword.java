package com.interview.youknow.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class SearchKeyword {

    @Id
    @Column(name="keyword")
    private String keyword;

    @Column(name="search_count")
    private int searchCount = 1;

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public SearchKeyword(){}
    public SearchKeyword(String keyword) {
        this.keyword = keyword;
    }
}
