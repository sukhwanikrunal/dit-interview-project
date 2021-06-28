package com.ditcanada.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Data response object")
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class DataResponse<T> extends BaseResponse{

    @ApiModelProperty(required = false, notes = "API response")
    @JsonProperty("data")
    private T data;
}
