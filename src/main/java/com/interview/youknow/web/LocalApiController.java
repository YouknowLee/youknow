package com.interview.youknow.web;

import com.interview.youknow.domain.entity.SearchKeyword;
import com.interview.youknow.domain.model.DataTableResponse;
import com.interview.youknow.domain.model.KakaoLocalSearchResponse;
import com.interview.youknow.domain.repository.SearchKeywordRepository;
import com.interview.youknow.domain.service.KakaoLocalSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/local")
public class LocalApiController {

    @Autowired
    private KakaoLocalSearchServiceImpl KakaoLocalSearchService;

    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    @GetMapping("/search")
    public DataTableResponse search (@RequestParam("keyword") String keyword, @RequestParam("start") int start, @RequestParam("draw") int draw) {
        if (keyword == null || keyword.isEmpty()) {
            return new DataTableResponse("");
        }

        KakaoLocalSearchResponse result = KakaoLocalSearchService.requestLocalSearch(start, keyword);

        // 키워드 저장
        if (draw == 1 && result.getMeta().getTotal_count() > 0) {
            Optional<SearchKeyword> optionalSearchKeyword = searchKeywordRepository.findById(keyword);
            if (optionalSearchKeyword.isPresent()) {
                optionalSearchKeyword.get().setSearchCount(optionalSearchKeyword.get().getSearchCount() + 1);
                searchKeywordRepository.save(optionalSearchKeyword.get());
            } else {
                searchKeywordRepository.save(SearchKeyword.builder().keyword(keyword).searchCount(1).build());
            }
        }

        return new DataTableResponse(result.getMeta().getTotal_count(), result.getMeta().getPageable_count(), result.getDocuments());
    }

    @GetMapping("/keyword")
    public @ResponseBody
    List<SearchKeyword> getKeywords () {
        List<SearchKeyword> searchKeywords = searchKeywordRepository.findAll(new Sort(Sort.Direction.DESC, "searchCount"));
        return searchKeywords.subList(0, searchKeywords.size() > 10 ? 10 : searchKeywords.size());
    }
}
