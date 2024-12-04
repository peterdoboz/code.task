package tractive.code.task.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tractive.code.task.entity.Cat;
import tractive.code.task.entity.Dog;
import tractive.code.task.entity.Pet;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;
import tractive.code.task.repo.CatRepository;
import tractive.code.task.repo.DogRepository;
import tractive.code.task.repo.PetRepository;

@Service
public class PetService {

	@Autowired
	private List<PetRepository<? extends Pet>> petRepositories;

	@Autowired
	private CatRepository catRepository;
	@Autowired
	private DogRepository dogRepository;

	public List<? extends Pet> getAllPets() {
		return petRepositories.stream().map(PetRepository::findAll).flatMap(List::stream).toList();
	}

	public Map<PetType, Map<TrackerType, Integer>> getPetsOutsideZone() {
		List<? extends Pet> pets = petRepositories.stream().map(r -> r.findByInZone(false)).flatMap(List::stream)
				.toList();
		return pets.stream().collect(Collectors.groupingBy(Pet::getPetType,
				Collectors.groupingBy(Pet::getTrackerType, Collectors.reducing(0, e -> 1, Integer::sum))));
	}

	public Pet save(Cat cat) {
		return catRepository.save(cat);
	}

	public Pet save(Dog dog) {
		return dogRepository.save(dog);
	}

	public Pet save(Pet pet) {
		if (pet.getPetType() == null) {
			throw new IllegalArgumentException("Missing PetType");
		}
		return switch (pet.getPetType()) {
		case CAT -> save((Cat) pet);
		case DOG -> save((Dog) pet);
		default -> throw new IllegalArgumentException("Unexpected value: " + pet.getPetType());
		};
	}

}
