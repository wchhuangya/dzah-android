package com.ch.wchhuangya.dzah.android.util.logger;

/**
 * 日志系统的基本接口，用于输入一个或多个目标。
 * Note that in addition to classes that will output these logs in some format,
 * one can also implement this interface over a filter and insert that in the chain,
 * such that no targets further down see certain data, or see manipulated forms of the data.
 * You could, for instance, write a "ToHtmlLoggerNode" that just converted all the log data
 * it received to HTML and sent it along to the next node in the chain, without printing it
 * anywhere.
 * Created by wchya on 2015-10-12.
 */
public interface LogNode {

    /**
     * 在列表中指示第一个LogNode，用于打印提供的日志数据     Instructs first LogNode in the list to print the log data provided.
     * @param priority 被打印的数据级别，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public void println(int priority, String tag, String msg, Throwable tr);
}
