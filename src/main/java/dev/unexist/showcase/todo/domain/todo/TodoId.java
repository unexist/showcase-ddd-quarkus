/**
 * @package Quarkus-DDD-Showcase
 *
 * @file DueDate class
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;
import org.jmolecules.ddd.annotation.ValueObject;

@DomainLayer
@ValueObject
public class TodoId {
    private String id;

    public TodoId(final String id) {
        this.setId(id);
    }

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
                && this.id.equals(((TodoId)obj).id);
    }

    @Override
    public String toString() {
        return String.format("%s", this.id);
    }
}
