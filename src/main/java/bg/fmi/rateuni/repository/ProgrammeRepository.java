package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, UUID> {
}
