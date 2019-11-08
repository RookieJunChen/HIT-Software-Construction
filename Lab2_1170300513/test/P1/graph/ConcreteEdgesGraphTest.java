/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


import java.util.Collections;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
	public ConcreteEdgesGraph<String> test = new ConcreteEdgesGraph<>();
	String s1 = "Test1";
	String s2 = "Test2"; 
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
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
    	test.set(s2, s1, 3);
    	test.set(s2, s1, 2);
    	test.set(s1, s2, 0);
    	test.set(s2, s1, 0);
    	
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
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   To print the test and compare it to a answer String.
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testtoString()
    {
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	String answer = "Vertices: \n" + 
    			"Test1\n" + 
    			"Test2\n" + 
    			"Edges: \n" + 
    			"(Test1,Test2) : 3\n";
    	assertEquals(answer, test.toString());
    }
    
    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    //   Use methods in classes to compare values.
    
    // TODO tests for operations of Edge
    @Test
    public void testEdge()
    {
    	Edge<String> e = new Edge<String>(s1, s2, 5);
    	assertEquals(s1, e.getsource());
    	assertEquals(s2, e.gettarget());
    	assertEquals(5, e.getweight());
    	String answer = "(Test1,Test2) : 5";
    	assertEquals(answer, e.toString());
    }
}
