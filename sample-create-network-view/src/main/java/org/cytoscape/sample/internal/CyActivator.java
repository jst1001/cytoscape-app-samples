package org.cytoscape.sample.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.CyNetworkNaming;

import java.util.Properties;

import static java.sql.DriverManager.println;


public class CyActivator extends AbstractCyActivator {
	public CyActivator() {
		super();
	}


	public void start(BundleContext bc) {
		println("HERE 1");

		CyNetworkNaming cyNetworkNamingServiceRef = getService(bc,CyNetworkNaming.class);
		println("HERE 2");
		
		CyNetworkFactory cyNetworkFactoryServiceRef = getService(bc,CyNetworkFactory.class);
		println("HERE 3");
		CyNetworkManager cyNetworkManagerServiceRef = getService(bc,CyNetworkManager.class);
		println("HERE 4");

		CyNetworkViewFactory cyNetworkViewFactoryServiceRef = getService(bc,CyNetworkViewFactory.class);
		println("HERE 5");
		CyNetworkViewManager cyNetworkViewManagerServiceRef = getService(bc,CyNetworkViewManager.class);
		println("HERE 6");

//		CyApplicationManager cyApplicationManagerServiceRef = getService(bc,CyApplicationManager.class);
		VisualMappingManager vmmServiceRef = getService(bc,VisualMappingManager.class);

		CyLayoutAlgorithmManager layoutManager = getService(bc, CyLayoutAlgorithmManager.class);
		
		CreateNetworkViewTaskFactory createNetworkViewTaskFactory = new CreateNetworkViewTaskFactory(cyNetworkNamingServiceRef, cyNetworkFactoryServiceRef,cyNetworkManagerServiceRef, cyNetworkViewFactoryServiceRef,cyNetworkViewManagerServiceRef, vmmServiceRef, layoutManager);
		println("HERE 7");
		Properties createNetworkViewTaskFactoryProps = new Properties();
		println("HERE 8");
		createNetworkViewTaskFactoryProps.setProperty("preferredMenu","Apps.Samples");
		println("HERE 9");
		createNetworkViewTaskFactoryProps.setProperty("title","Create Network View");
		println("HERE 10");
		registerService(bc,createNetworkViewTaskFactory,TaskFactory.class, createNetworkViewTaskFactoryProps);
		println("HERE 11");
	}
}

