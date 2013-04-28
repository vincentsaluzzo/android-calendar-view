package com.infteh.calendarview;

import java.util.Calendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * calendar view.
 * @author Sazonov-adm
 *
 */
public class CalendarView extends LinearLayout {
	
	/**
	 * pager.
	 */
	private ViewPager pager;
	
	/**
	 * adapter.
	 */
	private MonthPagerAdapter adapter;

	/**
	 * Year Label
	 */
	private TextView mYear;
	
	/**
	 * РљРѕРЅСЃС‚СЂСѓРєС‚РѕСЂ.
	 * 
	 * @param context РєРѕРЅС‚РµРєСЃС‚
	 */
	public CalendarView(final Context context) {
		this(context, null);
	}

	/**
	 * РљРѕРЅСЃС‚СЂСѓРєС‚РѕСЂ.
	 * 
	 * @param context РєРѕРЅС‚РµРєСЃС‚
	 * @param attrs Р°С‚СЂРёР±СѓС‚С‹
	 */
	public CalendarView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.calendar_view, this, true);
		//FIXME Вообще, искать сразу нельзя. Может быть null. Нужно искать в onFinishInflate.
		pager = ((ViewPager) findViewById(R.id.calendar_view_pager));
		adapter = new MonthPagerAdapter(inflater, pager);
		pager.setAdapter(adapter);
		pager.setCurrentItem(MonthPagerAdapter.INFINITE / 2);
		
		Button nextYear = (Button) findViewById(R.id.year_plus_button);
		Button previousYear = (Button) findViewById(R.id.year_minus_button);
		mYear = (TextView) findViewById(R.id.year_textview);
		
		nextYear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextYear();
			}
		});
		
		previousYear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previousYear();
			}
		});
	}
	
	/**
	 * Р—Р°СЂРµРіРёСЃС‚СЂРёСЂРѕРІР°С‚СЊ РЅРѕРІС‹Р№ РЅР°Р±Р»СЋРґР°С‚РµР»СЊ.
	 * 
	 * @param observer РќР°Р±Р»СЋРґР°С‚РµР»СЊ.
	 */
	public final void registerCalendarDatePickObserver(final CalendarDatePick observer) {
		((MonthPagerAdapter) pager.getAdapter()).setPickObserver(observer);
		this.initYearCaption();
	}
	
	/**
	 * set current day.
	 * @param month month.
	 */
	public final void setCurrentDay(Calendar currentDay) {
		adapter.setCurrentDay(currentDay);
	}
	
	/**
	 * set current month.
	 * @param month month.
	 */
	public final void setMonth(Calendar month) {
		adapter.setMonth(month);
		this.initYearCaption();
	}

	/**
	 * get current.
	 * @return month month.
	 */
	public final Calendar getMonth() {
		return adapter.getMonth();
	}
	
	private void initYearCaption() {
		String year;
		year = android.text.format.DateFormat.format("yyyy", adapter.getMonth()).toString();
		//mYear.setText(year);
	}
	
	public final void nextYear() {
		Calendar newMonth = this.getMonth();
		newMonth.set(Calendar.YEAR, newMonth.get(Calendar.YEAR)+1);
		this.setMonth(newMonth);
	}
	
	public final void previousYear() {
		Calendar newMonth = this.getMonth();
		newMonth.set(Calendar.YEAR, newMonth.get(Calendar.YEAR)-1);
		this.setMonth(newMonth);
	}
}
