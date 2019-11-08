package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutWriter implements OutStrategy {

  private FileWriter fw;

  public OutWriter(File file) throws IOException {
    fw = new FileWriter(file, false);
  }

  @Override
  public void write(String line) throws IOException {
    fw.write(line);
  }

  @Override
  public void close() throws IOException {
    fw.close();

  }

}
