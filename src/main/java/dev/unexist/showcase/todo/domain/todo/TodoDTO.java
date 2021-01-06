/**
 * @package Quarkus-DDD-Showcase
 *
 * @file TodoDTO class
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class TodoDTO {

    @NotBlank
    @Schema(type = SchemaType.STRING, example = "Title of a todo")
    private String title;

    @NotBlank
    @Schema(type = SchemaType.STRING, example = "Description of what to do")
    private String description;

    @NotBlank
    @Schema(type = SchemaType.STRING, example = TodoDTOAssembler.DATE_FORMAT)
    private String startDate;

    @NotBlank
    @Schema(type = SchemaType.STRING, example = TodoDTOAssembler.DATE_FORMAT)
    private String dueDate;

    @NotNull
    private Boolean done;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
