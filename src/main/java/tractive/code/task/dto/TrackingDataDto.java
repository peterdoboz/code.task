package tractive.code.task.dto;

import tractive.code.task.entity.PetType;
import tractive.code.task.entity.TrackerType;

public record TrackingDataDto(PetType petType, TrackerDataDto trackers) {

	public record TrackerDataDto(TrackerType trackerType, int count) {
	}

}
