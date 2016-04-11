package com.ch.wchhuangya.dzah.android.util;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件助手类
 * Created by wchya on 2016-01-08 14:37.
 */
public class FileHelper {
    /** 应用在外部存储上的根路径 */
    public static String DZAH_ROOT_DIR = getExternalDirPath() + Constant.DZAH_DIR;
    /** 应用在外部存储上日志目录的路径 */
    public static String DZAH_LOG_DOR = DZAH_ROOT_DIR + "/log";


    /**
     * 检查外部存储是否可用，true-可用；false-不可用；
     */
    public static boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取外部存储的根目录的路径
     */
    public static String getExternalDirPath() {
        return getExternalDirFile().getPath();
    }

    /**
     * 获取外部存储的根目录
     */
    public static File getExternalDirFile() {
        return Environment.getExternalStorageDirectory();
    }

    public static void initDir() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(DZAH_LOG_DOR);
                if (!file.exists())
                    file.mkdirs();
            }
        }).start();
    }

    /**
     * 创建文件，并写入文本内容
     * @param filePath 文件保存的路径（路径最后不包含分隔符）
     * @param fileName 要保存的文件名称
     * @param content 要保存的内容, 不能为null
     */
    public static void createFile(String filePath, String fileName, @NonNull String content) {
        if (content == null)
            return;
        FileOutputStream outputStream = null;
        try {
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();
            outputStream = new FileOutputStream(filePath + File.separator + fileName);
            outputStream.write(content.getBytes());
        } catch (Exception e) {
            LogHelper.e(FileHelper.class, e.getMessage());
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                LogHelper.e(FileHelper.class, e.getMessage());
            }
        }
    }
}
