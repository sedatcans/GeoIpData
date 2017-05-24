package com.sedatcan.GeoIpBatch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Builder
@Entity
public class GeoRandomData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guid;
    private Integer returnCode;
    private String ip;
    private String returnCodeDetails;
    private String countryName;
    private String countryCode;
    private Long id;
    private String quote;
}
