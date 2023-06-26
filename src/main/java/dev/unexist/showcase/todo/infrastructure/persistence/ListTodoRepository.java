/**
 * @package Quarkus-Kubernetes-Showcase
 *
 * @file Todo repository
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.persistence;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;
import dev.unexist.showcase.todo.domain.todo.TodoRepository;
import org.jmolecules.architecture.layered.InfrastructureLayer;
import org.jmolecules.ddd.annotation.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@InfrastructureLayer
@Repository
public class ListTodoRepository implements TodoRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTodoRepository.class);

    private List<Todo> list;

    /**
     * Constructor
     **/

    ListTodoRepository() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean add(final Todo todo) {
        return this.list.add(todo);
    }

    @Override
    public boolean update(final Todo todo) {
        this.list = this.list.stream()
                .map(t -> t.getId().equals(todo.getId()) ? todo : t)
                .collect(Collectors.toList());

        return true;
    }

    @Override
    public boolean deleteById(TodoIdentifier id) {
        return this.list.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public List<Todo> getAll() {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public Optional<Todo> findById(TodoIdentifier id) {
        return this.list.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
}