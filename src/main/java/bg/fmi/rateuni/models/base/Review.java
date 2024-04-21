package bg.fmi.rateuni.models.base;

import lombok.Data;

@Data
public class Review {
    private int id;
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
}
