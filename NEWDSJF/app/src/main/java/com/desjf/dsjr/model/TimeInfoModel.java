package com.desjf.dsjr.model;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:时间信息 —— 天、时、分、秒
 * @date 2017/2/13 12:00
 */
public class TimeInfoModel {
	private static final String TAG = "TimeInfoModel";
	public static final int MILLIS_PER_SEC = 1000;
	public static final int SEC_PER_MIN = 60;
	public static final int MIN_PER_HOUR = 60;
	public static final int DAY_PER_DAY = 24;

	private long mday, mHour, mMinute, mSecond;

	private TimeInfoModel() {
	}

	public static TimeInfoModel make(long curTime, boolean isHour) {
		long totalMillis = curTime;
		if (totalMillis <= 0)
			return new TimeInfoModel(0, 0, 0, 0);

		long totalSecond = totalMillis / MILLIS_PER_SEC;
		long totalMinute = totalSecond / SEC_PER_MIN;
		long totalHour = totalMinute / MIN_PER_HOUR;
		long totalDay = totalHour / DAY_PER_DAY;

		//Log.e(TAG, "day:" + totalDay + "  totalHour:" + totalHour + "  curTime:" + curTime);
		if (!isHour) {
			totalHour = totalHour % DAY_PER_DAY;
		}
		return new TimeInfoModel(
				totalDay,
				totalHour,
				totalMinute % MIN_PER_HOUR,
				totalSecond % SEC_PER_MIN
		);
	}

	public TimeInfoModel(long day, long hour, long minute, long second) {
		mHour = hour;
		mMinute = minute;
		mSecond = second;
		mday = day;
	}

	public long getHour() {
		return mHour;
	}

	public long getMinute() {
		return mMinute;
	}

	public long getSecond() {
		return mSecond;
	}

	public long getMday() {
		return mday;
	}

	public String getFormatedDay(boolean isHour) {
		return format(mday, isHour);
	}

	public String getFormatedHour(boolean isHour) {
		return format(mHour, isHour);
	}

	public String getFormatedMinute(boolean isHour) {
		return format(mMinute, isHour);
	}

	public String getFormatedSecond(boolean isHour) {
		return format(mSecond, isHour);
	}

	/**
	 * 格式化, 不足两位的补全, 如 "3" -> "03"
	 *
	 * @param time
	 * @return
	 */
	public static String format(long time, boolean isHour) {
		String result = time + "";
		if (isHour && (result.length() == 1)) {
			result = "0" + result;

		}
		return result;
	}
}
