/*
 * Copyright (c) 2012, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.ts.tests.jaxrs.common;

import jakarta.ws.rs.Path;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * This class is a superclass used in MessageBodyWriters which need to check
 *
 * @Path annotation value
 */
public abstract class AbstractMessageBodyRW {

    public static String getPathValue(Annotation[] annotations) {
        return getSpecifiedAnnotationValue(annotations, Path.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T getSpecifiedAnnotation(Annotation[] annotations, Class<T> clazz) {
        T t = null;
        for (Annotation a : annotations) {
            if (a.annotationType() == clazz) {
                t = (T) a;
            }
        }
        return t != null ? t : null;
    }

    public static <T extends Annotation> String getSpecifiedAnnotationValue(Annotation[] annotations, Class<T> clazz) {
        T t = getSpecifiedAnnotation(annotations, clazz);
        try {
            Method m = clazz.getMethod("value");
            return (String) m.invoke(t);
        } catch (Exception e) {
            return null;
        }
    }

}
