/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Description value object
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.annotation.ValueObject;
import org.apache.commons.lang3.Validate;

@DomainLayer
@ValueObject
public class Description {
    private String title;
    private String full;

    /**
     * Constructor
     *
     * @param  title  Title of the description
     * @param  full   Full text of the description
     **/

    Description(final String title, final String full) {
        this.setTitle(title);
        this.setFull(full);
    }

    /**
     * Get title of the description
     *
     * @return Title of the description
     **/

    String getTitle() {
        return title;
    }

    /**
     * Set title of the description
     *
     * @param  title  Title of the description
     **/

    protected void setTitle(String title) {
        this.title = Validate.notNull(title,
                "Title must be set");
    }

    /**
     * Get full text of the description
     *
     * @return Full text of the description
     **/

    String getFull() {
        return full;
    }

    /**
     * Set full text of the description
     *
     * @param  full  Full text of the description
     **/

    protected void setFull(String full) {
        this.full = Validate.notNull(full,
                "Full must be set");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass() == this.getClass()
                && this.title.equals(((Description)obj).title)
                && this.full.equals(((Description)obj).full);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.title, this.full);
    }
}
