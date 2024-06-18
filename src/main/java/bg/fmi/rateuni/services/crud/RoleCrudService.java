package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleCrudService {
    @Autowired
    private RoleRepository roleRepository;
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    public Optional<Role> getRoleById(UUID id) {
        return roleRepository.findById(id);
    }
    
    public void createRole(Role role) {
        roleRepository.save(role);
    }
    
    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
    
    public void addUserToRole(UUID roleId, User user) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        
        role.getUsers().add(user);
        roleRepository.save(role);
    }
}
