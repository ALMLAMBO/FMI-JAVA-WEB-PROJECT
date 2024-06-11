package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {

}
