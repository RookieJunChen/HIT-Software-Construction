package log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 自定义输出格式 在Recorder中调用.
 * 
 */
public class MyFormatter extends Formatter {
  // a表示显示上下午
  // hh：12小时制 HH：24小时制
  public final SimpleDateFormat sdf = new SimpleDateFormat(Recorder.dateFormat);

  /**
   * 当前调用类及方法在Thread中的位置.
   */

  @Override
  public String format(LogRecord record) {
    @SuppressWarnings("unused")
    String currentTime = sdf.format(new Date());
    String message = record.getMessage();
    return message;
  }

}
