package bg.fmi.rateuni.models;

import bg.fmi.rateuni.vo.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review_requests")
public class ReviewRequest {
    @Id
    private UUID id;
    private UUID userId;
    private UUID reviewId;
    
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    
    @ManyToMany(mappedBy = "reviewRequests")
    private Set<User> users;
    
}