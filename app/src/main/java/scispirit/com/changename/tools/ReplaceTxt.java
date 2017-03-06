package scispirit.com.changename.tools;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by 123 on 2017/3/6.
 */

public class ReplaceTxt {

    public static void replaceTxt(String[] replaceTxts, File file) {
        try {
            String srcStr = "456";
            String replaceStr = "张三";
            // 读
            FileReader in = new FileReader(file);
            BufferedReader bufIn = new BufferedReader(in);
            // 内存流, 作为临时流
            CharArrayWriter tempStream = new CharArrayWriter();
            // 替换
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                // 替换每行中, 符合条件的字符串
                for (String s : replaceTxts) {
                    line = line.replaceAll(s, replaceStr);
                }
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }
            // 关闭 输入流
            bufIn.close();
            // 将内存中的流 写入 文件
            FileWriter out = new FileWriter(file);
            tempStream.writeTo(out);
            out.close();
        } catch (Exception e) {

        }
    }
}
