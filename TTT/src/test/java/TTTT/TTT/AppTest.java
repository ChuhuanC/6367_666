package TTTT.TTT;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	@Test
	
	public void testApp1()
	{
		App a = new App();
		assertEquals(2,a.eq(2,1));
	}

	
	@Test
	
	public void testApp2()
	{
		App a = new App();
		assertEquals(2,a.add(1,1));
	}
	
	@Test
	
	public void testApp3()
	{
		App a = new App();
		assertEquals(2,a.minus(3,1));
	}
	
	@Test
	
	public void testApp4()
	{
		App a = new App();
		assertEquals(2,a.ABStest(2));
	}
	
	@Test

	public void testApp5()
	{
	App a = new App();
	assertEquals(2,a.UOI1test(1));
	}

	@Test

	public void testApp6()
	{
	App a = new App();
	assertEquals(2,a.UOI23test(2));
	}

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
