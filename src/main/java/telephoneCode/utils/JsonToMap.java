package telephoneCode.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMap {

    private URL RESOURCE1 =new URL("http://country.io/names.json");
    private URL RESOURCE2 =new URL("http://country.io/phone.json");

    public JsonToMap() throws MalformedURLException {
    }

    public Map<String, String> createMapCountry() {
        Map<String, String> mapCountry = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapCountry = mapper.readValue(RESOURCE1, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapCountry;
    }

    public Map<String, String> createMapTelephone() {
        Map<String, String> mapTelephone = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapTelephone = mapper.readValue(RESOURCE2, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapTelephone;
    }
}
