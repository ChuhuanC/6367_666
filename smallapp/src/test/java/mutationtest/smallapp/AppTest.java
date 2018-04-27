package mutationtest.smallapp;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @Test
    
    public void testApp()
    {
    		App a = null;
        assertEquals(null ,a.add(0, 1));
    }

}
