package com.ch.wchhuangya.dzah.android.util.logger;

/**
 * LoggerNode列表或树的助手类
 *
 * <p>When this is set as the head of the list,
 * an instance of it can function as a drop-in replacement for {@link android.util.Log}.
 * Most of the methods in this class server only to map a method call in Log to its equivalent
 * in LogNode.</p>
 * Created by wchya on 2015-10-12.
 */
public class Log {

    // 从Android原生的日志功能中抽取原生的值，这样使得集成和互操作更加的方便
    public static final int NONE = -1;
    public static final int VERBOSE = android.util.Log.VERBOSE;
    public static final int DEBUG = android.util.Log.DEBUG;
    public static final int INFO = android.util.Log.INFO;
    public static final int WARN = android.util.Log.WARN;
    public static final int ERROR = android.util.Log.ERROR;
    public static final int ASSERT = android.util.Log.ASSERT;

    // 存储LogNode拓扑的开始
    private static LogNode mLogNode;

    /**
     * 返回链式列表中的下一个NextNode对象
     * @return 链式列表中的下一个NextNode对象
     */
    public static LogNode getLogNode() {
        return mLogNode;
    }

    /**
     * 设置将要被发送的LogNode数据
     * @param node 将要被发送的LogNode数据
     */
    public static void setLogNode(LogNode node) {
        mLogNode = node;
    }

    /**
     * 指示用于打印提供的日志数据的LogNode，其它的LogNode可以与期望一样，被链到LogNode的末尾
     * @param priority 被打印的数据级别，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void println(int priority, String tag, String msg, Throwable tr) {
        if (mLogNode != null) {
            mLogNode.println(priority, tag, msg, tr);
        }
    }

    /**
     * 指示用于打印提供的日志数据的LogNode，其它的LogNode可以与期望一样，被链到LogNode的末尾
     * @param priority 被打印的数据级别，Verbose, Error等
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void println(int priority, String tag, String msg) {
        println(priority, tag, msg, null);
    }

    /**
     * 使用VERBOSE优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void v(String tag, String msg, Throwable tr) {
        println(VERBOSE, tag, msg, tr);
    }

    /**
     * 使用VERBOSE优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    /**
     * 使用DEBUG优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void d(String tag, String msg, Throwable tr) {
        println(DEBUG, tag, msg, tr);
    }

    /**
     * 使用DEBUG优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    /**
     * 使用INFO优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void i(String tag, String msg, Throwable tr) {
        println(INFO, tag, msg, tr);
    }

    /**
     * 使用INFO优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    /**
     * 使用WARN优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void w(String tag, String msg, Throwable tr) {
        println(WARN, tag, msg, tr);
    }

    /**
     * 使用WARN优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    /**
     * 使用WARN优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void w(String tag, Throwable tr) {
        w(tag, null, tr);
    }

    /**
     * 使用ERROR优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void e(String tag, String msg, Throwable tr) {
        println(ERROR, tag, msg, tr);
    }

    /**
     * 使用ERROR优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    /**
     * 使用ASSERT优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void wtf(String tag, String msg, Throwable tr) {
        println(ASSERT, tag, msg, tr);
    }

    /**
     * 使用ASSERT优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param msg 将被打印的真实信息
     */
    public static void wtf(String tag, String msg) {
        wtf(tag, msg, null);
    }

    /**
     * 使用ASSERT优先级打印日志
     *
     * @param tag 日志数据的标签。可以被用于组织日志语句
     * @param tr 如果抛出异常，异常将和日志工具一起来抽取和打印有用的信息
     */
    public static void wtf(String tag, Throwable tr) {
        wtf(tag, null, tr);
    }
}
