package com.sedatcan.GeoIpBatch.service;

import com.sedatcan.GeoIpBatch.entity.GeoRandomData;

public interface GeoRandomDataService {

    GeoRandomData saveGeoRandomData(Integer returnCode,
                                    String ip,
                                    String returnCodeDetails,
                                    String countryName,
                                    String countryCode,
                                    Long id,
                                    String quote);

    GeoRandomData saveGeoRandomData(GeoRandomData geoRandomData);
}
