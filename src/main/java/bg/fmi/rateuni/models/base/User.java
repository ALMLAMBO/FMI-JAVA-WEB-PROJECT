package bg.fmi.rateuni.models.base;

import bg.fmi.rateuni.vo.UserGender;
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
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String email;
    private String password;
    private String facultyNumber;
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @ManyToMany
    @JoinTable(name = "users_disciplines",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> userDisciplines;

    @ManyToMany
    @JoinTable(name = "users_faculties",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "faculty_id")
    )
    private Set<Faculty> userFaculties;
}
