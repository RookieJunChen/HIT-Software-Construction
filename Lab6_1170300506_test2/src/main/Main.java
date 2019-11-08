package main;

/**
 * Launcher.
 *
 */
public class Main {

  /**
   * launch
   */
  public static void main(String[] args) {

    /*
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/configure/cfg1").launch();
        ;
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/configure/cfg2").launch();
        ;
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/configure/cfg3").launch();
        ;
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/configure/cfg4").launch();
        ;
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/configure/cfg5").launch();
        ;
      }
    }).start();
    */
    
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        new Run("src/main/txt/Competition_1").launch();
        ;
      }
    }).start();
  }
}
