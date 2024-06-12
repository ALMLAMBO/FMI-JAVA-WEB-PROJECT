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

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    
    @OneToMany(mappedBy = "faculty")
    private Set<Programme> programs;
}
