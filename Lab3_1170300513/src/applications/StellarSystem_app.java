package applications;

import java.util.Scanner;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import physicalObject.PhysicalObject;



public class StellarSystem_app {
	public static void main(String[] args) {
		CircularOrbit<CentralObject, PhysicalObject> c = CircularOrbit.empty("Q2");
		CircularOrbitAPIs<CentralObject, PhysicalObject> apIs = new CircularOrbitAPIs<>();
		int choice = 0;
		String filename = null;
		System.out.println("Stellar System:");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Read data files.");
			System.out.println("2.Adding new track.");
			System.out.println("3.Adding object to a particular track.");
			System.out.println("4.Delete a object.");
			System.out.println("5.Delete a track.");
			System.out.println("6.Calculating the entropy value of object distribution in orbits.");
			System.out.println("7.Calculate the position of the planets at the time t of output.");
			System.out.println("8.Calculating the physical distance between the star and a planet.");
			System.out.println("9.Calculate the physical distance between two planets.");
			System.out.println("10.Visual simulation of planetary motion.");
			System.out.println("11.Show the difference from the original.");
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
				}
				break;
			case 2:
				System.out.println("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 3:
				System.out.println("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 4:
				System.out.println("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 5:
				System.out.println("Which track do you want to delete (please enter the track number):");
				int n1 = sc.nextInt();
				c.removeorbit(c.getorbits().get(n1).gettrack());
				break;
			case 6:
				System.out.println("The Entropy Value of Object Distribution in Orbits£º" + apIs.getObjectDistributionEntropy(c) );
				break;
			case 7:
				System.out.println("Input t:");
				double t = sc.nextDouble();
				c.getvpositions( t, "Y");
				break;
			case 8:
				System.out.println("Which orbital object to choose (please enter the track number):");
				int n2 = sc.nextInt();
				PhysicalObject planet = c.getorbits().get(n2).getthingsintrack().get(0);
				System.out.println("The distance between "+ c.getcenter().toString() +" and " + planet.toString() + 
						" is " + c.getdistancefromco(planet));
				break;
			case 9:
				System.out.println("The first orbital object to choose (please enter the track number):");
				int n3 = sc.nextInt();
				System.out.println("The second orbital object to choose (please enter the track number):");
				int n4 = sc.nextInt();
				PhysicalObject p1 = c.getorbits().get(n3).getthingsintrack().get(0);
				PhysicalObject p2 = c.getorbits().get(n4).getthingsintrack().get(0);
				System.out.println("The distance between "+ p1.toString() +" and " + p2.toString() + 
						" is " + apIs.getPhysicalDistance(c, p1, p2));
				break;
			case 10:
				CircularOrbitHelper.visualizeSNC(c);
				
				break;
				
			case 11:
				CircularOrbit<CentralObject , PhysicalObject> co = CircularOrbit.empty("Q2");
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
