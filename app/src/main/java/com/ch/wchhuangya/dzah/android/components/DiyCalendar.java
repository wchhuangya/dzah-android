package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.MonthDisplayHelper;
import android.view.View;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Calendar视图
 * Created by wchya on 2016/6/17.
 */
public class DiyCalendar extends View {
    private Context mContext;
    private MonthDisplayHelper mHelper;
    private Calendar mCalendar;

    /** 控件的宽度 */
    private int wholeWidth;
    /** 控件的高度 */
    private int wholeHeight;
    /** 单元格宽度 */
    private int cellWidth;
    /** 单元格高度 */
    private int cellHeight;
    /** 记录当前是几号 */
    private int today;
    /** 记录选中的是账号 */
    private int selected;
    /** 每个月中周的最大行数 */
    private static final int maxRows = 6;
    /** 每周中日的总个数 */
    private static final int allDays = 7;

    /** 星期标题的画笔 */
    private Paint mWeekTitlePaint;
    /** 星期单元格背景画笔 */
    private Paint mWeekTitleBgPaint;
    /** 一般单元格背景的画笔 */
    private Paint mCommonBgPaint;
    /** 选中单元格文字的画笔 */
    private Paint mSelectedTextPaint;
    /** 选中单元格背景的画笔 */
    private Paint mSelectedBgPaint;
    /** 绘制分隔线的画笔 */
    private Paint mDividerPaint;
    /** 绘制提醒红点的画笔 */
    private Paint mRemindPaint;

    /** 存储当月月视图下的所有天的日历数据 */
    private CalendarCell[][] mAllCells = null;

    /** 标识是否有提醒红点 */
    private boolean hasRemind = false;
    /** 定义星期显示的文字及顺序 */
    private String[] weeks = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    private Map<String, String> tempMap = new HashMap<>();

    public DiyCalendar(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public DiyCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();
    }

    private void init() {
        tempMap.put("2016-06-11", "23");
        tempMap.put("2016-06-03", "23");
        tempMap.put("2016-06-18", "23");
        tempMap.put("2016-06-30", "23");

        mCalendar = Calendar.getInstance();
        selected = today = mCalendar.get(Calendar.DAY_OF_MONTH);

        mWeekTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeekTitlePaint.setColor(mContext.getResources().getColor(R.color.canvas_week_title));

        mWeekTitleBgPaint = new Paint();
        mWeekTitleBgPaint.setStyle(Paint.Style.FILL);
        mWeekTitleBgPaint.setColor(mContext.getResources().getColor(R.color.canvas_week_bg));

        mCommonBgPaint = new Paint();
        mCommonBgPaint.setStyle(Paint.Style.FILL);
        mCommonBgPaint.setColor(mContext.getResources().getColor(R.color.white));

        mSelectedTextPaint = new Paint();
        mSelectedTextPaint.setColor(mContext.getResources().getColor(R.color.white));

        mSelectedBgPaint = new Paint();
        mSelectedBgPaint.setStyle(Paint.Style.FILL);
        mSelectedBgPaint.setColor(mContext.getResources().getColor(R.color.canvas_selected_bg));

        mDividerPaint = new Paint();
        mDividerPaint.setColor(mContext.getResources().getColor(R.color.canvas_divide));

        mRemindPaint = new Paint();
        mRemindPaint.setColor(mContext.getResources().getColor(R.color.red));

        initCalendarData();
    }

    private void initCalendarData() {
        mHelper = new MonthDisplayHelper(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), Calendar.MONDAY);

        // 给当月的日历数组中初始化数据
        mAllCells = new CalendarCell[maxRows][allDays];
        for (int week = 0; week < maxRows; week ++) {
            int[] row = mHelper.getDigitsForRow(week);
            for (int day = 0; day < row.length; day++) {
                if (tempMap.get(mHelper.getYear() + "-" + getDoubleMonth(mHelper.getMonth()) + "-" + getDoubleDay(row[day])) != null)
                    hasRemind = true;
                else
                    hasRemind = false;
                mAllCells[week][day] = new CalendarCell(mHelper.getYear(), mHelper.getMonth(), row[day], hasRemind);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 计算当前需要绘制几行，不是当前的空行不要
        int rowCount = 0;
        for (int week = 0; week < mAllCells.length; week++) {
            if (week != mAllCells.length - 1) // 如果当前不是最后一行
                if (mHelper.isWithinCurrentMonth(week, mAllCells[week].length - 1)) // 如果该行最后一日是在本月内
                    rowCount ++;
            else // 如果当前是最后一行
                if (mHelper.isWithinCurrentMonth(week, 0)) // 如果该行第一日是在本月内
                    rowCount ++;
        }

        // 计算单元格高度，还要把星期栏的高度计算进去
        cellHeight = wholeHeight / (rowCount + 1);

        // 绘制星期的矩形
        Rect weekBgRect = new Rect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + wholeWidth, getPaddingTop() + cellHeight);
        canvas.drawRect(weekBgRect, mWeekTitleBgPaint);
        // 绘制星期的文字
        mWeekTitlePaint.setTextSize(cellHeight * 0.35f);
        mSelectedTextPaint.setTextSize(cellHeight * 0.35f);
        Paint.FontMetrics fm = mWeekTitlePaint.getFontMetrics();
        int y = (int) (cellHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2); // 绘制文字时下边的偏移量，实际效果：居中
        for (int week = 0; week < weeks.length; week++) {
            int x = (int) ((cellWidth - mWeekTitlePaint.measureText(weeks[week])) / 2); // 绘制第一个文字时左边的偏移量，（单元格宽度 - 字的宽度）/ 2
            canvas.drawText(weeks[week], x + cellWidth * week, y, mWeekTitlePaint);
        }

        // 绘制具体的日期
        for (int week = 0; week < mAllCells.length; week ++) { // 循环周
            int innerY = cellHeight + cellHeight * week + y;
            for (int day = 0; day < mAllCells[week].length; day++) { // 循环日期
                if (mHelper.isWithinCurrentMonth(week, day)) {
                    int x = (int) ((cellWidth - mWeekTitlePaint.measureText(mAllCells[week][day].day + "")) / 2); // 绘制第一个文字时左边的偏移量，（单元格宽度 - 字的宽度）/ 2
                    if (selected == mAllCells[week][day].day) { // 如果
                        // 绘制选中背景矩形
                        Rect rect = new Rect(cellWidth * day, getPaddingTop() + cellHeight + cellHeight * week + week * 1, cellWidth * day + cellWidth,
                                getPaddingTop() + cellHeight + cellHeight * week + cellHeight);
                        canvas.drawRect(rect, mSelectedBgPaint);
                        canvas.drawText(mAllCells[week][day].day + "", x + cellWidth * day, innerY, mSelectedTextPaint);
                    } else
                        canvas.drawText(mAllCells[week][day].day + "", x + cellWidth * day, innerY, mWeekTitlePaint);
                    if (mAllCells[week][day].hasRemind) {
                        canvas.drawCircle(cellWidth * day + cellWidth - 15, getPaddingTop() + cellHeight + cellHeight * week + week * 1 + 15, 5, mRemindPaint);
                    }
                }
            }
            if (week != mAllCells.length - 1) {
                canvas.drawLine(0, cellHeight + (week + 1) * cellHeight + (week + 1) * 1, wholeWidth, cellHeight + (week + 1) * cellHeight + (week + 1) * 1, mDividerPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY)
            LogHelper.d(DiyCalendar.class, "width EXACTLY: " + widthSize);
        else if (widthMode == MeasureSpec.AT_MOST)
            LogHelper.d(DiyCalendar.class, "width AT_MOST: " + widthSize);
        else if (widthMode == MeasureSpec.UNSPECIFIED)
            LogHelper.d(DiyCalendar.class, "width UNSPECIFIED: " + widthSize);
        if (heightMode == MeasureSpec.EXACTLY)
            LogHelper.d(DiyCalendar.class, "height EXACTLY: " + heightSize);
        else if (heightMode == MeasureSpec.AT_MOST)
            LogHelper.d(DiyCalendar.class, "height AT_MOST: " + heightSize);
        else if (heightMode == MeasureSpec.UNSPECIFIED)
            LogHelper.d(DiyCalendar.class, "Uheight NSPECIFIED: " + heightSize);

        wholeWidth = widthSize;
        wholeHeight = heightSize;
        cellWidth = wholeWidth / 7;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    class CalendarCell {
        int year;
        int month;
        int day;
        boolean hasRemind;

        public CalendarCell(int year, int month, int day, boolean hasRemind) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hasRemind = hasRemind;
        }
    }

    private String getDoubleMonth(int month) {
        switch (month) {
            case 0:
                return "01";
            case 1:
                return "02";
            case 2:
                return "03";
            case 3:
                return "04";
            case 4:
                return "05";
            case 5:
                return "06";
            case 6:
                return "07";
            case 7:
                return "08";
            case 8:
                return "09";
            case 9:
                return "10";
            case 10:
                return "11";
            case 11:
                return "12";
        }
        return "";
    }

    private String getDoubleDay(int day) {
        switch (day) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            case 10:
                return "10";
            default:
                return day + "";
        }
    }
}
