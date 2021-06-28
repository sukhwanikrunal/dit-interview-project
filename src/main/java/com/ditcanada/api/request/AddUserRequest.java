package com.ditcanada.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "request object for login route")
public class AddUserRequest implements Serializable {

    private String lastname;
    private String firstname;
    private String username;
    private String password;
}
