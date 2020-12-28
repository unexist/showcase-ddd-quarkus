/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Description class
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.ValueObject;
import org.apache.commons.lang3.Validate;

@ValueObject
public class Description {
    private String title;
    private String full;

    Description(final String title, final String full) {
        this.setTitle(title);
        this.setFull(full);
    }

    String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = Validate.notNull(title,
                "Title must be set");
    }

    String getFull() {
        return full;
    }

    protected void setFull(String full) {
        this.full = Validate.notNull(full,
                "Full must be set");
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass() == this.getClass() &&
                this.title == ((Description)obj).title &&
                this.full == ((Description)obj).full;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.title, this.full);
    }
}