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
    @JoinTable(
            name = "users_programmes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "programme_id")
    )
    private Set<Programme> programmes;
    
    @ManyToMany
    @JoinTable(
            name = "users_reviews",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private Set<Review> userReviews;
    
    @ManyToMany
    @JoinTable(
            name = "users_review_requests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_request_id")
    )
    private Set<ReviewRequest> reviewRequests;
    
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles;
    
    @ManyToMany
    @JoinTable(
            name = "users_universities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    private Set<University> userUniversities;
}
