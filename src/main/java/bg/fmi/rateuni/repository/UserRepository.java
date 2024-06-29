package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Query("UPDATE User u SET u.userRequest = :userRequest WHERE u.id = :id")
    void addUserRequest(@Param("id") UUID id,
                        @Param("userRequest") UserRequest userRequest);

    @Query("SELECT u.userRequest FROM User u WHERE u.id = :id")
    UserRequest findUserRequestByUserId(@Param("id") UUID id);

    @Query("SELECT r FROM User u, Review r WHERE u.id = :id and r.user = u")
    List<Review> findAllReviewsByUserId(@Param("id") UUID id);
    
    @Query("SELECT rr FROM User u, ReviewRequest rr WHERE u.id = :id and rr.user = u")
    List<ReviewRequest> findAllReviewRequestsByUserId(@Param("id") UUID id);
    
    @Query("SELECT r FROM User u, Role r WHERE u.id = :id and r member of u.userRoles")
    List<Role> findUserRoles(@Param("id") UUID id);

    @Query("SELECT d FROM User u, Discipline d WHERE u.id = :id and d member of u.userDisciplines")
    List<Discipline> findAllDisciplinesByUserId(@Param("id") UUID id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
