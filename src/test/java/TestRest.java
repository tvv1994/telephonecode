import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import telephoneCode.GreetingController;
import telephoneCode.model.TelephoneCode;
import telephoneCode.repository.Repository;



public class TestRest {

    @Autowired
    private static Repository repository;
    @Autowired
    private static GreetingController greetingController;

    @BeforeAll
    public  static  void initTest(){
    }

    @Test
    public void getCode() throws Exception {
        TelephoneCode t = new TelephoneCode();
        t.setCountry("Nilfgaard");
        t.setCountryCode("Nilfgaard");
        t.setTelephoneCode("Nilfgaard");
        repository.save(t);
        ResponseEntity respTest = ResponseEntity.notFound().build();
        for(TelephoneCode e: repository.findAll()){
            System.out.println(e.getTelephoneCode());
        }
        assert respTest.equals(greetingController.index("sad"));
    }
}
