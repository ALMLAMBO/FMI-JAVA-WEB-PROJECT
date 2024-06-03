package bg.fmi.rateuni.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDto {
    private String comment;
    @NotBlank
    private String publishedAt;
    @NotNull
    private double courseRating;
    @NotNull
    private double lecturerRating;
    private double assistantsRating;
    @NotNull
    private int difficulty;
    @NotNull
    private int usefulness;
    @NotNull
    private int workLoad;
    @NotNull
    private boolean hasExam;
    @NotNull
    private boolean hasProject;
    @NotNull
    private boolean hasMidChecks;
    @NotNull
    private boolean hasHomeworks;
    @NotNull
    private boolean hasOnlineClasses;
    @NotNull
    private boolean hasBooks;
    @NotNull
    private boolean hasPresentations;
    @NotNull
    private boolean hasAdditionalMaterials;
    @NotNull
    private boolean visible;
}
