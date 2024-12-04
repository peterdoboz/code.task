package tractive.code.task.repo;

import org.springframework.stereotype.Repository;

import tractive.code.task.entity.Dog;
import tractive.code.task.entity.PetType;

@Repository
public interface DogRepository extends PetRepository<Dog> {

	@Override
	default PetType getType() {
		return PetType.DOG;
	}
}
