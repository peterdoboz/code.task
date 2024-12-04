package tractive.code.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tractive.code.task.dto.PetDto;
import tractive.code.task.dto.PetMapper;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;
import tractive.code.task.service.PetService;

@RestController
@RequestMapping("/api/pets")
@Validated
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	private PetMapper petMapper;

	@PostMapping()
	@Validated
	public PetDto addPetData(@Valid @RequestBody PetDto pet) {
		return petMapper.toPetDto(petService.save(petMapper.toPet(pet)));
	}

	@GetMapping("/outside-zone")
	public Map<PetType, Map<TrackerType, Integer>> getPetsOutsideZone() {
		return petService.getPetsOutsideZone();
	}

	@GetMapping
	public List<? extends PetDto> getAllPets() {
		return petMapper.toPetDto(petService.getAllPets());

	}

}