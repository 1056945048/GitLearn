package com.yunqu.yq.engine.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Bianhl
 * @Date: 2020/4/15 20:14
 */
public class DateUtils {
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String TIME_FORMAT_YMD = "yyyy-MM-dd";
	public static SimpleDateFormat DEFAULT_FORMAT;
	public static byte[] lock;

	static {
		DEFAULT_FORMAT = new SimpleDateFormat(TIME_FORMAT);
		lock = new byte[0];
	}

	public static Date getDate(String timeFormat, String time) {
		SimpleDateFormat format;
		if (StringUtils.isBlank(timeFormat)) {
			format = DEFAULT_FORMAT;
		} else {
			format = new SimpleDateFormat(timeFormat);
		}

		try {
			synchronized(lock) {
				return format.parse(time);
			}
		} catch (ParseException var5) {
			return null;
		}
	}

	public static String getCurrentDateStr(String format) {
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(new Date());
	}

	public static String now() {
		return getCurrentDateStr(TIME_FORMAT);
	}

	public static String getDateId() {
		return getCurrentDateStr("yyyyMMdd");
	}
}
