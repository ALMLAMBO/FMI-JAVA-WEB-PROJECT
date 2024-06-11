package bg.fmi.rateuni.models;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
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
@Table(name = "disciplines")
public class Discipline {
    @Id
    private UUID id;
    private String name;
    private String description;
    private double credits;

    @Enumerated(EnumType.STRING)
    private DisciplineCategory category; // ЯКН, ОКН, Мат...

    @Enumerated(EnumType.STRING)
    private DisciplineType type;
    private String lecturer;
    private String assistants;

    @OneToMany(mappedBy = "disciplineReviews")
    private Set<Review> disciplineReviews;

    @ManyToOne
    @JoinColumn(name = "programmeDisciplines")
    private Programme programme;

    @ManyToMany(mappedBy = "userDisciplines")
    private Set<User> users;

}