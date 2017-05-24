package com.sedatcan.GeoIpBatch.service.impl;

import com.sedatcan.GeoIpBatch.entity.GeoRandomData;
import com.sedatcan.GeoIpBatch.service.GeoRandomDataService;
import com.sedatcan.GeoIpBatch.service.external.impl.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@AutoConfigureTestEntityManager
public class GeoRandomDataServiceImplTest extends BaseServiceTest {

    @Autowired
    private GeoRandomDataService geoRandomDataService;

    @Test
    public void saveGeoRandomData() throws Exception {
        GeoRandomData geoRandomData = geoRandomDataService.saveGeoRandomData(1, "92.45.203.238", "Success", "Turkey", "Tur", 2L, "quoteTest");
        assertNotNull(geoRandomData);
        assertThat(geoRandomData.getCountryCode(), equalTo("Tur"));
        assertThat(geoRandomData.getCountryName(), equalTo("Turkey"));
        assertThat(geoRandomData.getId(), equalTo(2L));
        assertThat(geoRandomData.getReturnCode(), equalTo(1));
        assertThat(geoRandomData.getIp(), equalTo("92.45.203.238"));
        assertThat(geoRandomData.getReturnCode(), equalTo(1));
        assertThat(geoRandomData.getReturnCodeDetails(), equalTo("Success"));
        assertThat(geoRandomData.getQuote(), equalTo("quoteTest"));
        assertNotNull(geoRandomData.getGuid());
    }

}