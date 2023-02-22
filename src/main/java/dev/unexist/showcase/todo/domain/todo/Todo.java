/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo class and aggregate root
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.apache.commons.lang3.Validate;
import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifiable;

@DomainLayer
public class Todo implements AggregateRoot<Todo, TodoIdentifier>, Identifiable<TodoIdentifier> {
    private TodoIdentifier id;
    private Description description;
    private TimeWindow timeWindow;
    private Status status;

    /**
     * Constructor
     *
     * @param  id  Id of the entry
     **/

    public Todo(final TodoIdentifier id) {
        this.setId(id);
    }

    @Override
    public TodoIdentifier getId() {
        return this.id;
    }

    /**
     * Set id of the entry
     *
     * @param  id  Id of the entry
     **/

    protected void setId(TodoIdentifier id) {
        this.id = Validate.notNull(id, "Id ust be set");
    }

    /**
     * Get description of the entry
     *
     * @return Get description of the entry
     **/

    Description getDescription() {
        return description;
    }

    /**
     * Set descroption of the entry
     *
     * @param  description  Description of the entry
     **/

    void setDescription(Description description) {
        this.description = Validate.notNull(description,
            "Description must be set");
    }

    /**
     * Get time window of the entry
     *
     * @return Time window of the entry
     **/

    TimeWindow getTimeWindow() {
        return timeWindow;
    }

    /**
     * Set time window of the entry
     *
     * @param  timeWindow  Time window of the entry
     **/

    void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = Validate.notNull(timeWindow,
                "TimeWindow must be set");
    }

    /**
     * Get status of the entry
     *
     * @return Status of the entry
     **/

    Status getStatus() {
        return status;
    }

    /**
     * Set status of the entry
     *
     * @param  status  Status of the entry
     **/

    void setStatus(Status status) {
        this.status = Validate.notNull(status,
                "Status must be set");
    }
}
