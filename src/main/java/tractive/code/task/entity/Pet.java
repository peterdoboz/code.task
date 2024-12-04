package tractive.code.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "pettype")
	@Enumerated(EnumType.STRING)
	private PetType petType;

	@Column(name = "trackertype")
	@Enumerated(EnumType.STRING)
	private TrackerType trackerType;

	@Column(name = "ownerid")
	private Integer ownerId;

	@Column(name = "inzone")
	private boolean inZone;

}
