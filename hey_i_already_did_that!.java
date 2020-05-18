public static char[] mySort(char[] array)
	{
		for (int i = 1; i < array.length; i++) {
        	char current = array[i];
        	int j = i - 1;
        	while(j >= 0 && current < array[j]) {
            	array[j+1] = array[j];
            	j--;
        	}
        	array[j+1] = current;
    	}
    	return array;
	}
	
	public static String sortAsc(String s)
	{
		char[] chars = s.toCharArray();
		chars = mySort(chars);
        //Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
	}
	
	public static String sortDesc(String s)
	{
		char[] chars = s.toCharArray();
		//Arrays.sort(chars);
		chars = mySort(chars);
		String sorted = "";
		for(int i=chars.length-1;i>=0;i--)
		{
			sorted += chars[i];
		}
        /*for (int i = 0; i < (chars.length/2); i++) 
        {
			char temp = chars[i];
			chars[i] = chars[chars.length - 1 - i];
			chars[chars.length - 1 - i] = temp;
		}
        String sorted = new String(chars);*/
        return sorted;
	}
	
	public static String getSub(String x, String y, int b)
	{
		int xx = Integer.parseInt(x,b);
		int yy = Integer.parseInt(y,b);
		String result = Integer.toString(xx-yy,b);
		return result;
	}
	
	public static int solution(String n, int b) {
    	String z_str = n;
    	HashMap<Long, Long> map = new HashMap<>();
    	long count = 0;
    	long k = n.length();
    	if(k==1)
    		return 1;
    	while(true)
    	{
    		String x = sortDesc(n);
    		String y = sortAsc(n);
    		//System.out.println("x : "+ x);
    		//System.out.println("y : "+ y);
    		
    		long x_dec = Long.parseLong(x,b);
    		long y_dec = Long.parseLong(y,b);
    		long z = x_dec-y_dec;
    		if(z<b)
    			return 1;
    		if(map.containsKey(z))
    		{
    			long ind = map.get(z); 
    			return (int)(count-ind);
    		}
    		else
    		{
    			map.put(z,count);
    			n = Long.toString(z,b);
    		}
    		//map.put(x,count);
    		if(n.length()<k)
    		{
    			n += '0';
    		}
    		//System.out.println("z : "+ z);
    		count++;
    	}
    	//return 1;
    }
