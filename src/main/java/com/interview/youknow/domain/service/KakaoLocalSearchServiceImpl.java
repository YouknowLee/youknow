package com.interview.youknow.domain.service;

import com.interview.youknow.domain.model.KakaoLocalSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoLocalSearchServiceImpl implements LocalSearchService {

    private final String PREFIX_APPKEY = "KakaoAK";
    private final int PAGE_SIZE = 10;

    @Value("${kakao.restapi.key}")
    private String KAKAO_RESTAPI_KEY;

    @Value("${kakao.restapi.host}")
    private String KAKAO_RESTAPI_HOST;

    @Value("${kakao.local.search.url}")
    private String KAKAO_LOCAL_SEARCH_URL_PATH;

    @Override
    public KakaoLocalSearchResponse requestLocalSearch(int start, String keyword) {
        int requestPage = start / 10 + 1;
        String url = new StringBuilder().append(KAKAO_RESTAPI_HOST).append(KAKAO_LOCAL_SEARCH_URL_PATH)
                .append("?query=").append(keyword)
                .append("&page=").append(requestPage)
                .append("&size=").append(PAGE_SIZE).toString();

        return request(url);
    }

    @Override
    public KakaoLocalSearchResponse requestLocalDeatilSearch(String keyword, String x, String y) {
        String url = new StringBuilder().append(KAKAO_RESTAPI_HOST).append(KAKAO_LOCAL_SEARCH_URL_PATH)
                .append("?query=").append(keyword)
                .append("&x=").append(x)
                .append("&y=").append(y)
                .append("&radius=").append(0).toString();

        return request(url);
    }

    public KakaoLocalSearchResponse request (String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", PREFIX_APPKEY + " " +KAKAO_RESTAPI_KEY);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoLocalSearchResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, KakaoLocalSearchResponse.class);

        if (response.getStatusCodeValue() != 200)
            log.error("status[{}], url[{}]", response.getStatusCode(), url);

        return response.getBody();
    }
}
