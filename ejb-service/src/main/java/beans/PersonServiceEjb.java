package beans;


import entity.Person;
import interfaces.PersonService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import org.jboss.ejb3.annotation.Pool;

import java.util.ArrayList;
import java.util.List;

@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
@Pool("slsb-strict-max-pool")
public class PersonServiceEjb implements PersonService {
    @Override
    public List<Person> getAll() {
        return new ArrayList<>();
    }
}
