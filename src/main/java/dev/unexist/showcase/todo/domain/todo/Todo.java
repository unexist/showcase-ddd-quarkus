/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo class and aggregate root
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.Aggregate;
import dev.unexist.showcase.todo.infrastructure.stereotypes.AggregateId;
import org.apache.commons.lang3.Validate;

@Aggregate
public class Todo {
    private TodoId id;
    private Description description;
    private TimeWindow timeWindow;
    private Status status;

    public Todo(final TodoId id) {
        this.setId(id);
    }

    @AggregateId
    public TodoId id() {
        return id;
    }

    protected void setId(TodoId id) {
        this.id = Validate.notNull(id, "Id ust be set");
    }

    Description getDescription() {
        return description;
    }

    void setDescription(Description description) {
        this.description = Validate.notNull(description,
            "Description must be set");
    }

    TimeWindow getTimeWindow() {
        return timeWindow;
    }

    void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = Validate.notNull(timeWindow,
                "Timewindow must be set");
    }

    Status getStatus() {
        return status;
    }

    void setStatus(Status status) {
        this.status = Validate.notNull(status,
                "Status must be set");
    }
}
