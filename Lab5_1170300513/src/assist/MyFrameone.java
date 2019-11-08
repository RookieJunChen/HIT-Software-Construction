package assist;

import centralobject.CentralObject;
import circularorbit.CircularOrbit;
import circularorbit.SocialNetworkCircle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 用于进行可视化的辅助类.
 * 
 * @author junbaba
 *
 * @param <L> 中心物体类型
 * @param <E> 轨道物体类型
 */
public class MyFrameone<L, E> extends JFrame {
  private MyPanel mp;
  private CircularOrbit<L, E> cir;
  int x0 = 250;
  int y0 = 250;

  /**
   * 构造函数.
   */
  public MyFrameone(CircularOrbit<L, E> cir) {
    this.cir = cir;
    this.setBounds(100, 100, 500, 500);
    this.setDefaultCloseOperation(1);
    mp = new MyPanel();
    this.add(mp);
    this.setVisible(true);
  }

  class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
      List<DrawPosition<E>> positions = new ArrayList<>();
      super.paint(g);
      g.setColor(Color.RED);
      drawo(x0, y0, 10, g);
      CentralObject centralObject = (CentralObject) cir.getcenter();
      g.drawString(centralObject.getname(), x0 - 5, y0 + 3);
      for (int i = 0; i < cir.getorbits().size(); i++) {
        g.setColor(Color.BLACK);
        int r = (250 / (cir.getorbits().size() + 2))
            * cir.getorbits().get(i).gettrack().getridus().intValue();
        drawo(x0, y0, r, g);
        g.setColor(Color.BLUE);
        double angle =
            ((double) 360 / cir.getorbits().get(i).getthingsintrack().size()) / 180 * Math.PI;
        for (int j = 0; j < cir.getorbits().get(i).getthingsintrack().size(); j++) {
          int x = x0 + (int) (r * Math.cos(angle * j));
          int y = y0 + (int) (r * Math.sin(angle * j));
          drawo(x, y, 5, g);
          fillo(x, y, 5, g);
          positions
              .add(new DrawPosition<E>(cir.getorbits().get(i).getthingsintrack().get(j), x, y));
        }
      }
      if (cir.getClass() == SocialNetworkCircle.class) {
        g.setColor(Color.GREEN);
        for (int j = 0; j < cir.getrelbetweenco().get(0).getrel().size(); j++) {
          E obj = cir.getrelbetweenco().get(0).getrel().get(j).getobj();
          DrawPosition<E> p = getpos(positions, obj);
          g.drawLine(x0, y0, p.xpos, p.ypos);
        }
        g.setColor(Color.YELLOW);
        for (int i = 0; i < cir.getrelbetweenoo().size(); i++) {
          E obj1 = cir.getrelbetweenoo().get(i).geto();
          DrawPosition<E> p1 = getpos(positions, obj1);
          for (int j = 0; j < cir.getrelbetweenoo().get(i).getrel().size(); j++) {
            E obj2 = cir.getrelbetweenoo().get(i).getrel().get(j).getobj();
            DrawPosition<E> p2 = getpos(positions, obj2);
            g.drawLine(p1.xpos, p1.ypos, p2.xpos, p2.ypos);
          }
        }
      }
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


  private DrawPosition<E> getpos(List<DrawPosition<E>> positions, E obj) {
    int i;
    for (i = 0; i < positions.size(); i++) {
      if (positions.get(i).object.equals(obj)) {
        return positions.get(i);
      }
    }
    throw new UnsupportedOperationException();
  }
}
