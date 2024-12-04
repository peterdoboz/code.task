package tractive.code.task.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tractive.code.task.dto.PetDto;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/api/pets";
	}

	@Test
	void testAddPet() {
		// Given
		PetDto catDto = PetDto.builder().petType(PetType.CAT).trackerType(TrackerType.L).ownerId(101).inZone(true)
				.lostTracker(false).build();

		HttpEntity<PetDto> request = new HttpEntity<>(catDto);

		// When
		ResponseEntity<PetDto> response = restTemplate.exchange(getBaseUrl(), HttpMethod.POST, request, PetDto.class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		PetDto responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(PetType.CAT, responseBody.getPetType());
		assertEquals(TrackerType.L, responseBody.getTrackerType());
		assertEquals(101, responseBody.getOwnerId());
		assertTrue(responseBody.isInZone());
	}

	@Test
	void testGetPetsOutsideZone() {
		// When
		String url = "http://localhost:" + port + "/api/pets/outside-zone";
		ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().get(PetType.DOG.name()));
	}

	@Test
	void testGetAllPets() {
		// When
		ResponseEntity<PetDto[]> response = restTemplate.getForEntity(getBaseUrl(), PetDto[].class);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		PetDto[] allPets = response.getBody();
		assertNotNull(allPets);

		assertTrue(allPets.length >= 0);
	}
}
