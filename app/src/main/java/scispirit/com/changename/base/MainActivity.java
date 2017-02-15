package scispirit.com.changename.base;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.tbruyelle.rxpermissions2.RxPermissions;

import scispirit.com.changename.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                    } else {
                    }
                });
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Log.d("dddd", "gggggg");
                    } else {
                        Log.d("dddd", "rrrrrrr");
                    }
                });
    }
}
