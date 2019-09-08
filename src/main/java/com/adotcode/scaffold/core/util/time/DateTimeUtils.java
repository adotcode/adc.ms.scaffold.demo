package com.adotcode.scaffold.core.util.time;

import com.adotcode.scaffold.core.exception.application.ParseFailedException;
import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期时间工具类
 *
 * @author risfeng
 * @date 2019/08/17
 */
public class DateTimeUtils {

  /**
   * 日期时间格式样式
   */
  public interface Pattern {

    /**
     * yyyy-MM-dd
     */
    String DATE = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    String DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm
     */
    String DATETIME_MM = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    String DATETIME_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * HH:mm
     */
    String TIME = "HH:mm";

    /**
     * HH:mm:ss
     */
    String TIME_SS = "HH:mm:ss";

    /**
     * yyyy/MM/dd
     */
    String SYS_DATE = "yyyy/MM/dd";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    String SYS_DATETIME = "yyyy/MM/dd HH:mm:ss";

    /**
     * yyyy/MM/dd HH:mm
     */
    String SYS_DATETIME_MM = "yyyy/MM/dd HH:mm";

    /**
     * yyyy/MM/dd HH:mm:ss.SSS
     */
    String SYS_DATETIME_SSS = "yyyy/MM/dd HH:mm:ss.SSS";

    /**
     * yyyyMMdd
     */
    String NONE_DATE = "yyyyMMdd";

    /**
     * yyyyMMddHHmmss
     */
    String NONE_JOINER_DATETIME = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmm
     */
    String NONE_JOINER_DATETIME_MM = "yyyyMMddHHmm";
    /**
     * yyyyMMddHHmmssSSS
     */
    String NONE_JOINER_DATETIME_SSS = "yyyyMMddHHmmssSSS";
  }

  /**
   * 默认时间格式样式
   */
  public static final String DEFAULT_PATTERN = Pattern.DATETIME;

  /**
   * 可转换时间格式样式集合
   */
  public static final String[] PARSE_PATTERNS = new String[]{
      Pattern.DATE,
      Pattern.DATETIME,
      Pattern.DATETIME_MM,
      Pattern.DATETIME_SSS,
      Pattern.SYS_DATE,
      Pattern.SYS_DATETIME,
      Pattern.SYS_DATETIME_MM,
      Pattern.SYS_DATETIME_SSS
  };

  /**
   * 锁对象
   */
  private static final Object LOCK_OBJ = new Object();

  /**
   * 存放不同的日期模板格式的sdf的Map
   */
  private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

  /**
   * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
   *
   * @param pattern 日期格式化样式
   * @return SimpleDateFormat
   */
  private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
    ThreadLocal<SimpleDateFormat> threadLocal = sdfMap.get(pattern);
    // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
    if (threadLocal == null) {
      synchronized (LOCK_OBJ) {
        threadLocal = sdfMap.get(pattern);
        if (threadLocal == null) {
          // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
          threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
          sdfMap.put(pattern, threadLocal);
        }
      }
    }
    return threadLocal.get();
  }

  /**
   * 格式化日期时间
   *
   * @param date 日期时间
   * @return yyyy-MM-dd HH:mm:ss
   */
  public static String format(Date date) {
    return format(date, DEFAULT_PATTERN);
  }

  /**
   * 格式化日期
   *
   * @param date 日期(时间)
   * @param pattern 匹配模式 参考：{@link DateTimeUtils.Pattern}
   * @return 格式化后的字符串日期/时间
   */
  public static String format(Date date, String pattern) {
    if (date == null) {
      return null;
    }
    pattern = StringUtils.isNotBlank(pattern) ? pattern : DEFAULT_PATTERN;
    return getSimpleDateFormat(pattern).format(date);
  }

  /**
   * 字符串转日期
   *
   * @param date 日期字符串
   * @return 解析后的日期 默认格式：yyyy-MM-dd HH:mm:ss
   */
  public static Date parseDate(String date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    try {
      return DateUtils.parseDate(date, PARSE_PATTERNS);
    } catch (ParseException e) {
      throw new ParseFailedException(
          I18nMessageUtils.translate("exception.date.parse.failed", e.getMessage()));
    }
  }

  /**
   * 字符串转日期
   *
   * @param date 日期字符串
   * @param pattern 格式 参考：{@link DateTimeUtils.Pattern}
   * @return 解析后的日期，默认格式：yyyy-MM-dd HH:mm:ss
   */
  public static Date parseDate(String date, String pattern) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    String[] parsePatterns =
        StringUtils.isNotBlank(pattern) ? new String[]{pattern} : PARSE_PATTERNS;
    try {
      return DateUtils.parseDate(date, parsePatterns);
    } catch (ParseException e) {
      throw new ParseFailedException(
          I18nMessageUtils.translate("exception.date.parse.failed", e.getMessage()));
    }
  }
}
