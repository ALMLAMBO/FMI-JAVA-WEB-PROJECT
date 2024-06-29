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

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.dto.response.UserDetail;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.ReviewMapper;
import bg.fmi.rateuni.mappers.UserMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import bg.fmi.rateuni.vo.RequestStatus;
import bg.fmi.rateuni.vo.UserGender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceDiffblueTest {
    @MockBean
    private DisciplineCrudService disciplineCrudService;

    @MockBean
    private DisciplineMapper disciplineMapper;

    @MockBean
    private ReviewMapper reviewMapper;

    @MockBean
    private UserCrudService userCrudService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
        // Arrange
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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);
        when(userCrudService.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        User actualUserByEmail = userService.getUserByEmail("jane.doe@example.org");

        // Assert
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
        assertSame(user2, actualUserByEmail);
    }

    /**
     * Method under test: {@link UserService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userCrudService.getUserByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByEmail("jane.doe@example.org"));
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail3() {
        // Arrange
        when(userCrudService.getUserByEmail(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("User with this email does not exist"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByEmail("jane.doe@example.org"));
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserService#getUserDetails(UUID)}
     */
    @Test
    void testGetUserDetails() {
        // Arrange
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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);
        when(userCrudService.getDisciplinesByUserId(Mockito.<UUID>any())).thenThrow(new IllegalArgumentException("foo"));
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserDetails(UUID.randomUUID()));
        verify(userCrudService).getDisciplinesByUserId(isA(UUID.class));
        verify(userCrudService).getUserById(isA(UUID.class));
    }

    /**
     * Method under test: {@link UserService#getUserDetails(UUID)}
     */
    @Test
    void testGetUserDetails2() {
        // Arrange
        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);

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
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());
        when(userCrudService.getDisciplinesByUserId(Mockito.<UUID>any())).thenReturn(disciplineList);
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult);

        UserDetail userDetail = new UserDetail();
        userDetail.setDisciplines(new ArrayList<>());
        userDetail.setEmail("jane.doe@example.org");
        userDetail.setFacultyName("Faculty Name");
        userDetail.setFacultyNumber("42");
        userDetail.setId(UUID.randomUUID());
        userDetail.setProgrammeName("Programme Name");
        userDetail.setReviews(new ArrayList<>());
        userDetail.setUniversityName("University Name");
        when(userMapper.mapToUserDetail(Mockito.<User>any(), Mockito.<University>any(), Mockito.<Faculty>any(),
                Mockito.<Programme>any())).thenReturn(userDetail);

        // Act
        UserDetail actualUserDetails = userService.getUserDetails(UUID.randomUUID());

        // Assert
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(userMapper).mapToUserDetail(isA(User.class), isA(University.class), isA(Faculty.class),
                isA(Programme.class));
        verify(userCrudService).getDisciplinesByUserId(isA(UUID.class));
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        verify(userCrudService).getUserById(isA(UUID.class));
        assertEquals(1, actualUserDetails.getDisciplines().size());
        assertTrue(actualUserDetails.getReviews().isEmpty());
        assertSame(userDetail, actualUserDetails);
    }

    /**
     * Method under test: {@link UserService#getUserDetails(UUID)}
     */
    @Test
    void testGetUserDetails3() {
        // Arrange
        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);

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
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenThrow(new IllegalArgumentException("foo"));
        when(userCrudService.getDisciplinesByUserId(Mockito.<UUID>any())).thenReturn(disciplineList);
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult);

        UserDetail userDetail = new UserDetail();
        userDetail.setDisciplines(new ArrayList<>());
        userDetail.setEmail("jane.doe@example.org");
        userDetail.setFacultyName("Faculty Name");
        userDetail.setFacultyNumber("42");
        userDetail.setId(UUID.randomUUID());
        userDetail.setProgrammeName("Programme Name");
        userDetail.setReviews(new ArrayList<>());
        userDetail.setUniversityName("University Name");
        when(userMapper.mapToUserDetail(Mockito.<User>any(), Mockito.<University>any(), Mockito.<Faculty>any(),
                Mockito.<Programme>any())).thenReturn(userDetail);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUserDetails(UUID.randomUUID()));
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(userMapper).mapToUserDetail(isA(User.class), isA(University.class), isA(Faculty.class),
                isA(Programme.class));
        verify(userCrudService).getDisciplinesByUserId(isA(UUID.class));
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        verify(userCrudService).getUserById(isA(UUID.class));
    }

    /**
     * Method under test: {@link UserService#getUserDetails(UUID)}
     */
    @Test
    void testGetUserDetails4() {
        // Arrange
        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);

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
        when(userCrudService.getReviewsByUserId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());
        when(userCrudService.getDisciplinesByUserId(Mockito.<UUID>any())).thenReturn(disciplineList);
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult);

        UserDetail userDetail = new UserDetail();
        userDetail.setDisciplines(new ArrayList<>());
        userDetail.setEmail("jane.doe@example.org");
        userDetail.setFacultyName("Faculty Name");
        userDetail.setFacultyNumber("42");
        userDetail.setId(UUID.randomUUID());
        userDetail.setProgrammeName("Programme Name");
        userDetail.setReviews(new ArrayList<>());
        userDetail.setUniversityName("University Name");
        when(userMapper.mapToUserDetail(Mockito.<User>any(), Mockito.<University>any(), Mockito.<Faculty>any(),
                Mockito.<Programme>any())).thenReturn(userDetail);

        // Act
        UserDetail actualUserDetails = userService.getUserDetails(UUID.randomUUID());

        // Assert
        verify(disciplineMapper, atLeast(1)).mapToDto(Mockito.<Discipline>any());
        verify(userMapper).mapToUserDetail(isA(User.class), isA(University.class), isA(Faculty.class),
                isA(Programme.class));
        verify(userCrudService).getDisciplinesByUserId(isA(UUID.class));
        verify(userCrudService).getReviewsByUserId(isA(UUID.class));
        verify(userCrudService).getUserById(isA(UUID.class));
        assertEquals(2, actualUserDetails.getDisciplines().size());
        assertTrue(actualUserDetails.getReviews().isEmpty());
        assertSame(userDetail, actualUserDetails);
    }

    /**
     * Method under test: {@link UserService#addUserToDiscipline(UserDetail, UUID)}
     */
    @Test
    void testAddUserToDiscipline() {
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
        doNothing().when(disciplineCrudService).addUserToDiscipline(Mockito.<UUID>any(), Mockito.<User>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user2);
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult2);
        doNothing().when(userCrudService).addUserDiscipline(Mockito.<UUID>any(), Mockito.<Discipline>any());

        UserDetail userDetail = new UserDetail();
        userDetail.setDisciplines(new ArrayList<>());
        userDetail.setEmail("jane.doe@example.org");
        userDetail.setFacultyName("Faculty Name");
        userDetail.setFacultyNumber("42");
        userDetail.setId(UUID.randomUUID());
        userDetail.setProgrammeName("Programme Name");
        userDetail.setReviews(new ArrayList<>());
        userDetail.setUniversityName("University Name");

        // Act
        BaseResponse actualAddUserToDisciplineResult = userService.addUserToDiscipline(userDetail, UUID.randomUUID());

        // Assert
        verify(disciplineCrudService).addUserToDiscipline(isA(UUID.class), isA(User.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(userCrudService).addUserDiscipline(isA(UUID.class), isA(Discipline.class));
        verify(userCrudService).getUserById(isA(UUID.class));
        assertEquals("User added to discipline", actualAddUserToDisciplineResult.getMessage());
    }

    /**
     * Method under test: {@link UserService#addUserToDiscipline(UserDetail, UUID)}
     */
    @Test
    void testAddUserToDiscipline2() {
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
        doThrow(new IllegalArgumentException("User added to discipline")).when(disciplineCrudService)
                .addUserToDiscipline(Mockito.<UUID>any(), Mockito.<User>any());
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFacultyNumber("42");
        user.setGender(UserGender.MALE);
        user.setId(UUID.randomUUID());
        user.setPassword("iloveyou");
        user.setReviewRequests(new HashSet<>());
        user.setReviews(new HashSet<>());
        user.setUserDisciplines(new HashSet<>());
        user.setUserRequest(userRequest);
        user.setUserRoles(new HashSet<>());
        user.setUsername("janedoe");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest2.setFacultyName("Faculty Name");
        userRequest2.setFacultyNumber("42");
        userRequest2.setId(UUID.randomUUID());
        userRequest2.setImage("Image");
        userRequest2.setProgrammeName("Programme Name");
        userRequest2.setRequestStatus(RequestStatus.PENDING);
        userRequest2.setUniversityName("University Name");
        userRequest2.setUser(user);
        userRequest2.setUsername("janedoe");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setFacultyNumber("42");
        user2.setGender(UserGender.MALE);
        user2.setId(UUID.randomUUID());
        user2.setPassword("iloveyou");
        user2.setReviewRequests(new HashSet<>());
        user2.setReviews(new HashSet<>());
        user2.setUserDisciplines(new HashSet<>());
        user2.setUserRequest(userRequest2);
        user2.setUserRoles(new HashSet<>());
        user2.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user2);
        when(userCrudService.getUserById(Mockito.<UUID>any())).thenReturn(ofResult2);
        doNothing().when(userCrudService).addUserDiscipline(Mockito.<UUID>any(), Mockito.<Discipline>any());

        UserDetail userDetail = new UserDetail();
        userDetail.setDisciplines(new ArrayList<>());
        userDetail.setEmail("jane.doe@example.org");
        userDetail.setFacultyName("Faculty Name");
        userDetail.setFacultyNumber("42");
        userDetail.setId(UUID.randomUUID());
        userDetail.setProgrammeName("Programme Name");
        userDetail.setReviews(new ArrayList<>());
        userDetail.setUniversityName("University Name");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.addUserToDiscipline(userDetail, UUID.randomUUID()));
        verify(disciplineCrudService).addUserToDiscipline(isA(UUID.class), isA(User.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(userCrudService).addUserDiscipline(isA(UUID.class), isA(Discipline.class));
        verify(userCrudService).getUserById(isA(UUID.class));
    }
}
