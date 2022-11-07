package org.cytoscape.sample.internal;

//import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.net.http.*;
import java.util.HashMap;
import java.util.Collection;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.impl.client.DefaultHttpClient; // deprecated in 4.3
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyEdge;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.sql.DriverManager.println;

public class CreateNetworkViewTask extends AbstractTask {

	private final CyNetworkFactory cnf;
	private final CyNetworkViewFactory cnvf;
	private final CyNetworkViewManager networkViewManager;
	private final CyNetworkManager networkManager;
	private final CyNetworkNaming cyNetworkNaming;

	public CreateNetworkViewTask(CyNetworkNaming cyNetworkNaming, CyNetworkFactory cnf, CyNetworkManager networkManager,
			CyNetworkViewFactory cnvf, final CyNetworkViewManager networkViewManager) {
		this.cnf = cnf;
		this.cnvf = cnvf;
		this.networkViewManager = networkViewManager;
		this.networkManager = networkManager;
		this.cyNetworkNaming = cyNetworkNaming;
	}

	public void run(TaskMonitor monitor) throws IOException {

		// Create an empty network
		CyNetwork myNet = this.cnf.createNetwork();

		URL url = new URL("http://localhost:8000/services/person/login/");
		//Insert your JSON query request
		String query = "{'email': 'jthompson@gnshealthcare.com','password': 'secret'}";
		//It change the apostrophe char to double quote char, to form a correct JSON string
		query=query.replace("'", "\"");

		try{
			//make connection
			URLConnection urlc = url.openConnection();
			//It Content Type is so important to support JSON call
			urlc.setRequestProperty("Content-Type", "application/json");
			urlc.setRequestProperty("Accept", "application/json");
//			Msj("Conectando: " + url.toString());
			//use post mode
			urlc.setDoOutput(true);
			urlc.setAllowUserInteraction(false);

			//send query
			PrintStream ps = new PrintStream(urlc.getOutputStream());
			ps.print(query);
//			Msj("Consulta: " + query);
			ps.close();
			String token = urlc.getHeaderField("Authorization");

//			URL url2 = new URL("http://localhost:8000/services/refsmodel/defaultgraph/");
//			//Insert your JSON query request
//			String query2 = "{'biomarkers': 'number_of_lymph_nodes,Her2,LumA,histological_type,pathologic_stage,Basal,cyclophosphamide,GATA3,doxorubicin,docetaxel,paclitaxel,CDH1,TP53,anastrozole,PIK3CA,age,race,LumB,MUC4,radiation_therapy,tamoxifen','frequency': 0.2, 'max_depth': 4, 'mechanism_frequency': 0.8, 'model_id':17,'observation_frequency':'','offset':'','outcomes':'high_risk'}";
//			//It change the apostrophe char to double quote char, to form a correct JSON string
//			query2=query2.replace("'", "\"");
//
//			URLConnection urlc2 = url2.openConnection();
//			//It Content Type is so important to support JSON call
//			urlc2.setRequestProperty("Content-Type", "application/json");
//			urlc2.setRequestProperty("Accept", "application/json");
//			urlc2.setRequestProperty("Authorization", token);
//
////			Msj("Conectando: " + url.toString());
//			//use post mode
//			urlc2.setDoOutput(true);
//			urlc2.setAllowUserInteraction(false);
//
//			//send query
//			PrintStream ps2 = new PrintStream(urlc2.getOutputStream());
//			ps2.print(query2);
////			Msj("Consulta: " + query);
//			ps2.close();
//
//			//get result
//			BufferedReader br2 = new BufferedReader(new InputStreamReader(urlc2.getInputStream()));
//			//String l = null;
//			StringBuilder sb2 = new StringBuilder();
//			String line2 = new String();
//			//String responseBody = br.lines().collect(Collectors.joining());
//			while ((line2=br2.readLine())!=null) {
////				Msj(l);
//				sb2.append(line2);
//			}
//			br2.close();
//			JSONObject json = new JSONObject(sb2.toString());

			URL url2 = new URL("http://localhost:8000/services/refsmodelalltoallscores/defaultgraph/");
			//Insert your JSON query request
			String query2 = "{'metric': 'cohens_d','model_id':'17','number_of_paths':20}";
			//It change the apostrophe char to double quote char, to form a correct JSON string
			query2=query2.replace("'", "\"");

			URLConnection urlc2 = url2.openConnection();
			//It Content Type is so important to support JSON call
			urlc2.setRequestProperty("Content-Type", "application/json");
			urlc2.setRequestProperty("Accept", "application/json");
			urlc2.setRequestProperty("Authorization", token);

//			Msj("Conectando: " + url.toString());
			//use post mode
			urlc2.setDoOutput(true);
			urlc2.setAllowUserInteraction(false);

			//send query
			PrintStream ps2 = new PrintStream(urlc2.getOutputStream());
			ps2.print(query2);
//			Msj("Consulta: " + query);
			ps2.close();

			//get result
			BufferedReader br2 = new BufferedReader(new InputStreamReader(urlc2.getInputStream()));
			//String l = null;
			StringBuilder sb2 = new StringBuilder();
			String line2 = new String();
			//String responseBody = br.lines().collect(Collectors.joining());
			while ((line2=br2.readLine())!=null) {
//				Msj(l);
				sb2.append(line2);
			}
			br2.close();
			JSONObject json = new JSONObject(sb2.toString());


			JSONArray nodeArray = json.getJSONArray("nodes");
			HashMap<String, CyNode> nodeHashMap = new HashMap<String, CyNode>();
			CyNode myNode;
			for (int i = 0 ; i < nodeArray.length(); i++) {
				JSONObject obj = nodeArray.getJSONObject(i);
				myNode = myNet.addNode();
				myNet.getDefaultNodeTable().getRow(myNode.getSUID()).set("name", obj.get("name"));
				nodeHashMap.put((String) obj.get("name"), myNode);
			}

			JSONArray edgeArray = json.getJSONArray("edges");
			myNet.getDefaultEdgeTable().createColumn("cohens_d", Double.class, false);
			myNet.getDefaultEdgeTable().createColumn("tail_probability", Double.class, false);
			myNet.getDefaultEdgeTable().createColumn("metric", Double.class, true);


			for (int i = 0 ; i < edgeArray.length(); i++) {
				//obj = edgeArray.get(0);
				JSONArray edge = (JSONArray) edgeArray.get(i);
				CyEdge myEdge = myNet.addEdge(nodeHashMap.get(edge.get(0)), nodeHashMap.get(edge.get(1)), true);
				Double myDec = (Double) ((BigDecimal) edge.get(2)).doubleValue();
//				Double myDec = (Double) (double) i;
//				Double myDec = Double.valueOf(1.5);
				myNet.getDefaultEdgeTable().getRow(myEdge.getSUID()).set("metric", myDec);
			}


//			CyNode node1 = myNet.addNode();
//
//			// set name for the new node
//			myNet.getDefaultNodeTable().getRow(node1.getSUID()).set("name", "Node1");
//
//			myNet.getDefaultNetworkTable().getRow(myNet.getSUID())
//					.set("name", cyNetworkNaming.getSuggestedNetworkTitle("My Network"));

			if (myNet == null)
				return;
			this.networkManager.addNetwork(myNet);

			final Collection<CyNetworkView> views = networkViewManager.getNetworkViews(myNet);
			CyNetworkView myView = null;
			if(views.size() != 0)
				myView = views.iterator().next();

			if (myView == null) {
				// create a new view for my network
				myView = cnvf.createNetworkView(myNet);
				networkViewManager.addNetworkView(myView);
			} else {
				System.out.println("networkView already existed.");
			}

			// Set the variable destroyView to true, the following snippet of code
			// will destroy a view
			boolean destroyView = false;
			if (destroyView) {
				networkViewManager.destroyNetworkView(myView);
			}
		} catch (Exception e){
			Double x = 1.0;
//			Msj("Error ocurrido");
//			Msj(e.toString());
		}


//		URL url2 = new URL("http://localhost:8000/services/refsmodel/13/detail/");
//		//Insert your JSON query request
//		//String query2 = "{'email': 'jthompson@gnshealthcare.com','password': 'secret'}";
//		//It change the apostrophe char to double quote char, to form a correct JSON string
//		//query=query.replace("'", "\"");
//
//		try{
//			//make connection
//			URLConnection urlc = url.openConnection();
//			//It Content Type is so important to support JSON call
//			//urlc.setRequestProperty("Content-Type", "application/json");
////			Msj("Conectando: " + url.toString());
//			//use get mode
//			urlc.setDoOutput(false);
//			urlc.setAllowUserInteraction(false);
//
//			//send query
//			PrintStream ps = new PrintStream(urlc.getOutputStream());
//			ps.print(query);
////			Msj("Consulta: " + query);
//			ps.close();
//
//			//get result
//			BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//			String l = null;
//			while ((l=br.readLine())!=null) {
////				Msj(l);
//				System.out.println(l);
//			}
//			br.close();
//		} catch (Exception e){
//			Double x = 1.0;
////			Msj("Error ocurrido");
////			Msj(e.toString());
//		}


//		HttpClient httpclient = HttpClients.createDefault();
//		HttpPost httppost = new HttpPost("http://localhost:8000/services/person/login/");
//
//// Request parameters and other properties.
//		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//		params.add(new BasicNameValuePair("email", "jthompson@gnshealthcare.com"));
//		params.add(new BasicNameValuePair("password", "secret"));
////		params.add(new BasicNameValuePair("param-1", "12345"));
////		params.add(new BasicNameValuePair("param-2", "Hello!"));
//		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
////
//////Execute and get the response.
//		HttpResponse response = httpclient.execute(httppost);
//		HttpEntity entity = response.getEntity();
//
//		if (entity != null) {
//			try (InputStream instream = entity.getContent()) {
//				// do something useful
//			}
//		}
//
//		String rawData = "id=10";
//		String type = "application/x-www-form-urlencoded";
//		String encodedData = URLEncoder.encode( rawData, "UTF-8" );
//		URL u = new URL("http://www.example.com/page.php");
//		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
//		conn.setDoOutput(true);
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty( "Content-Type", type );
//		conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
//		OutputStream os = conn.getOutputStream();
//		os.write(encodedData.getBytes());

//		String postURL = "http://localhost:8000/services/person/login/";

//		HttpPost post = new HttpPost(postURL);

//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("email", "jthompson@gnshealthcare.com"));
//		params.add(new BasicNameValuePair("password", "secret"));

//		UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, "UTF-8");
//		post.setEntity(ent);
//
//		//HttpClient client = new DefaultHttpClient();
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		HttpResponse responsePOST = client.execute(post);
//		System.err.format("responsePOST is: %s\n", responsePOST);

//		HashMap<String, String> values = new HashMap<String, String>() {{
//			put("name", "John Doe");
//			put ("occupation", "gardener");
//		}};
//
//		var objectMapper = new ObjectMapper();
//		String requestBody = objectMapper
//				.writeValueAsString(values);
//
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://httpbin.org/post"))
//				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
//				.build();
//
//		HttpResponse<String> response = client.send(request,
//				HttpResponse.BodyHandlers.ofString());
//
//		System.out.println(response.body());

		// add a node to the network
//		CyNode node1 = myNet.addNode();
//
//		// set name for the new node
//		myNet.getDefaultNodeTable().getRow(node1.getSUID()).set("name", "Node1");
//
//		myNet.getDefaultNetworkTable().getRow(myNet.getSUID())
//				.set("name", cyNetworkNaming.getSuggestedNetworkTitle("My Network"));
//
//		if (myNet == null)
//			return;
//		this.networkManager.addNetwork(myNet);
//
//		final Collection<CyNetworkView> views = networkViewManager.getNetworkViews(myNet);
//		CyNetworkView myView = null;
//		if(views.size() != 0)
//			myView = views.iterator().next();
//
//		if (myView == null) {
//			// create a new view for my network
//			myView = cnvf.createNetworkView(myNet);
//			networkViewManager.addNetworkView(myView);
//		} else {
//			System.out.println("networkView already existed.");
//		}
//
//		// Set the variable destroyView to true, the following snippet of code
//		// will destroy a view
//		boolean destroyView = false;
//		if (destroyView) {
//			networkViewManager.destroyNetworkView(myView);
//		}
	}

//	private static void Msj(String texto){
//		System.out.println(texto);
//	}

}
