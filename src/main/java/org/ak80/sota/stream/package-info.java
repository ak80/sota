/**
 * A very simple and basic implementation of streams, providing
 * some of the functions of stream from Java 8.
 * <p>
 * Many methods are missing and some additional are provided: this
 * is not intended as a full replacement. When a method exists with
 * the same name as provided by Java 8 streams, then it has the
 * same signature and aims to be compatible.
 * <p>
 * There is no special optimization besides that the streams are
 * lazy fetching. Parallelism is not supported
 * <p>
 * Please also note that Java 8 lambdas are needed or a retrofitting
 * like RetroLambda
 * <p>
 * This project was useful as an educational tool, and is used e.g.
 * in Android Projects (with RetroLambda) or GWT 2.8.x projects.
 * <p>
 * For a full backport see http://sourceforge.net/projects/streamsupport/
 */
package org.ak80.sota.stream;