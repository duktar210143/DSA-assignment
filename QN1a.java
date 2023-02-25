// QN1 a> There are n nations linked by train routes. You are given a 2D array indicating routes between 
// countries and the time required to reach the target country, such that E[i]=[xi,yi,ki], 
// where xi represents the source country, yi represents the destination country, and ki represents the
//  time required to go from xi to yi. If you are also given information on the charges, you must pay
//  while entering any country. Create an algorithm that returns the cheapest route from county 
//  A to county B with a time constraint.

import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int vertex, cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node node) {
        return this.cost - node.cost;
    }
}

class ShortestPath {
    int V, E;
    ArrayList<Edge> edges;
    int[] charges;
    int[] dist;
    int[] time;
    boolean[] visited;

    ShortestPath(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new ArrayList<>();
        charges = new int[V];
        dist = new int[V];
        time = new int[V];
        visited = new boolean[V];
    }

    void dijkstra(int source) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(time, Integer.MAX_VALUE);
        PriorityQueue<Node> queue = new PriorityQueue<>();

        dist[source] = charges[source];
        time[source] = 0;

        queue.add(new Node(source, dist[source]));

        while (!queue.isEmpty()) {
            int u = queue.poll().vertex;

            if (visited[u])
                continue;

            visited[u] = true;

            for (Edge edge : edges) {
                if (edge.src == u) {
                    int v = edge.dest;
                    
                    int w = edge.weight;

                    if (time[v] > time[u] + w && time[u] + w <= 13) {
                        time[v] = time[u] + w;
                        dist[v] = dist[u] + charges[v];

                        queue.add(new Node(v, dist[v]));
                    }
                }
            }
        }
    }

    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public static void main(String[] args) {
        int V = 6;
        int E = 6;
        ShortestPath graph = new ShortestPath(V, E);

        int[] charges = {10, 2, 3, 25, 25, 4};
        graph.charges = charges;

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(3, 4, 5);
        graph.addEdge(4, 5, 6);
        graph.addEdge(2, 5, 5);

        int source = 0;
        int dest = 5;
        graph.dijkstra(source);

        System.out.println("Minimum Cost to Reach Destination: " + graph.dist[dest]);
        System.out.println("Minimum Time to Reach Destination: " + graph.time[dest]);
    }
}
