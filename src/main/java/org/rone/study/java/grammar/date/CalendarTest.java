package org.rone.study.java.grammar.date;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		//获取、设置日期数据
		//Calendar.YEAR(年份), Calendar.MONTH(月份-1)
		//Calendar.DAY_OF_MONTH(几号), Calendar.DAY_OF_WEEK(周几)
		//Calendar.HOUR(十二小时制),Calendar.HOUR_OF_DAY(二十四小时制)
		//Calendar.MINUTE(分), Calendar.SECOND(秒), Calendar.MILLISECOND(毫秒)
		calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, 1994);
		//获取该月最大的天数
		calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		//数据的增减
		calendar.add(Calendar.MONTH, 1);//加一
		calendar.add(Calendar.DAY_OF_MONTH, -1);//减一

	}

}
