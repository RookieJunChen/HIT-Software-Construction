package circularOrbit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import centralObject.CentralObject;
import physicalObject.PhysicalObject;
import track.Track;
import java.util.regex.*;

import assist.number;

/**
 * 一个实现CircularOrbit并继承了ConcreteCircularOrbit的类，用于完成Q3的AtomStructure。
 * @author junbaba
 *
 * @param 中心物体类型<L>
 * @param 轨道物体类型<E>
 */
public class AtomStructure<L,E> extends ConcreteCircularOrbit<L, E>  implements CircularOrbit<L,E>
{		
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
		String format1 = "(ElementName ::= )([A-Z][a-z]*)";
		String format2 = "(NumberOfTracks ::= )(\\d+)";
		String format3 = "(NumberOfElectron ::= )(\\d+/\\d+;?)+";
		String elename = null;
		int orbitnum = 0 , flag1 = 0 , flag2 = 0;
		String line;
		while((line = br.readLine()) != null)
		{
			Matcher m1 = Pattern.compile(format1).matcher(line);
			Matcher m2 = Pattern.compile(format2).matcher(line);
			Matcher m3 = Pattern.compile(format3).matcher(line);
			while(m1.find())
			{
				elename = m1.group(2);
				flag1 = 1;
			}
			while(m2.find())
			{
				orbitnum = Integer.valueOf(m2.group(2));
				flag2 = 1;
			}
			while(m3.find())
			{
				String[] couples = m3.group().split("NumberOfElectron ::= |;");
				for(int i = 1 ; i < couples.length ; i++)
				{
					String[] numbers = couples[i].split("/");
					Track t = Track.Roundcreator(Integer.valueOf(numbers[0]));
					super.addorbit(t);
					for(int j = 1 ; j <= Integer.valueOf(numbers[1]) ; j++)
					{
						PhysicalObject p = PhysicalObject.Q3creator();
						super.addtoorbit((E)p, t);
					}
				}
			}
		}
		if((flag1 == 1) && (flag2 == 1))
		{
			CentralObject center = CentralObject.Q3creator(elename);
			super.addcenterobject((L)center);
		}
		
	}

	@Override
	public boolean islegal() {
		return true;
	}

	
	
	
	

}
