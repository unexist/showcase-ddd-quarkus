/**
 * @package Quarkus-Kind-MP-Showcase
 *
 * @file Todo repository
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import dev.unexist.showcase.todo.infrastructure.stereotypes.Repository;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@ApplicationScoped
public class TodoRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRepository.class);

    private final List<Todo> list;

    /**
     * Constructor
     **/

    TodoRepository() {
        this.list = new ArrayList<>();
    }

    /**
     * Add {@link Todo} entry to list
     *
     * @param todo
     *          {@link Todo} entry to add
     * @return
     *          Either {@code true} on success; otherwise {@code false}
     **/

    public boolean add(final Todo todo) {
        todo.setId(this.list.size() + 1);

        return this.list.add(todo);
    }

    /**
     * Update {@link Todo} with given id
     *
     * @param todo
     *          A {@link Todo} to update
     * @return
     *          Either {@code true} on success; otherwise {@code false}
     **/

    public boolean update(final Todo todo) {
        boolean ret = false;

        try {
            this.list.set(todo.getId(), todo);

            ret = true;
        } catch (IndexOutOfBoundsException e) {
            LOGGER.warn("update: id={} not found", todo.getId());
        }

        return ret;
    }

    /**
     * Delete {@link Todo} with given id
     *
     * @param id
     *          A id to delete
     * @return
     *          Either {@code true} on success; otherwise {@code false}
     **/

    public boolean deleteById(int id) {
        boolean ret = false;

        try {
            this.list.remove(id);

            ret = true;
        } catch (IndexOutOfBoundsException e) {
            LOGGER.warn("update: id={} not found", id);
        }

        return ret;
    }

    /**
     * Get all {@link Todo} entries
     *
     * @return List of all stored {@link Todo}
     **/

    public List<Todo> getAll() {
        return Collections.unmodifiableList(this.list);
    }

    /**
     * Find {@link Todo} by given id
     *
     * @param id
     *          Id to find
     * @return
     *          A {@link Optional} with the result of the lookup
     **/

    public Optional<Todo> findById(int id) {
        return this.list.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Gauge(name = "numTodos", unit = MetricUnits.NONE, description = "Number of todos")
    public int numTodos() {
        return this.list.size();
    }
}