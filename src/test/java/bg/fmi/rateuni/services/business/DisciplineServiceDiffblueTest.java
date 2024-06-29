package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.dto.response.ReviewResponse;
import bg.fmi.rateuni.mappers.DisciplineMapper;
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

@ContextConfiguration(classes = {DisciplineService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DisciplineServiceDiffblueTest {
    @MockBean
    private DisciplineCrudService disciplineCrudService;

    @MockBean
    private DisciplineMapper disciplineMapper;

    @Autowired
    private DisciplineService disciplineService;

    @MockBean
    private ReviewCrudService reviewCrudService;

    @MockBean
    private ReviewMapper reviewMapper;

    /**
     * Method under test: {@link DisciplineService#getDisciplineById(UUID)}
     */
    @Test
    void testGetDisciplineById() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);
        when(reviewCrudService.getReviewsByDisciplineId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        DisciplineResponse actualDisciplineById = disciplineService.getDisciplineById(UUID.randomUUID());

        // Assert
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(reviewCrudService).getReviewsByDisciplineId(isA(UUID.class));
        assertTrue(actualDisciplineById.getReviews().isEmpty());
        assertSame(disciplineResponse, actualDisciplineById);
    }

    /**
     * Method under test: {@link DisciplineService#getDisciplineById(UUID)}
     */
    @Test
    void testGetDisciplineById2() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);
        when(reviewCrudService.getReviewsByDisciplineId(Mockito.<UUID>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> disciplineService.getDisciplineById(UUID.randomUUID()));
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(reviewCrudService).getReviewsByDisciplineId(isA(UUID.class));
    }

    /**
     * Method under test: {@link DisciplineService#getDisciplineById(UUID)}
     */
    @Test
    void testGetDisciplineById3() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(new University());

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(programme2);
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());

        Discipline discipline3 = new Discipline();
        discipline3.setAssistants("Assistants");
        discipline3.setCategory(DisciplineCategory.CSF);
        discipline3.setCredits(10.0d);
        discipline3.setDescription("The characteristics of someone or something");
        discipline3.setId(UUID.randomUUID());
        discipline3.setLecturer("Lecturer");
        discipline3.setName("Name");
        discipline3.setProgramme(new Programme());
        discipline3.setRatings(new HashSet<>());
        discipline3.setType(DisciplineType.ELECTIVE);
        discipline3.setUsers(new HashSet<>());

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
        review.setDiscipline(discipline3);
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
        review2.setDiscipline(discipline2);
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
        when(reviewCrudService.getReviewsByDisciplineId(Mockito.<UUID>any())).thenReturn(reviewList);

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
        DisciplineResponse actualDisciplineById = disciplineService.getDisciplineById(UUID.randomUUID());

        // Assert
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(reviewMapper).mapToDto(isA(Review.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(reviewCrudService).getReviewsByDisciplineId(isA(UUID.class));
        assertEquals(1, actualDisciplineById.getReviews().size());
        assertSame(disciplineResponse, actualDisciplineById);
    }

    /**
     * Method under test:
     * {@link DisciplineService#getDisciplinesByProgrammeId(UUID)}
     */
    @Test
    void testGetDisciplinesByProgrammeId() {
        // Arrange
        when(disciplineCrudService.getDisciplinesByProgrammeId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        List<DisciplineResponse> actualDisciplinesByProgrammeId = disciplineService
                .getDisciplinesByProgrammeId(UUID.randomUUID());

        // Assert
        verify(disciplineCrudService).getDisciplinesByProgrammeId(isA(UUID.class));
        assertTrue(actualDisciplinesByProgrammeId.isEmpty());
    }

    /**
     * Method under test:
     * {@link DisciplineService#getDisciplinesByProgrammeId(UUID)}
     */
    @Test
    void testGetDisciplinesByProgrammeId2() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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

        ArrayList<Discipline> disciplineList = new ArrayList<>();
        disciplineList.add(discipline);
        when(disciplineCrudService.getDisciplinesByProgrammeId(Mockito.<UUID>any())).thenReturn(disciplineList);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

        // Act
        List<DisciplineResponse> actualDisciplinesByProgrammeId = disciplineService
                .getDisciplinesByProgrammeId(UUID.randomUUID());

        // Assert
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplinesByProgrammeId(isA(UUID.class));
        assertEquals(1, actualDisciplinesByProgrammeId.size());
    }

    /**
     * Method under test:
     * {@link DisciplineService#getDisciplinesByProgrammeId(UUID)}
     */
    @Test
    void testGetDisciplinesByProgrammeId3() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("17 High St");
        university2.setId(UUID.randomUUID());
        university2.setName("42");
        university2.setRector("42");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("17 High St");
        faculty2.setDean("42");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("42");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
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

        ArrayList<Discipline> disciplineList = new ArrayList<>();
        disciplineList.add(discipline2);
        disciplineList.add(discipline);
        when(disciplineCrudService.getDisciplinesByProgrammeId(Mockito.<UUID>any())).thenReturn(disciplineList);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

        // Act
        List<DisciplineResponse> actualDisciplinesByProgrammeId = disciplineService
                .getDisciplinesByProgrammeId(UUID.randomUUID());

        // Assert
        verify(disciplineMapper, atLeast(1)).mapToDto(Mockito.<Discipline>any());
        verify(disciplineCrudService).getDisciplinesByProgrammeId(isA(UUID.class));
        assertEquals(2, actualDisciplinesByProgrammeId.size());
    }

    /**
     * Method under test:
     * {@link DisciplineService#getDisciplinesByProgrammeId(UUID)}
     */
    @Test
    void testGetDisciplinesByProgrammeId4() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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

        ArrayList<Discipline> disciplineList = new ArrayList<>();
        disciplineList.add(discipline);
        when(disciplineCrudService.getDisciplinesByProgrammeId(Mockito.<UUID>any())).thenReturn(disciplineList);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> disciplineService.getDisciplinesByProgrammeId(UUID.randomUUID()));
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplinesByProgrammeId(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link DisciplineService#createDiscipline(CreateDisciplineRequest)}
     */
    @Test
    void testCreateDiscipline() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineByName(Mockito.<String>any())).thenReturn(ofResult);

        CreateDisciplineRequest createDisciplineRequest = new CreateDisciplineRequest();
        createDisciplineRequest.setCategory(DisciplineCategory.CSF);
        createDisciplineRequest.setCredits(10.0d);
        createDisciplineRequest.setName("Name");
        createDisciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act
        BaseResponse actualCreateDisciplineResult = disciplineService.createDiscipline(createDisciplineRequest);

        // Assert
        verify(disciplineCrudService).getDisciplineByName(eq("Name"));
        assertEquals("Discipline with name Name already exists", actualCreateDisciplineResult.getMessage());
    }

    /**
     * Method under test:
     * {@link DisciplineService#createDiscipline(CreateDisciplineRequest)}
     */
    @Test
    void testCreateDiscipline2() {
        // Arrange
        Optional<Discipline> emptyResult = Optional.empty();
        when(disciplineCrudService.getDisciplineByName(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(disciplineCrudService).createUpdateDiscipline(Mockito.<Discipline>any());

        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        when(disciplineMapper.mapFromCreateRequest(Mockito.<CreateDisciplineRequest>any())).thenReturn(discipline);

        CreateDisciplineRequest createDisciplineRequest = new CreateDisciplineRequest();
        createDisciplineRequest.setCategory(DisciplineCategory.CSF);
        createDisciplineRequest.setCredits(10.0d);
        createDisciplineRequest.setName("Name");
        createDisciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act
        BaseResponse actualCreateDisciplineResult = disciplineService.createDiscipline(createDisciplineRequest);

        // Assert
        verify(disciplineMapper).mapFromCreateRequest(isA(CreateDisciplineRequest.class));
        verify(disciplineCrudService).createUpdateDiscipline(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineByName(eq("Name"));
        assertEquals("Discipline created successfully", actualCreateDisciplineResult.getMessage());
    }

    /**
     * Method under test:
     * {@link DisciplineService#updateDiscipline(UUID, CreateDisciplineRequest)}
     */
    @Test
    void testUpdateDiscipline() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        doNothing().when(disciplineCrudService).createUpdateDiscipline(Mockito.<Discipline>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(programme2);
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());
        when(disciplineMapper.mapFromCreateRequest(Mockito.<CreateDisciplineRequest>any())).thenReturn(discipline2);
        UUID id = UUID.randomUUID();

        CreateDisciplineRequest createDisciplineRequest = new CreateDisciplineRequest();
        createDisciplineRequest.setCategory(DisciplineCategory.CSF);
        createDisciplineRequest.setCredits(10.0d);
        createDisciplineRequest.setName("Name");
        createDisciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act
        BaseResponse actualUpdateDisciplineResult = disciplineService.updateDiscipline(id, createDisciplineRequest);

        // Assert
        verify(disciplineMapper).mapFromCreateRequest(isA(CreateDisciplineRequest.class));
        verify(disciplineCrudService).createUpdateDiscipline(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        assertEquals("Discipline updated successfully", actualUpdateDisciplineResult.getMessage());
    }

    /**
     * Method under test:
     * {@link DisciplineService#updateDiscipline(UUID, CreateDisciplineRequest)}
     */
    @Test
    void testUpdateDiscipline2() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        doThrow(new IllegalArgumentException("Discipline updated successfully")).when(disciplineCrudService)
                .createUpdateDiscipline(Mockito.<Discipline>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");

        Discipline discipline2 = new Discipline();
        discipline2.setAssistants("Assistants");
        discipline2.setCategory(DisciplineCategory.CSF);
        discipline2.setCredits(10.0d);
        discipline2.setDescription("The characteristics of someone or something");
        discipline2.setId(UUID.randomUUID());
        discipline2.setLecturer("Lecturer");
        discipline2.setName("Name");
        discipline2.setProgramme(programme2);
        discipline2.setRatings(new HashSet<>());
        discipline2.setType(DisciplineType.ELECTIVE);
        discipline2.setUsers(new HashSet<>());
        when(disciplineMapper.mapFromCreateRequest(Mockito.<CreateDisciplineRequest>any())).thenReturn(discipline2);
        UUID id = UUID.randomUUID();

        CreateDisciplineRequest createDisciplineRequest = new CreateDisciplineRequest();
        createDisciplineRequest.setCategory(DisciplineCategory.CSF);
        createDisciplineRequest.setCredits(10.0d);
        createDisciplineRequest.setName("Name");
        createDisciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> disciplineService.updateDiscipline(id, createDisciplineRequest));
        verify(disciplineMapper).mapFromCreateRequest(isA(CreateDisciplineRequest.class));
        verify(disciplineCrudService).createUpdateDiscipline(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
    }

    /**
     * Method under test: {@link DisciplineService#deleteDiscipline(UUID)}
     */
    @Test
    void testDeleteDiscipline() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        doNothing().when(disciplineCrudService).deleteDiscipline(Mockito.<UUID>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        BaseResponse actualDeleteDisciplineResult = disciplineService.deleteDiscipline(UUID.randomUUID());

        // Assert
        verify(disciplineCrudService).deleteDiscipline(isA(UUID.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        assertEquals("Discipline deleted successfully", actualDeleteDisciplineResult.getMessage());
    }

    /**
     * Method under test: {@link DisciplineService#deleteDiscipline(UUID)}
     */
    @Test
    void testDeleteDiscipline2() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        doThrow(new IllegalArgumentException("Discipline deleted successfully")).when(disciplineCrudService)
                .deleteDiscipline(Mockito.<UUID>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> disciplineService.deleteDiscipline(UUID.randomUUID()));
        verify(disciplineCrudService).deleteDiscipline(isA(UUID.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link DisciplineService#addReviewToDiscipline(UUID, CreateReviewRequest)}
     */
    @Test
    void testAddReviewToDiscipline() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);

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
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(reviewMapper.mapFromCreateReviewRequest(Mockito.<CreateReviewRequest>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        UUID id = UUID.randomUUID();

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
        assertThrows(IllegalArgumentException.class, () -> disciplineService.addReviewToDiscipline(id, reviewRequest));
        verify(reviewMapper).mapFromCreateReviewRequest(isA(CreateReviewRequest.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
    }
}
