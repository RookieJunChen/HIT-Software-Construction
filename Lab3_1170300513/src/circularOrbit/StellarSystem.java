package circularOrbit;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assist.Position;
import assist.number;
import centralObject.CentralObject;
import physicalObject.PhysicalObject;
import track.Track;
import thinginTrack.*;
import java.lang.Math;

/**
 *  一个实现CircularOrbit并继承了ConcreteCircularOrbit的类，用于完成Q2的StellarSystem
 * @author junbaba
 *
 * @param <L>
 * @param <E>
 */
public class StellarSystem<L,E> extends ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E>
{
	
	
	/**
	 * 辅助函数，用于判断StellarSystem是否合法
	 * @return 合法返回true，不合法则返回false
	 */
	public boolean islegal()
	{
		int flag = 0;
		if(super.center.size() == 1)
		{
			flag = 1;
		}
		for(int i = 0 ; i < super.orbitscontainobjects.size() ; i++)
		{
			if(super.orbitscontainobjects.get(i).getthingsintrack().size() != 1)
			{
				flag = 0;
			}
		}
		if(flag == 1)
			return true;
		else
			return false;
		
	}
		
	

	
	
	@Override
	public void addtoorbit(E object, Track orbit)
	{
		int i;
		PhysicalObject p1 = (PhysicalObject)object;
		for(i = 0 ; i < super.orbitscontainobjects.size() ; i++)
		{
			if(super.orbitscontainobjects.size() == 1)
			{
				super.addtoorbit(object, orbit);
				break;
			}
			if(super.orbitscontainobjects.get(i).gettrack().equals(orbit))
			{
				if(i == 0)
				{
					double deltar2 = super.orbitscontainobjects.get(i+1).gettrack().getridus().doubleValue() -  
							super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue();
					PhysicalObject p3 = (PhysicalObject)super.orbitscontainobjects.get(i+1).getthingsintrack().get(0);
					if((p1.getplanetr().doubleValue() + p3.getplanetr().doubleValue() < deltar2) &&
						 (super.orbitscontainobjects.get(i).getthingsintrack().size() == 0))
					{
						super.addtoorbit(object, orbit);
					}
					else
					{
						System.out.println("This operation causes the system to be illegal.");
						System.exit(0);
					}
				}
				else if(i ==  super.orbitscontainobjects.size()-1)
				{
					double deltar1 = super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue() -  
							super.orbitscontainobjects.get(i-1).gettrack().getridus().doubleValue();
					PhysicalObject p2 = (PhysicalObject)super.orbitscontainobjects.get(i-1).getthingsintrack().get(0);
					if((p1.getplanetr().doubleValue() + p2.getplanetr().doubleValue() < deltar1) &&
							(super.orbitscontainobjects.get(i).getthingsintrack().size() == 0))
					{
						super.addtoorbit(object, orbit);
					}
					else
					{
						System.out.println("This operation causes the system to be illegal.");
						System.exit(0);
					}
				}
					
				else
				{
					
						double deltar1 = super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue() -  
								super.orbitscontainobjects.get(i-1).gettrack().getridus().doubleValue();
						double deltar2 = super.orbitscontainobjects.get(i+1).gettrack().getridus().doubleValue() -  
								super.orbitscontainobjects.get(i).gettrack().getridus().doubleValue();
						PhysicalObject p2 = (PhysicalObject)super.orbitscontainobjects.get(i-1).getthingsintrack().get(0);
						PhysicalObject p3 = (PhysicalObject)super.orbitscontainobjects.get(i+1).getthingsintrack().get(0);
						if((p1.getplanetr().doubleValue() + p2.getplanetr().doubleValue() < deltar1) &&
								(p1.getplanetr().doubleValue() + p3.getplanetr().doubleValue() < deltar2) &&
									(super.orbitscontainobjects.get(i).getthingsintrack().size() == 0))
						{
							super.addtoorbit(object, orbit);
						}
						else
						{
							System.out.println("This operation causes the system to be illegal.");
							System.exit(0);
						}
					
				}
			}
		}
		
	}
	
	@Override
	public void readfile(String filename) throws IOException {
		String[] filenames = filename.split(" ");
		String pathname;
		if(filenames.length == 1)
			pathname = "src\\txt\\" + filename;
		else
			pathname = "test\\txt\\" + filenames[1];
		File file = new File(pathname);
    	InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
    	BufferedReader br = new BufferedReader(reader);
    	String format1 = "(Stellar ::= )<(\\w+,?|[\\w|.]+,?)+>";
		String format2 = "(Planet ::= )<(\\w+,?|[\\w|.]+,?)+>";
		String line;
		while((line = br.readLine()) != null)
		{
			Matcher m1 = Pattern.compile(format1).matcher(line);
			Matcher m2 = Pattern.compile(format2).matcher(line);
			while(m1.find())
			{
				String[] couples = m1.group().split("Stellar ::= <|,|>");
				number r , quality ;
				r = new number(couples[2]);
				quality = new number(couples[3]);
				CentralObject center = CentralObject.Q2creator(couples[1], r.doubleValue(), quality.doubleValue());
				super.addcenterobject((L)center);
			}
			while(m2.find())
			{
				String[] couples = m2.group().split("Planet ::= <|,|>");
				number v , objectr , orbitr ;
				objectr = new number(couples[4]);
				orbitr = new number(couples[5]);
				v = new number(couples[6]);
				double angle_360 = Double.valueOf(couples[8]) , angle;
				if(angle_360 >= 0 && angle_360 < 360)
				{
					angle = angle_360 ;
				}
				else
				{
					angle = (((angle_360%360)+360)%360) ;
				}
				Track t = Track.Roundcreator(orbitr.doubleValue());
				super.addorbit(t);
				PhysicalObject object = PhysicalObject.Q2creator(couples[1], couples[2], couples[3], objectr.doubleValue(), v.doubleValue(), couples[7]);
				addtoorbit((E)object, t);
				Position<E> p = new Position<E>((E)object , orbitr.doubleValue() , angle);
				super.positions.add(p);
			}
		}
		if(!islegal())
		{
			System.out.println("This Stellar System is illegal!");
			System.exit(0);
		}
	}

	
	@Override
	public void deleteorbitobj(E obj) {
		int i , j;
		for(i = 0 ;  i < objects.size() ; i++)
		{
			E o = objects.get(i);
			if(o.equals(obj))
			{
				objects.remove(o);
				for(j = 0 ; j < positions.size() ; j++)
				{
					Position<E> p = positions.get(j);
					if(p.getobject().equals(obj))
					{
						positions.remove(p);
						deleteinrelco(obj, relbetweenco);
						deleteinreloo(obj, relbetweenoo);
						System.out.println("Delete "+ obj.toString() +"'s operation succeeded.");
						checkRep();
						return;
					}
				}
			}
		}
		System.out.println("Delete operation failed.");
		checkRep();
		return;
	}

}
