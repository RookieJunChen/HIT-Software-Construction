package io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class StreamRead implements ReadStrategy {


  DataInputStream dis;

  public StreamRead(File file) throws IOException {
    dis = new DataInputStream(new FileInputStream(file));
  }


  @Override
  public String readLine() throws IOException {
    String line = dis.readLine();
    return line;
  }

  @Override
  public void close() throws IOException {
    dis.close();

  }

}
