package bg.fmi.rateuni.models.base;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
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

    @ManyToMany
    @JoinTable(name = "disciplines_reviews",
        joinColumns = @JoinColumn(name = "discipline_id"),
        inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private Set<Review> disciplineReviews;

    @ManyToMany(mappedBy = "programmeDisciplines")
    private Set<Programme> programs;

    @ManyToMany(mappedBy = "userDisciplines")
    private Set<User> users;

}