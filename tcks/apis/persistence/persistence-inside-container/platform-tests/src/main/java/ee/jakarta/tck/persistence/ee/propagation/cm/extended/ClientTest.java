package ee.jakarta.tck.persistence.ee.propagation.cm.extended;

import com.sun.ts.tests.common.vehicle.none.proxy.AppClient;
import com.sun.ts.tests.common.vehicle.none.proxy.ServletNoVehicle;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;

import java.net.URL;



@ExtendWith(ArquillianExtension.class)
@Tag("platform")
@Tag("tck-appclient")

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ClientTest extends ee.jakarta.tck.persistence.ee.propagation.cm.extended.Client {
    /**
        EE10 Deployment Descriptors:
        jpa_ee_propagation_cm_ext: META-INF/persistence.xml
        jpa_ee_propagation_cm_ext_client: jar.sun-application-client.xml
        jpa_ee_propagation_cm_ext_ejb: jar.sun-ejb-jar.xml

        */
        @TargetsContainer("tck-appclient")
        @OverProtocol("appclient")
        @Deployment(name = "jpa_ee_propagation_cm_ext", order = 2)
        public static EnterpriseArchive createDeployment(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
            // the jar with the correct archive name
            JavaArchive jpa_ee_propagation_cm_ext_client = ShrinkWrap.create(JavaArchive.class, "jpa_ee_propagation_cm_ext_client.jar");
            // The class files
            jpa_ee_propagation_cm_ext_client.addClasses(
                    IClient.class,
                    IClientProxy.class,
                    TestAppClient.class
            ).addClasses(AppClient.getAppClasses());
            // The application-client.xml descriptor
            URL resURL = null;
            // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
            jpa_ee_propagation_cm_ext_client.addAsManifestResource(new StringAsset("Main-Class: " + TestAppClient.class.getName() + "\n"), "MANIFEST.MF");
            // Call the archive processor
            archiveProcessor.processClientArchive(jpa_ee_propagation_cm_ext_client, Client.class, resURL);

        // Ejb 1
            // the jar with the correct archive name
            JavaArchive jpa_ee_propagation_cm_ext_ejb = ShrinkWrap.create(JavaArchive.class, "jpa_ee_propagation_cm_ext_ejb.jar");
            // The class files
            jpa_ee_propagation_cm_ext_ejb.addClasses(
                ee.jakarta.tck.persistence.ee.propagation.cm.extended.Stateful3IF.class,
                ee.jakarta.tck.persistence.ee.propagation.cm.extended.TellerBean.class,
                ee.jakarta.tck.persistence.ee.propagation.cm.extended.Teller.class,
                ee.jakarta.tck.persistence.ee.propagation.cm.extended.Stateful3Bean.class
            );
            // The ejb-jar.xml descriptor
            URL ejbResURL1 = Client.class.getResource("/ee/jakarta/tck/persistence/ee/propagation/cm/extended/jpa_ee_propagation_cm_ext_ejb.jar.sun-ejb-jar.xml");
            // The sun-ejb-jar.xml file
            jpa_ee_propagation_cm_ext_ejb.addAsManifestResource(ejbResURL1, "sun-ejb-jar.xml");
            // Call the archive processor
            archiveProcessor.processEjbArchive(jpa_ee_propagation_cm_ext_ejb, Client.class, ejbResURL1);

        // Par
            // the jar with the correct archive name
            JavaArchive jpa_ee_propagation_cm_ext = ShrinkWrap.create(JavaArchive.class, "jpa_ee_propagation_cm_ext.jar");
            // The class files
            jpa_ee_propagation_cm_ext.addClasses(
                ee.jakarta.tck.persistence.ee.common.A.class,
                ee.jakarta.tck.persistence.ee.common.Account.class,
                ee.jakarta.tck.persistence.ee.common.B.class
            );
            // The persistence.xml descriptor
            URL parURL = Client.class.getResource("/ee/jakarta/tck/persistence/ee/propagation/cm/extended/persistence.xml");
            jpa_ee_propagation_cm_ext.addAsManifestResource(parURL, "persistence.xml");
            // Call the archive processor
            archiveProcessor.processParArchive(jpa_ee_propagation_cm_ext, Client.class, parURL);

            // non-vehicle appclientproxy invoker war
            WebArchive appclientproxy = ShrinkWrap.create(WebArchive.class, "appclientproxy.war");
            appclientproxy.addClasses(Client.class, ClientServletTarget.class, ServletNoVehicle.class);
            appclientproxy.addAsWebInfResource(new StringAsset(""), "beans.xml");

        // Ear
            EnterpriseArchive jpa_ee_propagation_cm_ext_ear = ShrinkWrap.create(EnterpriseArchive.class, "jpa_ee_propagation_cm_ext.ear");

            // Any libraries added to the ear

            // The component jars built by the package target
            jpa_ee_propagation_cm_ext_ear.addAsModule(jpa_ee_propagation_cm_ext_ejb);
            jpa_ee_propagation_cm_ext_ear.addAsModule(jpa_ee_propagation_cm_ext_client);
            jpa_ee_propagation_cm_ext_ear.addAsModule(appclientproxy);

            jpa_ee_propagation_cm_ext_ear.addAsLibrary(jpa_ee_propagation_cm_ext);



            // The application.xml descriptor
            URL earResURL = null;
            // The sun-application.xml descriptor
            earResURL = Client.class.getResource("/.ear.sun-application.xml");
            if(earResURL != null) {
              jpa_ee_propagation_cm_ext_ear.addAsManifestResource(earResURL, "sun-application.xml");
            }
            // Call the archive processor
            archiveProcessor.processEarArchive(jpa_ee_propagation_cm_ext_ear, Client.class, earResURL);
        return jpa_ee_propagation_cm_ext_ear;
        }

        @Test
        @Override
        public void test1() throws java.lang.Exception {
            super.test1();
        }

        @Test
        @Override
        public void test2() throws java.lang.Exception {
            super.test2();
        }

        @Test
        @Override
        public void test3() throws java.lang.Exception {
            super.test3();
        }

        @Test
        @Override
        public void test4() throws java.lang.Exception {
            super.test4();
        }

        @Test
        @Override
        public void test5() throws java.lang.Exception {
            super.test5();
        }

        @Test
        @Override
        public void test6() throws java.lang.Exception {
            super.test6();
        }

        @Test
        @Override
        public void test7() throws java.lang.Exception {
            super.test7();
        }


}