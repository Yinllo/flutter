package com.desjf.dsjr.adapter.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.model.CalendarMonDetailsModel;
import com.desjf.dsjr.model.RecoverCalendarModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 日历gridview中的每一个item显示的textview
 *
 * @author Vincent Lee
 */
public class CalendarAdapter extends BaseAdapter {
	private boolean isLeapyear = false; // 是否为闰年
	private int daysOfMonth = 0; // 某月的天数
	private int dayOfWeek = 0; // 具体某一天是星期几
	private int lastDaysOfMonth = 0; // 上一个月的总天数
	private Context context;
	private String[] dayNumber; // 一个gridview中的日期存入此数组中
	// private static String week[] = {"周日","周一","周二","周三","周四","周五","周六"};
	private SpecialCalendar sc = null;
	private LunarCalendar lc = null;
	private Resources res = null;
	private Drawable drawable = null;

	private String currentYear = "";
	private String currentMonth = "";
	private String currentDay = "";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
	private int currentFlag = -1; // 用于标记当天
	private int[] schDateTagFlag = null; // 存储当月所有的日程日期

	private String showYear = ""; // 用于在头部显示的年份
	private String showMonth = ""; // 用于在头部显示的月份
	private String animalsYear = "";
	private String leapMonth = ""; // 闰哪一个月
	private String cyclical = ""; // 天干地支
	// 系统当前时间
	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";

	private List<CalendarMonDetailsModel> calendarMonDetailsModels;

	public CalendarAdapter() {
		Date date = new Date();
		sysDate = sdf.format(date); // 当期日期
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];

	}

	public CalendarAdapter(Context context, Resources rs, int jumpMonth, int jumpYear, int year_c, int month_c, int day_c) {
		this();
		this.context = context;
		sc = new SpecialCalendar();
		lc = new LunarCalendar();
		this.res = rs;

		int stepYear = year_c + jumpYear;
		int stepMonth = month_c + jumpMonth;
		if (stepMonth > 0) {
			// 往下一个月滑动
			if (stepMonth % 12 == 0) {
				stepYear = year_c + stepMonth / 12 - 1;
				stepMonth = 12;
			} else {
				stepYear = year_c + stepMonth / 12;
				stepMonth = stepMonth % 12;
			}
		} else {
			// 往上一个月滑动
			stepYear = year_c - 1 + stepMonth / 12;
			stepMonth = stepMonth % 12 + 12;
		}

		currentYear = String.valueOf(stepYear); // 得到当前的年份
		currentMonth = String.valueOf(stepMonth); // 得到本月
		// （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
		currentDay = String.valueOf(day_c); // 得到当前日期是哪天

		getCalendar(Integer.parseInt(currentYear), Integer.parseInt(currentMonth));

	}

	public void setRecoverCalendarModels(List<CalendarMonDetailsModel> calendarMonDetailsModels) {
		this.calendarMonDetailsModels = calendarMonDetailsModels;
		notifyDataSetChanged();
	}

   /*public CalendarAdapter(Context context, Resources rs, int year, int month, int day) {
		this();
		this.context = context;
		sc = new SpecialCalendar();
		lc = new LunarCalendar();
		this.res = rs;
		currentYear = String.valueOf(year);// 得到跳转到的年份
		currentMonth = String.valueOf(month); // 得到跳转到的月份
		currentDay = String.valueOf(day); // 得到跳转到的天
		getCalendar(Integer.parseInt(currentYear), Integer.parseInt(currentMonth));
	}*/

	@Override
	public int getCount() {
		return dayNumber.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final String d = dayNumber[position].split("\\.")[0];

		viewHolder.textView.setText(d);

		if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
			// 当前月信息显示
			viewHolder.textView.setTextColor(context.getResources().getColor(R.color.shenhei));// 当月字体设黑
			convertView.setVisibility(View.VISIBLE);
		} else {
			convertView.setVisibility(View.GONE);
		}
		DecimalFormat df   = new DecimalFormat("######0.00");
		double total=0.00;
		double real =0.00;
		viewHolder.tv_amount.setText("");
		//设置回款金额
		if (calendarMonDetailsModels != null) {
			for (int i = 0; i < calendarMonDetailsModels.size(); i++) {

				for (int j=0;j<calendarMonDetailsModels.size();j++){

					if (String.valueOf(Integer.parseInt(d)).equals(calendarMonDetailsModels.get(i).getCONCRETE_DATE())) {
						double money = Double.parseDouble(calendarMonDetailsModels.get(i).getRECOVER_AMOUNT_TOTAL());
						total = total+money;
						viewHolder.tv_amount.setText(String.valueOf(df.format(total/calendarMonDetailsModels.size())));

						//加判断，当前是否已全部还款
						if ("1".equals(calendarMonDetailsModels.get(i).getRECOVER_FLG())) {
							viewHolder.tv_amount.setTextColor(context.getResources().getColor(R.color.font_gray));
						} else {
//						viewHolder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.calender_white));
							viewHolder.tv_amount.setTextColor(context.getResources().getColor(R.color.main));
						}
					}
				}
			}
		}else{
            viewHolder.tv_amount.setText("");
        }

		if (currentFlag == position) {
			// 设置当天的背景
			drawable = context.getResources().getDrawable(R.mipmap.calender_yello);
			viewHolder.textView.setBackgroundDrawable(drawable);
			viewHolder.textView.setTextColor(Color.WHITE);
		}

		return convertView;
	}

	// 得到某年的某月的天数且这月的第一天是星期几
	public void getCalendar(int year, int month) {
		isLeapyear = sc.isLeapYear(year); // 是否为闰年
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
		dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
		lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month - 1); // 上一个月的总天数
		//设置gridview返回的数量, 根据日期动态改变数量
		if ((daysOfMonth + dayOfWeek) % 7 == 0) {
			dayNumber = new String[((daysOfMonth + dayOfWeek) / 7) * 7];
		} else {
			dayNumber = new String[((daysOfMonth + dayOfWeek) / 7 + 1) * 7];
		}

		Log.d("DAY", isLeapyear + " ======  " + daysOfMonth + "  ============  " + dayOfWeek + "  =========   " + lastDaysOfMonth);
		getweek(year, month);
	}

	// 将一个月中的每一天的值添加入数组dayNuber中
	private void getweek(int year, int month) {
		int j = 1;
		String lunarDay = "";

		// 得到当前月的所有日程日期(这些日期需要标记)
		for (int i = 0; i < dayNumber.length; i++) {
			if (i < dayOfWeek) { // 前一个月
				int temp = lastDaysOfMonth - dayOfWeek + 1;
				lunarDay = lc.getLunarDate(year, month - 1, temp + i, false);
				dayNumber[i] = (temp + i) + "." + lunarDay;

			} else if (i < daysOfMonth + dayOfWeek) { // 本月
				String day = String.valueOf(i - dayOfWeek + 1); // 得到的日期
				lunarDay = lc.getLunarDate(year, month, i - dayOfWeek + 1, false);
				dayNumber[i] = i - dayOfWeek + 1 + "." + lunarDay;
				// 对于当前月才去标记当前日期
				if (sys_year.equals(String.valueOf(year)) && sys_month.equals(String.valueOf(month)) && sys_day.equals(day)) {
					// 标记当前日期
					currentFlag = i;
				}
				setShowYear(String.valueOf(year));
				setShowMonth(String.valueOf(month));
				setAnimalsYear(lc.animalsYear(year));
				setLeapMonth(lc.leapMonth == 0 ? "" : String.valueOf(lc.leapMonth));
				setCyclical(lc.cyclical(year));
			} else { // 下一个月
				lunarDay = lc.getLunarDate(year, month + 1, j, false);
				dayNumber[i] = j + "." + lunarDay;
				j++;
			}
		}

		String abc = "";
		for (int i = 0; i < dayNumber.length; i++) {
			abc = abc + dayNumber[i] + ":";
		}
		Log.d("DAYNUMBER", abc);

	}


	/**
	 * 点击每一个item时返回item中的日期
	 *
	 * @param position
	 * @return
	 */
	public String getDateByClickItem(int position) {
		return dayNumber[position];
	}

	/**
	 * 在点击gridView时，得到这个月中第一天的位置
	 *
	 * @return
	 */
	public int getStartPositon() {
		return dayOfWeek + 7;
	}

	/**
	 * 在点击gridView时，得到这个月中最后一天的位置
	 *
	 * @return
	 */
	public int getEndPosition() {
		return (dayOfWeek + daysOfMonth + 7) - 1;
	}

	public String getShowYear() {
		return showYear;
	}

	public void setShowYear(String showYear) {
		this.showYear = showYear;
	}

	public String getShowMonth() {
		return showMonth;
	}

	public void setShowMonth(String showMonth) {
		this.showMonth = showMonth;
	}

	public String getAnimalsYear() {
		return animalsYear;
	}

	public void setAnimalsYear(String animalsYear) {
		this.animalsYear = animalsYear;
	}

	public String getLeapMonth() {
		return leapMonth;
	}

	public void setLeapMonth(String leapMonth) {
		this.leapMonth = leapMonth;
	}

	public String getCyclical() {
		return cyclical;
	}

	public void setCyclical(String cyclical) {
		this.cyclical = cyclical;
	}

	class ViewHolder {
		TextView textView;
		TextView tv_amount;

		public ViewHolder(View view) {
			textView = (TextView) view.findViewById(R.id.tvtext);
			tv_amount = (TextView) view.findViewById(R.id.tv_amount);
		}
	}
}
