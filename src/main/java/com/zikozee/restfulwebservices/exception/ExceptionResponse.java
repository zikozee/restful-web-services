package com.zikozee.restfulwebservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : zikoz
 * @created : 24 Apr, 2021
 */
@Getter @Setter
@AllArgsConstructor
public class ExceptionResponse{

    private Date timestamp;
    private String message;
    private String details;
}
