/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo resource
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapter;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoDTO;
import dev.unexist.showcase.todo.domain.todo.TodoDTOAssembler;
import dev.unexist.showcase.todo.domain.todo.TodoIdentifier;
import dev.unexist.showcase.todo.domain.todo.TodoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jmolecules.architecture.layered.InterfaceLayer;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
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
    public Response create(@Valid final TodoDTO todoDto, @Context UriInfo uriInfo) {
        Response.ResponseBuilder response;

        Optional<TodoIdentifier> id = this.todoService.create(todoDto);

        if (id.isPresent()) {
            URI uri = uriInfo.getAbsolutePathBuilder()
                    .path(id.toString())
                    .build();

            response = Response.created(uri);
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
        Optional<Todo> result = this.todoService.findById(new TodoIdentifier(id));

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

        if (this.todoService.update(new TodoIdentifier(id), todoDto)) {
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

        if (this.todoService.deleteById(new TodoIdentifier(id))) {
            response = Response.noContent();
        } else {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.build();
    }
}