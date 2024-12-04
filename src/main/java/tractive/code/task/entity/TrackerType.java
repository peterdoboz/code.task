package tractive.code.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrackerType {
	S("Small"), M("Medium"), L("Large");

	private String label;
}
