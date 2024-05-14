package bg.fmi.rateuni.models.base;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "universities")
public class University {
    @Id
    private UUID id;
    private String name;
    private String rector;
    private String hqAddress;

    @ManyToMany
    @JoinTable(name = "universities_faculties",
        joinColumns = @JoinColumn(name = "university_id"),
        inverseJoinColumns = @JoinColumn(name = "faculty_id")
    )
    private Set<Faculty> universityFaculties;
}
