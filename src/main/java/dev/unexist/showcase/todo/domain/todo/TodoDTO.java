/**
 * @package Quarkus-DDD-Showcase
 *
 * @file TodoDTO class
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import io.smallrye.common.constraint.NotNull;

public class TodoDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String start;

    @NotNull
    private String due;

    @NotNull
    private Boolean done;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(final String start) {
        this.start = start;
    }

    public String getDue() {
        return due;
    }

    public void setDue(final String due) {
        this.due = due;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
