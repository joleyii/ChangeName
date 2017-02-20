package scispirit.com.changename.Activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import scispirit.com.changename.Adapter.MainAdapter;
import scispirit.com.changename.R;

/**
 * 主界面，两个大的圆形按钮
 * 上边的按钮点击之后 搜索本地的 txt
 * 下边的按钮点击之后 sd卡根目录
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Log.d("dddd", "gggggg");
                    } else {
                        Log.d("dddd", "rrrrrrr");
                   }
                });
        ArrayList<Integer> integers = new ArrayList<>();
        MainAdapter mainAdapter = new MainAdapter(this, integers);
    }

    public void getFile(String keyword, File filepath) {
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

                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "查找发生错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

}
