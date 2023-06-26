/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo application
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.application;

import org.jmolecules.architecture.layered.ApplicationLayer;

import jakarta.ws.rs.core.Application;

@ApplicationLayer
public class RestApplication extends Application {
}