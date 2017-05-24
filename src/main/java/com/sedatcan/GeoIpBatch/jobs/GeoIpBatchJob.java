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
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(fixedDelay = 5000)
    public void geoIpBatchExecution() {
        try {
            JobParameters param = new JobParametersBuilder().addString("ip", Inet4Address.getLocalHost().getHostAddress()).addLong("time", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(job, param);
        } catch (UnknownHostException e) {
            log.error(GeoIpBatchErrorCodes.UNKNOWN_HOST.getErrorMessage());
            throw new GeoIpBatchException(GeoIpBatchErrorCodes.UNKNOWN_HOST);
        } catch (Exception e) {
            log.error("Exception occured", e);
            throw new GeoIpBatchException(e);
        }
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                        String ip = (String) chunkContext.getStepContext().getJobParameters().get("ip");
                        retriveGeoDataAndSave(ip);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Job job(Step step) throws Exception {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
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
