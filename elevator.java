public class Solution {
    
	static int kayCmp(String v1, String v2)
	{
		String[] version1 = v1.split("\\.");
		String[] version2 = v2.split("\\.");
		for(int i=0;i<Math.min(version1.length, version2.length);i++) 
		{
			int a = Integer.parseInt(version1[i]);
			int b = Integer.parseInt(version2[i]);
			if(a==b) 
			{
				continue;
			}
			else 
			{
				return a-b;
			}
		}
		return version1.length-version2.length;
	}
	
	public static String[] solution(String[] l) 
	{
		for (int i = 0; i < l.length; i++)
      			for (int j = 0; j < l.length - 1; j++)
        			if (kayCmp(l[j], l[j + 1]) > 0) 
        			{
				    String temp = l[j];
				    l[j] = l[j + 1];
				    l[j + 1] = temp;
        			}
        	return l;
	}
}
