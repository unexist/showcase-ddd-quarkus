/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo resource
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.boundary;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoDTO;
import dev.unexist.showcase.todo.domain.todo.TodoDTOAssembler;
import dev.unexist.showcase.todo.domain.todo.TodoId;
import dev.unexist.showcase.todo.domain.todo.TodoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jmolecules.architecture.layered.InterfaceLayer;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@InterfaceLayer
@Path("/todo")
public class TodoResource {

    @Inject
    TodoService todoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create new todo")
    @Tag(name = "Todo")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Todo created"),
            @APIResponse(responseCode = "406", description = "Bad data"),
            @APIResponse(responseCode = "500", description = "Server error")
    })
    public Response create(@Valid final TodoDTO todoDto) {
        Response.ResponseBuilder response;

        if (this.todoService.create(todoDto)) {
            response = Response.status(Response.Status.CREATED);
        } else {
            response = Response.status(Response.Status.NOT_ACCEPTABLE);
        }

        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all todos")
    @Tag(name = "Todo")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "List of todo", content =
                @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = TodoDTO.class))),
            @APIResponse(responseCode = "204", description = "Nothing found"),
            @APIResponse(responseCode = "500", description = "Server error")
    })
    public Response getAll() {
        List<Todo> todoList = this.todoService.getAll();

        Response.ResponseBuilder response;

        if (todoList.isEmpty()) {
            response = Response.noContent();
        } else {
            List<TodoDTO> outList = todoList.stream()
                    .map(TodoDTOAssembler::fromTodoToDto)
                    .collect(Collectors.toList());

            response = Response.ok(Entity.json(outList));
        }

        return response.build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get todo by id")
    @Tag(name = "Todo")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Todo found", content =
                @Content(schema = @Schema(implementation = TodoDTO.class))),
            @APIResponse(responseCode = "404", description = "Todo not found"),
            @APIResponse(responseCode = "500", description = "Server error")
    })
    public Response getById(@PathParam("id") final String id) {
        Optional<Todo> result = this.todoService.findById(new TodoId(id));

        Response.ResponseBuilder response;

        if (result.isPresent()) {
            TodoDTO todoDto = TodoDTOAssembler.fromTodoToDto(result.get());

            response = Response.ok(Entity.json(todoDto));
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update todo by id")
    @Tag(name = "Todo")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Todo updated"),
            @APIResponse(responseCode = "404", description = "Todo not found"),
            @APIResponse(responseCode = "500", description = "Server error")
    })
    public Response updateById(@PathParam("id") final String id, @Valid final TodoDTO todoDto) {
        Response.ResponseBuilder response;

        if (this.todoService.update(new TodoId(id), todoDto)) {
            response = Response.noContent();
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete todo by id")
    @Tag(name = "Todo")
    public Response delete(@PathParam("id") final String id) {
        Response.ResponseBuilder response;

        if (this.todoService.deleteById(new TodoId(id))) {
            response = Response.noContent();
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.build();
    }
}