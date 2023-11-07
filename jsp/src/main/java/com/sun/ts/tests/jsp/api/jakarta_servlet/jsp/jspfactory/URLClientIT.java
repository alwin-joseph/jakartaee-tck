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

package com.sun.ts.tests.jsp.api.jakarta_servlet.jsp.jspfactory;


import com.sun.ts.tests.jsp.common.client.AbstractUrlClient;

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
    setContextRoot("/jsp_jspfactory_web");
    setTestJsp("JspFactoryTest");

    }

  @Deployment(testable = false)
  public static WebArchive createDeployment() throws IOException {

    String packagePath = URLClientIT.class.getPackageName().replace(".", "/");
    WebArchive archive = ShrinkWrap.create(WebArchive.class, "jsp_jspfactory_web.war");
    archive.addClasses(
            com.sun.ts.tests.jsp.common.util.JspTestUtil.class,
            com.sun.ts.tests.jsp.common.util.SimpleContext.class);
    archive.setWebXML(URLClientIT.class.getClassLoader().getResource(packagePath+"/jsp_jspfactory_web.xml"));
    archive.add(new UrlAsset(URLClientIT.class.getClassLoader().getResource(packagePath+"/JspFactoryTest.jsp")), "JspFactoryTest.jsp");


    return archive;
  }

  
  /* Run tests */

  // ============================================ Tests ======

  /*
   * @testName: jspFactoryGetDefaultFactoryTest
   * 
   * @assertion_ids: JSP:JAVADOC:121
   * 
   * @test_Strategy: Validate the behavior of JspFactory.getDefaultFactory().
   */
  @Test
  public void jspFactoryGetDefaultFactoryTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "jspFactoryGetDefaultFactoryTest");
    invoke();
  }

  /*
   * @testName: jspFactoryGetPageContextTest
   * 
   * @assertion_ids: JSP:JAVADOC:122
   * 
   * @test_Strategy: Validate the behavior of JspFactory.getPageContext().
   */
  @Test
  public void jspFactoryGetPageContextTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "jspFactoryGetPageContextTest");
    invoke();
  }

  /*
   * @testName: jspFactoryGetEngineInfoTest
   * 
   * @assertion_ids: JSP:JAVADOC:124
   * 
   * @test_Strategy: Validate the behavior of JspFactory.getEngineInfo().
   */
  @Test
  public void jspFactoryGetEngineInfoTest() throws Exception {
    TEST_PROPS.setProperty(APITEST, "jspFactoryGetEngineInfoTest");
    invoke();
  }
}
