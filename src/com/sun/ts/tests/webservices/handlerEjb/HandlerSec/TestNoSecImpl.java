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

package com.sun.ts.tests.webservices.handlerEjb.HandlerSec;

import com.sun.ts.lib.util.*;
import com.sun.ts.lib.porting.*;
import com.sun.ts.lib.harness.*;
import com.sun.ts.tests.jaxrpc.common.*;
import com.sun.javatest.Status;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.xml.rpc.server.ServletEndpointContext;
import javax.xml.rpc.handler.MessageContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;

import java.util.*;

public class TestNoSecImpl implements TestNoSec {

  public void testingNoSec() throws RemoteException {
    ClassLoader bcl = this.getClass().getClassLoader();
    HandlerTracker.put("BeanClassLoader", bcl);
  }

  public String echoString(String parm) throws RemoteException {
    return parm;
  }

  public String badEchoString(String parm) throws RemoteException {
    return "FAIL";
  }

}
