package scispirit.com.changename.Activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}