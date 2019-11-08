package strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ladder.Ladder;

/**
 * 优先选择猴子数量最少且猴子数量小于于限制的梯子. 否则等待。
 *
 */
public class LimitNumberStrategy extends VacantBestStrategy {
  private final int limit;

  /**
   * construct super class.
   * 
   * @param newLadders - ladders
   */
  public LimitNumberStrategy(List<Ladder> newLadders, int limit) {
    super(newLadders);
    this.limit = limit;

  }
  
  @Override
  public Ladder getLadder(String direction) {

    // 获得方向相同或无方向的梯子
    Ladder first = null;

    Iterator<Map.Entry<Integer, Ladder>> iterator = ladders.entrySet().iterator();
    while (iterator.hasNext()) {
      first = iterator.next().getValue();
      if (first.getNumMonkeys() >= limit) {
        return null;
      }
      // 无猴子或方向一致
      if (first.getNumMonkeys() == 0 || first.getDirection().equals(direction)) {
        iterator.remove();
        return first;
      }
    }
    return null;
  }
}
