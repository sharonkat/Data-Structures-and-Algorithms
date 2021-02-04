import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	
    	int numOfNodes = g.getNbNodes();						// number of nodes to create disjoint sets
        DisjointSets d = new DisjointSets(numOfNodes);
    	ArrayList<Edge> listOfEdge = g.listOfEdgesSorted();		// list of nodes to iterate through
    	WGraph mst = new WGraph();								// graph to return
    	
    	int min = 99999;
    	int prevMin;
    	for (Edge e : listOfEdge) {
    		prevMin = min;
    		if (e.weight < prevMin) {
    			min = e.weight;				// this is the lowest weight
    		}
    	}
    	
        Boolean isSafe;
        for (Edge e : listOfEdge) {
        	isSafe = IsSafe(d, e);
        	if (isSafe == true) {
        		d.union(e.nodes[0], e.nodes[1]);			// This will remove them from disjoint sets
        		mst.addEdge(e);
        	}        	
        }
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	
    	int par0 = p.find(e.nodes[0]);
    	int par1 = p.find(e.nodes[1]);
    	
    	if (par0 != par1) {
    		return true;
    	}
        return false;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
