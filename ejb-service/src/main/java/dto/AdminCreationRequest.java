package dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreationRequest implements Serializable {
    String name;
    LocalDateTime birthday;
    Long height;
    Float weight;
    String passportID;
}
