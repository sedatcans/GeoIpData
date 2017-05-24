package com.sedatcan.GeoIpBatch.message;

import lombok.Data;

@Data
public class RandomResponse {
    private String type;
    private Value value;
}
