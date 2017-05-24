package com.sedatcan.GeoIpBatch.jobs;

import com.sedatcan.GeoIpBatch.entity.GeoRandomData;
import com.sedatcan.GeoIpBatch.exception.GeoIpBatchErrorCodes;
import com.sedatcan.GeoIpBatch.exception.GeoIpBatchException;
import com.sedatcan.GeoIpBatch.message.Value;
import com.sedatcan.GeoIpBatch.service.GeoRandomDataService;
import com.sedatcan.GeoIpBatch.service.external.GeoIpService;
import com.sedatcan.GeoIpBatch.service.external.RandomApiService;
import geoipservice.wsdl.GetGeoIPResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Component
@Slf4j
public class GeoIpBatchJob {

    @Autowired
    private GeoIpService geoIpService;

    @Autowired
    private RandomApiService randomApiService;

    @Autowired
    private GeoRandomDataService geoRandomDataService;

    @Scheduled(fixedDelay = 5000)
    public void geoIpBatchExecution() {
        try {
            retriveGeoDataAndSave(Inet4Address.getLocalHost().getHostAddress());
    } catch (Exception e) {
        log.error(GeoIpBatchErrorCodes.UNKNOWN_HOST.getErrorMessage());
        throw new GeoIpBatchException(GeoIpBatchErrorCodes.UNKNOWN_HOST);
    }
    }

    public void retriveGeoDataAndSave(String ip) {
            GetGeoIPResponse geoIP = geoIpService.getGeoIP(ip);
            Value randomValue = randomApiService.getRandomValue();
            GeoRandomData geoRandomData = GeoRandomData.builder()
                    .countryCode(geoIP.getGetGeoIPResult().getCountryCode())
                    .countryName(geoIP.getGetGeoIPResult().getCountryName())
                    .id(randomValue.getId())
                    .ip(geoIP.getGetGeoIPResult().getIP())
                    .quote(randomValue.getQuote())
                    .returnCode(geoIP.getGetGeoIPResult().getReturnCode())
                    .returnCodeDetails(geoIP.getGetGeoIPResult().getReturnCodeDetails())
                    .build();
            geoRandomData = geoRandomDataService.saveGeoRandomData(geoRandomData);
            log.info("Data saved");

    }
}
