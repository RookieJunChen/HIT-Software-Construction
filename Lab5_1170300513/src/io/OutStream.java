package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutStream implements OutStrategy {

  private FileOutputStream fos;


  public OutStream(File file) throws IOException {
    fos = new FileOutputStream(file, false);
  }


  @Override
  public void write(String line) throws IOException {
    byte[] b = line.getBytes();
    fos.write(b);
  }

  @Override
  public void close() throws IOException {
    fos.close();

  }

}
