package mutationtest.smallapp;

/**
 * Hello world!
 *
 */
public class App 
{
	public int add(int a, int b) 
	{
		return a+b;
	}
	public int minus(int a, int b) 
	{
		return a-b;
	}
	public int eq(int a, int b) 
	{
		if(a > b) 
		{
			return a/b;
		}
		return 0;
	}
    public static void main( String[] args )
    {
    	App a = null;
        a.add(1, 0);
    }
}