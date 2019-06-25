package telephoneCode.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import telephoneCode.model.TelephoneCode;
import telephoneCode.utils.JsonToMap;

import java.util.Map;

@Component
public class DataInit implements ApplicationRunner {

    private Repository repository;

    @Autowired
    public DataInit(Repository repository){
        this.repository=repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = repository.count();

        if(count==0){
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
        }
    }
}
