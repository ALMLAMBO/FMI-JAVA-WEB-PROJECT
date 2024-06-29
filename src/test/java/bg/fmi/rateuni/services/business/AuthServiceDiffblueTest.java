package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.dto.request.LoginRequest;
import bg.fmi.rateuni.dto.request.RegisterRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.mappers.UserMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.UserCrudService;
import bg.fmi.rateuni.services.crud.UserRequestCrudService;
import bg.fmi.rateuni.vo.RequestStatus;
import bg.fmi.rateuni.vo.UserGender;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthServiceDiffblueTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private UserCrudService userCrudService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRequestCrudService userRequestCrudService;

    /**
     * Method under test: {@link AuthService#registerUser(RegisterRequest)}
     */
    @Test
    void testRegisterUser() {
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

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("jane.doe@example.org");
        registerRequest.setFacultyName("Faculty Name");
        registerRequest.setFacultyNumber("42");
        registerRequest.setGender(UserGender.MALE);
        registerRequest.setPassword("iloveyou");
        registerRequest.setProgrammeName("Programme Name");
        registerRequest.setUniversityName("University Name");
        registerRequest.setUsername("janedoe");

        // Act
        BaseResponse actualRegisterUserResult = authService.registerUser(registerRequest);

        // Assert
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
        assertEquals("User already exists", actualRegisterUserResult.getMessage());
    }

    /**
     * Method under test: {@link AuthService#loginUser(LoginRequest)}
     */
    @Test
    void testLoginUser() {
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        // Act
        BaseResponse actualLoginUserResult = authService.loginUser(loginRequest);

        // Assert
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
        assertEquals("Correct user credentials", actualLoginUserResult.getMessage());
    }

    /**
     * Method under test: {@link AuthService#loginUser(LoginRequest)}
     */
    @Test
    void testLoginUser2() {
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
        User user2 = mock(User.class);
        when(user2.getPassword()).thenReturn("foo");
        doNothing().when(user2).setEmail(Mockito.<String>any());
        doNothing().when(user2).setFacultyNumber(Mockito.<String>any());
        doNothing().when(user2).setGender(Mockito.<UserGender>any());
        doNothing().when(user2).setId(Mockito.<UUID>any());
        doNothing().when(user2).setPassword(Mockito.<String>any());
        doNothing().when(user2).setReviewRequests(Mockito.<Set<ReviewRequest>>any());
        doNothing().when(user2).setReviews(Mockito.<Set<Review>>any());
        doNothing().when(user2).setUserDisciplines(Mockito.<Set<Discipline>>any());
        doNothing().when(user2).setUserRequest(Mockito.<UserRequest>any());
        doNothing().when(user2).setUserRoles(Mockito.<Set<Role>>any());
        doNothing().when(user2).setUsername(Mockito.<String>any());
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("iloveyou");

        // Act
        BaseResponse actualLoginUserResult = authService.loginUser(loginRequest);

        // Assert
        verify(user2).getPassword();
        verify(user2).setEmail(eq("jane.doe@example.org"));
        verify(user2).setFacultyNumber(eq("42"));
        verify(user2).setGender(eq(UserGender.MALE));
        verify(user2).setId(isA(UUID.class));
        verify(user2).setPassword(eq("iloveyou"));
        verify(user2).setReviewRequests(isA(Set.class));
        verify(user2).setReviews(isA(Set.class));
        verify(user2).setUserDisciplines(isA(Set.class));
        verify(user2).setUserRequest(isA(UserRequest.class));
        verify(user2).setUserRoles(isA(Set.class));
        verify(user2).setUsername(eq("janedoe"));
        verify(userCrudService).getUserByEmail(eq("jane.doe@example.org"));
        assertEquals("Invalid email or password", actualLoginUserResult.getMessage());
    }
}
