/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2002 International Business Machines Corp. All rights reserved.
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

package com.sun.ts.tests.webservices.handler.HandlerLifecycle;

import com.sun.ts.lib.util.*;
import com.sun.ts.lib.porting.*;
import com.sun.ts.lib.harness.*;
import com.sun.ts.tests.jaxrpc.common.*;
import com.sun.javatest.Status;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.xml.rpc.server.ServiceLifecycle;
import javax.xml.rpc.server.ServletEndpointContext;
import javax.xml.rpc.handler.MessageContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;

import java.util.*;
// Service Implementation Class - as outlined in JAX-RPC Specification

public class HelloImpl implements Hello, ServiceLifecycle {
  private boolean initCalled = false;

  private boolean destroyCalled = false;

  private Object context = null;

  private ServletEndpointContext servletEndpointContext = null;

  public void init(Object context) {
    System.out.println("init called ...");
    initCalled = true;
    System.out.println("initCalled = true");
    this.context = context;
    if (context instanceof ServletEndpointContext)
      servletEndpointContext = (ServletEndpointContext) context;
  }

  public void destroy() {
    System.out.println("destroy called ...");
    destroyCalled = true;
  }

  public String hello(String s) throws RemoteException {
    return "Hello, " + s + "!";
  }

  public String hi(String s) throws RemoteException {
    return "Hi, " + s + "!";
  }

  public String howdy(String s) throws RemoteException {
    return "Howdy, " + s + "!";
  }

  public String enventry(String s) throws RemoteException {
    return s;
  }

  public boolean wasInitCalled() throws RemoteException {
    return initCalled;
  }

  public boolean wasDestroyCalled() throws RemoteException {
    // Untestable so always return true;
    return true;
  }

  public boolean getHttpSessionTest() throws RemoteException {
    boolean pass = true;
    HttpSession v = null;
    try {
      if (servletEndpointContext != null) {
        v = servletEndpointContext.getHttpSession();
        System.out.println("HttpSession=" + v);
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      pass = false;
    }
    return pass;
  }

  public boolean getMessageContextTest() throws RemoteException {
    boolean pass = true;
    MessageContext v = null;
    System.out.println("getMessageContextTest called ... ");

    try {
      if (servletEndpointContext != null) {
        v = servletEndpointContext.getMessageContext();
        System.out.println("MessageContext=" + v);
        TestUtil.logMsg("MessageContext=" + v);
      } else {
        System.out.println("servletEndpointContext is null");
        TestUtil.logMsg("servletEndpointContext is null");
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      pass = false;
    }
    return pass;
  }

  public boolean getServletContextTest() throws RemoteException {
    boolean pass = true;
    ServletContext v = null;
    try {
      if (servletEndpointContext != null) {
        v = servletEndpointContext.getServletContext();
        System.out.println("ServletContext=" + v);
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      pass = false;
    }
    return pass;
  }

  public boolean getUserPrincipalTest() throws RemoteException {
    boolean pass = true;
    Principal v = null;
    try {
      if (servletEndpointContext != null) {
        v = servletEndpointContext.getUserPrincipal();
        System.out.println("UserPrincipal=" + v);
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
      pass = false;
    }
    return pass;
  }

}
