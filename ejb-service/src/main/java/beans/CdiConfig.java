package beans;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CdiConfig {
    @Produces
    @Dependent
    @PersistenceContext(unitName = "db_unit")
    public EntityManager entityManager;
}
