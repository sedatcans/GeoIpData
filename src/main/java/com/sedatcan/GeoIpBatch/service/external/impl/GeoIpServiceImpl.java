package com.sedatcan.GeoIpBatch.service.external.impl;

import com.sedatcan.GeoIpBatch.exception.GeoIpBatchErrorCodes;
import com.sedatcan.GeoIpBatch.exception.GeoIpBatchException;
import com.sedatcan.GeoIpBatch.service.external.GeoIpService;
import geoipservice.wsdl.GetGeoIP;
import geoipservice.wsdl.GetGeoIPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class GeoIpServiceImpl extends WebServiceGatewaySupport implements GeoIpService {

    private static final String SOAP_ACTION = "http://www.webservicex.net/GetGeoIP";
    @Value("${geoIpBatch.geoIp.service.url}")
    private String geoIpServiceURI;

    @Autowired
    public GeoIpServiceImpl(Jaxb2Marshaller marshaller) {
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    @Override
    public GetGeoIPResponse getGeoIP(String ipAddress) {
        GetGeoIP request = new GetGeoIP();
        request.setIPAddress(ipAddress);
        logger.debug("IpAddress : " + ipAddress);
        Object response = getWebServiceTemplate().marshalSendAndReceive(
                geoIpServiceURI,
                request,
                new SoapActionCallback(SOAP_ACTION));
        if (response != null) {
            return (GetGeoIPResponse) response;
        } else {
            throw new GeoIpBatchException(GeoIpBatchErrorCodes.GEOIPSERVICE_EMPTY_RESPONSE);
        }

    }
}
