package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.ReviewResponse;
import bg.fmi.rateuni.mappers.ReviewMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import bg.fmi.rateuni.services.crud.ReviewRequestCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import bg.fmi.rateuni.vo.RequestStatus;
import bg.fmi.rateuni.vo.UserGender;

import java.time.LocalDate;
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

@ContextConfiguration(classes = {ReviewService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewServiceDiffblueTest {
    @MockBean
    private DisciplineCrudService disciplineCrudService;

    @MockBean
    private ReviewCrudService reviewCrudService;

    @MockBean
    private ReviewMapper reviewMapper;

    @MockBean
    private ReviewRequestCrudService reviewRequestCrudService;

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private UserCrudService userCrudService;

    /**
     * Method under test: {@link ReviewService#createReview(CreateReviewRequest)}
     */
    @Test
    void testCreateReview() {
        // Arrange
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

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest.setFacultyName("Faculty Name");
        userRequest.setFacultyNumber("42");
        userRequest.setId(UUID.randomUUID());
        userRequest.setImage("Image");
        userRequest.setProgrammeName("Programme Name");
        userRequest.setRequestStatus(RequestStatus.PENDING);
        userRequest.setUniversityName("University Name");
        userRequest.setUser(user);
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

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user2);
        userRequest2.setUsername("janedoe");

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setFacultyNumber("42");
        user3.setGender(UserGender.MALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("iloveyou");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(userRequest2);
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("janedoe");
        when(reviewCrudService.getUserByReviewId(Mockito.<UUID>any(), Mockito.<UUID>any())).thenReturn(user3);

        CreateReviewRequest reviewRequest = new CreateReviewRequest();
        reviewRequest.setAssistantsRating(10.0d);
        reviewRequest.setComment("Comment");
        reviewRequest.setCourseRating(10.0d);
        reviewRequest.setDifficulty(1);
        reviewRequest.setDisciplineId(UUID.randomUUID());
        reviewRequest.setHasAdditionalMaterials(true);
        reviewRequest.setHasBooks(true);
        reviewRequest.setHasExam(true);
        reviewRequest.setHasHomeworks(true);
        reviewRequest.setHasMidChecks(true);
        reviewRequest.setHasOnlineClasses(true);
        reviewRequest.setHasPresentations(true);
        reviewRequest.setHasProject(true);
        reviewRequest.setLecturerRating(10.0d);
        reviewRequest.setPublishedAt("Published At");
        reviewRequest.setUsefulness(1);
        reviewRequest.setUserId(UUID.randomUUID());
        reviewRequest.setWorkLoad(1);

        // Act
        BaseResponse actualCreateReviewResult = reviewService.createReview(reviewRequest);

        // Assert
        verify(reviewCrudService).getUserByReviewId(isA(UUID.class), isA(UUID.class));
        assertEquals("User has already written a review", actualCreateReviewResult.getMessage());
    }

    /**
     * Method under test: {@link ReviewService#createReview(CreateReviewRequest)}
     */
    @Test
    void testCreateReview2() {
        // Arrange
        when(reviewCrudService.getUserByReviewId(Mockito.<UUID>any(), Mockito.<UUID>any()))
                .thenThrow(new IllegalArgumentException("User does not exist"));

        CreateReviewRequest reviewRequest = new CreateReviewRequest();
        reviewRequest.setAssistantsRating(10.0d);
        reviewRequest.setComment("Comment");
        reviewRequest.setCourseRating(10.0d);
        reviewRequest.setDifficulty(1);
        reviewRequest.setDisciplineId(UUID.randomUUID());
        reviewRequest.setHasAdditionalMaterials(true);
        reviewRequest.setHasBooks(true);
        reviewRequest.setHasExam(true);
        reviewRequest.setHasHomeworks(true);
        reviewRequest.setHasMidChecks(true);
        reviewRequest.setHasOnlineClasses(true);
        reviewRequest.setHasPresentations(true);
        reviewRequest.setHasProject(true);
        reviewRequest.setLecturerRating(10.0d);
        reviewRequest.setPublishedAt("Published At");
        reviewRequest.setUsefulness(1);
        reviewRequest.setUserId(UUID.randomUUID());
        reviewRequest.setWorkLoad(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reviewService.createReview(reviewRequest));
        verify(reviewCrudService).getUserByReviewId(isA(UUID.class), isA(UUID.class));
    }

    /**
     * Method under test: {@link ReviewService#getReviewById(UUID)}
     */
    @Test
    void testGetReviewById() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);
        Optional<Review> ofResult = Optional.of(review2);
        when(reviewCrudService.getReviewByID(Mockito.<UUID>any())).thenReturn(ofResult);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        // Act
        ReviewResponse actualReviewById = reviewService.getReviewById(UUID.randomUUID());

        // Assert
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(reviewCrudService).getReviewByID(isA(UUID.class));
        assertSame(reviewResponse, actualReviewById);
    }

    /**
     * Method under test: {@link ReviewService#getReviewById(UUID)}
     */
    @Test
    void testGetReviewById2() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);
        Optional<Review> ofResult = Optional.of(review2);
        when(reviewCrudService.getReviewByID(Mockito.<UUID>any())).thenReturn(ofResult);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reviewService.getReviewById(UUID.randomUUID()));
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(reviewCrudService).getReviewByID(isA(UUID.class));
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByDisciplineId(UUID)}
     */
    @Test
    void testGetReviewsByDisciplineId() {
        // Arrange
        when(disciplineCrudService.getReviewsByDisciplineId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        List<ReviewResponse> actualReviewsByDisciplineId = reviewService.getReviewsByDisciplineId(UUID.randomUUID());

        // Assert
        verify(disciplineCrudService).getReviewsByDisciplineId(isA(UUID.class));
        assertTrue(actualReviewsByDisciplineId.isEmpty());
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByDisciplineId(UUID)}
     */
    @Test
    void testGetReviewsByDisciplineId2() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review2);
        when(disciplineCrudService.getReviewsByDisciplineId(Mockito.<UUID>any())).thenReturn(reviewList);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        // Act
        List<ReviewResponse> actualReviewsByDisciplineId = reviewService.getReviewsByDisciplineId(UUID.randomUUID());

        // Assert
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(disciplineCrudService).getReviewsByDisciplineId(isA(UUID.class));
        assertEquals(1, actualReviewsByDisciplineId.size());
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByDisciplineId(UUID)}
     */
    @Test
    void testGetReviewsByDisciplineId3() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("17 High St");
        faculty2.setDean("42");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("42");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(new University());

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Mr");

        Discipline discipline3 = new Discipline();
        discipline3.setAssistants("42");
        discipline3.setCategory(DisciplineCategory.CSC);
        discipline3.setCredits(0.5d);
        discipline3.setDescription("Description");
        discipline3.setId(UUID.randomUUID());
        discipline3.setLecturer("42");
        discipline3.setName("42");
        discipline3.setProgramme(programme2);
        discipline3.setRatings(new HashSet<>());
        discipline3.setType(DisciplineType.MANDATORY);
        discipline3.setUsers(new HashSet<>());

        Discipline discipline4 = new Discipline();
        discipline4.setAssistants("42");
        discipline4.setCategory(DisciplineCategory.CSC);
        discipline4.setCredits(0.5d);
        discipline4.setDescription("Description");
        discipline4.setId(UUID.randomUUID());
        discipline4.setLecturer("42");
        discipline4.setName("42");
        discipline4.setProgramme(new Programme());
        discipline4.setRatings(new HashSet<>());
        discipline4.setType(DisciplineType.MANDATORY);
        discipline4.setUsers(new HashSet<>());

        ReviewRequest reviewRequest3 = new ReviewRequest();
        reviewRequest3.setId(UUID.randomUUID());
        reviewRequest3.setReview(new Review());
        reviewRequest3.setStatus(RequestStatus.APPROVED);
        reviewRequest3.setUser(new User());

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

        Review review3 = new Review();
        review3.setAssistantsRating(0.5d);
        review3.setComment("42");
        review3.setCourseRating(0.5d);
        review3.setDifficulty(0);
        review3.setDiscipline(discipline4);
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
        review3.setReviewRequest(reviewRequest3);
        review3.setUsefulness(0);
        review3.setUser(user5);
        review3.setVisible(false);
        review3.setWorkLoad(0);

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

        ReviewRequest reviewRequest4 = new ReviewRequest();
        reviewRequest4.setId(UUID.randomUUID());
        reviewRequest4.setReview(review3);
        reviewRequest4.setStatus(RequestStatus.APPROVED);
        reviewRequest4.setUser(user6);

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

        Review review4 = new Review();
        review4.setAssistantsRating(0.5d);
        review4.setComment("42");
        review4.setCourseRating(0.5d);
        review4.setDifficulty(0);
        review4.setDiscipline(discipline3);
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
        review4.setReviewRequest(reviewRequest4);
        review4.setUsefulness(0);
        review4.setUser(user8);
        review4.setVisible(false);
        review4.setWorkLoad(0);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review4);
        reviewList.add(review2);
        when(disciplineCrudService.getReviewsByDisciplineId(Mockito.<UUID>any())).thenReturn(reviewList);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        // Act
        List<ReviewResponse> actualReviewsByDisciplineId = reviewService.getReviewsByDisciplineId(UUID.randomUUID());

        // Assert
        verify(reviewMapper, atLeast(1)).mapToDto(Mockito.<Review>any());
        verify(disciplineCrudService).getReviewsByDisciplineId(isA(UUID.class));
        assertEquals(2, actualReviewsByDisciplineId.size());
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByUserId(UUID)}
     */
    @Test
    void testGetReviewsByUserId() {
        // Arrange
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        List<ReviewResponse> actualReviewsByUserId = reviewService.getReviewsByUserId(UUID.randomUUID());

        // Assert
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        assertTrue(actualReviewsByUserId.isEmpty());
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByUserId(UUID)}
     */
    @Test
    void testGetReviewsByUserId2() {
        // Arrange
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review2);
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenReturn(reviewList);

        // Act
        List<ReviewResponse> actualReviewsByUserId = reviewService.getReviewsByUserId(UUID.randomUUID());

        // Assert
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        assertEquals(1, actualReviewsByUserId.size());
    }

    /**
     * Method under test: {@link ReviewService#getReviewsByUserId(UUID)}
     */
    @Test
    void testGetReviewsByUserId3() {
        // Arrange
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("17 High St");
        faculty2.setDean("42");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("42");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(new University());

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Mr");

        Discipline discipline3 = new Discipline();
        discipline3.setAssistants("42");
        discipline3.setCategory(DisciplineCategory.CSC);
        discipline3.setCredits(0.5d);
        discipline3.setDescription("Description");
        discipline3.setId(UUID.randomUUID());
        discipline3.setLecturer("42");
        discipline3.setName("42");
        discipline3.setProgramme(programme2);
        discipline3.setRatings(new HashSet<>());
        discipline3.setType(DisciplineType.MANDATORY);
        discipline3.setUsers(new HashSet<>());

        Discipline discipline4 = new Discipline();
        discipline4.setAssistants("42");
        discipline4.setCategory(DisciplineCategory.CSC);
        discipline4.setCredits(0.5d);
        discipline4.setDescription("Description");
        discipline4.setId(UUID.randomUUID());
        discipline4.setLecturer("42");
        discipline4.setName("42");
        discipline4.setProgramme(new Programme());
        discipline4.setRatings(new HashSet<>());
        discipline4.setType(DisciplineType.MANDATORY);
        discipline4.setUsers(new HashSet<>());

        ReviewRequest reviewRequest3 = new ReviewRequest();
        reviewRequest3.setId(UUID.randomUUID());
        reviewRequest3.setReview(new Review());
        reviewRequest3.setStatus(RequestStatus.APPROVED);
        reviewRequest3.setUser(new User());

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

        Review review3 = new Review();
        review3.setAssistantsRating(0.5d);
        review3.setComment("42");
        review3.setCourseRating(0.5d);
        review3.setDifficulty(0);
        review3.setDiscipline(discipline4);
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
        review3.setReviewRequest(reviewRequest3);
        review3.setUsefulness(0);
        review3.setUser(user5);
        review3.setVisible(false);
        review3.setWorkLoad(0);

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

        ReviewRequest reviewRequest4 = new ReviewRequest();
        reviewRequest4.setId(UUID.randomUUID());
        reviewRequest4.setReview(review3);
        reviewRequest4.setStatus(RequestStatus.APPROVED);
        reviewRequest4.setUser(user6);

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

        Review review4 = new Review();
        review4.setAssistantsRating(0.5d);
        review4.setComment("42");
        review4.setCourseRating(0.5d);
        review4.setDifficulty(0);
        review4.setDiscipline(discipline3);
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
        review4.setReviewRequest(reviewRequest4);
        review4.setUsefulness(0);
        review4.setUser(user8);
        review4.setVisible(false);
        review4.setWorkLoad(0);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review4);
        reviewList.add(review2);
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenReturn(reviewList);

        // Act
        List<ReviewResponse> actualReviewsByUserId = reviewService.getReviewsByUserId(UUID.randomUUID());

        // Assert
        verify(reviewMapper, atLeast(1)).mapToDto(Mockito.<Review>any());
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        assertEquals(2, actualReviewsByUserId.size());
    }

    /**
     * Method under test: {@link ReviewService#deleteReview(UUID)}
     */
    @Test
    void testDeleteReview() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);
        Optional<Review> ofResult = Optional.of(review2);
        doNothing().when(reviewCrudService).deleteReview(Mockito.<UUID>any());
        when(reviewCrudService.getReviewByID(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        BaseResponse actualDeleteReviewResult = reviewService.deleteReview(UUID.randomUUID());

        // Assert
        verify(reviewCrudService).deleteReview(isA(UUID.class));
        verify(reviewCrudService).getReviewByID(isA(UUID.class));
        assertEquals("Review deleted successfully", actualDeleteReviewResult.getMessage());
    }

    /**
     * Method under test: {@link ReviewService#deleteReview(UUID)}
     */
    @Test
    void testDeleteReview2() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(1);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(1);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(1);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(1);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(1);
        Optional<Review> ofResult = Optional.of(review2);
        doThrow(new IllegalArgumentException("Review deleted successfully")).when(reviewCrudService)
                .deleteReview(Mockito.<UUID>any());
        when(reviewCrudService.getReviewByID(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reviewService.deleteReview(UUID.randomUUID()));
        verify(reviewCrudService).deleteReview(isA(UUID.class));
        verify(reviewCrudService).getReviewByID(isA(UUID.class));
    }

    /**
     * Method under test: {@link ReviewService#getLatestSixReviews()}
     */
    @Test
    void testGetLatestSixReviews() {
        // Arrange
        when(reviewCrudService.getAllReviews()).thenReturn(new ArrayList<>());

        // Act
        List<ReviewResponse> actualLatestSixReviews = reviewService.getLatestSixReviews();

        // Assert
        verify(reviewCrudService).getAllReviews();
        assertTrue(actualLatestSixReviews.isEmpty());
    }

    /**
     * Method under test: {@link ReviewService#getLatestSixReviews()}
     */
    @Test
    void testGetLatestSixReviews2() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(6);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(6);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(6);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(6);
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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(6);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(6);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review2);
        when(reviewCrudService.getAllReviews()).thenReturn(reviewList);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        // Act
        List<ReviewResponse> actualLatestSixReviews = reviewService.getLatestSixReviews();

        // Assert
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(reviewCrudService).getAllReviews();
        assertEquals(1, actualLatestSixReviews.size());
    }

    /**
     * Method under test: {@link ReviewService#getLatestSixReviews()}
     */
    @Test
    void testGetLatestSixReviews3() {
        // Arrange
        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(new University());

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(new Programme());
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setId(UUID.randomUUID());
        reviewRequest.setReview(new Review());
        reviewRequest.setStatus(RequestStatus.PENDING);
        reviewRequest.setUser(new User());

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

        Review review = new Review();
        review.setAssistantsRating(10.0d);
        review.setComment("Comment");
        review.setCourseRating(10.0d);
        review.setDifficulty(6);
        review.setDiscipline(discipline2);
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
        review.setReviewRequest(reviewRequest);
        review.setUsefulness(6);
        review.setUser(user);
        review.setVisible(true);
        review.setWorkLoad(6);

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

        ReviewRequest reviewRequest2 = new ReviewRequest();
        reviewRequest2.setId(UUID.randomUUID());
        reviewRequest2.setReview(review);
        reviewRequest2.setStatus(RequestStatus.PENDING);
        reviewRequest2.setUser(user2);

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

        Review review2 = new Review();
        review2.setAssistantsRating(10.0d);
        review2.setComment("Comment");
        review2.setCourseRating(10.0d);
        review2.setDifficulty(6);
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
        review2.setReviewRequest(reviewRequest2);
        review2.setUsefulness(6);
        review2.setUser(user4);
        review2.setVisible(true);
        review2.setWorkLoad(6);

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("17 High St");
        faculty2.setDean("42");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("42");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(new University());

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Mr");

        Discipline discipline3 = new Discipline();
        discipline3.setAssistants("42");
        discipline3.setCategory(DisciplineCategory.CSC);
        discipline3.setCredits(0.5d);
        discipline3.setDescription("Description");
        discipline3.setId(UUID.randomUUID());
        discipline3.setLecturer("42");
        discipline3.setName("42");
        discipline3.setProgramme(programme2);
        discipline3.setRatings(new HashSet<>());
        discipline3.setType(DisciplineType.MANDATORY);
        discipline3.setUsers(new HashSet<>());

        Discipline discipline4 = new Discipline();
        discipline4.setAssistants("42");
        discipline4.setCategory(DisciplineCategory.CSC);
        discipline4.setCredits(0.5d);
        discipline4.setDescription("Description");
        discipline4.setId(UUID.randomUUID());
        discipline4.setLecturer("42");
        discipline4.setName("42");
        discipline4.setProgramme(new Programme());
        discipline4.setRatings(new HashSet<>());
        discipline4.setType(DisciplineType.MANDATORY);
        discipline4.setUsers(new HashSet<>());

        ReviewRequest reviewRequest3 = new ReviewRequest();
        reviewRequest3.setId(UUID.randomUUID());
        reviewRequest3.setReview(new Review());
        reviewRequest3.setStatus(RequestStatus.APPROVED);
        reviewRequest3.setUser(new User());

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

        Review review3 = new Review();
        review3.setAssistantsRating(0.5d);
        review3.setComment("42");
        review3.setCourseRating(0.5d);
        review3.setDifficulty(1);
        review3.setDiscipline(discipline4);
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
        review3.setReviewRequest(reviewRequest3);
        review3.setUsefulness(1);
        review3.setUser(user5);
        review3.setVisible(false);
        review3.setWorkLoad(1);

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

        ReviewRequest reviewRequest4 = new ReviewRequest();
        reviewRequest4.setId(UUID.randomUUID());
        reviewRequest4.setReview(review3);
        reviewRequest4.setStatus(RequestStatus.APPROVED);
        reviewRequest4.setUser(user6);

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

        Review review4 = new Review();
        review4.setAssistantsRating(0.5d);
        review4.setComment("42");
        review4.setCourseRating(0.5d);
        review4.setDifficulty(1);
        review4.setDiscipline(discipline3);
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
        review4.setReviewRequest(reviewRequest4);
        review4.setUsefulness(1);
        review4.setUser(user8);
        review4.setVisible(false);
        review4.setWorkLoad(1);

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review4);
        reviewList.add(review2);
        when(reviewCrudService.getAllReviews()).thenReturn(reviewList);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setAssistantsRating(10.0d);
        reviewResponse.setComment("Comment");
        reviewResponse.setCourseRating(10.0d);
        reviewResponse.setDifficulty(1);
        reviewResponse.setHasAdditionalMaterials(true);
        reviewResponse.setHasBooks(true);
        reviewResponse.setHasExam(true);
        reviewResponse.setHasHomeworks(true);
        reviewResponse.setHasMidChecks(true);
        reviewResponse.setHasOnlineClasses(true);
        reviewResponse.setHasPresentations(true);
        reviewResponse.setHasProject(true);
        reviewResponse.setId(UUID.randomUUID());
        reviewResponse.setLecturerRating(10.0d);
        reviewResponse.setPublishedAt("Published At");
        reviewResponse.setUsefulness(1);
        reviewResponse.setWorkLoad(1);
        when(reviewMapper.mapToDto(Mockito.<Review>any())).thenReturn(reviewResponse);

        // Act
        List<ReviewResponse> actualLatestSixReviews = reviewService.getLatestSixReviews();

        // Assert
        verify(reviewMapper, atLeast(1)).mapToDto(Mockito.<Review>any());
        verify(reviewCrudService).getAllReviews();
        assertEquals(2, actualLatestSixReviews.size());
    }
}
