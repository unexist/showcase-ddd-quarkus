/**
 * @package Quarkus-jqAssistant-Showcase
 *
 * @file Stupid integration test
 * @copyright 2019 Christoph Kappel <unexist@subforge.org>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package dev.unexist.showcase.todo.application;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TodoResourceTest {

    @Test
    public void testTodoEndpoint() {
        given()
          .when().get("/todo")
          .then()
             .statusCode(204);
    }
}