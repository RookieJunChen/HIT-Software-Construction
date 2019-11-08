package APIs;



import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import circularOrbit.SocialNetworkCircle;
import physicalObject.PhysicalObject;


public class CircularOrbitHelper
{
	
	public static void visualize(CircularOrbit<CentralObject,PhysicalObject> c)
	{
		new myFrame1<CentralObject,PhysicalObject>(c);
	}
	
	
	/**
	 * SocialNetwoekCircle的可视化操作(动图)
	 * @param c
	 */
	public static  void visualizeSNC(CircularOrbit<CentralObject,PhysicalObject> c)
	{
		myPanel2<CentralObject,PhysicalObject> p = new myPanel2<>(c);
		Thread panelThread = new Thread(p);
	    JFrame frame = new JFrame();
	    frame.add(p);
	    frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(1);
		frame.setVisible(true);
		panelThread.start();
	}
	
}



/**
 * 用于进行可视化的辅助类。
 * @author junbaba
 *
 * @param 中心物体类型<L>
 * @param 轨道物体类型<E>
 */
class myFrame1<L,E> extends JFrame
{
	private myPanel mp;
	private  CircularOrbit<L,E> cir;
	int x0 = 250;
	int y0 = 250;
	
	public myFrame1(CircularOrbit<L,E> cir) 
	{
		this.cir = cir;
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(1);
		mp = new myPanel();
		this.add(mp);
		this.setVisible(true);
	}
	
	class myPanel extends JPanel
	{
		@Override
		public void paint(Graphics g)
		{
			List<DrawPosition<E>> positions = new ArrayList<>();
			int i , j;
			super.paint(g);
			double ang = ((double)360 / cir.getcenter().size()) /180 *Math.PI;
			for(i = 0 ; i < cir.getcenter().size() ; i++)
			{
				CentralObject center = (CentralObject)cir.getcenter().get(i);
				int x = x0 + (int)(10 * Math.cos(ang*i));
				int y = y0 + (int)(10 * Math.sin(ang*i));
				if(center.getname().equals("Proton"))
				{
					g.setColor(Color.RED);	
					drawo(x, y, 6, g);
				}
				else if(center.getname().equals("Neutron"))
				{
					g.setColor(Color.YELLOW);
					drawo(x, y, 6, g);
				}
				else
				{
					g.setColor(Color.RED);
					drawo(x0, y0, 10, g);
					g.drawString(center.getname(), x0-5, y0+3);
				}
			}
			for(i = 0 ; i < cir.getorbits().size() ; i++ )
			{
				g.setColor(Color.BLACK);
				int r = (250 / (cir.getorbits().size()+2)) * cir.getorbits().get(i).gettrack().getridus().intValue();
				drawo(x0, y0,r, g);
				g.setColor(Color.BLUE);
				double angle = ((double)360 / cir.getorbits().get(i).getthingsintrack().size()) /180 *Math.PI;
				for(j = 0 ; j < cir.getorbits().get(i).getthingsintrack().size() ; j++)
				{
					int x = x0 + (int)(r * Math.cos(angle*j));
					int y = y0 + (int)(r * Math.sin(angle*j));
					drawo(x, y, 3, g);
					fillo(x, y, 3, g);
					positions.add(new DrawPosition<E>(cir.getorbits().get(i).getthingsintrack().get(j), x, y));
				}
			}
			if(cir.getClass() == SocialNetworkCircle.class)
			{
				g.setColor(Color.GREEN);
				for(j = 0 ; j < cir.getrelbetweenco().get(0).getrel().size() ; j++)
				{
					E obj = cir.getrelbetweenco().get(0).getrel().get(j).getobj();
					DrawPosition<E> p = getpos(positions , obj);
					g.drawLine(x0, y0, p.x, p.y);
				}
				g.setColor(Color.YELLOW);
				for(i = 0 ; i < cir.getrelbetweenoo().size() ; i++)
				{
					E obj1 = cir.getrelbetweenoo().get(i).geto();
					DrawPosition<E> p1 = getpos(positions , obj1);
					for( j = 0 ; j < cir.getrelbetweenoo().get(i).getrel().size() ; j++)
					{
						E obj2 = cir.getrelbetweenoo().get(i).getrel().get(j).getobj();
						DrawPosition<E> p2 = getpos(positions , obj2);
						g.drawLine(p1.x, p1.y, p2.x, p2.y);
					}
				}
			}
		}
	}
	
	private void drawo(double x0 , double y0 , double r  , Graphics g)
	{
		int width = (int)(2 * r);
		int height = (int)(2 * r);
		int x = (int)(x0 - r);
		int y = (int)(y0 - r);
		g.drawOval(x, y, width, height);
	}
	
	private void fillo(double x0 , double y0 , double r  , Graphics g)
	{
		int width = (int)(2 * r);
		int height = (int)(2 * r);
		int x = (int)(x0 - r);
		int y = (int)(y0 - r);
		g.fillOval(x, y, width, height);;
	}
	
	
	private  DrawPosition<E> getpos(List<DrawPosition<E>> positions , E obj)
	{
		int i;
		for(i = 0 ; i < positions.size() ; i++)
		{
			if(positions.get(i).object.equals(obj))
			{
				return positions.get(i);
			}
		}
		throw new UnsupportedOperationException();
	}
}


/**
 * 用于辅助画图使用的数据结构，故无RI,AF等
 * @author junbaba
 *
 * @param <E>
 */
class DrawPosition<E> 
{
	public int x;
	public int y;
	public E object;
	
	public DrawPosition(E object , int x , int y) 
	{
		this.x = x;
		this.y = y;
		this.object = object;
	}
}






class myPanel2<L,E> extends JPanel implements Runnable
{
	CircularOrbit<L, E> cir;
	int x0 = 250;
	int y0 = 250;
	List<Integer> randoms = new ArrayList<>();
	
	public myPanel2(CircularOrbit<L, E> cir) 
	{
		this.cir = cir;
	}
	
	
	
	@Override
	public void paint(Graphics g)
	{
		int i , j;
		super.paint(g);
		g.setColor(Color.RED);
		drawo(x0, y0, 10, g);
		CentralObject centralObject = (CentralObject)cir.getcenter().get(0);
		g.drawString(centralObject.getname(), x0-5, y0+3);
		for(i = 0 ; i < cir.getorbits().size() ; i++ )
		{
			g.setColor(Color.BLACK);
			int r = (250 / (cir.getorbits().size()+2)) * (i+1) ;
			
			drawo(x0, y0,r, g);
			g.setColor(Color.BLUE);
			for(j = 0 ; j < cir.getorbits().get(i).getthingsintrack().size() ; j++)
			{
				
				Random random = new Random();
				randoms.add(random.nextInt(255));
				randoms.add(random.nextInt(255));
				randoms.add(random.nextInt(255));
				g.setColor(new Color(randoms.get(i*j), randoms.get(i*(j+1)) , randoms.get(i*(j+2)) ));
				double anglein360 = cir.getpositions(cir.getorbits().get(i).getthingsintrack().get(j)).getangle().doubleValue();
				double angle = anglein360 /180 * Math.PI;
				int x = x0 + (int)(r * Math.cos(angle));
				int y = y0 + (int)(r * Math.sin(angle));
				drawo(x, y, 5+i, g);
				fillo(x, y, 5+i, g);
			}
		}
		
	}
	
	@Override
	public void run() 
	{
		int t = 0;
		while(true)
		{
			
			try 
			{
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cir.getvpositions(t, "N");
			t += 100;
			this.repaint();
		}
		
	}
	
	private void drawo(double x0 , double y0 , double r  , Graphics g)
	{
		int width = (int)(2 * r);
		int height = (int)(2 * r);
		int x = (int)(x0 - r);
		int y = (int)(y0 - r);
		g.drawOval(x, y, width, height);
	}
	
	private void fillo(double x0 , double y0 , double r  , Graphics g)
	{
		int width = (int)(2 * r);
		int height = (int)(2 * r);
		int x = (int)(x0 - r);
		int y = (int)(y0 - r);
		g.fillOval(x, y, width, height);;
	}
}


