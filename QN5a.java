// Question 5
// a)
// You are given a 2D array containing coordinates and height of 
// rectangle such that height[i]=[xi,yi,hi], where xi the x coordinate of left edge, 
// yi represents x coordinate of right edge of rectangle and hi represents the height of the 
// peaks of each rectangle. If you want to construct a border line over the peaks of rectangle
//  represented in bar chart, return the key coordinates required to build a border line that 
//  contacts the peaks of the given chart.
// Note: key points are the left coordinates of shape 
// representing peaks where you need to draw boarder line.


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
class QN5a {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();
        
        List<int[]> heights=new ArrayList<>();
        
        transformBuilding(buildings,heights);
        
        //if heights of 2 points are same then place the point with smaller height first else place point with smaller starting point
        
        Collections.sort(heights,(a,b)->(a[0]==b[0]) ? a[1]-b[1] : a[0]-b[0]);// TC->O(nlog n)
        
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->(b-a));
        
        //seeding the Priority Queue
        pq.add(0);
        int prevMax=0;
        
        for(int[] height:heights){ //O(n)
            
            if(height[1]<0){
                pq.add(-height[1]);
            }
            else{
                pq.remove(height[1]); //O(log n)
            }
            
            int CurrentMax=pq.peek();
            if(CurrentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);
                
                res.add(subResult);
                prevMax=CurrentMax;
            }
        }
        
        return res;
    }
    //this will seperate the values of start point and end point with height 
    //example-->[1,2,3]-->[1,-3] & [2,3]-->here -(minus) is just for convention for starting point
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }
        
        
        
    }
    
    
    
}