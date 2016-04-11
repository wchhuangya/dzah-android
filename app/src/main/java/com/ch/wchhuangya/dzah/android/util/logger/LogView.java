package com.ch.wchhuangya.dzah.android.util.logger;

import android.app.Activity;
import android.content.Context;
import android.util.*;
import android.widget.TextView;

/**
 * 通过继承LogNode接口，用于输出接收到的日志数据的简单的TextView控件
 * Created by wchya on 2015-10-13.
 */
public class LogView extends TextView implements LogNode {

    public LogView(Context context) {
        super(context);
    }

    public LogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 格式化日志数据，并把它打印到LogView中
     * @param priority 被打印数据的日志优先级，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        String priorityStr = null;

        // 对于这个视图来说，我们想把优先级打印为一个可读的文本
        switch (priority) {
            case android.util.Log.VERBOSE:
                priorityStr = "VERBOSE";
                break;
            case android.util.Log.DEBUG:
                priorityStr = "DEBUG";
                break;
            case android.util.Log.INFO:
                priorityStr = "INFO";
                break;
            case android.util.Log.WARN:
                priorityStr = "WARN";
                break;
            case android.util.Log.ERROR:
                priorityStr = "ERROR";
                break;
            case android.util.Log.ASSERT:
                priorityStr = "ASSERT";
                break;
            default:
                break;
        }

        // Log类有一个工具可以很容易的把栈的跟踪调试信息转换为可用的字符串
        String exceptionStr = null;
        if(null != tr)
            exceptionStr = android.util.Log.getStackTraceString(tr);

        // 把优先级、标签、信息、异常这些必要的信息连接着一行可用的文本
        final StringBuilder outputBuilder = new StringBuilder();

        String delimiter = "\t";
        appendIfNotNull(outputBuilder, priorityStr, delimiter);
        appendIfNotNull(outputBuilder, tag, delimiter);
        appendIfNotNull(outputBuilder, msg, delimiter);
        appendIfNotNull(outputBuilder, exceptionStr, delimiter);

        // 为了防止最初从AsnycTask中调用，或从其它的非主线程的调用，要确保更新发生在UI线程上
        ((Activity)getContext()).runOnUiThread((new Thread(new Runnable() {
            @Override
            public void run() {
                // 显示使用LogView生成的文本
                appendToLog(outputBuilder.toString());
            }
        })));

        if (mNext != null) {
            mNext.println(priority, tag, msg, tr);
        }
    }
    /**
     * 接受一个字符串并给它添加分隔符，如果要添加少量的不为空的字符。
     * 因为日志接收的参数很多可能为null，因此，该方法有助于删除一些烦人的、反复的三行相同的内容
     * @param source 包含要被添加内容的StringBuilder对象
     * @param addStr 要添加的字符串
     * @param delimiter 用于分隔源内容和添加内容的字符串，例如：一个Tab符或一个逗号
     * @return 完整的、连接好的字符串，是StringBuilder对象
     */
    private StringBuilder appendIfNotNull(StringBuilder source, String addStr, String delimiter) {
        if (addStr != null) {
            if (addStr.length() == 0) {
                delimiter = "";
            }

            return source.append(addStr).append(delimiter);
        }
        return source;
    }

    public LogNode getNext() {
        return mNext;
    }

    public void setNext(LogNode node) {
        mNext = node;
    }

    // 链中的下一个LogNode
    LogNode mNext;

    /** 在LogView中，把字符串作为日志信息的一个新行输出 */
    public void appendToLog(String s) {
        append("\n" + s);
    }
}
