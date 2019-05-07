package rust;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class RustAndMurderer {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for(int t = 0; t < T; t++)
    {
        int noOfCites = sc.nextInt();
        int noOfRoads = sc.nextInt();
        HashMap<Integer,HashSet<Integer>> cityMap = new HashMap<>();


        for(int i = 0; i < noOfRoads; i++)
        {
            int source = sc.nextInt();
            int target = sc.nextInt();
            if(!cityMap.containsKey(source))
            {
        HashSet<Integer> roads = new HashSet<>(); roads.add(target);
                cityMap.put(source, roads);
            }
            else
             cityMap.get(source).add(target);

            if(!cityMap.containsKey(target)) 
            {
                HashSet<Integer> roads = new HashSet<>(); roads.add(source);
                cityMap.put(target, roads);
            }
            else
            cityMap.get(target).add(source);
          
        }
        
        int startingPoint = sc.nextInt();
        
        int[] distances = BFS_Distance(startingPoint, cityMap, noOfCites);
        
        StringBuilder output =new StringBuilder();
        for(int i = 0; i < distances.length; i++)
        {
            if(i+1 != startingPoint)
                output.append(distances[i]+" ");
        }
        System.out.println(output);
    }
}


static int[] BFS_Distance(int root, HashMap<Integer,HashSet<Integer>> graph, int noOfCites)
{
    int[] distances = new int[noOfCites];
    
    Set<Integer> unvisited = new HashSet<>();
    Set<Integer> visited = new HashSet<>();
    for(int i = 1; i <= noOfCites; i++)
    unvisited.add(i);
    
    Queue<Integer> queue = new LinkedList<>();
    
    queue.offer(root);
    unvisited.remove(root);
    distances[0] = 0;
    
    while(!queue.isEmpty())
    {
        int curr = queue.poll();
        HashSet<Integer> neighbors = graph.get(curr);
            
        for(int uv : unvisited)
        {
            if(neighbors == null || !neighbors.contains(uv))
            {
                queue.offer(uv);
                distances[uv-1] = distances[curr-1]+1;
                visited.add(uv);
            } 
        }
        unvisited.removeAll(visited);
        visited = new HashSet<>();
    }
    return distances;
}

}
