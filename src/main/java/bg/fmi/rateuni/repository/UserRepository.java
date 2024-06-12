package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Query("UPDATE User u SET u.userRequest = :userRequest WHERE u.id = :id")
    void addRequestByUserId(@Param("id") UUID id,
                            @Param("userRequest") UserRequest userRequest);

    @Query("SELECT u.userRequest FROM User u WHERE u.id = :id")
    UserRequest findUserRequestByUserId(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE User u SET u.reviews = :reviews WHERE u.id = :id")
    void addReviewByUserId(@Param("id") UUID id,
                           @Param("reviews") Set<Review> reviews);

    @Query("SELECT u.reviews FROM User u WHERE u.id = :id")
    List<Review> findAllReviewsByUserId(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE User u SET u.reviewRequests = :reviewRequests WHERE u.id = :id")
    void addReviewRequestsByUserId(@Param("id") UUID id,
                                   @Param("reviewRequests") Set<ReviewRequest> reviewRequests);

    @Query("SELECT u.reviewRequests FROM User u WHERE u.id = :id")
    Set<ReviewRequest> findAllReviewRequestsByUserId(@Param("id") UUID id);
    
    @Query("SELECT u.userRoles FROM User u WHERE u.id = :id")
    Set<Role> findAllRolesByUserId(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE User u SET u.userDisciplines = :userDisciplines WHERE u.id = :id")
    void addDisciplineToUserById(@Param("id") UUID id,
                                 @Param("userDisciplines") Set<Discipline> userDisciplines);

    @Query("SELECT u.userDisciplines FROM User u WHERE u.id = :id")
    Set<Discipline> findAllDisciplinesByUserId(@Param("id") UUID id);

}
