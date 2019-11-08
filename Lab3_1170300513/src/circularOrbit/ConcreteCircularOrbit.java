package circularOrbit;

import java.io.IOException;
import java.util.*;

import assist.Position;
import physicalObject.PhysicalObject;

import java.lang.Math;

import relationship.Socialtie;
import relationship.relationshipbetweenco;
import relationship.relationshipbetweenoo;
import thinginTrack.ThinginTrack;
import track.Track;


/**
 * 一个CircularOrbit接口的抽象类
 * 
 * @author junbaba
 *
 */
public abstract class ConcreteCircularOrbit<L,E> implements CircularOrbit<L, E> {

	// Abstraction function:
	// tracks数组中每个元素对应一条轨道。
	// center数组最多只能有一个元素，用于表示中心点。
	// orbitscontainobjects用于表示轨道与轨道上物体的关系。
	// relbetweenoo上的每个元素用于表示某个轨道上的点与其它轨道上的点间的联系。
	// relbetweenco上的元素表示中心点与其它轨道上的点间的联系。
	// positions上元素用于表示轨道上物体的极坐标（计算物理位置时需要使用）
	
	// Representation invariant:
	// tracks != null
	// center != null
	// objects != null
	// orbitscontainobjects != null
	// relbetweenco != null
	// relbetweenoo != null
	// positions != null
	
	
	// Safety from rep exposure:
	// 通过protected使其它非继承类中无法得知本类中的rep.
	
	protected final List<Track> tracks = new ArrayList<>();
	protected final List<L> center = new ArrayList<>();
	protected final List<E> objects = new ArrayList<>();
	protected final List<ThinginTrack<E>> orbitscontainobjects = new ArrayList<>();
	protected final List<relationshipbetweenoo<E>> relbetweenoo = new ArrayList<>();
	protected final List<relationshipbetweenco<L, E>> relbetweenco = new ArrayList<>();
	protected final List<Position<E>> positions = new ArrayList<>();

	// TODO checkRep
    public void checkRep()
    {
    	assert tracks != null;
    	assert center != null;
    	assert orbitscontainobjects != null;
    	assert relbetweenco != null;
    	assert relbetweenoo != null;
    	assert positions != null;
    }
	
    
    // TODO constructor
    public ConcreteCircularOrbit() 
    {
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
	@Override
	public void addorbit(Track neworbit) 
	{
		int flag = 0;
		for(Track t : tracks)
		{
			if(t.equals(neworbit))
				flag = 1;
		}
		if(flag == 0)
		{
			tracks.add(neworbit);
			orbitscontainobjects.add(ThinginTrack.creator(neworbit, "Basics"));
			sort(tracks,orbitscontainobjects);
		}
		else
		{
			System.out.println("Failure to add track");
		}
		checkRep();
		return;
	}

	
	
	
	
	
	
	@Override
	public void removeorbit(Track deletedorbit) 
	{
		int i , j , k;
		for(i = 0 ; i < tracks.size() ; i++)
		{
			Track t1 = tracks.get(i);
			if(t1.equals(deletedorbit))
			{
				tracks.remove(t1);
				for(j = 0 ; j < orbitscontainobjects.size() ; j++)
				{
					ThinginTrack<E> t2 = orbitscontainobjects.get(j);
					if(t2.gettrack().equals(deletedorbit))
					{
						for(k = 0; k < t2.getthingsintrack().size() ; k++)
						{
							E o1 = t2.getthingsintrack().get(k);
							deleteorbitobj(o1);
						}
						orbitscontainobjects.remove(t2);
						System.out.println("Successful track deletion.");
						checkRep();
						return ;
					}
				}
			}
		}
		System.out.println("Delete track failed.");
		checkRep();
		return ;
	}

	
	
	@Override
	public void deleteorbitobj(E obj)
	{
		int i , j , flag = 0;

		for(i = 0 ;  i < objects.size() ; i++)
		{
			E o = objects.get(i);
			if(o.equals(obj))
			{
				flag = 1;
				objects.remove(o);
				deleteinrelco(obj, relbetweenco);
				deleteinreloo(obj, relbetweenoo);
				for(i = 0 ; i < orbitscontainobjects.size() ; i++)
				{
					for(j = 0 ; j < orbitscontainobjects.get(i).getthingsintrack().size() ; j++)
					{
						if( orbitscontainobjects.get(i).getthingsintrack().get(j).equals(obj))
						{
							orbitscontainobjects.get(i).deleteobject(obj);
						}
					}
				}
				System.out.println("Delete "+ obj.toString() +"'s operation succeeded.");	
			}
		}
		if(flag != 1)
			System.out.println("Delete operation failed.");
		checkRep();
	}
	
	
	
	@Override
	public void deletecentralobj(L obj)
	{
		if(center.get(0).equals(obj))
		{
			center.remove(0);
			if(relbetweenco.get(0).getc().equals(obj))
			{
				relbetweenco.remove(0);
				System.out.println("Delete "+ obj.toString() +"'s operation succeeded.");
				checkRep();
				return;
			}
		}
		System.out.println("Delete operation failed.");
		checkRep();
		return;
	}
	
	
	@Override
	public boolean addcenterobject(L object) 
	{
		if(center.size() == 0)
		{
			center.add(object);
			relbetweenco.add(relationshipbetweenco.creator(object, "Basics"));
			checkRep();
			return true;
		}
		else
		{
			checkRep();
			return false;
		}
	}

	
	
	
	@Override
	public void addtoorbit(E object, Track orbit) 
	{
		for(ThinginTrack<E> t : orbitscontainobjects)
		{
			if(t.gettrack().equals(orbit))
			{
				t.addobject(object);
				relbetweenoo.add(relationshipbetweenoo.creator(object, "Basics"));
				objects.add(object);
				System.out.println("Successful addition for " + object.toString() + ".");
				checkRep();
				return;
			}
		}
		checkRep();
		System.out.println("Failed to add operation.");
		return;
	}

	
	
	
	@Override
	public void addcontactco(L c, E object , Number intimacy) 
	{
		int flag = 0;
		for(relationshipbetweenoo<E> rel : relbetweenoo)
		{
			if(rel.geto().equals(object))
				flag++;
		}
		for(relationshipbetweenco<L, E> rel : relbetweenco)
		{
			if(rel.getc().equals(c) && flag == 1)
			{
				rel.addconnection(object,intimacy);
				flag++;
			}
		}
		checkRep();
		if(flag == 2)
			System.out.println("Successful addition with" + c.toString() + " and " + object.toString() +".");
		else 
			System.out.println("Add operation fail.");
	}

	
	@Override
	public void deletecontactco(L c, E object) 
	{
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public void addcontactoo(E object1, E object2 , Number intimacy)
	{
		int flag = 0;
		for(relationshipbetweenoo<E> rel : relbetweenoo)
		{
			if(rel.geto().equals(object1))
			{
				rel.addconnection(object2,intimacy);
				flag++;
			}
			if(rel.geto().equals(object2))
			{
				rel.addconnection(object1,intimacy);
				flag++;
			}
		}
		checkRep();
		if(flag == 2)
			System.out.println("Successful addition with" + object1.toString() + " and " + object2.toString() +".");
		else
			System.out.println("Add operation fail.");
	}

	@Override
	public void deletecontactoo(E object1, E object2)
	{
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	abstract public void readfile(String filename) throws IOException;
	
	
	
	
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
					flag = 1;
				}
			}
		}
		
		for(i = 0 ; i < orbitscontainobjects.size() ; i++)
		{
			ThinginTrack<E> orbit = orbitscontainobjects.get(i);
			if(orbit.gettrack().equals(t) && flag == 1)
			{
				orbit.addobject(object);
				flag++;
			}
		}
		if(flag == 2)
			System.out.println("Successful transition.");
		else
			System.out.println("Transfer failure.");
		checkRep();
		return;
	}

	
	
	@Override
	public void move(E object, double sitha)
	{
		int i;
		for(i = 0 ; i < positions.size() ; i++)
		{
			if(positions.get(i).getobject().equals(object))
			{
				positions.get(i).changeangle(sitha);
			}
		}
	}
	
	
	@Override
	public L getcenter()
	{
		if(center.size() > 0)
			return center.get(0);
		else
		{
			System.out.println("No central object exists.");
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public int getlogicdistance(E e1 , E e2)
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
		checkRep();
		if(visit[e2num] == true)
		{
			return mycounter;
		}
		else
			return -1;
	}
	
	
	@Override
	public double getphysicaldistance(E e1 , E e2)
	{
		int i , j , flag = 0;
		for(i = 0 ; i < positions.size() ; i++)
		{
			if(positions.get(i).getobject().equals(e1))
			{
				flag++;
				break;
			}
		}
		for(j = 0 ; j < positions.size() ; j++)
		{
			if(positions.get(j).getobject().equals(e2))
			{
				flag++;
				break;
			}
		}
		if(flag == 2)
		{
			double r1 = positions.get(i).getridus().doubleValue();
			double r2 = positions.get(j).getridus().doubleValue();
			double deltaangle = positions.get(i).getangle().doubleValue()/180 * Math.PI - 
					positions.get(j).getangle().doubleValue()/180 * Math.PI;
			double result = Math.sqrt(Math.pow(r1, 2) + Math.pow(r2, 2) - 2*r1*r2*Math.cos(deltaangle) );
			return result;
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public double getdistancefromco(E e)
	{
		int i;
		double dis = -1;
		for(i = 0; i < positions.size() ; i++)
		{
			if(positions.get(i).getobject().equals(e))
			{
				dis = positions.get(i).getridus().doubleValue();
			}
		}
		return dis;
	}
	
	
	@Override
	public List<ThinginTrack<E>> getorbits()
	{
		return List.copyOf(orbitscontainobjects);
	}
	
	
	
	@Override
	public void getvpositions(Number t , String choice)
	{
		for(E obj : objects)
		{
			for(Position<E> p : positions)
			{
				if(p.getobject().equals(obj))
				{
					PhysicalObject planet = (PhysicalObject)obj;
					double length = t.doubleValue() * planet.getv().doubleValue();
					double newangle = 0;
					int flag = 0;
					double tempangle = ((length / p.getridus().doubleValue()) *180  / Math.PI) % 360;
					if(planet.getdirection().equals("CW"))
					{
						newangle = (p.getangle().doubleValue() + tempangle) % 360;
						flag = 1;
					}
					else if(planet.getdirection().equals("CCW"))
					{
						if(p.getangle().doubleValue() >= tempangle)
							newangle = p.getangle().doubleValue() - tempangle;
						else 
							newangle = p.getangle().doubleValue() - tempangle + 360;
						flag = 1;
					}
					if(flag == 1)
						p.changeangle(newangle);
					if(choice.equals("Y"))
						System.out.println(p);
				}
			}
		}
	}
	
	
	@Override
	public Position<E> getpositions(E obj)
	{
		for(Position<E> p : positions)
		{
			if(p.getobject().equals(obj))
				return p;
		}
		throw new UnsupportedOperationException();
	}
	
	
	
	
	@Override
	public List<relationshipbetweenoo<E>> getrelbetweenoo()
	{
		return List.copyOf(relbetweenoo);
	}
	
	
	@Override
	public List<relationshipbetweenco<L, E>> getrelbetweenco()
	{
		return List.copyOf(relbetweenco);
	}
	
	
	
	@Override
	abstract public boolean islegal();
	
	
	@Override
	public int getinfodiffu(E friend)
	{
		throw new UnsupportedOperationException();
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
	
	/**
	 * 个人定义的辅助函数，用于排序轨道
	 * @param tracks
	 * @param orbitscontainobjects
	 */
	protected void sort(List<Track> Tracks, List<ThinginTrack<E>> Orbitscontainobjects)
	{
		int i , j;
		for(i = 0 ; i < Tracks.size() ; i++)
		{
			for(j = i ; j < Tracks.size() ; j ++)
			{
				if(Tracks.get(i).getridus().doubleValue() > Tracks.get(j).getridus().doubleValue() )
				{
					Track t = Tracks.get(i);
					Tracks.set(i, Tracks.get(j));
					Tracks.set(j, t);
					
				}
			}
		}
		for(i = 0 ; i < Orbitscontainobjects.size() ; i++)
		{
			for(j = i ; j < Orbitscontainobjects.size() ; j++)
			{
				if(Orbitscontainobjects.get(i).gettrack().getridus().doubleValue() >
				Orbitscontainobjects.get(j).gettrack().getridus().doubleValue() )
				{
					ThinginTrack<E> t = Orbitscontainobjects.get(i);
					Orbitscontainobjects.set(i, Orbitscontainobjects.get(j));
					Orbitscontainobjects.set(j, t);
				}
			}
		}

		return;
	}
	
	
	/**
	 * 辅助函数，用于消除与obj有关的中心物体与轨道物体的关系
	 * @param obj
	 * @param relbetweenoo
	 */
	protected  void deleteinrelco(E obj , List<relationshipbetweenco<L,E>> relbetweenco)
	{
		int i;
		if(relbetweenco.size() != 0)
		{
			for(i = 0; i < relbetweenco.get(0).getrel().size() ; i++)
			{
				Socialtie<E> s = relbetweenco.get(0).getrel().get(i);
				if(s.getobj().equals(obj))
				{
					relbetweenco.get(0).getrel().remove(s);
				}
			}
		}
	}
	
	
	/**
	 * 辅助函数，用于消除与obj有关的轨道物体与轨道物体的关系
	 * @param obj
	 * @param relbetweenoo
	 */
	protected void deleteinreloo(E obj , List<relationshipbetweenoo<E>> relbetweenoo )
	{
		int i , j , k ,l;
		List<Socialtie<E>> relative = new ArrayList<>();
		for(i = 0 ; i < relbetweenoo.size() ; i++)
		{
			relationshipbetweenoo<E> rel = relbetweenoo.get(i);
			if(rel.geto().equals(obj))
			{
				relative = rel.getrel();
				relbetweenoo.remove(rel);
			}
		}
		for(j = 0 ; j < relative.size() ; j++)
		{
			Socialtie<E> s = relative.get(j);
			for(k = 0 ; k < relbetweenoo.size() ; k++)
			{
				relationshipbetweenoo<E> rel = relbetweenoo.get(k);
				if(s.getobj().equals(rel.geto()))
				{
					for(l = 0 ; l < rel.getrel().size() ; l++)
					{
						Socialtie<E> s2 = rel.getrel().get(l);
						if(s2.getobj().equals(obj))
						{
							rel.getrel().remove(s2);
						}
					}
				}
			}
		}
	}
}
