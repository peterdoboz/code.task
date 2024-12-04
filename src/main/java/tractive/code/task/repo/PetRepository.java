package tractive.code.task.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import tractive.code.task.entity.Pet;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;

@NoRepositoryBean
public interface PetRepository<T extends Pet> extends JpaRepository<T, Long> {

	PetType getType();

	List<T> findByInZone(boolean inZone);

	List<T> findByInZoneAndTrackerType(boolean inZone, TrackerType trackerType);

}
