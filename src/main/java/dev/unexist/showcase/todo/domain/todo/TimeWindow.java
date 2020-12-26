/**
 * @package Quarkus-Kind-MP-Showcase
 *
 * @file DueDate class
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.ValueObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@ValueObject
public class TimeWindow {
    private LocalDate start;
    private LocalDate due;

    TimeWindow(LocalDate start, LocalDate due) {
        if (start.isBefore(due)) {
            throw new IllegalArgumentException("Start must be before due");
        };

        this.setStart(start);
        this.setDue(due);
    }

    protected void setStart(LocalDate start) {
        if (null == start) {
            throw new IllegalArgumentException("Missing start date");
        }

        this.start = start;
    }

    protected void setDue(LocalDate due) {
        if (null == due) {
            throw new IllegalArgumentException("Missing due date");
        }

        this.due = due;
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass() == this.getClass() &&
                this.start == ((TimeWindow)obj).start &&
                this.due == ((TimeWindow)obj).due;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return String.format("%s - %s",
                sdf.format(this.start),
                sdf.format(this.due));

    }
}
