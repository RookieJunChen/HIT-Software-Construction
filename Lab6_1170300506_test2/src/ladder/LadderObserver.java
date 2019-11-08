package ladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import strategy.ChooseLadderStrategy;
import strategy.LimitNumberStrategy;
import strategy.VacantBestStrategy;
import strategy.SpeedBestStrategy;

/**
 * Observe all of the ladders.
 *
 */
public class LadderObserver {
  private List<Ladder> ladders = Collections.synchronizedList(new ArrayList<>());
  private final int numLadders;
  private final int rungs;
  private final ChooseLadderStrategy chooseLadder;
  private final int limit;

  /**
   * constructor
   */
  public LadderObserver(int numLadders, int rungs, int limit, int opt) {
    this.numLadders = numLadders;
    this.rungs = rungs;
    this.limit = limit;
    generateLadders();
    chooseLadder = getChooseLadderStrategy(opt);
  }

  public Ladder getLadder(String direction) {
    return chooseLadder.getLadder(direction);
  }

  public void returnLadder(Ladder ladder) {
    chooseLadder.returnLadder(ladder);
  }

  /**
   * 设置选择梯子策略.
   * 
   * @return 某种具体选择策略.
   */
  private ChooseLadderStrategy getChooseLadderStrategy(int opt) {
    switch (opt) {
    case 1:
      return new VacantBestStrategy(ladders);

    case 2:
      return new LimitNumberStrategy(ladders, limit);

    case 3:
      return new SpeedBestStrategy(ladders);

    default:
      return new VacantBestStrategy(ladders);
    }
  }

  private void generateLadders() {
    for (int i = 0; i < numLadders; i++) {
      ladders.add(new Ladder(i, rungs));
    }
  }

  /**
   * 所有梯子的可视化string.
   * 
   * @return - list string.
   */
  public List<String> getLadderView() {
    List<String> view = new ArrayList<>();
    for (Ladder ladder : ladders) {
      view.add(ladder.toView());
    }
    return view;
  }

}
