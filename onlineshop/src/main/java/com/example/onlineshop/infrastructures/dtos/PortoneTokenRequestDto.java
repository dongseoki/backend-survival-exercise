package com.example.onlineshop.infrastructures.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PortoneTokenRequestDto(
    @JsonProperty("imp_key")
    String key,

    @JsonProperty("imp_secret")
    String secret
) {
}
