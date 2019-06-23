package telephoneCode.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import telephoneCode.model.TelephoneCode;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<TelephoneCode, Long> {
//  @Query("select e from TELCODE e where e.country = :name")
//  TelephoneCode findByName(@Param("name") String name);
}
