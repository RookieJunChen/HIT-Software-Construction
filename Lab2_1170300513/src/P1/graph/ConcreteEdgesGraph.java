/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;



/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // 集合vertices中的每一个元素对应图中的点，类edges的列表中每一条边对应图中的边。
    
    // Representation invariant:
    // vertices != null
    // edges != null
    // Edge中起始点跟终止点必须存在于点集vertices中。
   
    
    // Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
    
    // TODO constructor
    public  ConcreteEdgesGraph() 
    {
		
	}
    
    // TODO checkRep
    public void checkRep()
    {
    	assert vertices != null;
    	assert edges != null;
    }
    

    

    
    @Override public boolean add(L vertex) {
    	//将已有点集中元素与新的点进行对比，如果名字相同说明重复，返回false.
    	for(L v : vertices) 
        {
        	if(v.equals(vertex))
        	{
        		return false;
        	}
        }
      //如不重复则将该点添加入点集并返回true。
        vertices.add(vertex);
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        Edge<L> newone = new Edge<L>(source, target, weight);
        int i , flag = 0;
      //判断source和target两点是否在点集内，如若不在则返回-1.
        for(L v : vertices) 
        {
        	if(v.equals(source))
        		flag++;
        	if(v.equals(target))
        		flag++;
        }
        if(flag != 2)
        {
        	return -1;
        }
        //判断该边是否已在边集中。
        for(i = 0 ; i < edges.size() ; i++)
        {
        	Edge<L> edge = edges.get(i);
        	//边在该边集中。
        	if(edge.getsource().equals(source) && edge.gettarget().equals(target))
        	{
        		//新权值为0说明要移除该边。
        		if(weight == 0)
        		{
        			edges.remove(i);
        		}
        		//新权值不为0则修改改权值。
        		else
        		{
        			edges.set(i, newone);
        		}
        		//返回原权值。
        		return edge.getweight();
        	}
        }
        //不在边集中则向边集添加该边并返回0.
        edges.add(newone);
        checkRep();
        return 0;
    }
    
    
    @Override public boolean remove(L vertex) {
    	//寻找点并删除，同时删除与其相连的边。成功返回true,失败返回false。
        for(L v : vertices)
        {
        	if(v.equals(vertex))
        	{
        		vertices.remove(v);
        		for(Edge<L> edge : edges)
        		{
        			if(edge.getsource().equals(v) || edge.gettarget().equals(v))
        			{
        				edges.remove(edge);
        			}
        		}
        		checkRep();
        		return true;
        	}
        }
        checkRep();
        return false;
    }
    
    
    @Override public Set<L> vertices() {
    	//返回点集的引用即可。
        return vertices;
    }
    
    //自行添加，返回边集引用，为方便测试。
    public List<Edge<L>> edges() 
    {
		return edges;
	}
    
    @Override public Map<L, Integer> sources(L target) {
    	//在边集中寻找target点，将其相应的source点和入边的权值这一对键值对记入键值对集合中。
    	Map<L, Integer> sourcesguide = new TreeMap<L, Integer>();
    	for(Edge<L> edge : edges)
    	{
    		if(edge.gettarget().equals(target))
    		{
    			sourcesguide.put(edge.getsource(), edge.getweight());
    		}
    	}

    	return sourcesguide; 
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	//在边集中寻找source点，将其相应的target点和入边的权值这一对键值对记入键值对集合中。
    	Map<L, Integer> targetsguide = new TreeMap<L, Integer>();
    	for(Edge<L> edge : edges)
    	{
    		if(edge.getsource().equals(source))
    		{
    			targetsguide.put(edge.gettarget(), edge.getweight());
    		}
    	}
    	return targetsguide;
    }
    
    // TODO toString()
    //利用StringBuilder重写toString方法，为了更直观地展示该类。
    @Override public String toString()
    {
    	StringBuilder str = new StringBuilder();
    	str.append("Vertices: \n");
    	for(L v : vertices)
    	{
    		str.append(v + "\n");
    	}
    	str.append("Edges: \n");
    	for(Edge<L> edge : edges)
    	{
    		str.append(edge.toString() + "\n");
    	}
    	return str.toString();
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L source;
    private final L target;
    private final int weight;
    
  

    // Abstraction function:
    // weight表示权值，source表示边的出发点，target表示边的目的点，三者便能描绘出一条边。

    //Representation invariant:
    // weight > 0.
    // source != null
    // target != null
    
    // Safety from rep exposure:
    //   Use private to prevent the parameter from exposure.
    
    // TODO constructor
    Edge(L source , L target , int weight)
    {
    	this.source = source;
    	this.target = target;
    	this.weight = weight;
    }
    
    // TODO checkRep
    public void checkRep()
    {
    	assert weight > 0;
    	assert target != null;
    }

    
    // TODO methods:To get the private parameters.
    public  L getsource()
    {
    	return source;
    }
    
    public L gettarget()
    {
    	return target;
	}
    
    public int getweight()
    {
    	return weight;
    }
    
    // TODO toString()
    @Override public String toString() 
    {
    	return "(" + this.getsource() + "," + this.gettarget() + ") : " +  this.getweight() ;
    }
}
