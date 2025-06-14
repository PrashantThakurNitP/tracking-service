package com.logistic.tracking.controller;

import com.logistic.tracking.dto.TrackingRequest;
import com.logistic.tracking.dto.TrackingResponse;
import com.logistic.tracking.service.TrackingService;
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
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping
    public ResponseEntity<TrackingResponse> getTrackingNumber(@Valid TrackingRequest request) {
        log.info("TrackingController :: Received Request to get tracking number for {}", request.getCustomer_id());
        return ResponseEntity.ok(trackingService.generateTrackingNumber(request));
    }
}
