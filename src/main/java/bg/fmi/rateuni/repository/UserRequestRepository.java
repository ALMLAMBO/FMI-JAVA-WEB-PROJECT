package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, UUID> {
    @Query("SELECT ur FROM UserRequest ur WHERE ur.user.id = :userId")
    Optional<UserRequest> findUserRequestByUserId (@Param("userId") UUID userid);
}
