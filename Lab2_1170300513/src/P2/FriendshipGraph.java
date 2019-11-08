package P2;

import java.util.*;
import P1.graph.*;

public class FriendshipGraph 
{
 ConcreteEdgesGraph<String> g = new ConcreteEdgesGraph<>();

    // Abstraction function:
    // g对应相应的诗集生成图。
    
    // Representation invariant: 
    // g != null
    
   
	
	// TODO checkRep
	public void checkRep()
    {
    	assert g != null;
    }
	
	/**客户端main。
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel"); //输入四个点并为其添加相应的边
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel); 
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));  //输出使用getDistance函数得到的结果。
		System.out.println(graph.getDistance(rachel, ben)); 
		System.out.println(graph.getDistance(rachel, rachel)); 	
		System.out.println(graph.getDistance(rachel, kramer)); 

	}
	
	/**添加点。
	 * 
	 * @param 需要添加的Person类点p。
	 */
	public void addVertex(Person p)
	{
		if(!g.add(p.name)) //在判断过程中就已进行了添加。
		{
			System.out.println("Name repetition!"); //如果有重复，报错并退出程序。
			System.exit(0);
		}
	}
	
	/**添加边。
	 * 
	 * @param 起始点Person类a。
	 * @param 终止点Person类b。
	 */
	public void addEdge(Person a , Person b)
	{
		if(g.set(a.name, b.name, 1) == -1) //在判断过程中就已经进行了添加。
		{
			System.out.println(a.name + " or "+ b.name + " is not in the Vretex!"); //如果a或b不在点集中就报错并退出程序。
			System.exit(0);
		}
	}
	
	/**寻找Person类a与Person类b间的最短距离。
	 * 
	 * @param 起始点Person类a。
	 * @param 终止点Person类b。
	 * @return Person类a与Person类b间的最短距离。
	 */
	public int getDistance(Person a , Person b)
	{
		int mycounter = 0 , pre = 0 , last = 1 , mid = 1; 
		List<String> mylist = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[g.vertices().size()];
		for(String p : g.vertices())
		{
			mylist.add(p); //将集合中的点放到List中（便于操作）。
		}
		int anum = find(a.name, mylist);
		int bnum = find(b.name, mylist);
		if((bnum == -1) || (anum == -1)) //如果a或b不在点集中就报错并退出程序。
		{
			System.out.println(a.name + " or "+ b.name + " is not in the Vretex!"); 
			System.exit(0);
		}
		queue.offer(anum);
		//广度搜索并记录最短距离。
		while(queue.peek() != null)
		{
			int i = queue.poll();
			visit[i] = true;
			if(visit[bnum] == true)
				break;
			pre++;
			String p = mylist.get(i);
			for(String per : g.targets(p).keySet())
			{
				int m = find(per, mylist);
				if(visit[m] != true)
				{
					queue.offer(m);
					last ++;
				}
			}
			if(pre == mid)
			{
				mid = last;
				mycounter++;
			}
		}
		if(visit[bnum] == true)
			return mycounter;
		else
			return -1;
	}
	
	/**寻找List中是否包含P,如果包含返回其下标，否则返回-1.
	 * 
	 * @param Person类名字p
	 * @param 名字列表mylist
	 * @return p在列表中的下标i，如果不存在则返回-1.
	 */
	public int find(String p , List<String> mylist)
	{
		int i;
		for(i = 0 ; i < mylist.size() ; i++)
		{
			if(p.equals(mylist.get(i)))
				return i;
		}
		return -1;
	}
	
	

}
