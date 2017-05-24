package com.sedatcan.GeoIpBatch.service.impl;

import com.sedatcan.GeoIpBatch.entity.GeoRandomData;
import com.sedatcan.GeoIpBatch.repository.GeoRandomDataRepository;
import com.sedatcan.GeoIpBatch.service.GeoRandomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoRandomDataServiceImpl implements GeoRandomDataService {

    @Autowired
    private GeoRandomDataRepository geoRandomDataRepository;

    @Override
    public GeoRandomData saveGeoRandomData(Integer returnCode,
                                           String ip,
                                           String returnCodeDetails,
                                           String countryName,
                                           String countryCode,
                                           Long id,
                                           String quote) {
        GeoRandomData geoRandomData = GeoRandomData.builder()
                .countryCode(countryCode)
                .countryName(countryName)
                .id(id)
                .ip(ip)
                .quote(quote)
                .returnCode(returnCode)
                .returnCodeDetails(returnCodeDetails)
                .build();
        return geoRandomDataRepository.save(geoRandomData);
    }

    @Override
    public GeoRandomData saveGeoRandomData(GeoRandomData geoRandomData) {
        return geoRandomDataRepository.save(geoRandomData);
    }
}
