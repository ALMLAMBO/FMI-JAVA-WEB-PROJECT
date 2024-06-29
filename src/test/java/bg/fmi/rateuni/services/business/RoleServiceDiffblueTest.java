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

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.RoleCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
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

@ContextConfiguration(classes = {RoleService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RoleServiceDiffblueTest {
    @MockBean
    private RoleCrudService roleCrudService;

    @Autowired
    private RoleService roleService;

    @MockBean
    private UserCrudService userCrudService;

    /**
     * Method under test: {@link RoleService#createRole(String)}
     */
    @Test
    void testCreateRole() {
        // Arrange
        when(roleCrudService.getAllRoles()).thenReturn(new ArrayList<>());
        doNothing().when(roleCrudService).createRole(Mockito.<Role>any());

        // Act
        BaseResponse actualCreateRoleResult = roleService.createRole("Role Name");

        // Assert
        verify(roleCrudService).createRole(isA(Role.class));
        verify(roleCrudService).getAllRoles();
        assertEquals("Added new role: Role Name", actualCreateRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#createRole(String)}
     */
    @Test
    void testCreateRole2() {
        // Arrange
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleCrudService.getAllRoles()).thenReturn(roleList);
        doNothing().when(roleCrudService).createRole(Mockito.<Role>any());

        // Act
        BaseResponse actualCreateRoleResult = roleService.createRole("Role Name");

        // Assert
        verify(roleCrudService).createRole(isA(Role.class));
        verify(roleCrudService).getAllRoles();
        assertEquals("Added new role: Role Name", actualCreateRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#createRole(String)}
     */
    @Test
    void testCreateRole3() {
        // Arrange
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        Role role2 = new Role();
        role2.setId(UUID.randomUUID());
        role2.setName("Role Name");
        role2.setUsers(new HashSet<>());

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role2);
        roleList.add(role);
        when(roleCrudService.getAllRoles()).thenReturn(roleList);

        // Act
        BaseResponse actualCreateRoleResult = roleService.createRole("Role Name");

        // Assert
        verify(roleCrudService).getAllRoles();
        assertEquals("Role already exists", actualCreateRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#createRole(String)}
     */
    @Test
    void testCreateRole4() {
        // Arrange
        when(roleCrudService.getAllRoles()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.createRole("Role Name"));
        verify(roleCrudService).getAllRoles();
    }

    /**
     * Method under test: {@link RoleService#createRole(String)}
     */
    @Test
    void testCreateRole5() {
        // Arrange
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        Role role2 = new Role();
        role2.setId(UUID.randomUUID());
        role2.setName("Role Name");
        role2.setUsers(new HashSet<>());

        Role role3 = new Role();
        role3.setId(UUID.randomUUID());
        role3.setName("Name");
        role3.setUsers(new HashSet<>());

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role3);
        roleList.add(role2);
        roleList.add(role);
        when(roleCrudService.getAllRoles()).thenReturn(roleList);

        // Act
        BaseResponse actualCreateRoleResult = roleService.createRole("Role Name");

        // Assert
        verify(roleCrudService).getAllRoles();
        assertEquals("Role already exists", actualCreateRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#deleteRole(UUID)}
     */
    @Test
    void testDeleteRole() {
        // Arrange
        doNothing().when(roleCrudService).deleteRole(Mockito.<UUID>any());

        // Act
        BaseResponse actualDeleteRoleResult = roleService.deleteRole(UUID.randomUUID());

        // Assert
        verify(roleCrudService).deleteRole(isA(UUID.class));
        assertEquals("Role deleted successfully", actualDeleteRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#deleteRole(UUID)}
     */
    @Test
    void testDeleteRole2() {
        // Arrange
        doThrow(new IllegalArgumentException("Role deleted successfully")).when(roleCrudService)
                .deleteRole(Mockito.<UUID>any());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.deleteRole(UUID.randomUUID()));
        verify(roleCrudService).deleteRole(isA(UUID.class));
    }

    /**
     * Method under test: {@link RoleService#getRoleById(UUID)}
     */
    @Test
    void testGetRoleById() {
        // Arrange
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());
        Optional<Role> ofResult = Optional.of(role);
        when(roleCrudService.getRoleById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        Role actualRoleById = roleService.getRoleById(UUID.randomUUID());

        // Assert
        verify(roleCrudService).getRoleById(isA(UUID.class));
        assertSame(role, actualRoleById);
    }

    /**
     * Method under test: {@link RoleService#getRoleById(UUID)}
     */
    @Test
    void testGetRoleById2() {
        // Arrange
        when(roleCrudService.getRoleById(Mockito.<UUID>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.getRoleById(UUID.randomUUID()));
        verify(roleCrudService).getRoleById(isA(UUID.class));
    }

    /**
     * Method under test: {@link RoleService#getAllRoles()}
     */
    @Test
    void testGetAllRoles() {
        // Arrange
        ArrayList<Role> roleList = new ArrayList<>();
        when(roleCrudService.getAllRoles()).thenReturn(roleList);

        // Act
        List<Role> actualAllRoles = roleService.getAllRoles();

        // Assert
        verify(roleCrudService).getAllRoles();
        assertTrue(actualAllRoles.isEmpty());
        assertSame(roleList, actualAllRoles);
    }

    /**
     * Method under test: {@link RoleService#getAllRoles()}
     */
    @Test
    void testGetAllRoles2() {
        // Arrange
        when(roleCrudService.getAllRoles()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.getAllRoles());
        verify(roleCrudService).getAllRoles();
    }

    /**
     * Method under test: {@link RoleService#addUserToRole(UUID, User)}
     */
    @Test
    void testAddUserToRole() {
        // Arrange
        doNothing().when(roleCrudService).addUserToRole(Mockito.<UUID>any(), Mockito.<User>any());
        UUID roleId = UUID.randomUUID();

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

        // Act
        BaseResponse actualAddUserToRoleResult = roleService.addUserToRole(roleId, user2);

        // Assert
        verify(roleCrudService).addUserToRole(isA(UUID.class), isA(User.class));
        assertEquals("User added to role", actualAddUserToRoleResult.getMessage());
    }

    /**
     * Method under test: {@link RoleService#addUserToRole(UUID, User)}
     */
    @Test
    void testAddUserToRole2() {
        // Arrange
        doThrow(new IllegalArgumentException("User added to role")).when(roleCrudService)
                .addUserToRole(Mockito.<UUID>any(), Mockito.<User>any());
        UUID roleId = UUID.randomUUID();

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

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.addUserToRole(roleId, user2));
        verify(roleCrudService).addUserToRole(isA(UUID.class), isA(User.class));
    }

    /**
     * Method under test: {@link RoleService#getUsersWithRole(Role)}
     */
    @Test
    void testGetUsersWithRole() {
        // Arrange
        when(userCrudService.getAllUsers()).thenReturn(new ArrayList<>());

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        // Act
        List<User> actualUsersWithRole = roleService.getUsersWithRole(role);

        // Assert
        verify(userCrudService).getAllUsers();
        assertTrue(actualUsersWithRole.isEmpty());
    }

    /**
     * Method under test: {@link RoleService#getUsersWithRole(Role)}
     */
    @Test
    void testGetUsersWithRole2() {
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

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        when(userCrudService.getAllUsers()).thenReturn(userList);

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        // Act
        List<User> actualUsersWithRole = roleService.getUsersWithRole(role);

        // Assert
        verify(userCrudService).getAllUsers();
        assertTrue(actualUsersWithRole.isEmpty());
    }

    /**
     * Method under test: {@link RoleService#getUsersWithRole(Role)}
     */
    @Test
    void testGetUsersWithRole3() {
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

        User user3 = new User();
        user3.setEmail("john.smith@example.org");
        user3.setFacultyNumber("Faculty Number");
        user3.setGender(UserGender.FEMALE);
        user3.setId(UUID.randomUUID());
        user3.setPassword("Password");
        user3.setReviewRequests(new HashSet<>());
        user3.setReviews(new HashSet<>());
        user3.setUserDisciplines(new HashSet<>());
        user3.setUserRequest(userRequest3);
        user3.setUserRoles(new HashSet<>());
        user3.setUsername("Username");

        UserRequest userRequest4 = new UserRequest();
        userRequest4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userRequest4.setFacultyName("42");
        userRequest4.setFacultyNumber("Faculty Number");
        userRequest4.setId(UUID.randomUUID());
        userRequest4.setImage("42");
        userRequest4.setProgrammeName("42");
        userRequest4.setRequestStatus(RequestStatus.APPROVED);
        userRequest4.setUniversityName("42");
        userRequest4.setUser(user3);
        userRequest4.setUsername("Username");

        User user4 = new User();
        user4.setEmail("john.smith@example.org");
        user4.setFacultyNumber("Faculty Number");
        user4.setGender(UserGender.FEMALE);
        user4.setId(UUID.randomUUID());
        user4.setPassword("Password");
        user4.setReviewRequests(new HashSet<>());
        user4.setReviews(new HashSet<>());
        user4.setUserDisciplines(new HashSet<>());
        user4.setUserRequest(userRequest4);
        user4.setUserRoles(new HashSet<>());
        user4.setUsername("Username");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user4);
        userList.add(user2);
        when(userCrudService.getAllUsers()).thenReturn(userList);

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        // Act
        List<User> actualUsersWithRole = roleService.getUsersWithRole(role);

        // Assert
        verify(userCrudService).getAllUsers();
        assertTrue(actualUsersWithRole.isEmpty());
    }

    /**
     * Method under test: {@link RoleService#getUsersWithRole(Role)}
     */
    @Test
    void testGetUsersWithRole4() {
        // Arrange
        when(userCrudService.getAllUsers()).thenThrow(new IllegalArgumentException("foo"));

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Name");
        role.setUsers(new HashSet<>());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.getUsersWithRole(role));
        verify(userCrudService).getAllUsers();
    }
}
