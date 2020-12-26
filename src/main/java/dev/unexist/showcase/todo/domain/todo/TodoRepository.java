/**
 * @package Quarkus-DDD-Showcase
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

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

    public TodoId nextId() {
        return new TodoId(
                UUID.randomUUID().toString().toUpperCase());
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
        int idx = this.findIndexById(todo.getId());

        if (-1 != idx) {
            this.list.set(idx, todo);

            ret = true;
        }

        return ret;
    }

    /**
     * Delete {@link Todo} with given id
     *
     * @param id
     *          A {@TodoId} to delete
     * @return
     *          Either {@code true} on success; otherwise {@code false}
     **/

    public boolean deleteById(TodoId id) {
        boolean ret = false;
        int idx = this.findIndexById(id);

        if (-1 != idx) {
            this.list.remove(idx);

            ret = true;
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
     *          A {@TodoId} to find
     * @return
     *          A {@link Optional} with the result of the lookup
     **/

    public Optional<Todo> findById(TodoId id) {
        return this.list.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Find index of {@link Todo} by given id
     *
     * @param id
     *          A {@TodoId} to find
     * @return
     *          Either found index on success; otherwise {@code -1}
     **/

    private int findIndexById(TodoId id) {
        return IntStream.range(0, this.list.size())
                .filter(idx  -> id.equals(this.list.get(idx).getId()))
                .findFirst()
                .orElse(-1);
    }
}