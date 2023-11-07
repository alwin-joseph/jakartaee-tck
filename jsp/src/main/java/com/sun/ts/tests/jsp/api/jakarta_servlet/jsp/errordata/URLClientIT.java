/*
 * Copyright (c) 2007, 2023 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.ts.tests.jsp.api.jakarta_servlet.jsp.errordata;


import com.sun.ts.tests.jsp.common.client.AbstractUrlClient;
import com.sun.ts.tests.jsp.common.util.JspTestUtil;

import java.io.IOException;
import java.io.InputStream;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jboss.shrinkwrap.api.asset.UrlAsset;


@ExtendWith(ArquillianExtension.class)
public class URLClientIT extends AbstractUrlClient {




  public URLClientIT() throws Exception {
    setup();
    setContextRoot("/jsp_errordata_web");
    setTestJsp("ErrorDataTest");
    System.out.println("Running TESTS");

    }

  @Deployment(testable = false)
  public static WebArchive createDeployment() throws IOException {

    String packagePath = URLClientIT.class.getPackageName().replace(".", "/");
    WebArchive archive = ShrinkWrap.create(WebArchive.class, "jsp_errordata_web.war");
    archive.addClasses(
            JspTestUtil.class);
    archive.setWebXML(URLClientIT.class.getClassLoader().getResource(packagePath+"/jsp_errordata_web.xml"));
    archive.add(new UrlAsset(URLClientIT.class.getClassLoader().getResource(packagePath+"/ErrorDataTest.jsp")), "ErrorDataTest.jsp");


    return archive;
  }

  
  /* Run tests */

  // ============================================ Tests ======

  /*
   * @testName: errorDataConstructorTest
   * 
   * @assertion_ids: JSP:JAVADOC:1
   * 
   * @test_Strategy: Validate proper construction of ErrorData object directly
   * via API.
   */
  @Test
  public void errorDataConstructorTest() throws Exception {
    System.out.println("In test method");
    TEST_PROPS.setProperty(APITEST, "errorDataConstructorTest");
    invoke();
  }

  /*
   * @testName: errorDataGetThrowableTest
   * 
   * @assertion_ids: JSP:JAVADOC:2
   * 
   * @test_Strategy: Validate behavior of ErrorData.getThrowable().
   */
  @Test
  public void errorDataGetThrowableTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "errorDataGetThrowableTest");
    invoke();
  }

  /*
   * @testName: errorDataGetStatusCodeTest
   * 
   * @assertion_ids: JSP:JAVADOC:3
   * 
   * @test_Strategy: Validate behavior of ErrorData.getStatusCode().
   */
  @Test
  public void errorDataGetStatusCodeTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "errorDataGetStatusCodeTest");
    invoke();
  }

  /*
   * @testName: errorDataGetRequestURITest
   * 
   * @assertion_ids: JSP:JAVADOC:4
   * 
   * @test_Strategy: Validate behavior of ErrorData.getRequestURI()
   */
  @Test
  public void errorDataGetRequestURITest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "errorDataGetRequestURITest");
    invoke();
  }

  /*
   * @testName: errorDataGetServletNameTest
   * 
   * @assertion_ids: JSP:JAVADOC:5
   * 
   * @test_Strategy: Validate behavior of ErrorData.getServletName().
   */
  @Test
  public void errorDataGetServletNameTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "errorDataGetServletNameTest");
    invoke();
  }
}
