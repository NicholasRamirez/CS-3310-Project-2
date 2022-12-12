import java.util.*;
import java.math.*;
import java.util.concurrent.ThreadLocalRandom;

public class ShortestPath
{
	public static double start, end = 0;
	final static int INF = 9999;
	public static double density = 0.1; //change this value between 0 and 1 to determine the density of the graph. The closer to 1 the denser, the closer to 0 the more sparse.
	public static void main(String[] args) 
	{
		int[][] graph = new int[][] 
		{
			{0,7,INF,INF,INF},
			{INF,0,9,INF,4},
			{5,INF,0,8,INF},
			{8,INF,INF,0,INF},
			{7,2,1,4,0}
		};
		int[][] d = new int[graph.length][graph.length];
	    int[][] f = new int[graph.length][graph.length];
		System.out.println("Base case graph:");
		printGraph(graph);
		System.out.println();


		start = System.nanoTime(); 
	    d = dijkstra(graph, 0);
	    end = System.nanoTime();
		printGraph(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println();
		start = System.nanoTime();
	    f = floydWarshall(graph);
	    end = System.nanoTime();
		printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		Scanner s = new Scanner(System.in);
		System.out.print("Enter density value between 0 and 1 (closer to 0 is sparse | closer to 1 is dense): ");
		density = s.nextDouble();


		System.out.println("random graph 10x10 printed to show density:");
		int randomGraph[][] = randomGraphGenerator(10);
		printGraph(randomGraph);
		System.out.println("\n");
		System.out.println("10 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(25);
		System.out.println("25 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(50);
		System.out.println("50 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(100);
		System.out.println("100 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(500);
		System.out.println("500 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(1000);
		System.out.println("1000 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		randomGraph = randomGraphGenerator(2000);
		System.out.println("2000 Vertices test:");
		start = System.nanoTime(); 
	    d = dijkstra(randomGraph, 0);
	    end = System.nanoTime();
		//printDijkstra(d);
	    System.out.println("dijkstra Run Time: " + (end-start) / 1000000000 + " Seconds");
		start = System.nanoTime();
	    f = floydWarshall(randomGraph);
	    end = System.nanoTime();
		//printGraph(f); 
	    System.out.println("Floyd Run Time: " + (end-start) / 1000000000 + " Seconds");
		System.out.println("\n");


		
	}
	public static int[][] dijkstra(int[][] graph, int source) 
	{
		int numVertices = graph.length;
		boolean[] vertexCheck = new boolean[numVertices];
		int[][] newGraph = new int[numVertices][numVertices];

		while (source < graph.length)
		{
			for (int i = 0; i < numVertices; i++) 
			{
		  		vertexCheck[i] = false;
		  		newGraph[source][i] = INF;
			}
		
			newGraph[source][source] = 0;
			for (int i = 0; i < numVertices; i++) 
			{
		  		int minDistance = minDistance(newGraph, vertexCheck, source);
		  		vertexCheck[minDistance] = true;
		  	
		  		for (int j = 0; j < numVertices; j++) 
				{
					if (!vertexCheck[j] && graph[minDistance][j] != 0 && (newGraph[source][minDistance] + graph[minDistance][j] < newGraph[source][j])) 
			  			newGraph[source][j] = newGraph[source][minDistance] + graph[minDistance][j];
		  		}
			}
			source += 1;
		}
		return newGraph;
	}
	private static int minDistance(int[][] newGraph, boolean[] vertexCheck, int source) 
	{
		int minDistance = Integer.MAX_VALUE;
		int minDistanceVertex = -1;

		for (int i = 0; i < newGraph.length; i++) 
		{
		  	if (!vertexCheck[i] && newGraph[source][i] < minDistance)
		  	{
				minDistance = newGraph[source][i];
				minDistanceVertex = i;
		  	}
		}
		return minDistanceVertex;
	}
	public static int[][] floydWarshall(int graph[][])
	{
		int numVertices = graph.length;
		int[][] newGraph = new int[numVertices][numVertices];
	
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
				newGraph[i][j] = graph[i][j];
		}
		for (int i = 0; i < numVertices; i++) 
		{
			for (int j = 0; j < numVertices; j++) 
		  	{
				for (int k = 0; k < numVertices; k++) 
				{
			  		if (newGraph[j][i] + newGraph[i][k] < newGraph[j][k])
						newGraph[j][k] = newGraph[j][i] + newGraph[i][k];
				}
		  	}
		}
		return newGraph;
	}
	public static int[][] randomGraphGenerator(int numVertices) 
	{
        int[][] randomGraph = new int[numVertices][numVertices];
        Random r = new Random();
		

        for (int i = 0; i < numVertices; i++) 
		{
            for (int j = i + 1; j < numVertices; j++) 
			{
                if (ThreadLocalRandom.current().nextFloat() % 1 < density)
					randomGraph[i][j] = r.nextInt(20);
				else 
					randomGraph[i][j] = INF;
                	

				if (ThreadLocalRandom.current().nextFloat() % 1 < density)
					randomGraph[j][i] = r.nextInt(20);
				else 
					randomGraph[j][i] = INF;
					
            }
        }
        return randomGraph;
    }
	public static void printGraph(int graph[][]) 
	{
    	int numVertices = graph.length;
      	for (int i = 0; i < numVertices; ++i) 
		{
        	for (int j = 0; j < numVertices; ++j) 
			{
          		if (graph[i][j] == INF)
            		System.out.print("INF\t");
          		else
            		System.out.print(graph[i][j] + "\t");
        	}
        	System.out.println();
      	}
    }
}