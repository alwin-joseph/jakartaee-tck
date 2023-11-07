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

package com.sun.ts.tests.jsp.spec.core_syntax.directives.include;


import java.io.IOException;
import java.io.InputStream;

import com.sun.ts.tests.jsp.common.client.AbstractUrlClient;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jboss.shrinkwrap.api.asset.UrlAsset;


@ExtendWith(ArquillianExtension.class)
public class URLClientIT extends AbstractUrlClient {



  public static String packagePath = URLClientIT.class.getPackageName().replace(".", "/");

  public URLClientIT() throws Exception {
    setup();

    setGeneralURI("/jsp/spec/core_syntax/directives/include");
    setContextRoot("/jsp_coresyntx_direct_include_web");

  }

  @Deployment(testable = false)
  public static WebArchive createDeployment() throws IOException {
    
    WebArchive archive = ShrinkWrap.create(WebArchive.class, "jsp_coresyntx_direct_include_web.war");
    archive.setWebXML(URLClientIT.class.getClassLoader().getResource(packagePath+"/jsp_coresyntx_direct_include_web.xml"));
    archive.add(new UrlAsset(URLClientIT.class.getClassLoader().getResource(packagePath+"/includecommon.jsp")), "includecommon.jsp");
    archive.add(new UrlAsset(URLClientIT.class.getClassLoader().getResource(packagePath+"/positiveIncludeCtxRelativeDirective.jsp")), "positiveIncludeCtxRelativeDirective.jsp");
    archive.add(new UrlAsset(URLClientIT.class.getClassLoader().getResource(packagePath+"/positiveIncludePageRelativeDirective.jsp")), "positiveIncludePageRelativeDirective.jsp");

    return archive;

  }

  /*
   * @class.setup_props: webServerHost; webServerPort; ts_home;
   *
   */

  /*
   * @testName: positiveIncludeCtxRelativeDirectiveTest
   * 
   * @assertion_ids: JSP:SPEC:69
   * 
   * @test_Strategy: Using an include directive, include content referenced by a
   * context-relative path. PENDING Merge with test below
   */

  @Test
  public void positiveIncludeCtxRelativeDirectiveTest() throws Exception {
    InputStream gfStream = URLClientIT.class.getClassLoader().getResourceAsStream(packagePath+"/positiveIncludeCtxRelativeDirective.gf");
    setGoldenFileStream(gfStream);
    TEST_PROPS.setProperty(STANDARD, "positiveIncludeCtxRelativeDirective");
    invoke();
  }

  /*
   * @testName: positiveIncludePageRelativeDirectiveTest
   * 
   * @assertion_ids: JSP:SPEC:69
   * 
   * @test_Strategy: Using an include directive, include content referenced by a
   * page-relative path.
   */

  @Test
  public void positiveIncludePageRelativeDirectiveTest() throws Exception {
    InputStream gfStream = URLClientIT.class.getClassLoader().getResourceAsStream(packagePath+"/positiveIncludePageRelativeDirective.gf");
    setGoldenFileStream(gfStream);
    TEST_PROPS.setProperty(STANDARD, "positiveIncludePageRelativeDirective");
    invoke();
  }
}
