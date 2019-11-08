package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferRead implements ReadStrategy {

  private BufferedReader br;

  public BufferRead(File file) throws IOException {
    br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
  }

  @Override
  public String readLine() throws IOException {
    String line = br.readLine();
    return line;
  }

  @Override
  public void close() throws IOException {
    br.close();

  }

}
