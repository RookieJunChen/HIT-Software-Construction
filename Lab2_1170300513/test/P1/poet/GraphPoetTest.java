/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;


import org.junit.Test;



/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest  {
	
	 
    //Testing strategy 
    //TODO Comparing Output with Correct Answer.
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testpoem()
    {
    	try 
    	{
    		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\mugar-omni-theater.txt"));
    		String answer = "Test of the system.";
    		String input = "Test the system.";
    		assertEquals(answer, nimoy.poem(input));
		} catch (Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    @Test
    public void testtoString()
    {
    	try 
    	{
    		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\mugar-omni-theater.txt"));
    		String str = "Vertices: \n" + 
    				"the\n" + 
    				"targets: <Mugar,1>  \n" + 
    				"sources: <of,1>  \n" + 
    				"a\n" + 
    				"targets: <test,1>  \n" + 
    				"sources: <is,1>  \n" + 
    				"Theater\n" + 
    				"targets: <sound,1>  \n" + 
    				"sources: <Omni,1>  \n" + 
    				"Mugar\n" + 
    				"targets: <Omni,1>  \n" + 
    				"sources: <the,1>  \n" + 
    				"test\n" + 
    				"targets: <of,1>  \n" + 
    				"sources: <a,1>  \n" + 
    				"of\n" + 
    				"targets: <the,1>  \n" + 
    				"sources: <test,1>  \n" + 
    				"sound\n" + 
    				"targets: <system.,1>  \n" + 
    				"sources: <Theater,1>  \n" + 
    				"This\n" + 
    				"targets: <is,1>  \n" + 
    				"sources: \n" + 
    				"is\n" + 
    				"targets: <a,1>  \n" + 
    				"sources: <This,1>  \n" + 
    				"Omni\n" + 
    				"targets: <Theater,1>  \n" + 
    				"sources: <Mugar,1>  \n" + 
    				"system.\n" + 
    				"targets: \n" + 
    				"sources: <sound,1>  \n";
			assertEquals(str, nimoy.toString());
		}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
  
}
