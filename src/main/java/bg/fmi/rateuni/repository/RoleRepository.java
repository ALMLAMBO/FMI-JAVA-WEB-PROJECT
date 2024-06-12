package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
    @Modifying
    @Query("UPDATE Role r SET r.users = :users WHERE r.id = :id")
    void addUserToRole(@Param("id") UUID id,
                       @Param("users") Set<User> users);

    @Query("SELECT r.users FROM Role r WHERE r.id = :id")
    Set<User> findAllRolesByUserId(@Param("id") UUID id);
}
