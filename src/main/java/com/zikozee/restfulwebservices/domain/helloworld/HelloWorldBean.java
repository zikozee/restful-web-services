package com.zikozee.restfulwebservices.domain.helloworld;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : zikoz
 * @created : 23 Apr, 2021
 */
@Getter @Setter @ToString
public class HelloWorldBean {

    private final String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    //TODO:  if you omit the getter ....> You'll get no converter found
}
