package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutBuffer implements OutStrategy {


  private BufferedWriter bw;

  public OutBuffer(File file) throws IOException {
    bw = new BufferedWriter(new FileWriter(file, false));
  }

  @Override
  public void write(String line) throws IOException {
    bw.write(line);
  }

  @Override
  public void close() throws IOException {
    bw.close();

  }

}
