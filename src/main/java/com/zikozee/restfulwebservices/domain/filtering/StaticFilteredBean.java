package com.zikozee.restfulwebservices.domain.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : zikoz
 * @created : 28 Apr, 2021
 */
@JsonIgnoreProperties(value = {"field2"})
@Getter @Setter
public class StaticFilteredBean {
    private String field1;
    private String field2;

    @JsonIgnore
    private String field3;

    public StaticFilteredBean(String field1, String field2, String field3) {

        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
}
