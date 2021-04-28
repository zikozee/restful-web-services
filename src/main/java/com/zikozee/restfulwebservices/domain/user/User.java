package com.zikozee.restfulwebservices.domain.user;

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
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Past(message = "birthdate can only be in the past")
    private Date birthDate;
}
