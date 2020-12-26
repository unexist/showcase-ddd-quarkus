/**
 * @package Quarkus-Kind-MP-Showcase
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

@Aggregate
public class Todo extends TodoBase {
    private int id;

    public Todo() {
    }

    public Todo(final TodoBase base) {
        this.update(base);
    }

    public void update(final TodoBase base) {
        this.setTimeWindow(base.getTimeWindow());
        this.setTitle(base.getTitle());
        this.setDescription(base.getDescription());
        this.setDone(base.getDone());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
