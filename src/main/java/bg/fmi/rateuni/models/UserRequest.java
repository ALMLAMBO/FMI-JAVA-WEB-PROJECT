package bg.fmi.rateuni.models;

import bg.fmi.rateuni.vo.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserRequest {
    @Id
    private UUID id;
    private String username;
    private String universityName;
    private String facultyName;
    private String programmeName;
    private String facultyNumber;
    
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private String image;
    private LocalDateTime createdAt;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
