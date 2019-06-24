package telephoneCode;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import telephoneCode.model.TelephoneCode;
import telephoneCode.repository.Repository;
import telephoneCode.utils.JsonToMap;


import java.net.MalformedURLException;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private Repository repository;
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }
//    //http://localhost:8080/add?cC=AAAA&c=AAAA&tC=+79
//    @GetMapping("/add")
//    public @ResponseBody String add(@RequestParam String cC, @RequestParam String c, @RequestParam String tC){
//        TelephoneCode t = new TelephoneCode();
//        t.setCountryCode(cC);
//        t.setCountry(c);
//        t.setTelephoneCode(tC);
//        repository.save(t);
//        return "Saved";
//    }
//    @GetMapping("/all")
//    public @ResponseBody Iterable<TelephoneCode> getAllCode(){
//        return repository.findAll();
//    }


    @ResponseBody
    @GetMapping("/code/{COUNTRYNAME}")
    public ResponseEntity index(@PathVariable("COUNTRYNAME") String countryName){
        for(TelephoneCode e : repository.findAll()){
            if(e.getCountry().equals(countryName)){
                return ResponseEntity.ok().body(e.getTelephoneCode());
            }
        }
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PostMapping("/reload")
    public String reload(){
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
                logger.info("reload");
            }
        } catch (MalformedURLException e) {
            logger.error(e.toString());
            return e.toString();
        }

        return "The update is done!";
    }

}
