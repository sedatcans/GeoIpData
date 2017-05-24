GeoIpData

A batch job using Spring Boot that runs every 5 seconds and consumes the http://gturnquist-quoters.cfapps.io/api/random restfull service to get id and content fields. Also consumes http://www.webservicex.net/geoipservice.asmx xml web service’s GetGeoIP method. Using these two information retrieved from both services please store it in h2 db using Spring Data JPA. For every record in database must contain fields below. ReturnCode, IP, ReturnCodeDetails, CountryName, CountryCode, id, quote ReturnCode, IP, ReturnCodeDetails, CountryName, CountryCode fields must be retrieved from given http://www.webservicex.net/geoipservice.asmx geoipservice using the server’s IP address. id, quote fields must be retrieved from given http://gturnquist-quoters.cfapps.io/api/random restfull service. 2Programming Assignment Job should run every 5 seconds and if the task doesn’t complete in 5 seconds other jobs should wait for it to complete.
