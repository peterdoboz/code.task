package tractive.code.task.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springframework.stereotype.Service;

import tractive.code.task.entity.Cat;
import tractive.code.task.entity.Dog;
import tractive.code.task.entity.Pet;

@Mapper(componentModel = ComponentModel.SPRING)
@Service
public abstract class PetMapper {

	public PetDto toPetDto(Pet pet) {

		if (pet instanceof Cat) {
			return toCatDto((Cat) pet);
		} else if (pet instanceof Dog) {
			return toDogDto((Dog) pet);
		} else
			throw new RuntimeException();
	}

	public Pet toPet(PetDto pet) {
		return switch (pet.getPetType()) {
		case CAT -> toCat(pet);
		case DOG -> toDog(pet);
		default -> throw new RuntimeException();
		};

	}

	abstract PetDto toCatDto(Cat cat);

	abstract Cat toCat(PetDto cat);

	abstract PetDto toDogDto(Dog dog);

	abstract Dog toDog(PetDto dog);

	public List<? extends PetDto> toPetDto(List<? extends Pet> pets) {
		return pets.stream().map(this::toPetDto).toList();
	}

}
