package com.logistic.tracking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Origin country code (ISO 3166-1 alpha-2)", example = "MY")
    private String origin_country_id;

    @NotBlank
    @Schema(description = "Destination country code (ISO 3166-1 alpha-2)", example = "ID")
    private String destination_country_id;

    @NotNull @DecimalMax("99999.999")
    @Schema(description = "Weight in kg (max 3 decimal places)", example = "1.234")
    private BigDecimal weight;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")

    @Schema(description = "RFC 3339 formatted creation timestamp", example = "2023-11-20T19:29:32+08:00")
    private OffsetDateTime created_at;

    @NotNull
    @Schema(description = "Customer UUID", example = "de619854-b59b-425e-9db4-943979e1bd49")
    private UUID customer_id;

    @NotBlank
    @Schema(description = "Customer name", example = "RedBox Logistics")
    private String customer_name;

    @NotBlank
    @Schema(description = "Customer slug", example = "redbox-logistics")
    private String customer_slug;
}
