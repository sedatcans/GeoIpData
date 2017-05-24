package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.message.Value;
import com.sedatcan.GeoIpBatch.service.external.RandomApiService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class RandomApiServiceImplTest extends BaseServiceTest {

    @Autowired
    private RandomApiService randomApiService;

    @Test
    public void getRandomValue() throws Exception {
        Value randomValue = randomApiService.getRandomValue();
        assertNotNull(randomValue);
        assertNotNull(randomValue.getQuote());
        assertNotNull(randomValue.getId());

    }

}