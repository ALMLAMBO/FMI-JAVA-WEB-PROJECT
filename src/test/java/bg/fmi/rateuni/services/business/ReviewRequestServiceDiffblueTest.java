package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import bg.fmi.rateuni.services.crud.ReviewRequestCrudService;
import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import bg.fmi.rateuni.vo.RequestStatus;
import bg.fmi.rateuni.vo.UserGender;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReviewRequestService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewRequestServiceDiffblueTest {
    @MockBean
    private ReviewCrudService reviewCrudService;

    @MockBean
    private ReviewRequestCrudService reviewRequestCrudService;

    @Autowired
    private ReviewRequestService reviewRequestService;

    /**
     * Method under test: {@link ReviewRequestService#getReviewRequestById(UUID)}
     */
    @Test
    void testGetReviewRequestById() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);
        Optional<ReviewRequest> ofResult = Optional.of(reviewRequest2);
        when(reviewRequestCrudService.getReviewRequestById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        ReviewRequest actualReviewRequestById = reviewRequestService.getReviewRequestById(UUID.randomUUID());

        // Assert
        verify(reviewRequestCrudService).getReviewRequestById(isA(UUID.class));
        LocalTime expectedToLocalTimeResult = actualReviewRequestById.getUser()
                .getUserRequest()
                .getCreatedAt()
                .toLocalTime();
        assertSame(expectedToLocalTimeResult,
                actualReviewRequestById.getReview().getUser().getUserRequest().getCreatedAt().toLocalTime());
    }

    /**
     * Method under test:
     * {@link ReviewRequestService#getReviewRequestByUserId(UUID)}
     */
    @Test
    void testGetReviewRequestByUserId() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);
        Optional<ReviewRequest> ofResult = Optional.of(reviewRequest2);
        when(reviewRequestCrudService.getReviewRequestByUserId(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        ReviewRequest actualReviewRequestByUserId = reviewRequestService.getReviewRequestByUserId(UUID.randomUUID());

        // Assert
        verify(reviewRequestCrudService).getReviewRequestByUserId(isA(UUID.class));
        LocalTime expectedToLocalTimeResult = actualReviewRequestByUserId.getUser()
                .getUserRequest()
                .getCreatedAt()
                .toLocalTime();
        assertSame(expectedToLocalTimeResult,
                actualReviewRequestByUserId.getReview().getUser().getUserRequest().getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link ReviewRequestService#getActiveReviewRequests()}
     */
    @Test
    void testGetActiveReviewRequests() {
        // Arrange
        when(reviewRequestCrudService.getAllReviewRequests()).thenReturn(new ArrayList<>());

        // Act
        List<ReviewRequest> actualActiveReviewRequests = reviewRequestService.getActiveReviewRequests();

        // Assert
        verify(reviewRequestCrudService).getAllReviewRequests();
        assertTrue(actualActiveReviewRequests.isEmpty());
    }

    /**
     * Method under test: {@link ReviewRequestService#getActiveReviewRequests()}
     */
    @Test
    void testGetActiveReviewRequests2() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);

        ArrayList<ReviewRequest> reviewRequestList = new ArrayList<>();
        reviewRequestList.add(reviewRequest2);
        when(reviewRequestCrudService.getAllReviewRequests()).thenReturn(reviewRequestList);

        // Act
        List<ReviewRequest> actualActiveReviewRequests = reviewRequestService.getActiveReviewRequests();

        // Assert
        verify(reviewRequestCrudService).getAllReviewRequests();
        assertEquals(1, actualActiveReviewRequests.size());
        ReviewRequest getResult = actualActiveReviewRequests.get(0);
        LocalTime expectedToLocalTimeResult = getResult.getUser().getUserRequest().getCreatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult,
                getResult.getReview().getUser().getUserRequest().getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link ReviewRequestService#getActiveReviewRequests()}
     */
    @Test
    void testGetActiveReviewRequests3() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(new Faculty());
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Mr");

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("42");
        discipline2.setCategory(DisciplineCategory.CSC);
        discipline2.setCredits(0.5d);
        discipline2.setDescription("Description");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("42");
        discipline2.setName("42");
        discipline2.setProgramme(programme2);
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.MANDATORY);
        discipline2.setUsers(new HashSet<>());

        Review review3 = new Review();
        review3.setAssistantsRating(0.5d);
        review3.setComment("42");
        review3.setCourseRating(0.5d);
        review3.setDifficulty(0);
        review3.setDiscipline(new Discipline());
        review3.setHasAdditionalMaterials(false);
        review3.setHasBooks(false);
        review3.setHasExam(false);
        review3.setHasHomeworks(false);
        review3.setHasMidChecks(false);
        review3.setHasOnlineClasses(false);
        review3.setHasPresentations(false);
        review3.setHasProject(false);
        review3.setId(UUID.randomUUID());
        review3.setLecturerRating(0.5d);
        review3.setPublishedAt("42");
        review3.setReviewRequest(new ReviewRequest());
        review3.setUsefulness(0);
        review3.setUser(new User());
        review3.setVisible(false);
        review3.setWorkLoad(0);

        User user5 = new User();
        user5.setEmail("john.smith@example.org");
        user5.setFacultyNumber("Faculty Number");
        user5.setGender(UserGender.FEMALE);
        user5.setId(UUID.randomUUID());
        user5.setPassword("Password");
        user5.setReviewRequests(new HashSet<>());
        user5.setReviews(new HashSet<>());
        user5.setUserDisciplines(new HashSet<>());
        user5.setUserRequest(new UserRequest());
        user5.setUserRoles(new HashSet<>());
        user5.setUsername("Username");

        ReviewRequest reviewRequest3 = new ReviewRequest();
        reviewRequest3.setId(UUID.randomUUID());
        reviewRequest3.setReview(review3);
        reviewRequest3.setStatus(RequestStatus.APPROVED);
        reviewRequest3.setUser(user5);

        UserRequest userRequest3 = new UserRequest();
        userRequest3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest3.setFacultyName("42");
        userRequest3.setFacultyNumber("Faculty Number");
        userRequest3.setId(UUID.randomUUID());
        userRequest3.setImage("42");
        userRequest3.setProgrammeName("42");
        userRequest3.setRequestStatus(RequestStatus.APPROVED);
        userRequest3.setUniversityName("42");
        userRequest3.setUser(new User());
        userRequest3.setUsername("Username");

        User user6 = new User();
        user6.setEmail("john.smith@example.org");
        user6.setFacultyNumber("Faculty Number");
        user6.setGender(UserGender.FEMALE);
        user6.setId(UUID.randomUUID());
        user6.setPassword("Password");
        user6.setReviewRequests(new HashSet<>());
        user6.setReviews(new HashSet<>());
        user6.setUserDisciplines(new HashSet<>());
        user6.setUserRequest(userRequest3);
        user6.setUserRoles(new HashSet<>());
        user6.setUsername("Username");

        Review review4 = new Review();
        review4.setAssistantsRating(0.5d);
        review4.setComment("42");
        review4.setCourseRating(0.5d);
        review4.setDifficulty(0);
        review4.setDiscipline(discipline2);
        review4.setHasAdditionalMaterials(false);
        review4.setHasBooks(false);
        review4.setHasExam(false);
        review4.setHasHomeworks(false);
        review4.setHasMidChecks(false);
        review4.setHasOnlineClasses(false);
        review4.setHasPresentations(false);
        review4.setHasProject(false);
        review4.setId(UUID.randomUUID());
        review4.setLecturerRating(0.5d);
        review4.setPublishedAt("42");
        review4.setReviewRequest(reviewRequest3);
        review4.setUsefulness(0);
        review4.setUser(user6);
        review4.setVisible(false);
        review4.setWorkLoad(0);

        User user7 = new User();
        user7.setEmail("john.smith@example.org");
        user7.setFacultyNumber("Faculty Number");
        user7.setGender(UserGender.FEMALE);
        user7.setId(UUID.randomUUID());
        user7.setPassword("Password");
        user7.setReviewRequests(new HashSet<>());
        user7.setReviews(new HashSet<>());
        user7.setUserDisciplines(new HashSet<>());
        user7.setUserRequest(new UserRequest());
        user7.setUserRoles(new HashSet<>());
        user7.setUsername("Username");

        UserRequest userRequest4 = new UserRequest();
        userRequest4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest4.setFacultyName("42");
        userRequest4.setFacultyNumber("Faculty Number");
        userRequest4.setId(UUID.randomUUID());
        userRequest4.setImage("42");
        userRequest4.setProgrammeName("42");
        userRequest4.setRequestStatus(RequestStatus.APPROVED);
        userRequest4.setUniversityName("42");
        userRequest4.setUser(user7);
        userRequest4.setUsername("Username");

        User user8 = new User();
        user8.setEmail("john.smith@example.org");
        user8.setFacultyNumber("Faculty Number");
        user8.setGender(UserGender.FEMALE);
        user8.setId(UUID.randomUUID());
        user8.setPassword("Password");
        user8.setReviewRequests(new HashSet<>());
        user8.setReviews(new HashSet<>());
        user8.setUserDisciplines(new HashSet<>());
        user8.setUserRequest(userRequest4);
        user8.setUserRoles(new HashSet<>());
        user8.setUsername("Username");

        ReviewRequest reviewRequest4 = new ReviewRequest();
        reviewRequest4.setId(UUID.randomUUID());
        reviewRequest4.setReview(review4);
        reviewRequest4.setStatus(RequestStatus.APPROVED);
        reviewRequest4.setUser(user8);

        ArrayList<ReviewRequest> reviewRequestList = new ArrayList<>();
        reviewRequestList.add(reviewRequest4);
        reviewRequestList.add(reviewRequest2);
        when(reviewRequestCrudService.getAllReviewRequests()).thenReturn(reviewRequestList);

        // Act
        List<ReviewRequest> actualActiveReviewRequests = reviewRequestService.getActiveReviewRequests();

        // Assert
        verify(reviewRequestCrudService).getAllReviewRequests();
        assertEquals(1, actualActiveReviewRequests.size());
        ReviewRequest getResult = actualActiveReviewRequests.get(0);
        LocalTime expectedToLocalTimeResult = getResult.getUser().getUserRequest().getCreatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult,
                getResult.getReview().getUser().getUserRequest().getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link ReviewRequestService#getActiveReviewRequests()}
     */
    @Test
    void testGetActiveReviewRequests4() {
        // Arrange
        when(reviewRequestCrudService.getAllReviewRequests()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reviewRequestService.getActiveReviewRequests());
        verify(reviewRequestCrudService).getAllReviewRequests();
    }

    /**
     * Method under test:
     * {@link ReviewRequestService#updateRequestStatus(UUID, RequestStatus)}
     */
    @Test
    void testUpdateRequestStatus() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);
        Optional<ReviewRequest> ofResult = Optional.of(reviewRequest2);
        doNothing().when(reviewRequestCrudService).createUpdateReviewRequest(Mockito.<ReviewRequest>any());
        when(reviewRequestCrudService.getReviewRequestById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        reviewRequestService.updateRequestStatus(UUID.randomUUID(), RequestStatus.PENDING);

        // Assert
        verify(reviewRequestCrudService).createUpdateReviewRequest(isA(ReviewRequest.class));
        verify(reviewRequestCrudService).getReviewRequestById(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link ReviewRequestService#updateRequestStatus(UUID, RequestStatus)}
     */
    @Test
    void testUpdateRequestStatus2() {
        // Arrange
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(new Faculty());
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(new Discipline());
        review.setHasAdditionalMaterials(true);
        review.setHasBooks(true);
        review.setHasExam(true);
        review.setHasHomeworks(true);
        review.setHasMidChecks(true);
        review.setHasOnlineClasses(true);
        review.setHasPresentations(true);
        review.setHasProject(true);
        review.setId(UUID.randomUUID());
        review.setLecturerRating(10.0d);
        review.setPublishedAt("Published At");
        review.setReviewRequest(new ReviewRequest());
        review.setUsefulness(1);
        review.setUser(new User());
        review.setVisible(true);
        review.setWorkLoad(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(new UserRequest());
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(review);
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(user);

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(new User());
        userRequest.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(1);
        review2.setDiscipline(discipline);
        review2.setHasAdditionalMaterials(true);
        review2.setHasBooks(true);
        review2.setHasExam(true);
        review2.setHasHomeworks(true);
        review2.setHasMidChecks(true);
        review2.setHasOnlineClasses(true);
        review2.setHasPresentations(true);
        review2.setHasProject(true);
        review2.setId(UUID.randomUUID());
        review2.setLecturerRating(10.0d);
        review2.setPublishedAt("Published At");
        review2.setReviewRequest(reviewRequest);
        review2.setUsefulness(1);
        review2.setUser(user2);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(new UserRequest());
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user3);
        userRequest2.setUsername("janedoe");

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setFacultyNumber("42");
        user4.setGender(UserGender.MALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("iloveyou");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest2);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("janedoe");

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review2);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user4);
        Optional<ReviewRequest> ofResult = Optional.of(reviewRequest2);
        doThrow(new IllegalArgumentException("foo")).when(reviewRequestCrudService)
                .createUpdateReviewRequest(Mockito.<ReviewRequest>any());
        when(reviewRequestCrudService.getReviewRequestById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> reviewRequestService.updateRequestStatus(UUID.randomUUID(), RequestStatus.PENDING));
        verify(reviewRequestCrudService).createUpdateReviewRequest(isA(ReviewRequest.class));
        verify(reviewRequestCrudService).getReviewRequestById(isA(UUID.class));
    }
}
