package bg.fmi.rateuni.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "programs")
public class Programme {
    @Id
    private UUID id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "facultyPrograms")
    private Faculty faculty;

    @OneToMany(mappedBy = "programme")
    private Set<Discipline> programmeDisciplines;
}
