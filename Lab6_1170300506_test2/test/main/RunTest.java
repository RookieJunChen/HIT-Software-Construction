package main;

import org.junit.Test;

/**
 * test Run.
 *
 */
public class RunTest {

  @Test
  public void test() {
    Run run = new Run("src/main/configure/cfg1");
    run.launch();
  }
}
