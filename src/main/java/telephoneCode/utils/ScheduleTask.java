package telephoneCode.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import telephoneCode.model.TelephoneCode;
import telephoneCode.repository.Repository;

import java.net.MalformedURLException;
import java.util.Map;

@Component
public class ScheduleTask {
    private Repository repository;

    @Scheduled(cron = "0 2 * * * ?")
    public void reload(){
        try {
            JsonToMap json = new JsonToMap();
            Map<String, String> map1 = json.createMapCountry();
            Map<String, String> map2 = json.createMapTelephone();
            for(Map.Entry<String,String> e: map1.entrySet()){
                TelephoneCode t = new TelephoneCode();
                t.setCountryCode(e.getKey());
                t.setCountry(e.getValue());
                t.setTelephoneCode(map2.get(e.getKey()));
                repository.save(t);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
