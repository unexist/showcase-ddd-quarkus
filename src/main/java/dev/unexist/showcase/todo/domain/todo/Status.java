/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Status enum
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.annotation.ValueObject;

@DomainLayer
@ValueObject
public enum Status {
    UNFINISHED, FINISHED;

    boolean isFinished() {
        return Status.FINISHED == this;
    }

    boolean isNotFinished() {
        return Status.UNFINISHED == this;
    }
}
