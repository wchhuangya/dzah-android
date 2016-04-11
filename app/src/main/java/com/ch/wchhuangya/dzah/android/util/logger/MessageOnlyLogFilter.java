package com.ch.wchhuangya.dzah.android.util.logger;

/**
 * 简单的{@link LogNode}过滤器，移掉了信息之外的所有内容
 * 有用的情况：当你不想在屏幕上显示元数据，只想显示易读的信息
 * Created by wchya on 2015-10-13.
 */
public class MessageOnlyLogFilter implements LogNode {
    LogNode mNext;

    /**
     * 把下一个LogNode节点作为参数，只是简单的对其进行链接
     * @param next 流水线的下一个LogNode
     */
    public MessageOnlyLogFilter(LogNode next) {
        mNext = next;
    }

    public MessageOnlyLogFilter() {
    }

    /**
     * 打印简单日志
     * @param priority 被打印的数据级别，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        if (mNext != null) {
            getNext().println(Log.NONE, null, msg, null);
        }
    }

    /**
     * Returns the next LogNode in the chain.
     */
    public LogNode getNext() {
        return mNext;
    }

    /**
     * Sets the LogNode data will be sent to..
     */
    public void setNext(LogNode node) {
        mNext = node;
    }
}
