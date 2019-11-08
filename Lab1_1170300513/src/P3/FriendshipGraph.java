package P3;

import java.util.*;

public class FriendshipGraph 
{
	List<Person> Vertex = new ArrayList<>();
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
	public void addVertex(Person p)
	{
		int i , flag = 1;
		for(i = 0; i < Vertex.size(); i++) //判断点集中是否已有这个人。
		{
			if(Vertex.get(i).name.equals(p.name))
				flag = 0;
		}
		if(flag == 1)
			Vertex.add(p);
		else
		{
			System.out.println("Name repetition!"); //如果有，报错并退出程序。
			System.exit(0);
		}
		return;
	}
	public void addEdge(Person a , Person b)
	{
		int i = getindex(a.name);
		int j = getindex(b.name);
		if((i >= 0) && (j >= 0)) //判断a,b是否在Vertex中，如果在添加对应的边。
		{
			Person n = Vertex.get(i);
			n.Edge.add(b);
			Vertex.set(i, n);
		}
		if(i == -1) //如果不在就报错并退出程序。
		{
			System.out.println(a.name + " is not in the Vretex!");
			System.exit(0);
		}
		if(j == -1)
		{
			System.out.println(b.name + " is not in the Vretex!");
			System.exit(0);
		}
		return;
	}
	public int getDistance(Person a , Person b)
	{
		int n = Vertex.size();
		int mycounter = 0 , pre = 0 , last = 1 , mid = 1; //mycounter用于记录距离。
		Queue<Integer> queue = new LinkedList<>(); //用队列来实现求两点间最短距离的方法（广搜）。
		boolean[] visit = new boolean[n];
		int numa = getindex(a.name);
		int numb = getindex(b.name);
		queue.offer(numa);
		while(queue.peek() != null)
		{
			int i = queue.poll();
			int j;
			visit[i] = true;
			if(visit[numb] == true)
				break;
			pre++;
			Person p1 = Vertex.get(i);
			for(j = 0 ; j < p1.Edge.size();j++)
			{
				int m = getindex(p1.Edge.get(j).name);
				Person p2 = Vertex.get(m);
				if(visit[m] != true) 
				{
					queue.offer(m);
					last ++;
				}
			}
			if(pre == mid) //说明已经遍历完了一层。
			{
				mycounter++;
				mid = last;
			}	
		}
		if(visit[numb] == true)
			return mycounter;
		else
			return -1;
	}
	private int getindex(String name) /**用name来对应寻找相应元素在队列中的下标  */
	{
		int i;
		for(i = 0 ; i < Vertex.size(); i++)
		{
			if(name.equals(Vertex.get(i).name))
				return i;
		}
		return -1;
	}
	private  boolean inEdge(Person a , Person b) /**判断a到b是否有边*/
	{
		int i;
		for(i = 0 ; i < a.Edge.size() ; i++)
		{
			if(b.name.equals(a.Edge.get(i).name))
				return true;
		}
		return false;
	}
}
