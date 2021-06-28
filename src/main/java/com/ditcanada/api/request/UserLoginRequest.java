package com.ditcanada.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "request object for login route")
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 34565674676576767L;

    private String username;
    private String password;
}
