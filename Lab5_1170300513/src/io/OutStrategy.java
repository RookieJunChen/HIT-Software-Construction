package io;

import java.io.File;
import java.io.IOException;

public interface OutStrategy {


  /**
   * 静态工厂方法，返回一个实现OutStrategy接口的类.
   */
  public static OutStrategy empty(File file, int choice) throws IOException {
    if (choice == 1) {
      return new OutWriter(file);
    } else if (choice == 2) {
      return new OutStream(file);
    } else if (choice == 3) {
      return new OutBuffer(file);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * 写文件的方法.
   * 
   * @param line 要写入的字符串
   */
  public void write(String line) throws IOException;


  /**
   * 关闭文件的方法.
   */
  public void close() throws IOException;

}
