package com.zikozee.restfulwebservices.domain.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author : zikoz
 * @created : 28 Apr, 2021
 */
@RestController
public class FilteringController {

    @GetMapping(path = "filtering")
    public StaticFilteredBean retrieveBean(){
        return new StaticFilteredBean("value1", "value2", "value3");
    }

    @GetMapping(path = "filtering-list")
    public List<StaticFilteredBean> retrieveListOfBean(){
        return Arrays.asList(new StaticFilteredBean("value1", "value2", "value3"),  new StaticFilteredBean("value11", "value22", "value33"));
    }
}
