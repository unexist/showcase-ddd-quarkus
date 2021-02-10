/**
 * @package Quarkus-DDD-Showcase
 *
 * @file TimeWindow class
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.unexist.showcase.todo.infrastructure.serializer.DateSerializer;
import dev.unexist.showcase.todo.infrastructure.stereotypes.ValueObject;
import org.apache.commons.lang3.Validate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@ValueObject
public class TimeWindow {

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate start;

    @JsonSerialize(using = DateSerializer.class)
    private LocalDate due;

    TimeWindow(LocalDate start, LocalDate due) {
        if (start.isBefore(due)) {
            throw new IllegalArgumentException("Start must be before due");
        }

        this.setStart(start);
        this.setDue(due);
    }

    LocalDate getStart() {
        return start;
    }

    protected void setStart(LocalDate start) {
        this.start = Validate.notNull(start, "Start must be set");
    }

    LocalDate getDue() {
        return due;
    }

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
