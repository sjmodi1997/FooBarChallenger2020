/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Point
{
  public int x;
  public int y;
  public int xInd;
  public int yInd;
  
  public Point(int x, int y, int a, int b)
  {
      this.x = x;
      this.y = y;
      this.xInd = a;
      this.yInd = b;
  }
};

class Pair
{
    public int x;
    public int y;
    
    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair key = (Pair) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
};

class Codechef
{
    public static HashMap<Pair,Integer> map = new HashMap<Pair,Integer>();
    
    public static Double findDistance(Pair p1, Pair p2)
    {
        int x1 = p1.x;
        int y1 = p1.y;
        
        int x2 = p2.x;
        int y2 = p2.y;
        
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }
    
    public static Double angle(Pair p1, Pair p2) 
    {
        int x1 = p1.x;
        int y1 = p1.y;
        
        int x2 = p2.x;
        int y2 = p2.y;
        
        return Math.atan2(x2 - x1, y2-y1);
    }
    
    public static ArrayList<Pair> bfs(int[] source, int width, int height, int distance, int[] your_position)
    {
        ArrayList <Pair> list = new ArrayList <Pair> ();
                  
        int[] segment0 = {width - source[0], source[0]};
        int[] segment1 = {height - source[1], source[1]};
        
        Pair your_pos = new Pair(your_position[0],your_position[1]);
        Queue<Point> q = new LinkedList<>();
        Point sr = new Point(source[0],source[1],0,0);
        Pair pr = new Pair(source[0],source[1]); 
        if(findDistance(your_pos,pr)<=distance)
        {
            q.add(sr);
            map.put(pr,1);
            list.add(pr);
        }
        
        while(q.size()!=0)
        {
            Point tp = q.peek();
            q.remove();
            Pair temp = new Pair (tp.x,tp.y);
            
            int[] nX = {tp.x-2*(segment0[1-tp.xInd]), tp.x+2*(segment0[tp.xInd])};
            
            int[] nY = {tp.y-2*(segment1[1-tp.yInd]), tp.y+2*(segment1[tp.yInd])};
            
            for(int i=0;i<2;i++)
            {
                Pair next = new Pair (nX[i],tp.y);
                if(map.containsKey(next)==false && findDistance(your_pos,next)<=distance)
                {
                    map.put(next,1);
                    list.add(next);
                    Point pt = new Point(nX[i],tp.y,1-tp.xInd,tp.yInd);
                    q.add(pt);
                }
            }
            
            for(int j=0;j<2;j++)
            {
                Pair next = new Pair (tp.x,nY[j]);
                if(map.containsKey(next)==false && findDistance(your_pos,next)<=distance)
                {
                    map.put(next,1);
                    list.add(next);
                    Point pt = new Point(tp.x,nY[j],tp.xInd,1-tp.yInd);
                    q.add(pt);
                }
            }
        }
        return list;
    }
    
    public static HashMap<Double,Double> fillMap(ArrayList<Pair> list, int[] your_position)
    {
        HashMap<Double,Double> mp = new HashMap<Double,Double>();
        Pair your_pos = new Pair(your_position[0],your_position[1]);
        for(int i=0;i<list.size();i++)
        {
            Pair pr = list.get(i);
            Double r = findDistance(pr, your_pos);
            Double angle = angle(pr, your_pos);
            if(pr.x==your_pos.x && pr.y==your_pos.y)
            {
                continue;
            }
            if(mp.containsKey(angle))
            {
                Double val = mp.get(angle);
                val = Math.min(val,r);
                mp.put(angle,val);
            }
            else
            {
                mp.put(angle,r);    
            }
        }
        return mp;
    }
    
    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) 
    {
        int width = dimensions[0];
        int height = dimensions[1];
        
        ArrayList<Pair> tagetList = bfs(guard_position,width,height,distance,your_position);
        /*for(int i=0;i<tagetList.size();i++)
        {
            Pair temp = tagetList.get(i);
            System.out.println(temp.x + " , " + temp.y);
        }*/
        //System.out.println(tagetList.size());
        ArrayList<Pair> capList = bfs(your_position,width,height,distance,your_position);
        
        HashMap<Double,Double> targetMap = fillMap(tagetList,your_position);
        
        /*for(Double key : targetMap.keySet())
        {
            System.out.println(key + " => " + targetMap.get(key));
        }*/
        
        HashMap<Double,Double> capMap = fillMap(capList,your_position);
        
        int ans = 0;
        int cnt = 0;
        
        for (Double key : targetMap.keySet()) {
            cnt ++;
            if (capMap.containsKey(key)) {
                if (targetMap.get(key) < capMap.get(key)) {
                    ans++;
                }
            }
            else {
                ans++;
            }
        }
        //System.out.println(cnt);
        return ans;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] dimensions = new int[2];
		dimensions[0] = 23;
		dimensions[1] = 10;
		
		int[] your_position = new int[2];
		your_position[0] = 6;
		your_position[1] = 4;
		
		int[] guard_position = new int[2];
		guard_position[0] = 3;
		guard_position[1] = 2;
		
		int distance = 23; 
		int ans = solution(dimensions,your_position,guard_position,distance);
		System.out.println(ans);
	}
}


// this solution didn't worked out in JAVA!
