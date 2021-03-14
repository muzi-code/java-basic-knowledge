package com.github.dev.muzi.base.concurrent.knowledge.working;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

/**
 * 【常用类】字典文件读取加载
 * Create by Muzi Li on 2019-09-12
 */
public class FileUtil {
    private FileUtil() {
    }

    /***
     * 通过文件的路径来获取文件行字符串list
     * @param filePath 文件的绝对路径或相对路径
     * @return 路径能正确找到文件、文件能读、未发生IO异常返回：list    其他情况返回：null
     */
    public static List<String> loadList(String filePath) {
        List<String> list = Lists.newArrayList();
        try (FileInputStream fileInputStream = getFileInputStream(filePath)) {
            if (fileInputStream != null) {
                // 输入为空，IO异常时会抛出IOexception
                list = IOUtils.readLines(fileInputStream, "UTF-8");
            }
        } catch (Exception e) {
            throw new IllegalStateException("DictionaryUtil：loadList：文件加载异常，可能出现的原因：文件不能读或IO发生错误。");
        }
        return list;
    }

    /***
     * 通过文件输入流获取文件行字符串list
     * @param fileInputStream 文件流
     * @return 路径能正确找到文件、文件能读、未发生IO异常返回：list    其他情况返回：null
     */
    public static List<String> loadList(InputStream fileInputStream) {
        Preconditions.checkNotNull(fileInputStream);
        List<String> list = Lists.newArrayList();
        try {
            if (fileInputStream != null) {
                // 输入为空，IO异常时会抛出IOexception
                list = IOUtils.readLines(fileInputStream, "UTF-8");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("DictionaryUtil：loadList：文件加载异常，可能出现的原因：文件不能读或IO发生错误。");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /***
     * 通过文件的路径来获取文件输入流
     * @param filePath 文件的绝对路径或相对路径
     * @return 文件不存在或者文件是文件夹返回：null 文件存在且不是文件夹返回：输入流对象
     * @throws IOException 文件不可读
     */
    private static FileInputStream getFileInputStream(String filePath) throws IOException {
        Preconditions.checkNotNull(filePath);
        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        if (file.exists() && !file.isDirectory()) {
            // 文件不存在，文件为目录时，文件不能读，三种情况会抛出IOException
            fileInputStream = FileUtils.openInputStream(file);
        }
        return fileInputStream;
    }

    private static FileOutputStream getFileOutputStream(String filePath) throws IOException {
        Preconditions.checkNotNull(filePath);
        File file = new File(filePath);
        FileOutputStream fileOutputStream = null;
        if (file.exists() && !file.isDirectory()) {
            // 文件不存在，文件为目录时，文件不能读，三种情况会抛出IOException
            fileOutputStream = FileUtils.openOutputStream(file);
        }
        return fileOutputStream;
    }

    public static void writeLine(String filePath, List<String> lines) {
        try (FileOutputStream fileOutputStream = getFileOutputStream(filePath)) {
            if (fileOutputStream != null) {
                // 输入为空，IO异常时会抛出IOexception
                IOUtils.writeLines(lines, null, fileOutputStream);
            }
        } catch (Exception e) {
            throw new IllegalStateException("DictionaryUtil：loadList：文件加载异常，可能出现的原因：文件不能读或IO发生错误。");
        }
    }
}
