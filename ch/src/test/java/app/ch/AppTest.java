package app.ch;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	@Test
	
	public void testApp()
	{
		App a = new App();
		assertEquals(1,a.eq(1,1));
	}

}
