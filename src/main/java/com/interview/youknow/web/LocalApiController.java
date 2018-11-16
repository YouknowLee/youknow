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
    public DataTableResponse search (@RequestParam("keyword") String keyword, @RequestParam("start") int start) {
        if (keyword == null || keyword.isEmpty()) {
            return new DataTableResponse("");
        }

        KakaoLocalSearchResponse result = KakaoLocalSearchService.requestLocalSearch(start, keyword);
        return new DataTableResponse(result.getMeta().getTotal_count(), result.getMeta().getPageable_count(), result.getDocuments());
    }

    @GetMapping("/keyword")
    public @ResponseBody
    List<SearchKeyword> keyword (@RequestParam("keyword") String keyword) {
        Optional<SearchKeyword> optionalSearchKeyword = searchKeywordRepository.findById(keyword);
        if (optionalSearchKeyword.isPresent()) {
            optionalSearchKeyword.get().setSearchCount(optionalSearchKeyword.get().getSearchCount() + 1);
            searchKeywordRepository.save(optionalSearchKeyword.get());
        } else {
            searchKeywordRepository.save(new SearchKeyword(keyword));
        }

        List<SearchKeyword> searchKeywords = searchKeywordRepository.findAll(new Sort(Sort.Direction.DESC, "searchCount"));
        return searchKeywords;
    }
}
