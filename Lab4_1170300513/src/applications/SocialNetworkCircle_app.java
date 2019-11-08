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
import circularOrbit.SocialNetworkCircle;
import physicalObject.PhysicalObject;


public class SocialNetworkCircle_app {
	public static FileHandler fh = null;
	static {
		try {
			fh = new FileHandler("src\\logging\\SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert fh != null;
		fh.setFormatter(new SimpleFormatter());
	}
	public static void main(String[] args) 
	{
		List<String> logs = new ArrayList<>();
		CircularOrbit<CentralObject, PhysicalObject> c = CircularOrbit.empty("Q5");
		CircularOrbitAPIs<CentralObject, PhysicalObject> apIs = new CircularOrbitAPIs<>();
		Logger log = SocialNetworkCircle.log;
		int choice = 0;
		int i , j;
		int t1 , t2 , o1 , o2;
		String filename = null;
		System.out.println("Social Network Circle:");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Read data files.");
			System.out.println("2.Add a social relationship.");
			System.out.println("3.Delete a social relationship.");
			System.out.println("4.Delete a friend.");
			System.out.println("5.Delete a track.");
			System.out.println("6.Calculating the entropy value of object distribution in orbits.");
			System.out.println("7.Visualization of Multi-track Structure on GUI.");
			System.out.println("8.Calculate the logical distance between users on two orbits.");
			System.out.println("9.Calculate the \"information diffusion\" of a friend in the first orbit.");
			System.out.println("10.Determine the user's trajectory.");
			System.out.println("11.Show the difference from the original.");
			System.out.println("12.View the log.");
			System.out.println("0.Quit.");
			System.out.println("Please input your choice:");
			choice = sc.nextInt();
			switch(choice)
			{
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
					c = CircularOrbit.empty("Q5");
				}catch (Exception e) {
					e.printStackTrace();
					//log.warning("The path is not valid, please re-enter it.");
					c = CircularOrbit.empty("Q5");
				}
				log.info("Successful reading of " + filename );
				break;
				
				
			case 2:
				System.out.println("1.The relation between central object and orbital object.");
				System.out.println("2.The relation between orbital object and orbital object.");
				System.out.println("Input your choice:");
				choice = sc.nextInt();
				if(choice == 2)
				{
					System.out.println("Input information for object1:");
					System.out.println("A new one?");
					PhysicalObject object1;
					if(sc.next().equals("Y"))
					{
						System.out.println("The object1's name:");
						String name = sc.next();
						System.out.println("The object1's sex:");
						String sex = sc.next();
						System.out.println("The object1's ages");
						int ages = sc.nextInt();
						object1 = PhysicalObject.Q5creator(name, sex, ages);
					}
					else
					{
						System.out.println("Please enter the track number:");
						t1 = sc.nextInt();
						if(t1 >= c.getorbits().size())
						{
							log.warning("Wrong operation!");
							break;
						}
						for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
						{
							System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
						}
						System.out.println("Which one would you like to choose:");
						o1 = sc.nextInt();
						if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
								(c.getorbits().get(t1).getthingsintrack().size() == 0))
						{
							log.warning("Wrong operation!");
							break;
						}
						object1 = c.getorbits().get(t1).getthingsintrack().get(o1);
					}
					System.out.println("Input information for object2:");
					System.out.println("A new one?");
					PhysicalObject object2;
					if(sc.next().equals("Y"))
					{
						System.out.println("The object2's name:");
						String name = sc.next();
						System.out.println("The object2's sex:");
						String sex = sc.next();
						System.out.println("The object2's ages");
						int ages = sc.nextInt();
						object2 = PhysicalObject.Q5creator(name, sex, ages);
					}
					else
					{
						System.out.println("Please enter the track number:");
						t2 = sc.nextInt();
						if(t2 >= c.getorbits().size())
						{
							log.warning("Wrong operation!");
							break;
						}
						for(i = 0 ; i < c.getorbits().get(t2).getthingsintrack().size();i++)
						{
							System.out.println(i + ". " + c.getorbits().get(t2).getthingsintrack().get(i));
						}
						System.out.println("Which one would you like to choose:");
						o2 = sc.nextInt();
						if((o2 >= c.getorbits().get(t2).getthingsintrack().size() )||  
								(c.getorbits().get(t2).getthingsintrack().size() == 0))
						{
							log.warning("Wrong operation!");
							break;
						}
						object2 = c.getorbits().get(t2).getthingsintrack().get(o2);
					}
					System.out.println("Input the intimacy:");
					double intimacy = sc.nextDouble();
					c.addcontactoo(object1, object2, intimacy);
					
				}
				else if(choice == 1)
				{
					System.out.println("Input information for object:");
					System.out.println("A new one?");
					PhysicalObject object;
					if(sc.next().equals("Y"))
					{
						System.out.println("The object's name:");
						String name = sc.next();
						System.out.println("The object's sex:");
						String sex = sc.next();
						System.out.println("The object's ages");
						int ages = sc.nextInt();
						object = PhysicalObject.Q5creator(name, sex, ages);
					}
					else
					{
						System.out.println("Please enter the track number:");
						t1 = sc.nextInt();
						if(t1 >= c.getorbits().size())
						{
							log.warning("Wrong operation!");
							break;
						}
						for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
						{
							System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
						}
						System.out.println("Which one would you like to choose:");
						o1 = sc.nextInt();
						if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
								(c.getorbits().get(t1).getthingsintrack().size() == 0))
						{
							log.warning("Wrong operation!");
							break;
						}
						object = c.getorbits().get(t1).getthingsintrack().get(o1);
					}
					System.out.println("Input the intimacy:");
					double intimacy = sc.nextDouble();
					c.addcontactco(c.getcenter(), object, intimacy);
				}
				else
				{
					System.out.println("Input wrong!");
				}
				break;
				
				
				
			case 3:
				System.out.println("1.The relation between central object and orbital object.");
				System.out.println("2.The relation between orbital object and orbital object.");
				System.out.println("Input your choice:");
				choice = sc.nextInt();
				if(choice == 2)
				{
					System.out.println("Input information for object1:");
					System.out.println("Please enter the track number:");
					t1 = sc.nextInt();
					if(t1 >= c.getorbits().size())
					{
						log.warning("Wrong operation!");
						break;
					}
					for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
					{
						System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
					}
					System.out.println("Which one would you like to choose:");
					o1 = sc.nextInt();
					if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
							(c.getorbits().get(t1).getthingsintrack().size() == 0))
					{
						log.warning("Wrong operation!");
						break;
					}
					PhysicalObject object1 = c.getorbits().get(t1).getthingsintrack().get(o1);
					
					System.out.println("Input information for object2:");
					System.out.println("Please enter the track number:");
					t2 = sc.nextInt();
					if(t2 >= c.getorbits().size())
					{
						System.out.println("Wrong operation!");
						break;
					}
					for(i = 0 ; i < c.getorbits().get(t2).getthingsintrack().size();i++)
					{
						System.out.println(i + ". " + c.getorbits().get(t2).getthingsintrack().get(i));
					}
					System.out.println("Which one would you like to choose:");
					o2 = sc.nextInt();
					if((o2 >= c.getorbits().get(t2).getthingsintrack().size() )||  
							(c.getorbits().get(t2).getthingsintrack().size() == 0))
					{
						log.warning("Wrong operation!");
						break;
					}
					PhysicalObject object2 = c.getorbits().get(t2).getthingsintrack().get(o2);
					c.deletecontactoo(object1, object2);
				}
				else if(choice == 1)
				{
					System.out.println("Please enter the track number:");
					t1 = sc.nextInt();
					if(t1 >= c.getorbits().size())
					{
						log.warning("Wrong operation!");
						break;
					}
					for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
					{
						System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
					}
					System.out.println("Which one would you like to choose:");
					o1 = sc.nextInt();
					if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
							(c.getorbits().get(t1).getthingsintrack().size() == 0))
					{
						log.warning("Wrong operation!");
						break;
					}
					PhysicalObject object = c.getorbits().get(t1).getthingsintrack().get(o1);
					c.deletecontactco(c.getcenter(), object);
				}
				else
				{
					log.warning("Input wrong!");
				}
				break;
				
				
				
			case 4:
				System.out.println("Please enter the track number:");
				t1 = sc.nextInt();
				if(t1 >= c.getorbits().size())
				{
					log.warning("Wrong operation!");
					break;
				}
				for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
				{
					System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
				}
				System.out.println("Which one would you like to choose:");
				o1 = sc.nextInt();
				if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
						(c.getorbits().get(t1).getthingsintrack().size() == 0))
				{
					log.warning("Wrong operation!");
					break;
				}
				PhysicalObject friend = c.getorbits().get(t1).getthingsintrack().get(o1);
				c.deleteorbitobj(friend);
				break;
				
				
				
			case 5:
				System.out.println("The track number of the track to be deleted:");
				int n1 = sc.nextInt();
				c.removeorbit(c.getorbits().get(n1).gettrack());
				break;
				
				
				
			case 6:
				System.out.println("The Entropy Value of Object Distribution in Orbits：" + apIs.getObjectDistributionEntropy(c) );
				break;
				
				
			case 7:
				CircularOrbitHelper.visualize(c);
				break;
				
				
				
			case 8:
				System.out.println("Please enter the track number:");
				t1 = sc.nextInt();
				if(t1 >= c.getorbits().size())
				{
					log.warning("Wrong operation!");
					break;
				}
				for(i = 0 ; i < c.getorbits().get(t1).getthingsintrack().size();i++)
				{
					System.out.println(i + ". " + c.getorbits().get(t1).getthingsintrack().get(i));
				}
				System.out.println("Which one would you like to choose:");
				o1 = sc.nextInt();
				if((o1 >= c.getorbits().get(t1).getthingsintrack().size() )||  
						(c.getorbits().get(t1).getthingsintrack().size() == 0))
				{
					log.warning("Wrong operation!");
					break;
				}
				System.out.println("Please enter the track number:");
				t2 = sc.nextInt();
				if(t2 >= c.getorbits().size())
				{
					log.warning("Wrong operation!");
					break;
				}
				for(i = 0 ; i < c.getorbits().get(t2).getthingsintrack().size();i++)
				{
					System.out.println(i + ". " + c.getorbits().get(t2).getthingsintrack().get(i));
				}
				System.out.println("Which one would you like to choose:");
				o2 = sc.nextInt();
				if((o2 >= c.getorbits().get(t2).getthingsintrack().size() )||  
						(c.getorbits().get(t2).getthingsintrack().size() == 0))
				{
					log.warning("Wrong operation!");
					break;
				}
				PhysicalObject p1 = c.getorbits().get(t1).getthingsintrack().get(o1);
				PhysicalObject p2 = c.getorbits().get(t2).getthingsintrack().get(o2);
				int dis = apIs.getLogicalDistance(c, p1, p2);
				System.out.println("The logical distance between " + p1.toString() + " and " + p2.toString() +" is " + dis );
				break;
				
				
				
			case 9:
				for(i = 0 ; i < c.getorbits().get(0).getthingsintrack().size();i++)
				{
					System.out.println(i + ". " + c.getorbits().get(0).getthingsintrack().get(i));
				}
				System.out.println("Which one would you like to choose:");
				int o = sc.nextInt();
				if((o >= c.getorbits().get(0).getthingsintrack().size() )||  
						(c.getorbits().get(0).getthingsintrack().size() == 0))
				{
					log.warning("Wrong operation!");
					break;
				}
				PhysicalObject p = c.getorbits().get(0).getthingsintrack().get(o);
				System.out.println("Through " + p.toString() + " you can meet " + c.getinfodiffu(p) + " friend(s)");
				break;
				
				
				
				
				
			case 10:
				for(i = 0 ; i < c.getorbits().size() ; i++)
				{
					System.out.println("Track " + c.getorbits().get(i).gettrack().getridus().intValue() + ":");
					for(j = 0 ; j < c.getorbits().get(i).getthingsintrack().size(); j++)
					{
						System.out.println(c.getorbits().get(i).getthingsintrack().get(j));
					}
				}
				System.out.println();
				break;
				
			case 11:
				CircularOrbit<CentralObject , PhysicalObject> co = CircularOrbit.empty("Q5");
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
					File file = new File("src\\logging\\SocialNetworkCircle.txt");
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
