package com.interview.youknow.web;

import com.interview.youknow.domain.model.KakaoLocalSearchResponse;
import com.interview.youknow.domain.service.KakaoLocalSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/local")
public class LocalController {

    @Autowired
    private KakaoLocalSearchServiceImpl KakaoLocalSearchService;

    @RequestMapping("/index")
    public ModelAndView index (ModelAndView mav) {
        mav.setViewName("local/index");
        return mav;
    }

    @RequestMapping("/datail")
    public ModelAndView detail (ModelAndView mav, @RequestParam("place_name") String placeName
                                                , @RequestParam("x") String x, @RequestParam("y") String y) {
        mav.setViewName("local/detail");
        KakaoLocalSearchResponse response = KakaoLocalSearchService.requestLocalDeatilSearch(placeName, x, y);
        mav.addObject("datailPlaceInfo", response.getDocuments().get(0));
        return mav;
    }

}
