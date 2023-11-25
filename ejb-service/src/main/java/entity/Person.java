package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Table(name = "person")
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
