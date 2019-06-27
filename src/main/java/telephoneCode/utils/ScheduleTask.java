package telephoneCode.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import telephoneCode.model.TelephoneCode;
import telephoneCode.repository.Repository;

import java.util.Map;

@Component
public class ScheduleTask {

    @Autowired
    private Repository repository;

    @Transactional
    @Scheduled(cron = "0 0/2 * * * ?")
    public void reload() {
        JsonToMap json = new JsonToMap();
        Map<String, String> map1 = json.createMapCountry();
        Map<String, String> map2 = json.createMapTelephone();
        for (Map.Entry<String, String> e : map1.entrySet()) {
            TelephoneCode t = new TelephoneCode();
            t.setCountryCode(e.getKey());
            t.setCountry(e.getValue());
            t.setTelephoneCode(map2.get(e.getKey()));
            repository.save(t);
        }
    }
}
