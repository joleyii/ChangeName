package scispirit.com.changename.bean;

import java.io.File;

/**
 * Created by yanjun on 2017/2/21.
 */

public class FileBean {
    private String address;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
