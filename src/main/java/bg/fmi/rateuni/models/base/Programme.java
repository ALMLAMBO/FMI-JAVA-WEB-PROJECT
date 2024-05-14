package bg.fmi.rateuni.models.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

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

    @ManyToMany (mappedBy = "facultyPrograms")
    private Set<Faculty> faculties;

    @ManyToMany
    @JoinTable(name = "programs_disciplines",
        joinColumns = @JoinColumn(name = "program_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> programmeDisciplines;
}
