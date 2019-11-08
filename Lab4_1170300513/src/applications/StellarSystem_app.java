package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import Exceptions.ReadFileFailException;
import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import circularOrbit.StellarSystem;
import physicalObject.PhysicalObject;



public class StellarSystem_app {
	public static FileHandler fh = null;
	static {
		try {
			fh = new FileHandler("src\\logging\\StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert fh != null;
		fh.setFormatter(new SimpleFormatter());
	}
	public static void main(String[] args) {
		List<String> logs = new ArrayList<>();
		CircularOrbit<CentralObject, PhysicalObject> c = CircularOrbit.empty("Q2");
		CircularOrbitAPIs<CentralObject, PhysicalObject> apIs = new CircularOrbitAPIs<>();
		Logger log = StellarSystem.log;
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
			System.out.println("12.View the log.");
			System.out.println("0.Quit.");
			System.out.println("Please input your choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				fh.close();
				System.exit(0);
			case 1:
				System.out.println("Input the file name:");
				filename = sc.next();
				try 
				{
					c.readfile(filename);
				} catch (ReadFileFailException e) 
				{
					log.warning("The file is illegal, please select another text file.");
					c = CircularOrbit.empty("Q2");
				}catch (Exception e) {
					log.warning("The path is not valid, please re-enter it.");
					c = CircularOrbit.empty("Q2");
				}
				log.info("Successful reading of " + filename );
				break;
			case 2:
				log.severe("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 3:
				log.severe("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 4:
				log.severe("This operation will cause the system to be illegal so that you can't do it.");
				break;
			case 5:
				System.out.println("Which track do you want to delete (please enter the track number):");
				int n1 = sc.nextInt();
				c.removeorbit(c.getorbits().get(n1).gettrack());
				break;
			case 6:
				System.out.println("The Entropy Value of Object Distribution in Orbits：" + apIs.getObjectDistributionEntropy(c) );
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
					log.warning("Failed to read the file.");
				}
				System.out.println(apIs.getDifference(c, co));
				break;
			
			case 12:
				try {
					File file = new File("src\\logging\\StellarSystem.txt");
			    	InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			    	BufferedReader br = new BufferedReader(reader);
			    	String line1 , line2 , matcher;
			    	while((line1 = br.readLine()) != null && (line2 = br.readLine()) != null)
			    	{
			    		logs.add(line1 + "\n" +line2);
			    	}
			    	System.out.println("1.Filtration by time interval.");
					System.out.println("2.Type-by-type filtering.");
					System.out.println("3.Filtering by class.");
					System.out.println("4.Filtering by operation.");
					System.out.println("Please input your choice:");
					choice = sc.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Starting time:");
						String stime = sc.next();
						System.out.println("Ending time:");
						String etime = sc.next();
						int button = 0;
						for(String line : logs)
						{
							if(line.indexOf(stime) != -1)
								button = 1;
							if(button == 1)
								System.out.println(line);
							if(line.indexOf(etime) != -1)
								button = 0;
						}
						break;

					case 2:
						System.out.println("1.信息.");
						System.out.println("2.警告.");
						System.out.println("3.严重.");
						choice = sc.nextInt();
						matcher = "严重";
						switch (choice) {
						case 1:
							matcher = "信息";
							break;
						case 2:
							matcher = "警告";
							break;
						case 3:
							matcher = "严重";
							break;
						default:
							log.warning("Input wrong!");
							break;
						}
						for(String line : logs)
						{
							if(line.indexOf(matcher) != -1)
							{
								System.out.println(line);
							}
						}
						break;
						
					case 3:
						System.out.println("Enter the class:");
						matcher = sc.next();
						for(String line : logs)
						{
							if(line.indexOf(matcher) != -1)
							{
								System.out.println(line);
							}
						}
						break;
						
					case 4:
						System.out.println("Enter the operation:");
						matcher = sc.next();
						for(String line : logs)
						{
							if(line.indexOf(matcher) != -1)
							{
								System.out.println(line);
							}
						}
						break;
						
					default:
						log.warning("Input wrong!");
						break;
					}
					logs = new ArrayList<>();
				} catch (RuntimeException e) {
				    throw e;
				}catch (Exception e) {
					log.warning("Failed to read the log.");
				}
				break;
				
				
			default:
				log.warning("Input wrong!");
				break;
			}
			sc.nextLine();
			sc.nextLine();
			System.out.println();
		}
	}
}
