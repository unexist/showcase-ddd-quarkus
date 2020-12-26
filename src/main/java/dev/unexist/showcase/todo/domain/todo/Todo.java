/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo class and aggregate root
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.Aggregate;
import dev.unexist.showcase.todo.infrastructure.stereotypes.AggregateId;

@Aggregate
public class Todo extends TodoBase {
    private TodoId id;

    public Todo(final TodoId id, final TodoBase base) {
        this.setId(id);
        this.update(base);
    }

    public void update(final TodoBase base) {
        this.setTimeWindow(base.getTimeWindow());
        this.setTitle(base.getTitle());
        this.setDescription(base.getDescription());
        this.setDone(base.getDone());
    }

    @AggregateId
    public TodoId getId() {
        return id;
    }

    protected void setId(final TodoId id) {
        if (null != id) {
            throw new IllegalArgumentException("Missing id");
        }

        this.id = id;
    }
}
