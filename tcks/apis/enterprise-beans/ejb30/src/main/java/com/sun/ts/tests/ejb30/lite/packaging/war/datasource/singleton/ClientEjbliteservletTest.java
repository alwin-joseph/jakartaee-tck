package com.sun.ts.tests.ejb30.lite.packaging.war.datasource.singleton;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;

import java.net.URL;


@ExtendWith(ArquillianExtension.class)
@Tag("platform")
@Tag("ejb_web")
@Tag("web")
@Tag("tck-javatest")

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ClientEjbliteservletTest extends Client {
    static final String VEHICLE_ARCHIVE = "ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle";

        /**
        EE10 Deployment Descriptors:
        ejblite_packaging_war_datasource_singleton_ejblitejsf_vehicle_web: WEB-INF/ejb-jar.xml,WEB-INF/beans.xml,WEB-INF/faces-config.xml,WEB-INF/web.xml
        ejblite_packaging_war_datasource_singleton_ejblitejsp_vehicle_web: WEB-INF/ejb-jar.xml
        ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web: WEB-INF/ejb-jar.xml,WEB-INF/web.xml
        ejblite_packaging_war_datasource_singleton_ejbliteservlet2_vehicle_web: WEB-INF/ejb-jar.xml,WEB-INF/web.xml

        Found Descriptors:
        War:

        /com/sun/ts/tests/common/vehicle/ejbliteservlet/ejbliteservlet_vehicle_web.xml
        */
        @TargetsContainer("tck-javatest")
        @OverProtocol("javatest")
        @Deployment(name = VEHICLE_ARCHIVE, order = 2)
        public static WebArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {

        // War
            // the war with the correct archive name
            WebArchive ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web = ShrinkWrap.create(WebArchive.class, "ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web.war");
            // The class files
            ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web.addClasses(
            com.sun.ts.tests.common.vehicle.ejbliteshare.EJBLiteClientIF.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
            Fault.class,
            DataSourceBean.class,
            com.sun.ts.tests.common.vehicle.ejbliteshare.ReasonableStatus.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
            com.sun.ts.tests.ejb30.lite.packaging.war.datasource.common.ComponentBase.class,
            com.sun.ts.tests.ejb30.lite.packaging.war.datasource.singleton.Client.class,
            EJBLiteServletVehicle.class,
            com.sun.ts.tests.ejb30.common.lite.NumberEnum.class,
            com.sun.ts.tests.ejb30.lite.packaging.war.datasource.common.DataSourceTest.class,
            com.sun.ts.tests.ejb30.common.helper.Helper.class,
            com.sun.ts.tests.ejb30.common.lite.EJBLiteClientBase.class,
            com.sun.ts.tests.ejb30.common.lite.EJBLiteJsfClientBase.class,
            com.sun.ts.tests.ejb30.lite.packaging.war.datasource.common.DataSourceIF.class,
            com.sun.ts.lib.harness.EETest.class,
            HttpServletDelegate.class,
            com.sun.ts.lib.harness.ServiceEETest.class,
            com.sun.ts.tests.ejb30.common.helper.ServiceLocator.class,
            SetupException.class,
            com.sun.ts.tests.common.vehicle.VehicleClient.class,
            com.sun.ts.tests.ejb30.common.lite.NumberIF.class
            );
            // The web.xml descriptor
            URL warResURL = com.sun.ts.tests.ejb30.lite.packaging.war.datasource.singleton.Client.class.getResource("ejbliteservlet_vehicle_web.xml");
            if(warResURL != null) {
              ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web.addAsWebInfResource(warResURL, "web.xml");
            }

            // Any libraries added to the war

            // Web content
            warResURL = com.sun.ts.tests.ejb30.lite.packaging.war.datasource.singleton.Client.class.getResource("ejb-jar.xml");
            if(warResURL != null) {
              ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web.addAsWebResource(warResURL, "/WEB-INF/ejb-jar.xml");
            }

           // Call the archive processor
           archiveProcessor.processWebArchive(ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web, Client.class, warResURL);

        return ejblite_packaging_war_datasource_singleton_ejbliteservlet_vehicle_web;
        }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void postConstructRecords() {
        super.postConstructRecords();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void postConstructRecordsEJB() {
        super.postConstructRecordsEJB();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void getConnectionEJB() {
        super.getConnectionEJB();
    }
}