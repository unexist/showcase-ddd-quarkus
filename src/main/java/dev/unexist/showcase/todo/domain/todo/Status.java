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

    /**
     * Check whether status is {@code FINISHED}
     *
     * @return
     *          Either {@code true} when the status is {@code FINISHED}; otherwise {@code false}
     **/

    boolean isFinished() {
        return Status.FINISHED == this;
    }

    /**
     * Check whether status is {@code UNFINISHED}
     *
     * @return
     *          Either {@code true} when the status is {@code UNFINISHED}; otherwise {@code false}
     **/

    boolean isNotFinished() {
        return Status.UNFINISHED == this;
    }
}
