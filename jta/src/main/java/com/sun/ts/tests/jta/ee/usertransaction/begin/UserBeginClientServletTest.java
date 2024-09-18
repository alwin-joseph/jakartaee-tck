package com.sun.ts.tests.jta.ee.usertransaction.begin;

import com.sun.ts.tests.jta.ee.usertransaction.begin.UserBeginClient;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;

import java.lang.System.Logger;

@ExtendWith(ArquillianExtension.class)
@Tag("jta")
@Tag("platform")
@Tag("web")
@Tag("tck-javatest")

public class UserBeginClientServletTest extends com.sun.ts.tests.jta.ee.usertransaction.begin.UserBeginClient {
    static final String VEHICLE_ARCHIVE = "begin_servlet_vehicle";

    private static String packagePath = UserBeginClientServletTest.class.getPackageName().replace(".", "/");

    private static final Logger logger = System.getLogger(UserBeginClientServletTest.class.getName());

    @BeforeEach
    void logStartTest(TestInfo testInfo) {
        logger.log(Logger.Level.INFO, "STARTING TEST : " + testInfo.getDisplayName());
    }
  
    @AfterEach
    void logFinishTest(TestInfo testInfo) {
        logger.log(Logger.Level.INFO, "FINISHED TEST : " + testInfo.getDisplayName());
    }

    @AfterEach
    public void cleanup() {
      logger.log(Logger.Level.INFO, "cleanup ok");
    }

    /**
    EE10 Deployment Descriptors:
    begin_ejb_vehicle: 
    begin_ejb_vehicle_client: META-INF/application-client.xml,jar.sun-application-client.xml
    begin_ejb_vehicle_ejb: META-INF/ejb-jar.xml,jar.sun-ejb-jar.xml
    begin_jsp_vehicle: 
    begin_jsp_vehicle_web: WEB-INF/web.xml,war.sun-web.xml
    begin_servlet_vehicle: 
    begin_servlet_vehicle_web: WEB-INF/web.xml,war.sun-web.xml

    Found Descriptors:
    War:

    /com/sun/ts/tests/jta/ee/usertransaction/begin/servlet_vehicle_web.xml
    /com/sun/ts/tests/common/vehicle/servlet/servlet_vehicle_web.xml
    Ear:

    */
    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = VEHICLE_ARCHIVE, order = 2)
    public static EnterpriseArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {
    // War
        // the war with the correct archive name
        WebArchive begin_servlet_vehicle_web = ShrinkWrap.create(WebArchive.class, "begin_servlet_vehicle_web.war");
        // The class files
        begin_servlet_vehicle_web.addClasses(
        com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
        com.sun.ts.lib.harness.EETest.Fault.class,
        com.sun.ts.tests.jta.ee.common.Transact.class,
        com.sun.ts.tests.jta.ee.common.InvalidStatusException.class,
        com.sun.ts.tests.jta.ee.common.InitFailedException.class,
        com.sun.ts.tests.common.vehicle.servlet.ServletVehicle.class,
        com.sun.ts.tests.jta.ee.common.TransactionStatus.class,
        com.sun.ts.tests.jta.ee.usertransaction.begin.UserBeginClient.class,
        com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
        com.sun.ts.tests.jta.ee.common.InvalidStatusException.class,
        com.sun.ts.tests.jta.ee.common.InitFailedException.class,
        com.sun.ts.lib.harness.EETest.class,
        com.sun.ts.lib.harness.ServiceEETest.class,
        com.sun.ts.tests.jta.ee.common.TransactionStatus.class,
        com.sun.ts.lib.harness.EETest.SetupException.class,
        com.sun.ts.tests.common.vehicle.VehicleClient.class,
        UserBeginClientServletTest.class
        );
        // The web.xml descriptor
        URL warResURL = UserBeginClientServletTest.class.getClassLoader().getResource(packagePath+"/servlet_vehicle_web.xml");
        if(warResURL != null) {
            begin_servlet_vehicle_web.setWebXML(warResURL);
        }
        // The sun-web.xml descriptor
        warResURL = UserBeginClientServletTest.class.getClassLoader().getResource(packagePath+"/begin_servlet_vehicle_web.war.sun-web.xml");
        if(warResURL != null) {
            begin_servlet_vehicle_web.addAsWebInfResource(warResURL, "sun-web.xml");
        }
        // Web content
        archiveProcessor.processWebArchive(begin_servlet_vehicle_web, UserBeginClientServletTest.class, warResURL);

    // Ear
        EnterpriseArchive begin_servlet_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "begin_servlet_vehicle.ear");
        begin_servlet_vehicle_ear.addAsModule(begin_servlet_vehicle_web); 
        return begin_servlet_vehicle_ear;
    }

    @Test
    @Override
    @TargetVehicle("servlet")
    public void testUserBegin001() throws java.lang.Exception {
        super.testUserBegin001();
    }

    @Test
    @Override
    @TargetVehicle("servlet")
    public void testUserBegin002() throws java.lang.Exception {
        super.testUserBegin002();
    }


}