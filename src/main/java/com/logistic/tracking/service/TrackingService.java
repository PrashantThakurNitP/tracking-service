package com.logistic.tracking.service;

import com.logistic.tracking.dto.TrackingRequest;
import com.logistic.tracking.dto.TrackingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingService {

    private final RedisTemplate<String, String> redisTemplate;

    public TrackingResponse generateTrackingNumber(TrackingRequest req) {
        String raw = String.format("%s%s%s%s",req.getCustomer_id().toString() , req.getCreated_at().toString() ,
                req.getOrigin_country_id() , req.getDestination_country_id());
        String hash = DigestUtils.md5DigestAsHex(raw.getBytes(StandardCharsets.UTF_8));
        String base36 = new BigInteger(hash, 16).toString(36).toUpperCase().substring(0, 16);

        String key = String.format("tracking: %s" , base36);
        Boolean isNew = redisTemplate.opsForValue().setIfAbsent(key, "1", Duration.ofMinutes(5));

        if (Boolean.FALSE.equals(isNew)) {
            log.error("Error in generation of tracking number");
            throw new IllegalStateException("Tracking number collision, retry");
        }

        return new TrackingResponse(base36, OffsetDateTime.now());
    }
}
