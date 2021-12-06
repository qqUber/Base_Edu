package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class TestGeo {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
        System.out.println(geoIp);}

}
