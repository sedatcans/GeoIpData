package com.sedatcan.GeoIpBatch.repository;

import com.sedatcan.GeoIpBatch.entity.GeoRandomData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoRandomDataRepository extends JpaRepository<GeoRandomData, Long> {
}
