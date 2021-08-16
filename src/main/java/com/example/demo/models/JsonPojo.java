package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonPojo {

    @JsonProperty("type")
    private String type;

    @JsonProperty("radius")
    private Double radius;

    @JsonProperty("l")
    private Double length;

    @JsonProperty("b")
    private Double breadth;

    @JsonProperty("side")
    private Double side;

}
