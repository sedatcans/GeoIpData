package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.configuration.GeoIpBatchConfiguration;
import com.sedatcan.GeoIpBatch.repository.GeoRandomDataRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(GeoIpBatchConfiguration.class)
public abstract class BaseServiceTest {
}
