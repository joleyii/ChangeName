package scispirit.com.changename.tools;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import scispirit.com.changename.bean.FileBean;

/**
 * Created by yanjun on 2017/2/21.
 */

public class FileTools {

    public static ArrayList<FileBean> getFile(String keyword, File filepath) {
        ArrayList<FileBean> arrayList = new ArrayList<>();
        //判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File[] files = filepath.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //如果目录可读就执行（一定要加，不然会挂掉）
                        if (file.canRead()) {
                            getFile(keyword, file);  //如果是目录，递归查找
                        }
                    } else {
                        //判断是文件，则进行文件名判断
                        try {
                            if (file.getName().contains(keyword) ||
                                    file.getName().contains(keyword.toUpperCase())) {
                                FileBean fileBean = new FileBean();
                                fileBean.setFile(file);
                                fileBean.setAddress(file.getAbsolutePath());
                                arrayList.add(fileBean);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
