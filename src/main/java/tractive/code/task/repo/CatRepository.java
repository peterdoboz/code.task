package tractive.code.task.repo;

import org.springframework.stereotype.Repository;

import tractive.code.task.entity.Cat;
import tractive.code.task.entity.PetType;

@Repository
public interface CatRepository extends PetRepository<Cat> {

	@Override
	default PetType getType() {
		return PetType.CAT;
	}
}
