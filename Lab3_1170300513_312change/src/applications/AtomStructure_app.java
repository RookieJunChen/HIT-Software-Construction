package applications;

import java.io.IOException;
import java.util.Scanner;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import physicalObject.PhysicalObject;
import thinginTrack.ThinginTrack;
import track.Track;

public class AtomStructure_app {
	public static void main(String[] args) 
	{
		CircularOrbit<CentralObject,PhysicalObject> c = CircularOrbit.empty("Q3");
		CircularOrbitAPIs<CentralObject, PhysicalObject> apIs = new CircularOrbitAPIs<>();
		
		int choice = 0;
		String filename = null;
		System.out.println("Atom Structure:");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Read data files.");
			System.out.println("2.Adding new track.");
			System.out.println("3.Adding object to a particular track.");
			System.out.println("4.Delete a object.");
			System.out.println("5.Delete a track.");
			System.out.println("6.Calculating the Entropy Value of Object Distribution in Orbits.");
			System.out.println("7.Electron transition.");
			System.out.println("8.Visualization of Multi-track Structure on GUI.");
			System.out.println("9.Show the difference from the original.");
			System.out.println("0.Quit.");
			System.out.println("Please input your choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.exit(0);
			case 1:
				System.out.println("Input the file name:");
				filename = sc.next();
				try 
				{
					c.readfile(filename);
				} catch (Exception e) 
				{
					System.out.println("Failed to read the file.");
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please enter track layers:");
				int r1 = sc.nextInt();
				Track t1 = Track.Roundcreator(r1);
				c.addorbit(t1);
				break;
			case 3:
				System.out.println("Which track do you want to add:");
				int r2 = sc.nextInt();
				Track t2 = Track.Roundcreator(r2);
				c.addtoorbit(PhysicalObject.Q3creator(), t2);
				break;
			case 4:
				System.out.println("1.Delete a central object.");
				System.out.println("2.Delete a physical object.");
				System.out.println("Input your choice:");
				choice = sc.nextInt();
				if(choice == 1)
				{
					System.out.println("Input central object information:");
					System.out.println("name:");
					String name = sc.next();
					c.deletecentralobj(CentralObject.Q3creator(name));
				}
				else if(choice == 2)
				{
					System.out.println("Which level of orbital electrons to delete:");
					int n1 = sc.nextInt();
					int flag = 0;
					int i;
					for(i = 0 ; i < c.getorbits().size() ; i++)
					{
						ThinginTrack<PhysicalObject> l = c.getorbits().get(i);
						if(l.gettrack().getridus().intValue() == n1 && l.getthingsintrack().size() >= 1)
						{	
							c.deleteorbitobj(l.getthingsintrack().get(0));
							flag++;
							break;
						}
					}
					if(flag == 0)
						System.out.println("Orbital absence or orbital absence of electrons.");
				}
				else
				{
					System.out.println("Input wrong!");
				}
				break;
			case 5:
				System.out.println("Please enter track layers:");
				int r3 = sc.nextInt();
				Track t3 = Track.Roundcreator(r3);
				c.removeorbit(t3);
				break;
			case 6:
				System.out.println("The Entropy Value of Object Distribution in Orbits£º" + apIs.getObjectDistributionEntropy(c) );
				break;
			case 7:
				System.out.println("Which level of orbital electrons to choose:");
				int n1 = sc.nextInt();
				System.out.println("To what level:");
				int r4 = sc.nextInt();
				Track t4 = Track.Roundcreator(r4);
				int flag2 = 0;
				for(ThinginTrack<PhysicalObject> l : c.getorbits())
				{
					if(l.gettrack().getridus().intValue() == n1 && l.getthingsintrack().size() >= 1)
					{
						c.transit(l.getthingsintrack().get(0), t4);
						flag2 ++;
					}
				}
				if(flag2 == 0)
					System.out.println("Orbital absence or orbital absence of electrons");
				break;
			case 8:
				CircularOrbitHelper.visualize(c);
				break;
			case 9:
				CircularOrbit<CentralObject , PhysicalObject> co = CircularOrbit.empty("Q3");
				try 
				{
					co.readfile(filename);
				} catch (Exception e) 
				{
					System.out.println("Failed to read the file.");
				}
				System.out.println(apIs.getDifference(c, co));
				break;
			default:
				System.out.println("Input wrong!");
				break;
			}
			sc.nextLine();
			sc.nextLine();
			System.out.println();
		}
	}
}
