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
@Table(name = "reviews")
public class Review {
    @Id
    private UUID id;
    private String comment;
    private String publishedAt;
    private double courseRating;
    private double lecturerRating;
    private double assistantsRating;
    private int difficulty;
    private int usefulness;
    private int workLoad;
    private boolean hasExam;
    private boolean hasProject;
    private boolean hasMidChecks;
    private boolean hasHomeworks;
    private boolean hasOnlineClasses;
    private boolean hasBooks;
    private boolean hasPresentations;
    private boolean hasAdditionalMaterials;
    private boolean visible;
    
    @ManyToOne
    @JoinColumn(name = "userReviews")
    private User user;

    @ManyToOne
    @JoinColumn(name = "disciplineReviews")
    private Discipline discipline;
}
