package org.cytoscape.sample.internal;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class CreateNetworkViewTaskFactory extends AbstractTaskFactory {

	private final CyNetworkFactory cnf;
	private final CyNetworkViewFactory cnvf;
	private final CyNetworkViewManager networkViewManager;
	private final CyNetworkManager networkManager;
	private final CyNetworkNaming cyNetworkNaming;

	private final VisualMappingManager vmmServiceRef;

	private final CyLayoutAlgorithmManager layoutManager;
	
	public CreateNetworkViewTaskFactory(CyNetworkNaming cyNetworkNaming, CyNetworkFactory cnf, CyNetworkManager networkManager, CyNetworkViewFactory cnvf,
			 final CyNetworkViewManager networkViewManager, final VisualMappingManager vmmServiceRef, final CyLayoutAlgorithmManager layoutManager){

		this.cnf = cnf;
		this.cnvf = cnvf;
		this.networkViewManager = networkViewManager;
		this.networkManager = networkManager;
		this.cyNetworkNaming = cyNetworkNaming;

		this.vmmServiceRef = vmmServiceRef;
		this.layoutManager = layoutManager;
	}
	
	public TaskIterator createTaskIterator(){
		return new TaskIterator(new CreateNetworkViewTask(cyNetworkNaming, cnf,networkManager, cnvf, networkViewManager, vmmServiceRef, layoutManager));
	}
}
