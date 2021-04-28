package com.zikozee.restfulwebservices.domain.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : zikoz
 * @created : 28 Apr, 2021
 */
@RestController
public class FilteringController {
    public static final String DYNAMIC_FILTER="dynamicFilter";

    @GetMapping(path = "filtering")
    public StaticFilteredBean retrieveBean(){
        return new StaticFilteredBean("value1", "value2", "value3");
    }

    @GetMapping(path = "filtering-list")
    public List<StaticFilteredBean> retrieveListOfBean(){
        return Arrays.asList(new StaticFilteredBean("value1", "value2", "value3"),  new StaticFilteredBean("value11", "value22", "value33"));
    }

    //filter out field3
    @GetMapping(path = "dynamicFiltering")
    public MappingJacksonValue retrieveDynamicBean(){
        DynamicFilteredBean dynamicFilteredBean = new DynamicFilteredBean("value1", "value2", "value3");

        return getMappingJacksonValue(dynamicFilteredBean, new HashSet<>(Arrays.asList("field1", "field2")));
    }

    //filter out field1
    @GetMapping(path = "dynamic-filtering-list")
    public MappingJacksonValue retrieveDynamicListOfBean(){
        List<DynamicFilteredBean> dynamicFilteredBeans = Arrays.asList(new DynamicFilteredBean("value1", "value2", "value3"),
                new DynamicFilteredBean("value11", "value22", "value33"));

        return getMappingJacksonValue(dynamicFilteredBeans, new HashSet<>(Arrays.asList("field2", "field3")));
    }


    private MappingJacksonValue getMappingJacksonValue(Object beanObject, Set<String> fields){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(DYNAMIC_FILTER, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(beanObject);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
