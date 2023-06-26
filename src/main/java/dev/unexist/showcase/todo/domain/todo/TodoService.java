/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo domain service
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.jmolecules.architecture.layered.DomainLayer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@DomainLayer
@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    /**
     * Create new {@link Todo} entry and store it in repository
     *
     * @param  todoDto  A {@link TodoDTO} entry
     *
     * @return Either an Optional of {@link TodoIdentifier}
     **/

    public Optional<TodoIdentifier> create(final TodoDTO todoDto) {
        Todo todo = new Todo(TodoIdentifier.nextId());

        TodoDTOAssembler.updateTodoFromDto(todo, todoDto);

        return Optional.ofNullable(this.todoRepository.add(todo) ? todo.getId() : null);
    }

    /**
     * Update {@link Todo} at with given id
     *
     * @param  id       Id to update
     * @param  todoDto  Values for the entry
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    public boolean update(final TodoIdentifier id, final TodoDTO todoDto) {
        Optional<Todo> todo = this.findById(id);
        boolean ret = false;

        if (todo.isPresent()) {
            TodoDTOAssembler.updateTodoFromDto(todo.get(), todoDto);

            ret = this.todoRepository.update(todo.get());
        }

        return ret;
    }

    /**
     * Delete {@link Todo} with given id
     *
     * @param  id  Id to delete
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    public boolean deleteById(final TodoIdentifier id) {
        return this.todoRepository.deleteById(id);
    }

    /**
     * Get all {@link Todo} entries
     *
     * @return List of all {@link Todo}; might be empty
     **/

    public List<Todo> getAll() {
        return this.todoRepository.getAll();
    }

    /**
     * Find {@link Todo} by given id
     *
     * @param  id  Id to look for
     *
     * @return A {@link Optional} of the entry
     **/

    public Optional<Todo> findById(final TodoIdentifier id) {
        return this.todoRepository.findById(id);
    }
}
