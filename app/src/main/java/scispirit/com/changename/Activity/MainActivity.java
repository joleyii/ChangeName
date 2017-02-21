package scispirit.com.changename.Activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import scispirit.com.changename.Adapter.MainAdapter;
import scispirit.com.changename.R;
import scispirit.com.changename.bean.FileBean;

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
        ArrayList<FileBean> integers = new ArrayList<>();
        MainAdapter mainAdapter = new MainAdapter(this, integers);

    }

    @OnClick
    public void searchClick(View view) {

    }

}
