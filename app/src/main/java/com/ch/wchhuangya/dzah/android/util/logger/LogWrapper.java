package com.ch.wchhuangya.dzah.android.util.logger;

/**
 * 包装了Android原先的日志工具集的助手类。正常DDMS输出的方式可以是同时接收和输出多个标签中的某一个
 * Created by wchya on 2015-10-13.
 */
public class LogWrapper implements LogNode {

    // For piping:  在一个节点已经完成工作后，下一个接收日志信息的节点
    private LogNode mNext;

    /**
     * 在链式列表中返回下一个LogNode
     */
    public LogNode getNext() {
        return mNext;
    }

    /**
     * 设置将被发送的LogNode
     */
    public void setNext(LogNode node) {
        mNext = node;
    }

    /**
     * 格式化日志数据，并把它打印到LogView中
     * @param priority 被打印的数据级别，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    @Override
    public void println(int priority, String tag, String msg, Throwable tr) {
        // 实际上有不带msg参数的日志方法。目前为止，如果出现这种情况，把null转换为了空字符串，然后继续后续步骤
        String useMsg = msg;
        if (useMsg == null) {
            useMsg = "";
        }

        // 如果提供了异常信息，把异常转换为了可用的字符串，并把它放到信息的最后
        if (tr != null) {
            msg += "\n" + android.util.Log.getStackTraceString(tr);
        }

        // 在功能上，下面这句与Log.x(tag, useMsg)方法完全相同
        // 例如，如果优先级是Log.VERBOSE，那么下面的方法与Log.v(tag, useMsg)方法相同
        Log.println(priority, tag, useMsg);

        // 如果当前节点不是链中的最后节点，一起把东东移走
        if (mNext != null) {
            mNext.println(priority, tag, msg, tr);
        }
    }
}
