package com.logistic.tracking.controller;

import com.logistic.tracking.dto.TrackingRequest;
import com.logistic.tracking.dto.TrackingResponse;
import com.logistic.tracking.service.TrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/next-tracking-number")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tracking API", description = "API for generating tracking numbers")
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping
    @Operation(
            summary = "Generate a new tracking number",
            description = "Generates a unique tracking number based on input parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tracking number generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TrackingResponse> getTrackingNumber(@Valid TrackingRequest request) {
        log.info("TrackingController :: Received Request to get tracking number for {}", request.getCustomer_id());
        return ResponseEntity.ok(trackingService.generateTrackingNumber(request));
    }
}
