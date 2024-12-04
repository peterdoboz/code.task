package tractive.code.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import tractive.code.task.entity.Cat;
import tractive.code.task.entity.Dog;
import tractive.code.task.entity.Pet;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;
import tractive.code.task.repo.CatRepository;
import tractive.code.task.repo.DogRepository;
import tractive.code.task.repo.PetRepository;

public class PetServiceTest {
	@InjectMocks
	private PetService petService;

	@Spy
	private List<PetRepository<? extends Pet>> petRepositories = new ArrayList<>();

	@Mock
	private CatRepository catRepository;

	@Mock
	private DogRepository dogRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(catRepository.getType()).thenCallRealMethod();
		when(dogRepository.getType()).thenCallRealMethod();
		petRepositories.add(catRepository);
		petRepositories.add(dogRepository);

	}

	@Test
	void testGetAllPets() {
		// Given
		Cat cat = Cat.builder().trackerType(TrackerType.S).ownerId(101).inZone(true).lostTracker(false).build();
		Dog dog = Dog.builder().trackerType(TrackerType.L).ownerId(202).inZone(false).build();
		when(catRepository.findAll()).thenReturn(List.of(cat));
		when(dogRepository.findAll()).thenReturn(List.of(dog));

		// When
		List<? extends Pet> allPets = petService.getAllPets();

		// Then
		assertNotNull(allPets);
		assertEquals(2, allPets.size());
		assertTrue(allPets.contains(cat));
		assertTrue(allPets.contains(dog));
	}

	@Test
	void testGetPetsOutsideZone() {
		// Given
		Cat cat = Cat.builder().petType(PetType.CAT).trackerType(TrackerType.S).ownerId(101).inZone(false)
				.lostTracker(false).build();
		Dog dog = Dog.builder().petType(PetType.DOG).trackerType(TrackerType.L).ownerId(202).inZone(false).build();
		when(catRepository.findByInZone(false)).thenReturn(List.of(cat));
		when(dogRepository.findByInZone(false)).thenReturn(List.of(dog));

		// When
		Map<PetType, Map<TrackerType, Integer>> result = petService.getPetsOutsideZone();

		// Then
		assertNotNull(result);
		assertEquals(1, result.get(PetType.CAT).get(TrackerType.S));
		assertEquals(1, result.get(PetType.DOG).get(TrackerType.L));
	}

	@Test
	void testSaveCat() {
		// Given
		Cat cat = Cat.builder().trackerType(TrackerType.S).ownerId(101).inZone(true).lostTracker(false).build();
		when(catRepository.save(cat)).thenReturn(cat);

		// When
		Pet savedCat = petService.save(cat);

		// Then
		assertNotNull(savedCat);
		assertEquals(cat, savedCat);
	}

	@Test
	void testSaveDog() {
		// Given
		Dog dog = Dog.builder().trackerType(TrackerType.L).ownerId(202).inZone(false).build();
		when(dogRepository.save(dog)).thenReturn(dog);

		// When
		Pet savedDog = petService.save(dog);

		// Then
		assertNotNull(savedDog);
		assertEquals(dog, savedDog);
	}

	@Test
	void testSavePetWithCat() {
		// Given
		Cat cat = Cat.builder().trackerType(TrackerType.L).ownerId(101).inZone(true).lostTracker(false).build();
		when(catRepository.save(cat)).thenReturn(cat);

		// When
		Pet savedPet = petService.save(cat);

		// Then
		assertNotNull(savedPet);
		assertTrue(savedPet instanceof Cat);
		assertEquals(cat, savedPet);
	}

	@Test
	void testSavePetWithDog() {
		// Given
		Dog dog = Dog.builder().trackerType(TrackerType.L).ownerId(202).inZone(false).build();
		when(dogRepository.save(dog)).thenReturn(dog);

		// When
		Pet savedPet = petService.save(dog);

		// Then
		assertNotNull(savedPet);
		assertTrue(savedPet instanceof Dog);
		assertEquals(dog, savedPet);
	}

	@Test
	void testSavePetWithUnknownType() {
		// Given
		Pet unknownPet = new Pet() {
			@Override
			public PetType getPetType() {
				return null;
			}
		};

		// Then
		assertThrows(IllegalArgumentException.class, () -> petService.save(unknownPet));
	}
}
