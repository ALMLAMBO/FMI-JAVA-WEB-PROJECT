package bg.fmi.rateuni.models.base;

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

    @ManyToMany(mappedBy = "programmes")
    private Set<User> users;

    @ManyToMany (mappedBy = "facultyPrograms")
    private Set<Faculty> faculties;

    @ManyToMany
    @JoinTable(name = "programs_disciplines",
        joinColumns = @JoinColumn(name = "program_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> programmeDisciplines;
}
