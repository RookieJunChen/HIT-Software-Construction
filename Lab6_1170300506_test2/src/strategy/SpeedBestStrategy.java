package strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import ladder.Ladder;

/**
 * 优先选择没有猴子的梯子，若有猴子，则选择前方猴子最快的梯子.
 * 
 */
public class SpeedBestStrategy implements ChooseLadderStrategy {
  /**
   * 根据最前猴子速度对梯子降序排列.
   */
  private SortedMap<Integer, Ladder> ladders = Collections
      .synchronizedSortedMap(new TreeMap<Integer, Ladder>(new Comparator<Integer>() {
        public int compare(Integer int1, Integer int2) {
          if (int1 == int2) {
            return 1;
          } else {
            return int2 - int1;
          }
        }
      }));

  /**
   * add ladders to tree map.
   * 
   * @param newLadders - to be sorted.
   */
  public SpeedBestStrategy(List<Ladder> newLadders) {
    for (Ladder ladder : newLadders) {
      returnLadder(ladder);
    }
    System.out.println("----------init------------");
    System.out.println(ladders.size());
  }

  @Override
  public Ladder getLadder(String direction) {

    // 获得方向相同或无方向的梯子
    Ladder first = null;

    Iterator<Map.Entry<Integer, Ladder>> iterator = ladders.entrySet().iterator();

    while (iterator.hasNext()) {
      first = iterator.next().getValue();
      // 无猴子或方向一致
      if (first.getNumMonkeys() == 0 || first.getDirection().equals(direction)) {
        iterator.remove();
        return first;
      }
    }

    return null;
  }

  @Override
  public boolean returnLadder(Ladder ladder) {
    if (ladder == null) {
      return false;
    }
    return ladders.put(ladder.getFirstSpeed(), ladder) != null;
  }
}
