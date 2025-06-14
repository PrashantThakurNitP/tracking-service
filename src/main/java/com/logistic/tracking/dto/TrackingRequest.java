package com.logistic.tracking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class TrackingRequest {
    @NotBlank
    private String origin_country_id;

    @NotBlank
    private String destination_country_id;

    @NotNull @DecimalMax("99999.999")
    private
    BigDecimal weight;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime created_at;

    @NotNull
    private UUID customer_id;

    @NotBlank
    private String customer_name;

    @NotBlank
    private String customer_slug;
}
