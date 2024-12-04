package tractive.code.task.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Dog extends Pet {

}
