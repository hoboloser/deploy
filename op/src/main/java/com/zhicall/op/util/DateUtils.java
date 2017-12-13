package com.zhicall.op.util;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	/** 源日期格式 */
	private static final String SOURCE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm";
	
	/** 目标日期格式 */
	private static final String TARGET_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private static final String TARGET_DATE_PATTERN_01 = "yyyyMMdd";
	
	private static final String TARGET_DATE_PATTERN_02 = "yyyy-MM-dd";
	
	/**
	 * 字符日期转Date
	 * yyyy-MM-dd'T'HH:mm:ss
	 *   	 |  源日期格式
	 * @param scheduleDate
	 * @return
	 */
	public static Date convertDate(String scheduleDate){
		return DateUtil.parseDate(scheduleDate, SOURCE_DATE_PATTERN);
	}
	
	/**
	 * 字符日期格式转换
	 *  yyyy-MM-dd'T'HH:mm:ss
	 *   	 |  源日期格式
	 *  yyyy-MM-dd HH:mm:ss
	 *   	 |  目标日期格式
	 * @param scheduleDate
	 * @return
	 */
	public static String convertDate2Str(String scheduleDate){
		return convertDate2Str(scheduleDate, SOURCE_DATE_PATTERN, TARGET_DATE_PATTERN);
	}
	
	/**
	 * 字符日期格式转换
	 *  yyyy-MM-dd'T'HH:mm:ss
	 *   	 |  源日期格式
	 *  yyyyMMdd
	 *   	 |  目标日期格式
	 * @param scheduleDate
	 * @return
	 */
	public static String convertDate2Str01(String scheduleDate){
		return convertDate2Str(scheduleDate, SOURCE_DATE_PATTERN, TARGET_DATE_PATTERN_01);
	}
	/**
	 * 字符日期格式转换
	 *  yyyy-MM-dd'T'HH:mm:ss
	 *   	 |  源日期格式
	 *  yyyy-MM-dd
	 *   	 |  目标日期格式
	 * @param scheduleDate
	 * @return
	 */
	public static String convertDate2Str02(String scheduleDate){
		return convertDate2Str(scheduleDate, SOURCE_DATE_PATTERN, TARGET_DATE_PATTERN_02);
	}
	
	/**
	 * 字符日期格式转换
	 *  yyyy-MM-dd'T'HH:mm:ss
	 *   	 |  源日期格式
	 * @param scheduleDate
	 * @param targetPattern  |  目标日期格式
	 * @return
	 */
	public static String convertDate2Str(String scheduleDate, String targetPattern){
		return convertDate2Str(scheduleDate, SOURCE_DATE_PATTERN, targetPattern);
	}
	
	/**
	 * 字符日期格式转换
	 * @param scheduleDate
	 * @param sourcePattern  |  源日期格式
	 * @param targetPattern  |  目标日期格式
	 * @return
	 */
	public static String convertDate2Str(String scheduleDate, String sourcePattern, String targetPattern){
		Date date = DateUtil.parseDate(scheduleDate, sourcePattern);
		return DateUtil.formatDate(targetPattern, date);
	}
	public static Date getYesterday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}
	
	/**
	 * 
	 * <p>
	 * Sunday = 1
	 * </p>
	 * <p>
	 * Monday = 2
	 * </p>
	 * <p>
	 * Tuesday = 3
	 * </p>
	 * <p>
	 * Wednesday = 4
	 * </p>
	 * <p>
	 * Thursday = 5
	 * </p>
	 * <p>
	 * Friday = 6
	 * </p>
	 * <p>
	 * Saturday = 7
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static int dayForWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}
	
	public static boolean isSunday(Date date) {
		return (1 == dayForWeek(date));
	}
	
	public static boolean isSaturday(Date date) {
		return (7 == dayForWeek(date));
	}
}
