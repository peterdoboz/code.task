package tractive.code.task.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

	private String id;

	private PetType petType;

	private TrackerType trackerType;

	private Integer ownerId;

	private boolean inZone;

	private Boolean lostTracker;

	@AssertTrue(message = "Tracker type must be SMALL or BIG for cats and SMALL, MEDIUM, or LARGE for dogs")
	private boolean isTrackerTypeValid() {
		return this.petType.getSupportedTrackers().contains(trackerType);
	}

	@AssertTrue(message = "Lost tracker attribute is only applicable for cats")
	private boolean isLostTrackerValid() {
		if (petType == PetType.CAT) {
			return lostTracker != null;
		} else if (petType == PetType.DOG) {
			return lostTracker == null;
		}
		return true;
	}

}
