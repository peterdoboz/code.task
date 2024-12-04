package tractive.code.task.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetType {
	CAT(List.of(TrackerType.S, TrackerType.L)), DOG(List.of(TrackerType.values()));

	private List<TrackerType> supportedTrackers;

}
