package assist;

import java.util.*;

import physicalObject.PhysicalObject;
import thinginTrack.ThinginTrack;

/**
 *  一个用于表示两个轨道系统差异的类
 * @author junbaba
 *
 * @param <E>
 */
public class Difference<E> 
{
	private final int orbitnumdif;
	private final List<orbitdifference<E>> orbitdifferences = new ArrayList<>();
	// Abstraction function:
	// orbitnumdif对应轨道数目差异，orbitdifferences中每一个元素对应第i个轨道上物体差异
	
	// Representation invariant:
	// orbitdifferences != null
	
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
	public void checkRep()
	{
		assert orbitdifferences != null;
	}
		
	// TODO constructor
	public Difference(List<ThinginTrack<E>> c1 , List<ThinginTrack<E>> c2) 
	{
		int i , min = c1.size();
		orbitnumdif = c1.size() - c2.size();
		if(c1.size() > c2.size())
			min = c2.size();
		for(i = 0 ; i < min ; i++)
		{
			orbitdifferences.add(new orbitdifference<E>(i+1, c1.get(i).getthingsintrack(), c2.get(i).getthingsintrack()));
		}
		checkRep();
	}
	
	
	
	public int getorbitnumdifference()
	{
		return orbitnumdif;
	}
	
	
	
	public List<orbitdifference<E>> getorbitdifferences()
	{
		return List.copyOf(orbitdifferences);
	}
	
	
	@Override
	public String toString() 
	{
		int i;
		StringBuilder str = new StringBuilder();
		str.append("轨道数差异:" + orbitnumdif + "\n");
		for(i = 0 ; i < orbitdifferences.size() ; i++)
		{
			str.append(orbitdifferences.get(i).toString() + "\n");		
		}
		return str.toString();
	}
}

/**
 *  辅助类，用于辅助Difference类表示
 * @author junbaba
 *
 * @param <E>
 */
class orbitdifference<E>
{
	private final int orbitnum;
	private final int thingnumdif;
	private final List<E> orbitunique1 = new ArrayList<>();
	private final List<E> orbitunique2 = new ArrayList<>();
	
	
	// Abstraction function:
	// orbitnum对应轨道层数，thingnumdif对应轨道上物体数量差，orbitunique1对应独属于轨道一的物体，oebiunique2对应独属于轨道二的物体
	
	// Representation invariant:
	// orbitnum > 0
	// orbitunique1 != null
	// orbitunique2 != null
	
	
	// Safety from rep exposure:
	// 通过private使其它类中无法得知本类中的rep
	
	// TODO checkRep
	public void checkRep()
	{
		assert orbitunique1 != null;
		assert orbitunique2 != null;
		assert orbitnum > 0;
	}
			
	// TODO constructor
	public orbitdifference(int n , List<E> o1 , List<E> o2 ) 
	{
		orbitnum = n;
		thingnumdif = o1.size() - o2.size();
		int flag = 0;
		for(E thingin1 : o1)
		{
			PhysicalObject thingino1 = (PhysicalObject) thingin1;
			for(E thingin2 : o2)
			{
				PhysicalObject thingino2 = (PhysicalObject) thingin2;
				if(thingino2.equals(thingino1))
					flag = 1;
			}
			if(flag == 0)
				orbitunique1.add(thingin1);
			flag = 0;
		}
		for(E thingin2 : o2)
		{
			PhysicalObject thingino2 = (PhysicalObject) thingin2;
			for(E thingin1 : o1)
			{
				PhysicalObject thingino1 = (PhysicalObject) thingin1;
				if(thingino1.equals(thingino2))
					flag = 1;
			}
			if(flag == 0)
				orbitunique2.add(thingin2);
			flag = 0;
		}
		checkRep();
	}
	
	
	public int getorbitnum()
	{
		return orbitnum;
	}
	
	
	public int getthingnumdif()
	{
		return thingnumdif;
	}
	
	
	public List<E> getorbitunique1()
	{
		return List.copyOf(orbitunique1);
	}
	
	public List<E> getorbitunique2()
	{
		return List.copyOf(orbitunique2);
	}
	
	
	
	@Override
	public String toString() 
	{
		int i;
		StringBuilder str = new StringBuilder();
		str.append("轨道" + orbitnum + "的物体数量差异:" + thingnumdif );
		str.append("; 物体差异:");
		PhysicalObject p = null;
		if(orbitunique1.size() != 0)
		{
			p = (PhysicalObject)orbitunique1.get(0); 
		}
		if(orbitunique2.size() != 0)
		{
			p = (PhysicalObject)orbitunique2.get(0);
		}
		if((orbitunique1.size() != 0 || orbitunique2.size() != 0) && !p.getname().equals("Electronics"))
		{
			str.append("{");
			for(i = 0 ; i < orbitunique1.size() ; i++)
			{
				if(i == orbitunique1.size() - 1)
					str.append(orbitunique1.get(i).toString());
				else
					str.append( orbitunique1.get(i).toString() + ",");
			}
			str.append("}-");
			str.append("{");
			for(i = 0 ; i < orbitunique2.size() ; i++)
			{
				if(i == orbitunique2.size() - 1)
					str.append(orbitunique2.get(i).toString());
				else
					str.append( orbitunique2.get(i).toString() + ",");
			}
			str.append("}");
		}
		else
		{
			str.append("无");
		}
		return str.toString();
	}
}