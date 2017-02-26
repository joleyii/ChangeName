package scispirit.com.changename.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scispirit.com.changename.Adapter.MainAdapter;
import scispirit.com.changename.R;
import scispirit.com.changename.bean.FileBean;
import scispirit.com.changename.view.BarbershopBar;

/**
 * 主界面，两个大的圆形按钮
 * 上边的按钮点击之后 搜索本地的 txt
 * 下边的按钮点击之后 sd卡根目录
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @BindView(R.id.tv_manual)
    TextView tvManual;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_currentaddress)
    TextView tvCurrentAddress;

    @BindView(R.id.bbb_main)
    BarbershopBar bbb;

    private MainAdapter mainAdapter;
    private ArrayList<FileBean> fileBeanArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        mainAdapter = new MainAdapter(this, fileBeanArrayList);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(mainAdapter);
    }

    @OnClick(R.id.tv_search)
    public void searchClick() {
        rvMain.setVisibility(View.VISIBLE);
        tvSearch.setVisibility(View.GONE);
        tvManual.setVisibility(View.GONE);
        File file = Environment.getExternalStorageDirectory();
        getFile(file);

    }

    @OnClick(R.id.tv_manual)
    public void manualClick() {

    }

    public void getFile(File filepath) {

        //判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File[] files = filepath.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    tvCurrentAddress.setText(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        if (file.canRead()) {
                            getFile(file);
                        }
                    } else {
                        //判断是文件，则进行文件名判断
                        if (getSuffixName(file)) {
                            FileBean fileBean = new FileBean();
                            fileBean.setFile(file);
                            fileBean.setAddress(file.getAbsolutePath());
                            fileBeanArrayList.add(0, fileBean);
                            mainAdapter.notifyItemInserted(0);
                        }
                    }
                }
            }
        }
    }

    private boolean getSuffixName(File file) {
        String fileName = file.getAbsolutePath();
        String suffix;
        if (fileName.contains(".")) {
            String middle[] = fileName.split("\\.");
            if (middle.length > 1) {
                suffix = middle[middle.length - 1];
            } else {
                suffix = "s";
            }
        } else {
            suffix = "s";
        }
        return suffix.equals(getString(R.string.txt)) ||
                suffix.equals(getString(R.string.txt).toLowerCase());

    }
}
