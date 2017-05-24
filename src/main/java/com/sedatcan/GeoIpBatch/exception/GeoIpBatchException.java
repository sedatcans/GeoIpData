package com.sedatcan.GeoIpBatch.exception;


public class GeoIpBatchException extends RuntimeException {
    public GeoIpBatchException(GeoIpBatchErrorCodes errorCode) {
        super(errorCode.getErrorMessage());
    }
}
