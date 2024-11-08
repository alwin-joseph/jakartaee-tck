package ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype;

import ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.Client;
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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;
import com.sun.ts.lib.harness.Status;


@ExtendWith(ArquillianExtension.class)
@Tag("persistence")
@Tag("platform")
@Tag("tck-appclient")

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ClientAppmanagednotxTest extends ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.Client {
    static final String VEHICLE_ARCHIVE = "jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle";

    public static void main(String[] args) {
      ClientAppmanagednotxTest theTests = new ClientAppmanagednotxTest();
      Status s = theTests.run(args, System.out, System.err);
      s.exit();
    }

        /**
        EE10 Deployment Descriptors:
        jpa_core_metamodelapi_identifiabletype: META-INF/persistence.xml
        jpa_core_metamodelapi_identifiabletype_appmanaged_vehicle_client: META-INF/application-client.xml
        jpa_core_metamodelapi_identifiabletype_appmanaged_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client: META-INF/application-client.xml
        jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_metamodelapi_identifiabletype_pmservlet_vehicle_web: WEB-INF/web.xml
        jpa_core_metamodelapi_identifiabletype_puservlet_vehicle_web: WEB-INF/web.xml
        jpa_core_metamodelapi_identifiabletype_stateful3_vehicle_client: META-INF/application-client.xml
        jpa_core_metamodelapi_identifiabletype_stateful3_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_metamodelapi_identifiabletype_stateless3_vehicle_client: META-INF/application-client.xml
        jpa_core_metamodelapi_identifiabletype_stateless3_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_metamodelapi_identifiabletype_vehicles: 

        Found Descriptors:
        Client:

        /com/sun/ts/tests/common/vehicle/appmanagedNoTx/appmanagedNoTx_vehicle_client.xml
        Ejb:

        Ear:

        */
        @TargetsContainer("tck-appclient")
        @OverProtocol("appclient")
        @Deployment(name = VEHICLE_ARCHIVE, order = 2)
        public static EnterpriseArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
            // the jar with the correct archive name
            JavaArchive jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client = ShrinkWrap.create(JavaArchive.class, "jpa_core_metamodelapi_identifiabletype_vehicles_client.jar");
            // The class files
            jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client.addClasses(
            com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
            com.sun.ts.tests.common.vehicle.appmanagedNoTx.AppManagedNoTxVehicleIF.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManager.class,
            com.sun.ts.tests.common.vehicle.ejb3share.EJB3ShareIF.class,
            com.sun.ts.lib.harness.EETest.Fault.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManagerFactory.class,
            com.sun.ts.tests.common.vehicle.EmptyVehicleRunner.class,
            ee.jakarta.tck.persistence.common.PMClientBase.class,
            com.sun.ts.tests.common.vehicle.appmanagedNoTx.AppManagedNoTxVehicleRunner.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UserTransactionWrapper.class,
            com.sun.ts.lib.harness.EETest.class,
            com.sun.ts.lib.harness.ServiceEETest.class,
            com.sun.ts.tests.common.vehicle.ejb3share.EntityTransactionWrapper.class,
            com.sun.ts.lib.harness.EETest.SetupException.class,
            com.sun.ts.tests.common.vehicle.VehicleClient.class,
            com.sun.ts.tests.common.vehicle.ejb3share.NoopTransactionWrapper.class,
            ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.Client.class,
            ClientAppmanagednotxTest.class
            );
            // The application-client.xml descriptor
            URL resURL = Client.class.getResource("/com/sun/ts/tests/common/vehicle/appmanagedNoTx/appmanagedNoTx_vehicle_client.xml");
            if(resURL != null) {
              jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client.addAsManifestResource(resURL, "application-client.xml");
            }
            // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
            resURL = Client.class.getResource("/com/sun/ts/tests/common/vehicle/appmanagedNoTx/appmanagedNoTx_vehicle_client.jar.sun-application-client.xml");
            if(resURL != null) {
              jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client.addAsManifestResource(resURL, "sun-application-client.xml");
            }
            jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client.addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");
            // Call the archive processor
            archiveProcessor.processClientArchive(jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client, Client.class, resURL);

        // Ejb 1
            // the jar with the correct archive name
            JavaArchive jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb = ShrinkWrap.create(JavaArchive.class, "jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb.jar");
            // The class files
            jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb.addClasses(
                com.sun.ts.tests.common.vehicle.ejb3share.EJB3ShareBaseBean.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.appmanagedNoTx.AppManagedNoTxVehicleIF.class,
                com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManager.class,
                com.sun.ts.tests.common.vehicle.ejb3share.EJB3ShareIF.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.Client.class,
                com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManagerFactory.class,
                ee.jakarta.tck.persistence.common.PMClientBase.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.ejb3share.UserTransactionWrapper.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.common.vehicle.ejb3share.EntityTransactionWrapper.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.common.vehicle.ejb3share.NoopTransactionWrapper.class,
                com.sun.ts.tests.common.vehicle.appmanagedNoTx.AppManagedNoTxVehicleBean.class
            );
            // The ejb-jar.xml descriptor
            URL ejbResURL1 = Client.class.getResource("/com/sun/ts/tests/common/vehicle/appmanagedNoTx/appmanagedNoTx_vehicle_client.xml");
            if(ejbResURL1 != null) {
//              jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb.addAsManifestResource(ejbResURL1, "ejb-jar.xml");
            }
            // The sun-ejb-jar.xml file
            ejbResURL1 = Client.class.getResource("/com/sun/ts/tests/common/vehicle/appmanagedNoTx/appmanagedNoTx_vehicle_ejb.jar.sun-ejb-jar.xml");
            if(ejbResURL1 != null) {
              jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb.addAsManifestResource(ejbResURL1, "sun-ejb-jar.xml");
            }
            // Call the archive processor
            archiveProcessor.processEjbArchive(jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb, Client.class, ejbResURL1);


        // Par
            // the jar with the correct archive name
            JavaArchive jpa_core_metamodelapi_identifiabletype = ShrinkWrap.create(JavaArchive.class, "jpa_core_metamodelapi_identifiabletype.jar");
            // The class files
            jpa_core_metamodelapi_identifiabletype.addClasses(
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.B.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.DID2EmployeeId.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.DID2Employee.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.Address.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.A.class,
                ee.jakarta.tck.persistence.core.metamodelapi.identifiabletype.ZipCode.class
            );
            // The persistence.xml descriptor
            URL parURL = Client.class.getResource("persistence.xml");
            if(parURL != null) {
              jpa_core_metamodelapi_identifiabletype.addAsManifestResource(parURL, "persistence.xml");
            }
            // Add the Persistence mapping-file
            URL mappingURL = Client.class.getResource("myMappingFile.xml");
            if(mappingURL != null) {
              jpa_core_metamodelapi_identifiabletype.addAsResource(mappingURL, "myMappingFile.xml");
            }
            mappingURL = Client.class.getResource("myMappingFile1.xml");
            if(mappingURL != null) {
              jpa_core_metamodelapi_identifiabletype.addAsResource(mappingURL, "myMappingFile1.xml");
            }
            mappingURL = Client.class.getResource("myMappingFile2.xml");
            if(mappingURL != null) {
              jpa_core_metamodelapi_identifiabletype.addAsResource(mappingURL, "myMappingFile2.xml");
            }
            // Call the archive processor
            archiveProcessor.processParArchive(jpa_core_metamodelapi_identifiabletype, Client.class, parURL);
            parURL = Client.class.getResource("orm.xml");
            if(parURL != null) {
              jpa_core_metamodelapi_identifiabletype.addAsManifestResource(parURL, "orm.xml");
            }

        // Ear
            EnterpriseArchive jpa_core_metamodelapi_identifiabletype_vehicles_ear = ShrinkWrap.create(EnterpriseArchive.class, "jpa_core_metamodelapi_identifiabletype_vehicles.ear");

            // Any libraries added to the ear

            // The component jars built by the package target
            jpa_core_metamodelapi_identifiabletype_vehicles_ear.addAsModule(jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_ejb);
            jpa_core_metamodelapi_identifiabletype_vehicles_ear.addAsModule(jpa_core_metamodelapi_identifiabletype_appmanagedNoTx_vehicle_client);

            jpa_core_metamodelapi_identifiabletype_vehicles_ear.addAsLibrary(jpa_core_metamodelapi_identifiabletype);



            // The application.xml descriptor
            URL earResURL = null;
            // The sun-application.xml descriptor
            earResURL = Client.class.getResource("/.ear.sun-application.xml");
            if(earResURL != null) {
              jpa_core_metamodelapi_identifiabletype_vehicles_ear.addAsManifestResource(earResURL, "sun-application.xml");
            }
            // Call the archive processor
            archiveProcessor.processEarArchive(jpa_core_metamodelapi_identifiabletype_vehicles_ear, Client.class, earResURL);
        return jpa_core_metamodelapi_identifiabletype_vehicles_ear;
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getId() throws java.lang.Exception {
            super.getId();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getIdIllegalArgumentException() throws java.lang.Exception {
            super.getIdIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getVersion() throws java.lang.Exception {
            super.getVersion();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getVersionIllegalArgumentException() throws java.lang.Exception {
            super.getVersionIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredId() throws java.lang.Exception {
            super.getDeclaredId();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredIdIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredIdIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredVersion() throws java.lang.Exception {
            super.getDeclaredVersion();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredVersionIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredVersionIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSupertype() throws java.lang.Exception {
            super.getSupertype();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void hasSingleIdAttribute() throws java.lang.Exception {
            super.hasSingleIdAttribute();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void hasVersionAttribute() throws java.lang.Exception {
            super.hasVersionAttribute();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getIdClassAttributes() throws java.lang.Exception {
            super.getIdClassAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getIdClassAttributesIllegalArgumentException() throws java.lang.Exception {
            super.getIdClassAttributesIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getIdType() throws java.lang.Exception {
            super.getIdType();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getAttribute() throws java.lang.Exception {
            super.getAttribute();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getAttributeIllegalArgumentException() throws java.lang.Exception {
            super.getAttributeIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getAttributes() throws java.lang.Exception {
            super.getAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getCollectionStringClass() throws java.lang.Exception {
            super.getCollectionStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getCollectionStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getCollectionStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getCollectionString() throws java.lang.Exception {
            super.getCollectionString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getCollectionStringIllegalArgumentException() throws java.lang.Exception {
            super.getCollectionStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredAttribute() throws java.lang.Exception {
            super.getDeclaredAttribute();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredAttributeIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredAttributeIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredAttributes() throws java.lang.Exception {
            super.getDeclaredAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredCollectionStringClass() throws java.lang.Exception {
            super.getDeclaredCollectionStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredCollectionStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredCollectionStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredCollectionString() throws java.lang.Exception {
            super.getDeclaredCollectionString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredCollectionStringIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredCollectionStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredListStringClass() throws java.lang.Exception {
            super.getDeclaredListStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredListStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredListStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredListString() throws java.lang.Exception {
            super.getDeclaredListString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredListStringIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredListStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredMapStringClassClass() throws java.lang.Exception {
            super.getDeclaredMapStringClassClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredMapStringClassClassIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredMapStringClassClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredMapString() throws java.lang.Exception {
            super.getDeclaredMapString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredMapStringIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredMapStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSetStringClass() throws java.lang.Exception {
            super.getDeclaredSetStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSetStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredSetStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSetString() throws java.lang.Exception {
            super.getDeclaredSetString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSetStringIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredSetStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSingularAttributeStringClass() throws java.lang.Exception {
            super.getDeclaredSingularAttributeStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSingularAttributeStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredSingularAttributeStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSingularAttributeString() throws java.lang.Exception {
            super.getDeclaredSingularAttributeString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSingularAttributeStringIllegalArgumentException() throws java.lang.Exception {
            super.getDeclaredSingularAttributeStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredSingularAttributes() throws java.lang.Exception {
            super.getDeclaredSingularAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getListStringClass() throws java.lang.Exception {
            super.getListStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getListStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getListStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getListString() throws java.lang.Exception {
            super.getListString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getListStringIllegalArgumentException() throws java.lang.Exception {
            super.getListStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getMapStringClassClass() throws java.lang.Exception {
            super.getMapStringClassClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getMapStringClassClassIllegalArgumentException() throws java.lang.Exception {
            super.getMapStringClassClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getMapString() throws java.lang.Exception {
            super.getMapString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getMapStringIllegalArgumentException() throws java.lang.Exception {
            super.getMapStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getPluralAttributes() throws java.lang.Exception {
            super.getPluralAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getDeclaredPluralAttributes() throws java.lang.Exception {
            super.getDeclaredPluralAttributes();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSetStringClass() throws java.lang.Exception {
            super.getSetStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSetStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getSetStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSetString() throws java.lang.Exception {
            super.getSetString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSetStringIllegalArgumentException() throws java.lang.Exception {
            super.getSetStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSingularAttributeStringClass() throws java.lang.Exception {
            super.getSingularAttributeStringClass();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSingularAttributeStringClassIllegalArgumentException() throws java.lang.Exception {
            super.getSingularAttributeStringClassIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSingularAttributeString() throws java.lang.Exception {
            super.getSingularAttributeString();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSingularAttributeStringIllegalArgumentException() throws java.lang.Exception {
            super.getSingularAttributeStringIllegalArgumentException();
        }

        @Test
        @Override
        @TargetVehicle("appmanagedNoTx")
        public void getSingularAttributes() throws java.lang.Exception {
            super.getSingularAttributes();
        }

}
