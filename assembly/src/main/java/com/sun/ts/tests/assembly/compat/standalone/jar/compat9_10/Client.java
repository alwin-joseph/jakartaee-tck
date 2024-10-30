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
 * %W %E
 */

package com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10;

import java.util.Properties;

import com.sun.ts.lib.harness.Status;
import com.sun.ts.lib.harness.EETest;
import com.sun.ts.lib.util.TSNamingContext;

import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.asset.UrlAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.System.Logger;

@Tag("assembly")
@Tag("platform")
@Tag("tck-appclient")
@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class Client extends EETest {
  /** JNDI Name we use to lookup the bean */
  public static final String lookupName = "java:comp/env/ejb/TestBean";

  private TSNamingContext nctx = null;

  private Properties props = null;

  public static void main(String[] args) throws Exception {
    Client theTests = new Client();
    Status s = theTests.run(args, System.out, System.err);
    s.exit();
  }

  /*
   * @class.setup_props: org.omg.CORBA.ORBClass; java.naming.factory.initial;
   *
   */
  public void setup(String[] args, Properties props) throws Fault {

    try {
      this.props = props;
      logMsg("[Client] setup(): getting Naming Context...");
      nctx = new TSNamingContext();

      logMsg("[Client] Setup OK!");
    } catch (Exception e) {
      throw new Fault("Setup failed:" + e, e);
    }
  }

  private static final Logger logger = System.getLogger(Client.class.getName());

  @BeforeEach
  void logStartTest(TestInfo testInfo) {
    logger.log(Logger.Level.INFO, "STARTING TEST : " + testInfo.getDisplayName());
  }

  @AfterEach
  void logFinishTest(TestInfo testInfo) {
    logger.log(Logger.Level.INFO, "FINISHED TEST : " + testInfo.getDisplayName());
  }

  public Client() throws Exception {

  }

  static final String VEHICLE_ARCHIVE = "assembly_classpath_ejb";

  @TargetsContainer("tck-appclient")
  @OverProtocol("appclient")
  @Deployment(name = VEHICLE_ARCHIVE, order = 2)
  public static EnterpriseArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {

    JavaArchive assembly_compat_standalone_jar_compat9_10_client = ShrinkWrap.create(JavaArchive.class,
        "assembly_compat_standalone_jar_compat9_10_client.jar");
        assembly_compat_standalone_jar_compat9_10_client.addClasses(
        com.sun.ts.lib.harness.EETest.Fault.class,
        com.sun.ts.lib.harness.EETest.class,
        com.sun.ts.lib.harness.EETest.SetupException.class,
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.TestBean.class,
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.TestBeanEJB.class,
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.Client.class);
    // The application-client.xml descriptor
    URL resURL = Client.class.getResource("application-client.xml");
    if (resURL != null) {
      assembly_compat_standalone_jar_compat9_10_client.addAsManifestResource(resURL, "application-client.xml");
    }
    resURL = Client.class.getResource("assembly_compat_standalone_jar_compat9_10_client.jar.sun-application-client.xml");
    if(resURL != null) {
      assembly_compat_standalone_jar_compat9_10_client.addAsManifestResource(resURL, "sun-application-client.xml");
    }
    assembly_compat_standalone_jar_compat9_10_client
        .addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");
    archiveProcessor.processClientArchive(assembly_compat_standalone_jar_compat9_10_client, Client.class, resURL);


    
    EnterpriseArchive assembly_compat_standalone_jar_compat9_10_ear = ShrinkWrap.create(EnterpriseArchive.class,
        "assembly_compat_standalone_jar_compat9_10_ear.ear");
        assembly_compat_standalone_jar_compat9_10_ear.addAsModule(assembly_compat_standalone_jar_compat9_10_client);
    URL earResURL = Client.class.getResource("application.xml");
    if (earResURL != null) {
      assembly_compat_standalone_jar_compat9_10_ear.addAsManifestResource(earResURL, "application.xml");
    }
    // assembly_compat_standalone_jar_compat9_10_ear
    //     .addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");
    archiveProcessor.processEarArchive(assembly_compat_standalone_jar_compat9_10_ear, Client.class, earResURL);
    return assembly_compat_standalone_jar_compat9_10_ear;
  }

  @Deployment(name = "assembly_compat_standalone_jar_compat9_10_component_ejb", order = 1, testable = false)
  public static JavaArchive createEjbDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {

    JavaArchive assembly_compat_standalone_jar_compat9_10_component_ejb = ShrinkWrap.create(JavaArchive.class,
        "assembly_compat_standalone_jar_compat9_10_component_ejb.jar");
        assembly_compat_standalone_jar_compat9_10_component_ejb.addClasses(
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.TestBean.class,
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.TestBeanEJB.class,
        com.sun.ts.tests.common.ejb.wrappers.Stateless3xWrapper.class,
        com.sun.ts.lib.util.RemoteLoggingInitException.class,
        com.sun.ts.tests.assembly.compat.standalone.jar.compat9_10.Client.class);
    // The application-client.xml descriptor
    URL resURL = Client.class.getResource("ejb-jar.xml");
    if (resURL != null) {
      assembly_compat_standalone_jar_compat9_10_component_ejb.addAsManifestResource(resURL, "ejb-jar.xml");
    }
    resURL = Client.class.getResource("assembly_compat_standalone_jar_compat9_10_component_ejb.jar.sun-ejb-jar.xml");
    if(resURL != null) {
      assembly_compat_standalone_jar_compat9_10_component_ejb.addAsManifestResource(resURL, "sun-ejb-jar.xml");
    }
    // assembly_compat_standalone_jar_compat9_10_component_ejb
    //     .addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");
    archiveProcessor.processEjbArchive(assembly_compat_standalone_jar_compat9_10_component_ejb, Client.class, resURL);

    return assembly_compat_standalone_jar_compat9_10_component_ejb;
  }

  /**
   * @testName: testStandaloneJar
   *
   * @assertion_ids: JavaEE:SPEC:261; JavaEE:SPEC:283; JavaEE:SPEC:284
   *
   * @test_Strategy: Package an ejb-jar file using a Jakarta EE 9 DD
   *                 (assembly_standalone_jar_compat9_10_component_ejb.jar).
   *
   *                 Package a .ear file (Jakarta EE 10 DD's) containing an
   *                 application client accessing a bean in the stand-alone
   *                 ejb-jar module (JNDI names match in runtime information).
   *
   *                 Deploy the ejb-jar module and the .ear file.
   *
   *                 Run the client and check that we can call a business method
   *                 on the referenced bean at runtime.
   */
  @Test
  @OperateOnDeployment("assembly_classpath_ejb")
  public void testStandaloneJar() throws Fault {
    TestBean bean;
    boolean pass;

    try {
      logTrace("[Client] Looking up bean Home...");
      bean = (TestBean) nctx.lookup(lookupName, TestBean.class);
      bean.initLogging(props);

      logTrace("[Client] Calling bean...");
      pass = bean.ping();
      if (!pass) {
        throw new Fault("classpath test failed");
      }
    } catch (Exception e) {
      throw new Fault("classpath test failed: " + e, e);
    }
  }

  public void cleanup() throws Fault {
    logMsg("[Client] cleanup()");
  }
}
