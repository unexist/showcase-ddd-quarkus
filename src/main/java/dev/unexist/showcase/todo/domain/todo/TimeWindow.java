/**
 * @package Quarkus-DDD-Showcase
 *
 * @file TimeWindow value object
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.unexist.showcase.todo.infrastructure.serializer.DateSerializer;
import org.apache.commons.lang3.Validate;
import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.annotation.ValueObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@DomainLayer
@ValueObject
public class TimeWindow {
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate start;

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate due;

    /**
     * Constructor
     *
     * @param  start  Start date of the window
     * @param  due    End date of the window
     **/

    TimeWindow(LocalDate start, LocalDate due) {
        if (start.isBefore(due)) {
            throw new IllegalArgumentException("Start must be before due");
        }

        this.setStart(start);
        this.setDue(due);
    }

    /**
     * Get start date of the window
     *
     * @return Start date of the window
     **/

    LocalDate getStart() {
        return start;
    }

    /**
     * Set start date of the window
     *
     * @param  start  Start date of the window
     **/

    protected void setStart(LocalDate start) {
        this.start = Validate.notNull(start, "Start must be set");
    }

    /**
     * Get due date of the window
     *
     * @return Due date of the window
     **/

    LocalDate getDue() {
        return due;
    }

    /**
     * Set due date of the window
     *
     * @param  due  Due date of the window
     **/

    protected void setDue(LocalDate due) {
        this.due = Validate.notNull(due, "Due must be set");
    }

    @Override
    public int hashCode() {
        return this.start.hashCode() + this.due.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass() == this.getClass()
                && this.start.equals(((TimeWindow)obj).start)
                && this.due.equals(((TimeWindow)obj).due);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return String.format("%s - %s", sdf.format(this.start),
                sdf.format(this.due));
    }
}
