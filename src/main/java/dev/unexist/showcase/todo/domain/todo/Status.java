/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Status enum
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.ValueObject;

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
