/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


import java.util.Collections;

import java.util.Set;

import org.junit.Test;
 
/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //  emptyInstance()
	//  no inputs, only output is empty graph
	//  observe with vertices()
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    //下列方法检验均为构造空集来检验是否为空。
    @Test
    public void testadd()
    {
    	Graph<String> g = emptyInstance();
    	Set<String> answer = g.vertices();
    	answer.add("null");
    	g.add("null");
    	assertEquals(answer, g.vertices());
    	
    }
    
    @Test
    public void testset()
    {
    	Graph<String> g = emptyInstance();
    	g.set(null, null, 0);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), g.vertices());
    }
    
    @Test
    public void testremove()
    {
    	Graph<String> g = emptyInstance();
    	g.remove(null);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), g.vertices());
    }
    
    @Test
    public void testsource()
    {
    	Graph<String> g = emptyInstance();
    	g.sources(null);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), g.vertices());
    }
    
    @Test
    public void testtarget()
    {
    	Graph<String> g = emptyInstance();
    	g.targets(null);
    	assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), g.vertices());
    }
}
