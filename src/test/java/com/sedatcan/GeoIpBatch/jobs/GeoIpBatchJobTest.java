package com.sedatcan.GeoIpBatch.jobs;

import com.sedatcan.GeoIpBatch.configuration.GeoIpBatchConfiguration;
import com.sedatcan.GeoIpBatch.entity.GeoRandomData;
import com.sedatcan.GeoIpBatch.message.Value;
import com.sedatcan.GeoIpBatch.service.GeoRandomDataService;
import com.sedatcan.GeoIpBatch.service.external.GeoIpService;
import com.sedatcan.GeoIpBatch.service.external.RandomApiService;
import com.sedatcan.GeoIpBatch.service.external.impl.BaseServiceTest;
import geoipservice.wsdl.GeoIP;
import geoipservice.wsdl.GetGeoIPResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@MockBean({GeoRandomDataService.class,
        GeoIpService.class,
        RandomApiService.class})
@Import(GeoIpBatchJob.class)
public class GeoIpBatchJobTest {

    @Autowired
    private GeoIpBatchJob geoIpBatchJob;

    @Autowired
    private GeoRandomDataService geoRandomDataService;

    @Autowired
    private GeoIpService geoIpService;

    @Autowired
    private RandomApiService randomApiService;

    @Test
    public void retriveGeoDataAndSave() throws Exception {
        GetGeoIPResponse geoIpResponse = new GetGeoIPResponse();
        GeoIP geoIp = new GeoIP();
        geoIp.setCountryCode("Tur");
        geoIp.setCountryName("Turkey");
        geoIp.setIP("1.1.1.1");
        geoIp.setReturnCode(3);
        geoIp.setReturnCodeDetails("Success");
        geoIpResponse.setGetGeoIPResult(geoIp);
        when(randomApiService.getRandomValue()).thenReturn(Value.builder().id(1L).quote("quote").build());
        when(geoIpService.getGeoIP("1.1.1.1")).thenReturn(geoIpResponse);
        when(geoRandomDataService.saveGeoRandomData(GeoRandomData.builder()
                .returnCode(3)
                .countryCode("Tur")
                .returnCodeDetails("Success")
                .countryName("Turkey")
                .id(1L)
                .ip("1.1.1.1")
                .quote("quote")
                .build())).thenReturn(GeoRandomData.builder()
                .returnCode(3)
                .countryCode("Tur")
                .returnCodeDetails("Success")
                .countryName("Turkey")
                .id(1L)
                .ip("1.1.1.1")
                .quote("quote")
                .guid(234L)
                .build());

        geoIpBatchJob.retriveGeoDataAndSave("1.1.1.1");
        verify(randomApiService).getRandomValue();
        verify(geoIpService).getGeoIP("1.1.1.1");
        verify(geoRandomDataService).saveGeoRandomData(GeoRandomData.builder()
                .returnCode(3)
                .countryCode("Tur")
                .returnCodeDetails("Success")
                .countryName("Turkey")
                .id(1L)
                .ip("1.1.1.1")
                .quote("quote")
                .build());
    }

}