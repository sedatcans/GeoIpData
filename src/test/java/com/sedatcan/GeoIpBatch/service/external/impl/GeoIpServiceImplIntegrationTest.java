package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.service.external.GeoIpService;
import geoipservice.wsdl.GetGeoIPResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GeoIpServiceImplIntegrationTest extends BaseServiceTest {

    @Autowired
    private GeoIpService geoIpService;

    @Test
    public void getGeoIP() throws Exception {
        GetGeoIPResponse geoIPResponce = geoIpService.getGeoIP("92.45.203.238");
        assertNotNull(geoIPResponce);
        assertNotNull(geoIPResponce.getGetGeoIPResult());
        assertThat(geoIPResponce.getGetGeoIPResult().getCountryCode(), equalTo("TUR"));
        assertThat(geoIPResponce.getGetGeoIPResult().getCountryName(), equalTo("Turkey"));
        assertThat(geoIPResponce.getGetGeoIPResult().getReturnCode(), equalTo(1));
    }

}