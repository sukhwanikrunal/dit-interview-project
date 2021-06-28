package com.ditcanada.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private long id;
    private String lastname;
    private String firstname;
    private String username;
    /**
     * Setting this to transient to prevent it to expose password accidently while converting to JSON
    */
    private transient String password;
    private Timestamp createdTS;
    private Timestamp lastLoginTS;
}
