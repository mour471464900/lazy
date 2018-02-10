package com.haohao.lazy.english.lazyenglish.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author GaiQS E-mail:gaiqs@sina.com
 * @Date 2015年5月11日
 * @Time 下午12:07:52
 */
public class DateUtil {
    public static final int MINUTES = 60 * 1000;// 分毫秒值
    public static final int HOUR = 60 * MINUTES;// 小时毫秒值
    public static final int DAY = 24 * HOUR;// 天毫秒值

    /**
     * 根据日期获得星期
     * <p>
     * Date date = new Date(); date.setDate(1);//设置日期 传入1-31
     *
     * @return
     */
    public static String getWeekOfDate(int day) {
        Date date = new Date();
        date.setDate(day);

        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 获取指定月份的日历信息
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static int[] getMonthCalendar(int year, int month) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month, 1);
        int firstDay = cl.getMinimum(Calendar.DAY_OF_MONTH);
        int lastDay = cl.getMaximum(Calendar.DAY_OF_MONTH);

        int[] day = new int[lastDay];

        for (int i = 0; i < lastDay; i++) {
            day[i] = i + firstDay;
        }
        return day;
    }

    /**
     * 测试时间类
     */
    public static void DateTest() {
        Calendar c = Calendar.getInstance();
        Date curDate = new Date();
        c.setTime(curDate);
        int year = c.get(Calendar.YEAR); // 获取年
        int month = c.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
        int day = c.get(Calendar.DAY_OF_MONTH); // 获取当前天数
        int first = c.getActualMinimum(c.DAY_OF_MONTH); // 获取本月最小天数
        int last = c.getActualMaximum(c.DAY_OF_MONTH); // 获取本月最大天数
        int time = c.get(Calendar.HOUR_OF_DAY); // 获取当前小时
        int min = c.get(Calendar.MINUTE); // 获取当前分钟
        int xx = c.get(Calendar.SECOND); // 获取当前秒
        // // 一周第一天是否为星期天
        // boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
        // // 获取周几
        // int weekDay = c.get(Calendar.DAY_OF_WEEK);
        // // 若一周第一天为星期天，则-1
        // if (isFirstSunday) {
        // weekDay = weekDay - 1;
        // if (weekDay == 0) {
        // weekDay = 7;
        // }
        // }
        // // 打印周几
        // Log.e("weekDay", weekDay + "");
    }

    /**
     * 格式化进度信息
     *
     * @param duration 时间数
     * @return
     */
    public static String getProgressTime(long duration) {
        return getFromat("mm:ss", duration);
    }


    /**
     * 格式化时间值
     *
     * @param format
     *            格化
     * @param duration
     *            时间值
     * @return
     */
//	public static String getFromat(String format, long duration) {
//
////		public static String getTimeOfMinute(long duration) {
////			Date date = null;
////			String result = null;
////			try {
////				date = new Date(duration);
////				result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
////			return result;
//		Date	date = new Date(duration);
//		SimpleDateFormat format2 = new SimpleDateFormat(format);
//
//		return format2.format(duration);
//	}

    /**
     * 格式化日期例如20141122--》2014-11-22
     *
     * @param duration
     * @return
     */
    public static String getLongFromat(long duration) {
        Date date = null;
        String result = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(duration));
            result = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getLongFromat(String duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(Long.valueOf(duration) * 1000);
            result = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getFromat(String format, long duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(duration * 1000);
            result = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getTimeOfMinute(String duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(Long.valueOf(duration) * 1000);
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getTimeOfMinute(long duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(duration);
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getTimeOfDay(long duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(duration);
            result = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //	public static String getTimeOfMinute(long duration) {
//		Date date = null;
//		String result = null;
//		try {
//			date = new Date(Long.valueOf(duration) * 1000);
//			result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
    public static String getDateOfMinute(String duration) {
        Date date = null;
        String result = null;
        try {
            date = new Date(Long.valueOf(duration) * 1000);
            result = new SimpleDateFormat("MM-dd HH:mm").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 给定时间是否为今天时间值
     *
     * @param startThrntableTime
     * @return
     */
    public static boolean isToday(long startThrntableTime) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day, 0, 0, 0);
        return startThrntableTime > calendar.getTimeInMillis();
    }


}
