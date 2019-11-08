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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    // vertices列表中每一个元素对应图中的点及与其相连的边。
    
    // Representation invariant:
    // vertices != null
    
    
    // Safety from rep exposure: 
    // Use private to prevent the parameter from exposure.
    
    
    // TODO constructor
    public  ConcreteVerticesGraph() 
    {
    	
	}
		
	
    // TODO checkRep
    public void checkRep()
    {
    	assert vertices != null;
    }
    
    
    @Override public boolean add(L vertex) {
    	Vertex<L> newone = new Vertex<L>(vertex);
        for(Vertex<L> v : vertices)
        {
        	if(v.getname().equals(vertex))
        	{
        		checkRep();
        		return false;
        	}
        }
        vertices.add(newone);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        int i , j , flag = 0;
        myEdge<L> e1 = new myEdge<L>(source, weight);
        myEdge<L> e2 = new myEdge<L>(target, weight);
        for(i = 0 ; i < vertices.size() ; i++)
        {
        	Vertex<L> v1 = vertices.get(i);
        	if(v1.getname().equals(source)) //找到起始点。
        	{
        		List<myEdge<L>> out = v1.getoutedges();
        		for(j = 0 ; j < out.size() ; j++) //查找是否存在这条出边。
        		{
        			myEdge<L> e = out.get(j);
        			if(e.name.equals(target))//存在这条出边的情况。
        			{
        				if(weight == 0)
        				{
        					out.remove(j);
        					vertices.set(i, v1);
        					flag = 1;
            				break;
        				}
        				else
        				{
        					e.weight = weight;
        					out.set(j, e);
        					vertices.set(i, v1);
        					flag = 1;
        					break;
        				}
        			}
        		}
        		if(flag == 0)//遍历完后查明不存在该出边，故添加此条边。
        		{
        			out.add(e2);
					vertices.set(i, v1);
					break;
        		}
        	}
        }
        for(i = 0 ; i < vertices.size() ; i++) //同理处理入边情况。
        {
        	Vertex<L> v2 = vertices.get(i);
        	if(v2.getname().equals(target))
        	{
        		List<myEdge<L>> in = v2.getinedges();
        		for(j = 0 ; j < in.size() ; j++)
        		{
        			myEdge<L> e = in.get(j);
        			if(e.name.equals(source))
        			{
        				if(weight == 0)
        				{
        					int num = e.weight;
        					in.remove(j);
        					vertices.set(i, v2);
        					checkRep();
        					return num;
        				}
        				else
        				{
        					int num = e.weight;
        					e.weight = weight;
        					in.set(j, e);
        					vertices.set(i, v2);
        					checkRep();
        					return num;
        				}
        			}
        		}
        		if(flag == 0)
        		{
        			in.add(e1);
        			vertices.set(i, v2);
        			break;
        		}
        	}
        }
        checkRep();
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        
    	int i , j ,  flag = 0;
    	for(i = 0 ; i < vertices.size(); i++)
    	{
    		Vertex<L> v = vertices.get(i);
    		if(v.getname().equals(vertex))
    		{
    			vertices.remove(i);
    			flag = 1;
    			break;
    		}
    	}
    	for(i = 0 ; i < vertices.size(); i++)
    	{
    		Vertex<L> v = vertices.get(i);
    		List<myEdge<L>> in = v.getinedges();
    		List<myEdge<L>> out = v.getoutedges();
    		for(j = 0 ; j < in.size() ; j++)
    		{
    			if(in.get(j).name.equals(vertex))
    			{
    				in.remove(j);
    				vertices.set(i, v);
    				break;
    			}
    		}
    		for(j = 0 ; j < out.size() ; j++)
    		{
    			if(out.get(j).name.equals(vertex))
    			{
    				out.remove(j);
    				vertices.set(i, v);
    				break;
    			}
    		}
    	}
    	checkRep();
    	if(flag == 1)
    		return true;
    	else
    		return false;
    }
    
    @Override public Set<L> vertices() {
        Set<L> vertices2 = new HashSet<>();
        for(Vertex<L> v : vertices)
        {
        	vertices2.add(v.getname());
        }
        return vertices2;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> sourcesguide = new TreeMap<L, Integer>();
    	int i , flag = 0;
    	for(i = 0 ; i < vertices.size() ; i++)
    	{
    		if(vertices.get(i).getname().equals(target))
    		{
    			flag = 1;
    			break;
    		}
    	}
    	if(flag == 0)
    		return sourcesguide;
    	Vertex<L> v = vertices.get(i);
    	List<myEdge<L>> in = v.getinedges();
    	for(i = 0 ;i < in.size(); i++)
    	{
    		sourcesguide.put(in.get(i).name, in.get(i).weight);
    	}
    	return sourcesguide;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> targetsguide = new TreeMap<L, Integer>();
    	int i , flag = 0;
    	for(i = 0 ; i < vertices.size() ; i++)
    	{
    		if(vertices.get(i).getname().equals(source))
    		{
    			flag = 1;
    			break;
    		}
    	}
    	if(flag == 0)
    		return targetsguide;
    	Vertex<L> v = vertices.get(i);
    	List<myEdge<L>> out = v.getoutedges();
    	for(i = 0 ; i < out.size() ; i++)
    	{
    		targetsguide.put(out.get(i).name, out.get(i).weight);
    	}
    	return targetsguide;
    }
    
    // TODO toString()
    @Override public String toString()
    {
    	StringBuilder str = new StringBuilder();
    	for(Vertex<L> v: vertices)
    	{
    		str.append(v.toString() + "\n");
    	}
    	return str.toString(); 
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
    private final L name;
    private final List<myEdge<L>> outedges = new ArrayList<>();
    private final List<myEdge<L>> inedges = new ArrayList<>();
    // Abstraction function:
    // outedges列表中每一个元素对应名为name的点的一条出边，inedges列表中的每一个元素对应名为name的点的一条入边。
    
    // Representation invariant:
    // name != null
    // outedges != null
    // inedges != null
    // inedges与outedges中的myEdge.name必须对应vertices中存在的点。
    
    // Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
    
    // TODO constructor
     Vertex(L name) 
     {
		this.name = name;
	}
     
    // TODO checkRep
     public void checkRep()
     {
     	assert name != null;
     	assert outedges != null;
     	assert inedges != null;
     }
     
    // TODO methods
     public L getname()
     {
    	 return name;
     }
     
     public List<myEdge<L>> getoutedges()
     {
    	 return outedges;
     }
     
     public List<myEdge<L>> getinedges()
     {
    	 return inedges;
     }
     
     
    // TODO toString()
     @Override public String toString()
     {
    	 StringBuilder str = new StringBuilder();
    	 str.append("vertex: " + name + "\n");
    	 str.append("inedges: ");
    	 for(myEdge<L> edge : inedges)
    	 {
    		 str.append(edge.toString() + " ");
    	 }
    	 str.append("\n");
    	 str.append("outedges: ");
    	 for(myEdge<L> edge : outedges)
    	 {
    		 str.append(edge.toString() + " ");
    	 }
    	 str.append("\n");
    	 return str.toString();
     }
}


/**辅助类，用于记录出边或入边
 * 
 * @author junbaba
 *
 * @param <L>
 */
class myEdge<L>
{
	public L name;
	public int weight;
	
    myEdge(L name , int weight)
	{
		this.name = name;
		this.weight = weight;
	}
    
    @Override public String toString()
    {
    	return "(" + name + "," + weight + ")" ;
    }
}