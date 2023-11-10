package ejb;


import interfaces.TryInterface;

import javax.ejb.Stateless;

@Stateless(name = "TryEjb")
public class TryEjb implements TryInterface {

    @Override
    public void nothing() {
        System.out.println("A");
    }
}
