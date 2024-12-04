package tractive.code.task.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tractive.code.task.entity.Cat;
import tractive.code.task.entity.Dog;
import tractive.code.task.entity.Pet;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;

public class PetMapperTest {

	private PetMapper petMapper = new PetMapperImpl();

	@BeforeEach
	void setUp() {

	}

	@Test
	void testToPetDtoWithCat() {
		// Given
		Cat cat = Cat.builder().petType(PetType.CAT).trackerType(TrackerType.S).ownerId(101).inZone(true)
				.lostTracker(false).build();

		// When
		PetDto petDto = petMapper.toPetDto(cat);

		// Then
		assertNotNull(petDto);
		assertEquals(PetType.CAT, petDto.getPetType());
		assertEquals(TrackerType.S, petDto.getTrackerType());
		assertEquals(101, petDto.getOwnerId());
		assertTrue(petDto.isInZone());
	}

	@Test
	void testToPetDtoWithDog() {
		// Given
		Dog dog = Dog.builder().petType(PetType.DOG).trackerType(TrackerType.L).ownerId(202).inZone(false).build();

		// When
		PetDto petDto = petMapper.toPetDto(dog);

		// Then
		assertNotNull(petDto);
		assertEquals(PetType.DOG, petDto.getPetType());
		assertEquals(TrackerType.L, petDto.getTrackerType());
		assertEquals(202, petDto.getOwnerId());
		assertFalse(petDto.isInZone());
	}

	@Test
	void testToPetDtoWithUnknownPet() {
		// Given
		Pet unknownPet = new Pet() {
		};

		// Then
		assertThrows(RuntimeException.class, () -> petMapper.toPetDto(unknownPet));
	}

	@Test
	void testToPetWithCatDto() {
		// Given
		PetDto petDto = PetDto.builder().petType(PetType.CAT).trackerType(TrackerType.L).ownerId(101).inZone(true)
				.build();

		// When
		Pet pet = petMapper.toPet(petDto);

		// Then
		assertNotNull(pet);
		assertTrue(pet instanceof Cat);
		assertEquals(TrackerType.L, pet.getTrackerType());
		assertEquals(101, pet.getOwnerId());
		assertTrue(pet.isInZone());
	}

	@Test
	void testToPetWithDogDto() {
		// Given
		PetDto petDto = PetDto.builder().petType(PetType.DOG).trackerType(TrackerType.L).ownerId(202).inZone(false)
				.build();

		// When
		Pet pet = petMapper.toPet(petDto);

		// Then
		assertNotNull(pet);
		assertTrue(pet instanceof Dog);
		assertEquals(TrackerType.L, pet.getTrackerType());
		assertEquals(202, pet.getOwnerId());
		assertFalse(pet.isInZone());
	}

	@Test
	void testToPetWithUnknownPetType() {
		// Given
		PetDto petDto = PetDto.builder().petType(null).trackerType(TrackerType.L).ownerId(202).inZone(false).build();

		// Then
		assertThrows(RuntimeException.class, () -> petMapper.toPet(petDto));
	}
}
