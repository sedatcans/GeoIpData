package com.sedatcan.GeoIpBatch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GeoIpBatchErrorCodes {
    GEOIPSERVICE_EMPTY_RESPONSE("GetGeoIp service response is null"),
    UNKNOWN_HOST("UnknownHost");

    @Getter
    private String errorMessage;
}
