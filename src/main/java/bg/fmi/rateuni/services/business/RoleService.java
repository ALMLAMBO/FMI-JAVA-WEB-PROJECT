package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.services.crud.RoleCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleCrudService roleCrudService;

    @Autowired
    private UserCrudService userCrudService;

    public BaseResponse createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);

        if (roleCrudService.getAllRoles().stream().anyMatch(roles -> roles.getName().equals(roleName))) {
            return new BaseResponse("Role already exists");
        }

        roleCrudService.createRole(role);
        return new BaseResponse("Added new role: " + roleName);
    }

    public BaseResponse deleteRole(UUID roleId) {
        roleCrudService.deleteRole(roleId);
        return new BaseResponse("Role deleted successfully");
    }

    public Role getRoleById(UUID roleId) {
        Role role = roleCrudService.getRoleById(roleId).get();
        if(role == null) {
            throw new IllegalArgumentException("Role not found");
        }

        return role;
    }

    public List<Role> getAllRoles() {
        return roleCrudService.getAllRoles();
    }

    public BaseResponse addUserToRole(UUID roleId, User user) {
        roleCrudService.addUserToRole(roleId, user);
        return new BaseResponse("User added to role");
    }

    public List<User> getUsersWithRole(Role role) {
        return userCrudService.getAllUsers()
                .stream()
                .filter(users -> users.getUserRoles().contains(role))
                .toList();
    }
}
