package beans;


import entity.Person;
import interfaces.PersonService;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
public class PersonServiceEjb implements PersonService {
    @Override
    public List<Person> getAll() {
        return new ArrayList<>();
    }
}
