/**
 * @package Quarkus-DDD-Showcase
 *
 * @file Todo application
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.application;

import org.jmolecules.architecture.layered.ApplicationLayer;

import javax.ws.rs.core.Application;

@ApplicationLayer
public class RestApplication extends Application {
}