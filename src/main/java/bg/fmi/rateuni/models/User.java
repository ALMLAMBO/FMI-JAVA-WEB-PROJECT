package bg.fmi.rateuni.models;

import bg.fmi.rateuni.vo.UserGender;
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
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String email;
    private String username;
    private String password;
    private String facultyNumber;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    
    @OneToMany(mappedBy = "user")
    private Set<Review> userReviews;
    
    @OneToMany(mappedBy = "user")
    private Set<ReviewRequest> reviewRequests;
    
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles;

    @ManyToMany
    @JoinTable(name = "users_disciplines",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> userDisciplines;

}
