package bg.fmi.rateuni.models.base;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
    
    @ManyToMany(mappedBy = "userUniversities")
    private Set<User> users;
}
