import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

import javafx.scene.shape.Path;




public class FordFulkerson {


	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE */
		Stack.add(source);
		
		ArrayList<Integer> Visited = new ArrayList<Integer>();
		Visited.add(source);
		
		int current;
		
		// Iterate through the stack while you haven't reached the destination
		while (!(Stack.isEmpty())
		    && !(destination.equals(Stack.get(Stack.size()-1)))){
			// INDEXING!!! Finally ;_;
			current = Stack.get(Stack.size()-1);
			int index = 0;
			boolean isGood = false;
			// While we haven't used every edge possible...
			while (!isGood && (index < graph.listOfEdgesSorted().size())) {
				Edge currentEdge = graph.listOfEdgesSorted().get(index);
				// If the edge's directed node is the one we're looking at
				// and we haven't yet visited the node it is pointing to
				// and the weight is non-negative --> add the node to our path!
				if ((currentEdge.nodes[0] == current) 
				 && (!Visited.contains(currentEdge.nodes[1]))
				 && (currentEdge.weight>0)) {
					isGood = true;
					Stack.add(currentEdge.nodes[1]);
					Visited.add(currentEdge.nodes[1]);
				}
//				System.out.println("Our stack "+Stack);
				index++;
			}
			if (!isGood) {
//				System.out.println("Our bad stack "+Stack);
				Stack.remove(Stack.get(Stack.size()-1));
			}
		}
		
		
		return Stack;
	}
	
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260789251"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		/* YOUR CODE GOES HERE */
		// Make a residual graph and populate it with the given capacities in the original graph.
		// FF algorithm calculates the max flow by finding the !!!
		
		WGraph graphFlow = new WGraph(graph);
		ArrayList<Integer> Path = pathDFS(source, destination, graph);
		
		while (Path.size() > 1) {
			int flow = Integer.MAX_VALUE;
			for (int i=0; i<Path.size()-1; i++) {
				Edge e = graph.getEdge(Path.get(i), Path.get(i+1));
				if (e.weight < flow) {
					flow = e.weight;
				}
			}
			// Calculating residual graphs
			for (int i=0; i<Path.size()-1; i++) {
				Edge e = graph.getEdge(Path.get(i), Path.get(i+1));
				graph.setEdge(Path.get(i), Path.get(i+1), e.weight-flow);
				
				Edge backwardsEdge = graph.getEdge(Path.get(i+1), Path.get(i));
				if (backwardsEdge == null) {
					Edge newE = new Edge(Path.get(i+1), Path.get(i), flow);
					graph.addEdge(newE);
				} else {
					graph.setEdge(Path.get(i+1), Path.get(i), backwardsEdge.weight+flow);
				}
			}
			maxFlow += flow;
			Path = pathDFS(source, destination, graph);
		}
		
		ArrayList<Edge> residualE = graph.getEdges();
		ArrayList<Edge>	graphFlowE = graphFlow.getEdges();
		
		// Determining which edge we will use for max flow
		for (int i=0; i<graphFlowE.size(); i++) {
			Edge flowEdge = graphFlowE.get(i);
			Edge residualEdge = null;
			for (int j=0; j<residualE.size(); j++) {
				residualEdge = residualE.get(j);
				if (residualEdge.nodes[0] == flowEdge.nodes[0] 
				 && residualEdge.nodes[1] == flowEdge.nodes[1]) {
					break;
				}
			}
			graphFlow.setEdge(flowEdge.nodes[0], flowEdge.nodes[1], flowEdge.weight-residualEdge.weight);
		}
		graph = graphFlow;
				
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesn't exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
