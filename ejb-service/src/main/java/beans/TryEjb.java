package beans;

import interfaces.TryInterface;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless(name = "TryEjb")
@Remote(TryInterface.class)
public class TryEjb implements TryInterface {

    @Override
    public String nothing() {
        return "A";
    }
}
