package bg.fmi.rateuni.models.base;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private UUID id;
    private String name;
    
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users;
}
