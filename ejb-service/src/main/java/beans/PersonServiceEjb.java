package beans;

import entity.Person;
import interfaces.PersonService;
import interfaces.RepositoryService;
import jakarta.ejb.EJB;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import lombok.Data;
import org.jboss.ejb3.annotation.Pool;

import java.util.List;

@Data
@Remote(PersonService.class)
@Stateless(name = "PersonServiceEjb")
@Pool("slsb-strict-max-pool")
public class PersonServiceEjb implements PersonService {
    @EJB
    private RepositoryService repositoryService;

    @Override
    public List<Person> getAll() {
        return repositoryService.getPersonRepository().findAll();
    }
}

