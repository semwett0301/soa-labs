package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class Person implements Serializable {
    @Id
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private long height;
    private float weight;
    private String passportID;

    public Person() {

    }

    public Integer getId() {
        return id;
    }
}
