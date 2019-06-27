package telephoneCode.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonToMap {

    private String RESOURCE1 = "http://country.io/names.json";
    private String RESOURCE2 = "http://country.io/phone.json";

    @Autowired
    private RestTemplate restTemplate;


    public JsonToMap(){
    }

    public Map<String, String> createMapCountry() {
        return restTemplate.getForObject(RESOURCE1, Map.class);
    }

    public Map<String, String> createMapTelephone() {
        return restTemplate.getForObject(RESOURCE2, Map.class);
    }
}
