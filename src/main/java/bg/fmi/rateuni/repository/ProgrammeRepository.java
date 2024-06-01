package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
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
public interface ProgrammeRepository extends JpaRepository<Programme, UUID> {
    @Modifying
    @Query("UPDATE Programme p SET p.users = :users WHERE p.id = :programmeId")
    void addUserToProgramme(@Param("programmeId") UUID programmeId, @Param("users") List<User> users);

    @Query("SELECT p.users FROM Programme p WHERE p.id = :programmeId")
    List<User> findAllUsersByProgrammeId(@Param("programmeId") UUID programmeId);
}
