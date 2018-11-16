package com.interview.youknow.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KakaoLocalSearchResponse extends LocalSearchResponse {
    @JsonProperty("documents")
    private List<KakaoLocalSearchDocument> documents;
    @JsonProperty("meta")
    private KakaoLocalSearchMeta meta;

    public List<KakaoLocalSearchDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<KakaoLocalSearchDocument> documents) {
        this.documents = documents;
    }

    public KakaoLocalSearchMeta getMeta() {
        return meta;
    }

    public void setMeta(KakaoLocalSearchMeta meta) {
        this.meta = meta;
    }
}
