/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function: 
    // graph对应相应的诗集生成图。
    
    // Representation invariant:
    // graph != null
    
    // Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    
    public GraphPoet(File corpus) throws IOException {
    	InputStreamReader reader = new InputStreamReader(new FileInputStream(corpus));
    	BufferedReader br = new BufferedReader(reader);
    	List<String> line = new ArrayList<>();
    	String t;
    	int i , j;
		while(( t = br.readLine()) != null)
    	{
    		line.add(t);
    	}
    	for(i = 0 ; i < line.size() ; i++)
    	{
    		String lastline = null;
    		String[] str = line.get(i).split(" ");
    		for(j = 0 ; j < str.length ; j++)
    		{
    			if((t = find(str[j])) == null)//该词不存在于图中.
    			{
    				graph.add(str[j]);
    			}
    			
    			if((i != 0) && (j == 0))//将上一行末尾与此行首位进行关联.
				{
					String pre = find(lastline);
					String last = find(str[j]);
					int m = graph.set(pre, last, 1); //通过此操作将原边权值+1.
					graph.set(pre, last, m+1);
				}
				if(j != 0)
				{
					String pre = find(str[j-1]);
					String last = find(str[j]);
					int m = graph.set(pre, last, 1);
					graph.set(pre, last, m+1);
				}
    		}
    		lastline = str[str.length - 1];
    	}
    	checkRep();
    }
    
    // TODO checkRep
    public void checkRep()
    {
    	assert graph != null;
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        StringBuilder output = new StringBuilder();
        int i;
        String[] sortinput = input.split(" ");
        for(i = 0 ; i < sortinput.length - 1 ; i++)
        {
        	String pre , lst;
        	//将输入拆分为单词后两两判断，最后一位需注意（越界问题）
        	if(i == sortinput.length-2)
        	{
        		if(((pre=find(sortinput[i])) != null) && ((lst=find(sortinput[i+1])) != null))
        		{
        			String tmp = findmaxmedium(pre, lst);
        			if(tmp != null)
        			{
        				output.append(sortinput[i] + " ");
        				output.append(tmp.toLowerCase() + " ");
        				output.append(sortinput[i+1] + " ");
        			}
        			else
        			{
        				output.append(sortinput[i] + " ");
        				output.append(sortinput[i+1] + " ");
        			}
        		}
        		else
        		{
        			output.append(sortinput[i] + " ");
        			output.append(sortinput[i+1] + " ");
        		}
        	}
        	else
        	{
        		if(((pre=find(sortinput[i])) != null) && ((lst=find(sortinput[i+1])) != null))
        		{
        			String tmp = findmaxmedium(pre, lst);
        			if(tmp != null)
        			{
        				output.append(sortinput[i] + " ");
        				output.append(tmp.toLowerCase() + " ");
        			}
        			else
        			{
        				output.append(sortinput[i] + " ");
        			}
        		}
        		else
        		{
        			output.append(sortinput[i] + " "); 
        		}
        	}
        	
        }
        return output.substring(0, output.length()-1); //切掉最后的空格并返回.
    }
    
    // TODO toString()
    @Override public String toString()
    {
    	StringBuilder str = new StringBuilder();
    	str.append("Vertices: \n");
    	for(String v : graph.vertices())
    	{
    		str.append(v + "\n");
    		str.append("targets: ");
    		for(String key : graph.targets(v).keySet())
    		{
    			str.append("<"+ key + "," + graph.targets(v).get(key)+ ">  ");
    		}
    		str.append("\n");
    		str.append("sources: ");
    		for(String key : graph.sources(v).keySet())
    		{
    			str.append("<"+ key + "," + graph.sources(v).get(key)+ ">  ");
    		}
    		str.append("\n");
    	}
    	return str.toString();
    }
    
    /**返回字符集合中的词（忽略大小写）
     * 
     * @param 词名str
     * @return 点集中与str不区分大小写相同的点名。
     */
    public String find( String str)
    {
    	for(String v : graph.vertices())
    	{
    		if(str.equalsIgnoreCase(v))
    		{
    			return v;
    		}
    	}
    	return null;
    }
    
    /**find a two-edge-long path with maximum-weight weight among all 
     * the two-edge-long paths from w1 to w2 in the affinity graph.
     * 
     * @param the source point's name p and the target point's name l.
     * @return The point string name in the two-edge-long paths.
     */
    public String findmaxmedium(String p , String l)
    {
    	int i , j;
    	int flag = 0;
    	List<Rankpiece> ranktemp = new ArrayList<>(); //为方便操作设置的可变数组，用于储存与source相连的点。
    	List<Rankpiece> finalrank = new ArrayList<>();//最终用于排序的两点间的中间点数组。
    	String pre = find(p);
    	String lst = find(l);
    	Map<String, Integer> targets = graph.targets(pre);
    	for(String key : targets.keySet()) //找出与source相连的点。
    	{
    		if(!(key.equalsIgnoreCase(pre)))
    		{
    			Rankpiece tmp = new Rankpiece();
    			tmp.name = key;
    			tmp.num = targets.get(key);
    			ranktemp.add(tmp);
    		}
    	}
    	for(i = 0 ; i < ranktemp.size() ; i++) //判断与source相连的点是否连接着target，如果相连则加入finalrank中。
    	{
    		targets = graph.targets(ranktemp.get(i).name);
    		for(String key : targets.keySet())
    		{
    			if(key.equalsIgnoreCase(lst))
    			{
    				flag = 1;
    		   		Rankpiece tmp = new Rankpiece();
    	    		tmp.name = ranktemp.get(i).name;
    	    		tmp.num = ranktemp.get(i).num + targets.get(key);
    	    		finalrank.add(tmp);
    	    		break;
    			}
    		}
    	}
    	if(flag == 0)
    		return null;
    	//排序找出权值和最大的中间点。
    	for(i = 0 ; i < finalrank.size(); i++)
    	{
    		for(j = i ; j < finalrank.size() ; j++)
    		{
    			if(finalrank.get(i).num < finalrank.get(j).num)
    			{
    				Rankpiece temp = new Rankpiece();
    				temp = finalrank.get(i);
    				finalrank.set(i, finalrank.get(j));
    				finalrank.set(j, temp);
    			}
    		}
    	}
    	return finalrank.get(0).name;
    }


}

/**为便于排序并返回名字而设置的辅助类。
 * 
 * @author junbaba
 *
 */
class Rankpiece
{
	String name;
	int num;
}
