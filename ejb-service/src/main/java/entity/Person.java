package entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Person implements Serializable {
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
