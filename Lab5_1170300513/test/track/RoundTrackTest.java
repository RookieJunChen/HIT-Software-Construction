package track;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoundTrackTest {

  Track track = Track.roundcreator(2);

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testGetridus() {
    int r = 2;
    assertEquals(r, track.getridus().intValue());
  }

}
