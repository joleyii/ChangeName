package scispirit.com.changename.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scispirit.com.changename.R;

/**
 * Created by yanjun on 2017/2/23.
 */

public class ChangeNameActivity extends BaseActivity {

    @BindView(R.id.tv_change)
    TextView tvChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changename);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_change)
    public void changeClick() {

    }
}
