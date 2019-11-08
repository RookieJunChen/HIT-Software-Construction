package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderRead implements ReadStrategy {

  private BufferedReader br;

  public ReaderRead(File file) throws IOException {
    br = new BufferedReader(new FileReader(file));
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
