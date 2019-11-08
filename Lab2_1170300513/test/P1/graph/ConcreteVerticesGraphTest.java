/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest. 
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    public  ConcreteVerticesGraph<String> test = new ConcreteVerticesGraph<>();
    String s1 = "Test1";
	String s2 = "Test2";
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    } 
    
    /*
     * Testing ConcreteVerticesGraph...
     */

    @Test
    public void testadd()
    {
    	test.add(s1);
    	Set<String> answers = new HashSet<>();
    	answers.add(s1);
    	assertEquals(false, test.add(s1));
    	assertEquals(answers, test.vertices());
    }
    
    @Test
    public void testset()
    {
    	test.set(null, null, 0);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), test.vertices());
    	test.set(s1, s2, 0);
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	test.set(s1, s2, 2);
    	test.set(s1, s2, 0);
    	
    }
    
    @Test
    public void testremove()
    {
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 1);
    	test.set(s2, s1, 1);
    	test.remove(s1);
    	Set<String> answers = new HashSet<>();
    	test.remove(s1);
    	answers.add(s2);
    	assertEquals(answers, test.vertices());
    }
    
    @Test
    public void testsource()
    {
    	Map<String, Integer> answers = new TreeMap<String, Integer>();
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	answers.put(s1, 3);
    	assertEquals(answers, test.sources(s2));
    }
    
    @Test
    public void testtarget()
    {
    	Map<String, Integer> answers = new TreeMap<String, Integer>();
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	answers.put(s2, 3);
    	assertEquals(answers, test.targets(s1));
    }

    // Testing strategy for ConcreteVerticesGraph.toString()
    //   To print the test.
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testtoString()
    {
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	String answer = "vertex: Test1\n" + 
    			"inedges: \n" + 
    			"outedges: (Test2,3) \n" + 
    			"\n" + 
    			"vertex: Test2\n" + 
    			"inedges: (Test1,3) \n" + 
    			"outedges: \n\n" ;
    	System.out.println(test);
    	assertEquals(answer, test.toString());
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   Use methods in classes to compare values.
    
    // TODO tests for operations of Vertex
    @Test
    public void testVertex()
    {
    	List<myEdge<String>> out = new ArrayList<>();
    	List<myEdge<String>> in = new ArrayList<>();
    	Vertex<String> v = new Vertex<String>(s1);
    	assertEquals(s1, v.getname());
    	assertEquals(out, v.getoutedges());
    	assertEquals(in, v.getinedges());
    	String answer = "vertex: Test1\n" + 
    			"inedges: \n" + 
    			"outedges: \n";
    	assertEquals(answer, v.toString());
    }
}
