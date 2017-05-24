package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.message.RandomResponse;
import com.sedatcan.GeoIpBatch.message.Value;
import com.sedatcan.GeoIpBatch.service.external.RandomApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RandomApiServiceImpl implements RandomApiService {
    @Autowired
    private RestTemplate restTemplate;

    @org.springframework.beans.factory.annotation.Value("${geoIpBatch.randomapi.rest.url}")
    private String randomAPiUrl;

    @Override
    public Value getRandomValue() {
        RandomResponse randomResponse = restTemplate.getForObject(randomAPiUrl, RandomResponse.class);
        if (randomResponse != null) {
            log.debug("Random type: " + randomResponse.getType() +
                    " id: " + randomResponse.getValue().getId() +
                    " quote: " + randomResponse.getValue().getQuote());
            randomResponse.getValue();
            return randomResponse.getValue();
        }
        return null;
    }
}
