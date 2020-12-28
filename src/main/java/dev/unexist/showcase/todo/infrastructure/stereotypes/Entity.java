package dev.unexist.showcase.todo.infrastructure.stereotypes;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Represents an entity.
 */
@Target(TYPE)
@Documented
public @interface Entity {

}
