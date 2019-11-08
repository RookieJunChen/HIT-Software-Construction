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
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
	int s1 = 1 , s2 =2;
	//添加整数类的检验。
	Graph<Integer> test = Graph.empty();
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testadd()
    {
    	test.add(s1);
    	Set<Integer> answers = new HashSet<>();
    	answers.add(s1);
    	assertEquals(answers, test.vertices());
    	
    }
    
    @Test
    public void testset()
    {
    	test.set(null, null, 0);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), test.vertices());
    }
    
    @Test
    public void testremove()
    {
    	test.add(s1);
    	test.add(s2);
    	test.remove(s1);
    	Set<Integer> answers = new HashSet<>();
    	answers.add(s2);
    	assertEquals(answers, test.vertices());
    }
    
    @Test
    public void testsource()
    {
    	Map<Integer, Integer> answers = new TreeMap<Integer, Integer>();
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	answers.put(s1, 3);
    	assertEquals(answers, test.sources(s2));
    }
    
    @Test
    public void testtarget()
    {
    	Map<Integer, Integer> answers = new TreeMap<Integer, Integer>();
    	test.add(s1);
    	test.add(s2);
    	test.set(s1, s2, 3);
    	answers.put(s2, 3);
    	assertEquals(answers, test.targets(s1));
    }
}
