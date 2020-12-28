/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo DTO assembler
 * @copyright 2020 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import org.apache.commons.lang3.BooleanUtils;

public class TodoDTOAssembler {
    /**
     * Create {@link TodoDTO} from given {@link Todo}
     *
     * @param todo
     *          A source {@ink Todo}
     * @return
     *          Newly created {@link TodoDTO}
     **/

    public static TodoDTO fromTodoToDto(final Todo todo) {
        TodoDTO todoDto = new TodoDTO();

        todoDto.setTitle(todo.getDescription().getTitle());
        todoDto.setDescription(todo.getDescription().getFull());

        todoDto.setStart(todo.getTimeWindow().getStart());
        todoDto.setDue(todo.getTimeWindow().getDue());

        todoDto.setDone(Status.FINISHED.equals(todo.getStatus()));

        return todoDto;
    }

    /**
     * Update given {@link Todo} with data from {@link TodoDTO}
     *
     * @param todo
     *          A {@link Todo} to update
     * @param todoDto
     *          A source {@link TodoDTO}
     **/

    public static void updateTodoFromDto(final Todo todo, final TodoDTO todoDto) {
        todo.setDescription(
                new Description(todoDto.getTitle(), todoDto.getDescription()));
        todo.setTimeWindow(
                new TimeWindow(todoDto.getStart(), todoDto.getDue()));
        todo.setStatus(BooleanUtils.isTrue(todoDto.getDone()) ?
            Status.FINISHED : Status.UNFINISHED);
    }
}