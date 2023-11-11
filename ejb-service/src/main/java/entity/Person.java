package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Person implements Serializable {
    @Id
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private long height;
    private float weight;
    private String passportID;

    public Integer getId() {
        return id;
    }
}
