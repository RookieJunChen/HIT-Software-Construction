package circularOrbit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assist.number;
import centralObject.CentralObject;
import physicalObject.PhysicalObject;
import relationship.*;
import thinginTrack.ThinginTrack;
import track.Track;

import java.util.*;

/**
 * 一个实现CircularOrbit并继承了ConcreteCircularOrbit的类，用于完成Q5的SocialNetworkCircle
 * @author junbaba
 *
 * @param 中心物体类型<L>
 * @param 轨道物体类型<E>
 */
public class SocialNetworkCircle<L,E> extends ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E>
{

	
	@Override
	public void addtoorbit(E object, Track orbit) 
	{
		for(ThinginTrack<E> t : orbitscontainobjects)
		{
			if(t.gettrack().equals(orbit))
			{
				t.addobject(object);
				System.out.println("Successful addition with " + object.toString() + ".");
				checkRep();
				return;
			}
		}
		checkRep();
		System.out.println("Failed to add operation.");
		return;
	}
	
	
	
	@Override
	public void deleteorbitobj(E obj) 
	{
		super.deleteorbitobj(obj);
		reset();
	}
	
	
	
	@Override
	public void removeorbit(Track deletedorbit) 
	{
		super.removeorbit(deletedorbit);
		reset();
	}
	
	
	@Override
	public void addcontactoo(E object1, E object2, Number intimacy) 
	{
		int i , flag1 = 0 , flag2 = 0;
		for(i = 0 ; i < objects.size() ; i++)
		{
			if(objects.get(i).equals(object1))
			{
				flag1 = 1;
			}
			if(objects.get(i).equals(object2))
			{
				flag2 = 1;
			}
		}
		if(flag1 == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object1, "Basics"));
			objects.add(object1);
		}
		if(flag2 == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object2, "Basics"));
			objects.add(object2);
		}
		super.addcontactoo(object1, object2, intimacy);
		reset();
	}
	
	
	
	@Override
	public void deletecontactoo(E object1, E object2) 
	{
		int flag1 = 0 , flag2 = 0;
		int i , j;
		int remi1 = 0 , remj1 = 0 ,remi2 = 0 , remj2 = 0;
		for(i = 0 ; i < relbetweenoo.size() ; i++)
		{
			if(relbetweenoo.get(i).geto().equals(object1))
			{
				for(j = 0 ; j < relbetweenoo.get(i).getrel().size() ; j++)
				{
					if(relbetweenoo.get(i).getrel().get(j).getobj().equals(object2))
					{
						flag1 = 1;
						remi1 = i;
						remj1 = j;
					}
				}
			}
		}
		/*for(i = 0 ; i < relbetweenoo.size() ; i++)
		{
			if(relbetweenoo.get(i).geto().equals(object2))
			{
				for(j = 0 ; j < relbetweenoo.get(i).getrel().size() ; j++)
				{
					if(relbetweenoo.get(i).getrel().get(j).getobj().equals(object1))
					{
						flag1 = 1;
						remi2 = i;
						remj2 = j;
					}
				}
			}
		}*/
		if(flag1 == 1)
		{
			relbetweenoo.get(remi1).getrel().remove(remj1);
			//relbetweenoo.get(remi2).getrel().remove(remj2);
			reset();
			System.out.println("Delete operation succeed.");
		}
		else
			System.out.println("Delete operation failed.");
		return;
	}
	
	
	@Override
	public void addcontactco(L c, E object, Number intimacy) {
		int i , flag = 0;
		for(i = 0 ; i < objects.size() ; i++)
		{
			if(objects.get(i).equals(object))
			{
				flag = 1;
			}
		}
		if(flag == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object, "Basics"));
			objects.add(object);
		}
		super.addcontactco(c, object, intimacy);
		reset();
	}
	
	
	
	@Override
	public void deletecontactco(L c, E object) 
	{
		int flag1 = 0 , flag2 = 0;
		int i;
		int remi = 0;
		if(c.equals(getcenter()))
		{
			flag1 = 1;
		}
		for(i = 0 ; i < relbetweenco.get(0).getrel().size() ; i++)
		{
			if(relbetweenco.get(0).getrel().get(i).getobj().equals(object))
			{
				flag2 = 1;
				remi = i;
			}
		}
		if(flag1 == 1 && flag2 == 1)
		{
			relbetweenco.get(0).getrel().remove(remi);
			reset();
			System.out.println("Delete operation succeed.");
		}
		else
			System.out.println("Delete operation failed.");
		return;
		
	}
	
	@Override
	public void transit(E object, Track t) 
	{
		int flag = 0;
		int i , j;
		for(i = 0 ; i < orbitscontainobjects.size() ; i++)
		{
			ThinginTrack<E> orbit = orbitscontainobjects.get(i);
			for(j = 0 ; j < orbit.getthingsintrack().size() ; j++)
			{
				E o = orbit.getthingsintrack().get(j);
				if(o.equals(object))
				{
					orbit.deleteobject(o);
				}
			}
		}
		for(i = 0 ; i < orbitscontainobjects.size() ; i++)
		{
			ThinginTrack<E> orbit = orbitscontainobjects.get(i);
			if(orbit.gettrack().equals(t))
			{
				orbit.addobject(object);
				flag++;
			}
		}
		if(flag == 1)
			System.out.println("Successful transition.");
		else
			System.out.println("Transfer failure.");
		checkRep();
		return;
	}
	
	
	
	@Override
	public void readfile(String filename) throws IOException 
	{
		String[] filenames = filename.split(" ");
		String pathname;
		if(filenames.length == 1)
			pathname = "src\\txt\\" + filename;
		else
			pathname = "test\\txt\\" + filenames[1];
		File file = new File(pathname);
    	InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    	BufferedReader br = new BufferedReader(reader);
		String format1 = "(CentralUser ::= )<(\\w+,?|[\\w|.]+,?)+>";
		String format2 = "(Friend ::= )<(\\w+,?|[\\w|.| ]+,?)+>";
		String format3 = "(SocialTie ::= )<(\\w+,?|[\\w|.| ]+,?)+>";
		String line;
		CentralObject center = null;
		List<PhysicalObject> physicalObjects = new ArrayList<>();
		//第一遍扫描先读CentralUser和Friend
		while((line = br.readLine()) != null)
		{
			Matcher m1 = Pattern.compile(format1).matcher(line);
			Matcher m2 = Pattern.compile(format2).matcher(line);
			while(m1.find())
			{
				String[] couples = m1.group().split("CentralUser ::= <|,|>");
				center = CentralObject.Q5creator(couples[1], couples[3], Integer.valueOf(couples[2]));
				super.addcenterobject((L)center);
			}
			while(m2.find())
			{
				String[] couples = m2.group().split("Friend ::= <|, |>");
				physicalObjects.add(PhysicalObject.Q5creator(couples[1], couples[3], Integer.valueOf(couples[2])));
			}
			
		}
		//第二遍扫描再读SocialTie
		file = new File(pathname);
		reader = new InputStreamReader(new FileInputStream(file));
		br = new BufferedReader(reader);
		while((line = br.readLine()) != null)
		{
			Matcher m3 = Pattern.compile(format3).matcher(line);
			while(m3.find())
			{
				String[] couples = m3.group().split("SocialTie ::= <|, |>");
				if(couples[1].equals(center.getname()))
				{
					double v = Double.valueOf(couples[3]);
					for(PhysicalObject p : physicalObjects)
					{
						if(p.getname().equals(couples[2]))
						{
							myaddcontactco((L)center, (E)p, v);
						}
					}
					
				}
				else
				{
					for(PhysicalObject p1 : physicalObjects)
					{
						if(p1.getname().equals(couples[1]))
						{
							for(PhysicalObject p2 : physicalObjects)
							{
								if(p2.getname().equals(couples[2]))
								{
									myaddcontactoo((E)p1, (E)p2, Double.valueOf(couples[3]));
								}
							}
						}
					}
				}
			}
		}
		
		int max = 0;
		for(PhysicalObject p : physicalObjects)
		{
			int logic;
			if((logic = getminlogicdisfromc((E)p)) != -1  &&  max < logic)
			{
				max = logic;
			}
		}
		for(int i = 1; i <= max ; i++)
		{
			Track t = Track.Roundcreator(i);
			super.addorbit(t);
			for(PhysicalObject p : physicalObjects)
			{
				if(getminlogicdisfromc((E)p) == i)
				{
					addtoorbit((E)p, t);
				}
			}	
		}
		reset();
	}

	
	@Override
	public boolean islegal() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getlogicdistance(E e1, E e2) {
		int dis = super.getlogicdistance(e1, e2);
		if(dis != -1)
			return dis;
		else
		{
			dis = getminlogicdisfromc(e1) + getminlogicdisfromc(e2);
			return dis;
		}
	}
	
	
	@Override
	public int getinfodiffu(E friend) {
		int friends = 0;
		for(E obj : super.objects)
		{
			if(strictlogicdistance(friend, obj) != -1)
				friends++;
		}
		return friends;
	}
	
	
	/**
	 * 个人定义的辅助函数，计算参考亲密度的最短逻辑距离（亲密度超过0.5算相连）
	 * @param 轨道上物体e1
	 * @param 轨道上物体e2
	 * @return 如果e1与e2之间能相连，则返回其最短逻辑距离，否则返回-1.
	 */
	private int strictlogicdistance(E e1 , E e2)
	{
		int mycounter = 0 , pre = 0 , last = 1 , mid = 1;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[objects.size()];
		int e1num = find(objects, e1);
		int e2num = find(objects, e2);
		queue.offer(e1num);
		while(queue.peek() != null)
		{
			int i = queue.poll();
			visit[i] = true;
			if(visit[e2num] == true)
				break;
			pre++;
			E e = objects.get(i);
			for(Socialtie<E> obj : getlist(relbetweenoo,e))
			{
				int m = find(objects, obj.getobj());
				if(visit[m] != true && obj.getIntimacy().doubleValue() > 0.5)
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
		checkRep();
		if(visit[e2num])
		{
			return mycounter;
		}
		else
			return -1;
	}
	
	
	
	/**
	 * 个人定义的辅助函数，用于计算轨道物体到中心物体的最短逻辑距离
	 * @return 计算轨道物体到中心物体的最短逻辑距离，如果无联系则返回-1.
	 */
	private int getminlogicdisfromc(E e)
	{
		int mindistance = -1;
		for(Socialtie<E> s : super.relbetweenco.get(0).getrel())
		{
			if(s.getobj().equals(e))
			{
				mindistance = 1;
				return mindistance;
			}
		}
		
		for(Socialtie<E> s : super.relbetweenco.get(0).getrel())
		{
			int logic;
			if(mindistance == -1 && (logic = super.getlogicdistance(s.getobj(), e)) != -1)
			{
				mindistance = logic + 1;
			}
			if((logic = super.getlogicdistance(s.getobj(), e)) != -1  &&  
					mindistance > logic+1)
			{
				mindistance = logic + 1;
			}
		}
		return mindistance;
		
	}
	
	
	/**
	 * 个人定义的辅助函数，用于调整增加或删除后的轨道结构
	 */
	private void reset()
	{
		int max = 0 , i , j , k , l;
		int rem1 = 0 , rem2;
		for(i = 0 ; i < relbetweenoo.size() ; i ++)
		{
			for(j = 0 ; j < orbitscontainobjects.size() ; j++)
			{
				for( k = 0 ; k < orbitscontainobjects.get(j).getthingsintrack().size() ; k++)
				{
					if(relbetweenoo.get(i).geto().equals(orbitscontainobjects.get(j).getthingsintrack().get(k)))
					{
						rem1 = j;
					}
				}
			}
			for(j = 0 ; j < relbetweenoo.get(i).getrel().size() ; j++)
			{
				E obj = relbetweenoo.get(i).getrel().get(j).getobj();
				for(k = 0 ; k < orbitscontainobjects.size() ; k++)
				{
					
					for(l = 0 ; l < orbitscontainobjects.get(k).getthingsintrack().size() ; l++)
					{
						if(obj.equals(orbitscontainobjects.get(k).getthingsintrack().get(l)))
						{
							rem2 = k;
							if(rem1 > rem2)
							{
								relbetweenoo.get(i).getrel().remove(j);
							}
						}
					}
				}
			}
		}
		for(i = 0 ; i < objects.size() ; i++)
		{
			E e  = objects.get(i);
			int logic;
			if((logic = getminlogicdisfromc(e)) != -1  &&  max < logic)
			{
				max = logic;
			}
		}
		
		for(i = 1 ; i <= max ; i++)
		{
			int flag = 0;
			for(j = 0 ; j < super.tracks.size() ; j++)
			{
				if(super.tracks.get(j).getridus().intValue() == i)
				{
					flag = 1;
					break;
				}
			}
			if(flag == 0)
			{
				Track t = Track.Roundcreator(i);
				super.addorbit(t);
			}
		}
		

		for(i = 0 ; i < objects.size() ; i++)
		{
			E e  = objects.get(i);
			int logic;
			if( (logic = getminlogicdisfromc(e)) == -1)
			{
				super.deleteorbitobj(e);
				i--;
			}
			else
			{

				transit(e, super.tracks.get(logic - 1));
			}
		}
			
		for(i = max ; i < super.tracks.size() ; i++)
		{
			super.removeorbit(super.tracks.get(i));
		}

	}

	
	
    /**
     * 个人定义的辅助函数，用于读文件时辅助构建轨道系统
     * @param c
     * @param object
     * @param intimacy
     */
	private void myaddcontactco(L c, E object, Number intimacy) 
	{
		int i , flag = 0;
		for(i = 0 ; i < objects.size() ; i++)
		{
			if(objects.get(i).equals(object))
			{
				flag = 1;
			}
		}
		if(flag == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object, "Basics"));
			objects.add(object);
		}
		super.addcontactco(c, object, intimacy);
	}
	
	
	
	
	/**
	 * 个人定义的辅助函数，用于读文件时辅助构建轨道系统
	 * @param object1
	 * @param object2
	 * @param intimacy
	 */
	private void myaddcontactoo(E object1, E object2, Number intimacy) 
	{
		int i , flag1 = 0 , flag2 = 0;
		for(i = 0 ; i < objects.size() ; i++)
		{
			if(objects.get(i).equals(object1))
			{
				flag1 = 1;
			}
			if(objects.get(i).equals(object2))
			{
				flag2 = 1;
			}
		}
		if(flag1 == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object1, "Basics"));
			objects.add(object1);
		}
		if(flag2 == 0)
		{
			relbetweenoo.add(relationshipbetweenoo.creator(object2, "Basics"));
			objects.add(object2);
		}
		super.addcontactoo(object1, object2, intimacy);
	}
	
	
	
	/**
	 * 个人定义的辅助函数，用于寻找objects数组中object的下标。
	 * @param objects
	 * @param object
	 * @return objects数组中object的下标
	 */
	protected  int find(List<E> objects , E object)
	{
		int i;
		for(i = 0 ; i < objects.size() ; i++)
		{
			if(objects.get(i).equals(object))
				return i;
		}
		return -1;
	}
	
	
	/**
     * 个人定义的辅助函数，返回与轨道物体object有联系的所有轨道物体所组成的列表
     * @param relbetweenoo
     * @param object
     * @return 与轨道物体object有联系的所有轨道物体所组成的列表
     */
	protected List<Socialtie<E>> getlist( List<relationshipbetweenoo<E>> relbetweenoo ,E object)
	{
		int i;
		for(i = 0 ; i < relbetweenoo.size() ;i++)
		{
			if(relbetweenoo.get(i).geto().equals(object))
			{
				return relbetweenoo.get(i).getrel();
			}
		}
		return null;
	}
}
