package io;

import java.io.File;
import java.io.IOException;

public interface ReadStrategy {


  /**
   * 静态工厂方法，返回一个实现ReadStrategy的类.
   */
  public static ReadStrategy empty(File file, int choice) throws IOException {
    if (choice == 1) {
      return new ReaderRead(file);
    } else if (choice == 2) {
      return new StreamRead(file);
    } else if (choice == 3) {
      return new BufferRead(file);
    } else {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * 按行读文件的方法.
   * 
   * @return 文件的某一行.
   */
  public String readLine() throws IOException;



  /**
   * 关闭文件的方法.
   */
  public void close() throws IOException;

}
