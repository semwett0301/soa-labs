package beans;

import entity.Person;
import interfaces.PersonService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Data;
import org.jboss.ejb3.annotation.Pool;

import java.util.List;

@Data
@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
@Pool("slsb-strict-max-pool")
public class PersonServiceEjb implements PersonService {

    @PersistenceContext(unitName="db_unit")
    private EntityManager entityManager;

    @Override
    public List<Person> getAll() {
        System.out.println(entityManager.createQuery("SELECT person FROM Person person").getResultList());
        return entityManager.createQuery("SELECT person FROM Person person").getResultList();
    }
}

