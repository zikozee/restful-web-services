package com.zikozee.restfulwebservices.domain.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : zikoz
 * @created : 28 Apr, 2021
 */
@Getter @Setter
@JsonFilter(FilteringController.DYNAMIC_FILTER)
public class DynamicFilteredBean {
    private String field1;
    private String field2;
    private String field3;

    public DynamicFilteredBean(String field1, String field2, String field3) {

        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
}
