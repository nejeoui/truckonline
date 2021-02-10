package com.truckonline.api.exam.tests.base;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.jboss.shrinkwrap.resolver.api.maven.strategy.RejectDependenciesStrategy;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * ArquillianBase
 */
public abstract class ArquillianBase {

	protected static final String WEB_ARCHIVE_TEST_NAME = "truckonline-api-exam-test.war";

	@Deployment
	@OverProtocol("Servlet 3.0")
	public static WebArchive createDeployment() {
		
		File[] apps_libs = Maven.configureResolver()
				.workOffline()
				.withMavenCentralRepo(false)
				.loadPomFromFile("pom.xml")
				.importDependencies(ScopeType.COMPILE, ScopeType.TEST)
				.resolve()
				.using(new RejectDependenciesStrategy(false, "org.jboss.resteasy:resteasy-client", "org.jboss.resteasy:resteasy-jaxrs"))
				.asFile();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, WEB_ARCHIVE_TEST_NAME)
				.addPackages(true, "com.truckonline.api.exam")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(new File("src/test/resources/jboss-web.xml"))
				.addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"))
				// .addAsResource("META-INF/ejb-jar.xml", "META-INF/ejb-jar.xml")
				.addAsLibraries(apps_libs)
				.addAsManifestResource("MANIFEST.MF");

		archive.deletePackage("org.jboss.resteasy");

		// Ajouter les resources.
		for (File f : new File("src/main/resources").listFiles())
		{
			if(f.getPath().contains("META-INF"))
				continue;

			System.out.println("Add As Resource : "+f);
			archive.addAsResource(f);
		}

		for (File f : new File("src/test/resources").listFiles())
		{
			System.out.println("Add As Test Resource : "+f);
			archive.addAsResource(f);
		}

		System.out.println(archive.toString(Formatters.VERBOSE));

		return archive;
	}

	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("\n================================================================================\n");
			System.out.println(" Starting test : " + description.getClassName() + "#" + description.getMethodName()+"()");
			System.out.println("\n================================================================================\n");
		}
		
		protected void succeeded(Description description) {
			System.out.println("\n================================================================================\n");
			System.out.println(" Test OK");
			System.out.println("\n================================================================================\n");
		}

		protected void failed(Throwable e, Description description) {
			System.out.println("\n================================================================================\n");
			System.out.println(" Test FAILED");
			System.out.println("\n================================================================================\n");
		}

		protected void skipped(AssumptionViolatedException e, Description description) {
			super.skipped(e, description);
			System.out.println("\n================================================================================\n");
			System.out.println(" Test SKIPPED");
			System.out.println("\n================================================================================\n");
		}
	};
}
