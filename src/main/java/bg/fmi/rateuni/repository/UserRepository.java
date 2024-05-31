package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
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
    @Query("SELECT u.userReviews FROM User u WHERE u.id = :id")
    List<Review> findUserReviewsById(@Param("id") UUID id);
    
    @Modifying
    @Query("UPDATE User u SET u.userReviews = :reviews WHERE u.id = :userId")
    void addUserReview(@Param("userId") UUID userId, @Param("reviews") Set<Review> reviews);
    
    @Query("SELECT u.reviewRequests FROM User u WHERE u.id = :id")
    List<ReviewRequest> findUserReviewRequestsById(@Param("id") UUID id);
    
    @Modifying
    @Query("UPDATE User u SET u.reviewRequests = :reviewRequests WHERE u.id = :userId")
    void addUserReviewRequest(@Param("userId") UUID userId, @Param("reviewRequests") Set<ReviewRequest> reviewRequests);
    
    @Modifying
    @Query("UPDATE User u SET u.userRoles = :roles WHERE u.id = :userId")
    void addUserRoles(@Param("userId") UUID userId, @Param("roles") Set<Role> roles);
}
