package com.sedatcan.GeoIpBatch.service.external;

import geoipservice.wsdl.GetGeoIPResponse;

public interface GeoIpService {
    GetGeoIPResponse getGeoIP(String ipAddress);
}
