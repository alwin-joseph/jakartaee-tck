/*
 * Copyright (c) 2007, 2024 Oracle and/or its affiliates. All rights reserved.
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
 * $Id$
 */

package com.sun.ts.tests.common.vehicle.ejb;

import java.util.Properties;

import com.sun.ts.lib.harness.Status;
import com.sun.ts.lib.porting.TSLoginContext;
import com.sun.ts.lib.util.TSNamingContext;
import com.sun.ts.lib.util.TestUtil;
import com.sun.ts.tests.common.vehicle.VehicleRunnable;

public class EJBVehicleRunner implements VehicleRunnable {
    public Status run(String[] argv, Properties p) {

        Status sTestStatus = Status.passed("");
        String username = TestUtil.getProperty(p, "user");
        String password = TestUtil.getProperty(p, "password");

        String isSecuredEjbClientValue = TestUtil.getProperty(p, "secured.ejb.vehicle.client");
        boolean isSecuredEjbClient = (isSecuredEjbClientValue != null);
        TestUtil.logTrace("%%%%%%% isSecuredEjbClient = " + isSecuredEjbClient);

        if (isSecuredEjbClient) {
            try {
                TestUtil.logTrace("Test login in appclient for user " + username + " password " + password);
                TSLoginContext loginContext = new TSLoginContext();
                loginContext.login(username, password);
            } catch (Exception e) {
                TestUtil.logErr("login failed", e);
                sTestStatus = Status.failed("Test login in appclient failed for user " + username + " password " + password);
            }
        }

        String sEJBVehicleJndiName = "";
        EJBVehicleRemote ref = null;
        try {
            TSNamingContext jc = new TSNamingContext();
            sEJBVehicleJndiName = "java:comp/env/ejb/EJBVehicle";
            ref = (EJBVehicleRemote) jc.lookup(sEJBVehicleJndiName, EJBVehicleRemote.class);
            ref.initialize(argv, p);
            TestUtil.logTrace("in ejbvehicle: initialize ok; call runTest()");
            sTestStatus = (ref.runTest()).toStatus();
        } catch (Exception e) {
            TestUtil.logErr("Test failed", e);
            sTestStatus = Status.failed("Test run in ejb vehicle failed");
        }
        return sTestStatus;
    }
}
