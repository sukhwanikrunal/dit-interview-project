package com.ditcanada.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "Base response object")
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class BaseResponse {

    @ApiModelProperty(required = true, notes = "api status")
    @JsonProperty("success")
    private boolean success = false;

    @ApiModelProperty(required = false, notes = "api status code")
    @JsonProperty("error")
    private String error;

    public BaseResponse(){
        // No args constructor
    }

    public BaseResponse(boolean success){
        this.success = success;
    }

    public BaseResponse(boolean success, String error){
        this.success = success;
        this.error = error;
    }

}
