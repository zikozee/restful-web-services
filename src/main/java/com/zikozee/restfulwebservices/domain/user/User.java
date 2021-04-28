package com.zikozee.restfulwebservices.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author : zikoz
 * @created : 23 Apr, 2021
 */
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User details")
public class User {

    private Integer id;

    @ApiModelProperty(notes = "name should have at least two character")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Birth should be in the past")
    @Past(message = "birthdate can only be in the past")
    private Date birthDate;
}
