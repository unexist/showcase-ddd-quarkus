/**
 * @package Quarkus-Kubernetes-Showcase
 *
 * @file Todo repository
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv3.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.persistence;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoId;
import dev.unexist.showcase.todo.domain.todo.TodoRepository;
import org.jmolecules.architecture.layered.InfrastructureLayer;
import org.jmolecules.ddd.annotation.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@InfrastructureLayer
@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    private List<Todo> list;

    /**
     * Constructor
     **/

    TodoRepositoryImpl() {
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
        this.list = this.list.stream()
                .map(t -> t.id().equals(todo.id()) ? todo : t)
                .collect(Collectors.toList());

        return true;
    }

    /**
     * Delete {@link Todo} with given id
     *
     * @param id
     *          Id to delete
     * @return
     *          Either {@code true} on success; otherwise {@code false}
     **/

    public boolean deleteById(TodoId id) {
        return this.list.removeIf(t -> t.id().equals(id));
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

    public Optional<Todo> findById(TodoId id) {
        return this.list.stream()
                .filter(t -> t.id().equals(id))
                .findFirst();
    }
}