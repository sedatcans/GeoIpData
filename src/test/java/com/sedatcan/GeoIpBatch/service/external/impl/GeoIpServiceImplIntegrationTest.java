package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.configuration.GeoIpBatchConfiguration;
import com.sedatcan.GeoIpBatch.service.external.GeoIpService;
import geoipservice.wsdl.GetGeoIPResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GeoIpServiceImplIntegrationTest extends BaseServiceTest{

    @Autowired
    private GeoIpService geoIpService;

    @Test
    public void getGeoIP() throws Exception {
        GetGeoIPResponse geoIPResponce = geoIpService.getGeoIP("92.45.203.238");
        assertNotNull(geoIPResponce);
        assertNotNull(geoIPResponce.getGetGeoIPResult());
        assertThat(geoIPResponce.getGetGeoIPResult().getCountryCode(),equalTo("TUR"));
        assertThat(geoIPResponce.getGetGeoIPResult().getCountryName(),equalTo("Turkey"));
        assertThat(geoIPResponce.getGetGeoIPResult().getReturnCode(),equalTo(1));
    }

}