package com.ditcanada.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Data response object")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse<T> extends BaseResponse{

    @ApiModelProperty(required = false, notes = "API response")
    @JsonProperty("data")
    private List<T> data;
}
