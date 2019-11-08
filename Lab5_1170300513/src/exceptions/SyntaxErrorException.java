package exceptions;

/**
 * 用于描述读文件中的语法错误的异常.
 * 
 * @author junbaba
 *
 */
public class SyntaxErrorException extends Exception {
  private int linenum;
  private String line;

  public SyntaxErrorException() {
    // TODO Auto-generated constructor stub
  }

  public SyntaxErrorException(int linenum, String line) {
    this.linenum = linenum;
    this.line = line;
  }


  public int getlinenum() {
    return linenum;
  }

  public String getline() {
    return line;
  }
}
