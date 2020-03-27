/*
 * Copyright (c) 2007, 2018, 2020 Oracle and/or its affiliates. All rights reserved.
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

/*
 * @(#)TestBeanEJB.java	1.26 03/05/16
 */

package com.sun.ts.tests.ejb.ee.bb.entity.cmp.nonreentranttest;

import com.sun.ts.lib.util.*;
import com.sun.ts.lib.porting.*;

import java.util.*;
import javax.naming.*;
import jakarta.ejb.*;
import java.rmi.*;
import java.sql.*;

public class TestBeanEJB implements EntityBean {
  private EntityContext ectx = null;

  private TSNamingContext nctx = null;

  private static final String beanName = "java:comp/env/ejb/LoopBackBean";

  // Entity instance data
  public String BRAND_NAME;

  public Integer KEY_ID;

  public float PRICE;

  public Integer ejbCreate(Properties p, int KEY_ID, String BRAND_NAME,
      float PRICE) throws CreateException {
    TestUtil.logTrace("ejbCreate");
    try {
      TestUtil.logMsg("Initialize remote logging");
      TestUtil.init(p);
      this.KEY_ID = new Integer(KEY_ID);
      this.BRAND_NAME = BRAND_NAME;
      this.PRICE = PRICE;
    } catch (RemoteLoggingInitException e) {
      TestUtil.printStackTrace(e);
      throw new CreateException(e.getMessage());
    } catch (Exception e) {
      TestUtil.printStackTrace(e);
      throw new CreateException("Exception occurred: " + e);
    }
    return this.KEY_ID;
  }

  public void ejbPostCreate(Properties p, int KEY_ID, String BRAND_NAME,
      float PRICE) {
    TestUtil.logTrace("ejbPostCreate");
  }

  public void setEntityContext(EntityContext c) {
    TestUtil.logTrace("setEntityContext");
    ectx = c;
    try {
      TestUtil.logMsg("Obtain naming context");
      nctx = new TSNamingContext();
    } catch (NamingException e) {
      TestUtil.logErr("NamingException ... " + e, e);
      throw new EJBException("unable to obtain naming context");
    } catch (Exception e) {
      TestUtil.logErr("Exception ... " + e, e);
      throw new EJBException("Exception occurred: " + e);
    }
  }

  public void unsetEntityContext() {
    TestUtil.logTrace("unsetEntityContext");
  }

  public void ejbRemove() throws RemoveException {
    TestUtil.logTrace("ejbRemove");
  }

  public void ejbActivate() {
    TestUtil.logTrace("ejbActivate");
  }

  public void ejbPassivate() {
    TestUtil.logTrace("ejbPassivate");
  }

  public void ejbLoad() {
    TestUtil.logTrace("ejbLoad");
  }

  public void ejbStore() {
    TestUtil.logTrace("ejbStore");
  }

  // ===========================================================
  // TestBean interface (our business methods)

  public void ping() {
    TestUtil.logTrace("ping");
  }

  public void sleep(int n) {
    TestUtil.logTrace("sleep");
    long t1, t2;
    t1 = System.currentTimeMillis();
    while ((t2 = System.currentTimeMillis()) < (t1 + n))
      ;
  }

  public boolean loopBackSameBean() {
    TestUtil.logTrace("loopBackSameBean");

    boolean pass;

    TestUtil.logMsg("Perform loopback test");
    try {
      TestUtil.logMsg("getPrimaryKey() object");
      Object o = ectx.getPrimaryKey();
      TestUtil.logMsg("getEJBObject() reference");
      TestBean ref = (TestBean) ectx.getEJBObject();
      TestUtil.logMsg("Performing self-referential loopback call test");
      ref.ping();
      TestUtil.logErr("No exception occurred during loopback call");
      pass = false;
    } catch (RemoteException e) {
      TestUtil.logMsg("Caught RemoteException as expected: " + e);
      pass = true;
    } catch (Exception e) {
      TestUtil.logErr("Caught unexpected Exception: " + e);
      TestUtil.printStackTrace(e);
      pass = false;
    }
    return pass;
  }

  public boolean loopBackAnotherBean(Properties p) {
    TestUtil.logTrace("loopBackAnotherBean");

    boolean pass;

    try {
      // lookup EJB home
      TestUtil.logMsg("Obtain naming context");
      nctx = new TSNamingContext();
      TestUtil.logMsg("Lookup home interface for EJB: " + beanName);
      LoopBackHome beanHome = (LoopBackHome) nctx.lookup(beanName,
          LoopBackHome.class);
      // create EJB instance
      TestUtil.logMsg("Create EJB instance");
      LoopBack beanRef = (LoopBack) beanHome.create();
      TestUtil.logMsg("Initialize remote logging");
      beanRef.initLogging(p);
      TestUtil.logMsg("Set bean reference");
      beanRef.setBeanRef((TestBean) ectx.getEJBObject());
      TestUtil.logMsg("Performing loopback call test");
      pass = beanRef.loopBackTest();
    } catch (Exception e) {
      TestUtil.logErr("Caught exception: " + e.getMessage());
      TestUtil.printStackTrace(e);
      pass = false;
    }
    return pass;
  }
}
