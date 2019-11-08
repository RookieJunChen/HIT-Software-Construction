package assist;

import centralobject.CentralObject;
import circularorbit.CircularOrbit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;


public class MyPaneltwo<L, E> extends JPanel implements Runnable {
  CircularOrbit<L, E> cir;
  int x0 = 250;
  int y0 = 250;
  List<Integer> randoms = new ArrayList<>();

  public MyPaneltwo(CircularOrbit<L, E> cir) {
    this.cir = cir;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.RED);
    drawo(x0, y0, 10, g);
    CentralObject centralObject = (CentralObject) cir.getcenter();
    g.drawString(centralObject.getname(), x0 - 5, y0 + 3);
    for (int i = 0; i < cir.getorbits().size(); i++) {
      g.setColor(Color.BLACK);
      int r = (250 / (cir.getorbits().size() + 2)) * (i + 1);

      drawo(x0, y0, r, g);
      g.setColor(Color.BLUE);
      for (int j = 0; j < cir.getorbits().get(i).getthingsintrack().size(); j++) {

        Random random = new Random();
        randoms.add(random.nextInt(255));
        randoms.add(random.nextInt(255));
        randoms.add(random.nextInt(255));
        g.setColor(
            new Color(randoms.get(i * j), randoms.get(i * (j + 1)), randoms.get(i * (j + 2))));
        double anglein360 = cir.getpositions(cir.getorbits().get(i).getthingsintrack().get(j))
            .getangle().doubleValue();
        double angle = anglein360 / 180 * Math.PI;
        int x = x0 + (int) (r * Math.cos(angle));
        int y = y0 + (int) (r * Math.sin(angle));
        drawo(x, y, 5 + i, g);
        fillo(x, y, 5 + i, g);
      }
    }

  }

  @Override
  public void run() {
    int t = 0;
    while (true) {

      try {
        Thread.sleep(10);
      } catch (Exception e) {
        e.printStackTrace();
      }
      cir.getvpositions(t, "N");
      t += 100;
      this.repaint();
    }

  }

  private void drawo(double x0, double y0, double r, Graphics g) {
    int width = (int) (2 * r);
    int height = (int) (2 * r);
    int x = (int) (x0 - r);
    int y = (int) (y0 - r);
    g.drawOval(x, y, width, height);
  }

  private void fillo(double x0, double y0, double r, Graphics g) {
    int width = (int) (2 * r);
    int height = (int) (2 * r);
    int x = (int) (x0 - r);
    int y = (int) (y0 - r);
    g.fillOval(x, y, width, height);;
  }
}
