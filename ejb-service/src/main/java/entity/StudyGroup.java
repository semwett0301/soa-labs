package entity;



import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Data
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate = LocalDate.now();

    private Long studentsCount;

    @Enumerated(EnumType.STRING)
    private FormOfEducation formOfEducation;

    @Enumerated(EnumType.STRING)
    private Semester semesterEnum;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person groupAdmin;

    // Constructors, getters, and setters

    public StudyGroup() {
    }

    public StudyGroup(Integer id, String name, LocalDate creationDate, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

}
