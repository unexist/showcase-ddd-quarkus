/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo DTO assembler
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.apache.commons.lang3.BooleanUtils;
import org.jmolecules.architecture.layered.DomainLayer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DomainLayer
public class TodoDTOAssembler {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Create {@link TodoDTO} from given {@link Todo}
     *
     * @param  todo  source {@ink Todo}
     *
     * @return Newly created {@link TodoDTO}
     **/

    public static TodoDTO fromTodoToDto(final Todo todo) {
        TodoDTO todoDto = new TodoDTO();

        todoDto.setTitle(todo.getDescription().getTitle());
        todoDto.setDescription(todo.getDescription().getFull());

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        todoDto.setStartDate(sdf.format(todo.getTimeWindow().getStart()));
        todoDto.setDueDate(sdf.format(todo.getTimeWindow().getDue()));

        todoDto.setDone(Status.FINISHED.equals(todo.getStatus()));

        return todoDto;
    }

    /**
     * Update given {@link Todo} with data from {@link TodoDTO}
     *
     * @param  todo     A {@link Todo} to update
     * @param  todoDto  A source {@link TodoDTO}
     **/

    public static void updateTodoFromDto(final Todo todo, final TodoDTO todoDto) {
        todo.setDescription(new Description(todoDto.getTitle(),
                todoDto.getDescription()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);

        todo.setTimeWindow(new TimeWindow(LocalDate.parse(todoDto.getStartDate(), dtf),
                        LocalDate.parse(todoDto.getDueDate(), dtf)));
        todo.setStatus(BooleanUtils.isTrue(todoDto.getDone())
                ? Status.FINISHED
                : Status.UNFINISHED);
    }
}