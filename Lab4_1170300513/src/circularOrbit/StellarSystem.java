package circularOrbit;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.ReadFileFailException;
import Exceptions.SyntaxErrorException;
import Exceptions.TheSameTagRException;
import StellarSystemExceptions.AngleException;
import StellarSystemExceptions.DirctionException;
import StellarSystemExceptions.PlanetNumberException;
import StellarSystemExceptions.SunNumberException;
import applications.StellarSystem_app;
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
	public void addtoorbit(E object, Track orbit)  throws TheSameTagRException
	{
		assert object != null;
		assert orbit != null;
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
						throw new UnsupportedOperationException();
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
						throw new UnsupportedOperationException();
					}
				}
					
				/*else
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
							throw new UnsupportedOperationException();
						}
					
				}*/
			}
		}
		
	}
	
	@Override
	public void readfile(String filename) throws IOException,ReadFileFailException {
		
		Logger log = ConcreteCircularOrbit.log;
		if(log.getHandlers().length == 0)
			log.addHandler(StellarSystem_app.fh);
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
		int linecounter = 1 , eflag = 0;
		while((line = br.readLine()) != null)
		{
			try 
			{
				Matcher m1 = Pattern.compile(format1).matcher(line);
				Matcher m2 = Pattern.compile(format2).matcher(line);
				while(m1.find())
				{
					String[] couples = m1.group().split("Stellar ::= <|,|>");
					number r = null, quality = null ;
					try {
						r = new number(couples[2]);
						quality = new number(couples[3]);
					} catch (Exception e) {
						throw new SunNumberException();
					}
					CentralObject center = CentralObject.Q2creator(couples[1], r.doubleValue(), quality.doubleValue());
					super.addcenterobject((L)center);
				}
				while(m2.find())
				{
					String[] couples = m2.group().split("Planet ::= <|,|>");
					number v = null, objectr = null , orbitr = null;
					try 
					{
						objectr = new number(couples[4]);
						orbitr = new number(couples[5]);
						v = new number(couples[6]);
					} catch (Exception e) {
						throw new PlanetNumberException();
					}
					double angle_360 = Double.valueOf(couples[8]) , angle;
					if(angle_360 >= 0 && angle_360 < 360)
					{
						angle = angle_360 ;
					}
					else
					{
						throw new AngleException(angle_360);
						//angle = (((angle_360%360)+360)%360) ;
					}
					if((!couples[7].equals("CW")) && (!couples[7].equals("CCW")))
						throw new DirctionException();
					Track t = Track.Roundcreator(orbitr.doubleValue());
					super.addorbit(t);
					PhysicalObject object = PhysicalObject.Q2creator(couples[1], couples[2], couples[3], objectr.doubleValue(), v.doubleValue(), couples[7]);
					addtoorbit((E)object, t);
					Position<E> p = new Position<E>((E)object , orbitr.doubleValue() , angle);
					super.positions.add(p);
				}
				m1 = Pattern.compile(format1).matcher(line);
				m2 = Pattern.compile(format2).matcher(line);
				if((!m1.find()) && (!m2.find()))
					throw new SyntaxErrorException(linecounter, line);
				linecounter++;
			}catch (TheSameTagRException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line + " : " + e.showwrong());
			}catch (SyntaxErrorException e) {
				eflag = 1;
				String suf = "line " + e.getlinenum() + ": " + e.getline();
				String[] words = e.getline().split(" ");
				if((!words[0].equals("Stellar")) && (!words[0].equals("Planet")))
					log.severe(suf + " : Prefix name mismatch.");
				else
					log.severe( suf + " : Syntax mismatch.");
			}catch (AngleException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line + " : " + e.getangle().doubleValue() + " is not in [0,360).");
			}catch (SunNumberException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line + " : Radius and quality do not meet the number's requirement.");
			}catch (PlanetNumberException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line + 
						" : Amounts of planets that do not meet the number's requirement.");
			}catch (DirctionException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line + " : Direction does not meet the requirements.");
			}catch (UnsupportedOperationException e) {
				eflag = 1;
				log.severe("line " + linecounter + ": " + line +" : The add operation causes the system to be illegal.");
			}finally {
				linecounter++;
			}
		}
		if(!islegal())
		{
			log.severe("This Stellar System is illegal!");
			System.out.println();
			throw new ReadFileFailException();
		}
		if(eflag == 1)
			throw new ReadFileFailException();
	}

	
	@Override
	public void deleteorbitobj(E obj) {
		assert obj != null;
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
						log.info("Delete "+ obj.toString() +"'s operation succeeded.");
						checkRep();
						return;
					}
				}
			}
		}
		log.warning("Delete operation failed.");
		checkRep();
		return;
	}

}
