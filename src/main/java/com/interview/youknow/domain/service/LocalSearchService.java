package com.interview.youknow.domain.service;

public interface LocalSearchService<T> {
    T requestLocalSearch(int start, String keyword);

    T requestLocalDeatilSearch(String keyword, String x, String y);
}
