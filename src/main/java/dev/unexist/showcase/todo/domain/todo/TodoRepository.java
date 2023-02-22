/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo repository interface
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;

import java.util.List;
import java.util.Optional;

@DomainLayer
public interface TodoRepository {

    /**
     * Add {@link Todo} entry to list
     *
     * @param  todo  {@link Todo} entry to add
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    boolean add(Todo todo);

    /**
     * Update {@link Todo} with given id
     *
     * @param  todo  A {@link Todo} to update
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    boolean update(Todo todo);

    /**
     * Delete {@link Todo} with given id
     *
     * @param  id  Id to delete
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    boolean deleteById(TodoIdentifier id);

    /**
     * Get all {@link Todo} entries
     *
     * @return List of all stored {@link Todo}
     **/

    List<Todo> getAll();

    /**
     * Find {@link Todo} by given id
     *
     * @param  id  Id to find
     *
     * @return A {@link Optional} with the result of the lookup
     **/

    Optional<Todo> findById(TodoIdentifier id);
}
