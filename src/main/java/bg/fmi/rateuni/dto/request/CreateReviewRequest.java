package bg.fmi.rateuni.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateReviewRequest {
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
    private UUID userId;
    private UUID disciplineId;
}
