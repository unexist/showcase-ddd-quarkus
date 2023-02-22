/**
 * @package Quarkus-DDD-Showcase
 *
 * @file TodoIdentifier value object
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Locale;
import java.util.UUID;

@DomainLayer
@ValueObject
public class TodoIdentifier implements Identifier {
    private String id;

    /**
     * Constructor
     *
     * @param  id  Id for the entry
     **/

    public TodoIdentifier(final String id) {
        this.setId(id);
    }

    /**
     * Set id of entry
     *
     * @param  id  Id for the entry
     **/

    protected void setId(final String id) {
        if (null == id) {
            throw new IllegalArgumentException("Missing id");
        }

        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj.getClass() == this.getClass()
                && this.id.equals(((TodoIdentifier)obj).id);
    }

    @Override
    public String toString() {
        return String.format("%s", this.id);
    }

    /**
     * Create a new {@link TodoIdentifier}
     *
     * @return New {@link TodoIdentifier}
     **/

    public static TodoIdentifier nextId() {
        return new TodoIdentifier(
                UUID.randomUUID().toString().toUpperCase(Locale.getDefault()));
    }
}
