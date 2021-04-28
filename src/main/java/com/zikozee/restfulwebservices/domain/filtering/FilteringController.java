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
    public SomeBean retrieveBean(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping(path = "filtering-list")
    public List<SomeBean> retrieveListOfBean(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),  new SomeBean("value11", "value22", "value33"));
    }
}
