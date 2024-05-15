package bg.fmi.rateuni.models;

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
@Table(name = "faculties")
public class Faculty {
    @Id
    private UUID id;
    private String name;
    private String dean;
    private String address;

    @ManyToMany
    @JoinTable (name = "faculty_programs",
    joinColumns = @JoinColumn(name = "faculty_id"),
    inverseJoinColumns = @JoinColumn(name = "programme_id"))
    private Set<Programme> facultyPrograms;

    @ManyToMany(mappedBy = "universityFaculties")
    private Set<University> universities;

    @ManyToMany(mappedBy = "userFaculties")
    private Set<User> users;
}
