package scispirit.com.changename.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scispirit.com.changename.R;

/**
 * Created by yanjun on 2017/2/23.
 * 只要edittext放生变化就 增加item数量
 */

public class ChangeNameActivity extends BaseActivity {
    static final int FIRST_ADD_VIEW_NUMBER = 5;
    SparseArray<String> changedNames = new SparseArray<>();
    SparseArray<String> changeToNames = new SparseArray<>();

    @BindView(R.id.tv_change)
    TextView tvChange;

    @BindView(R.id.ll_change)
    LinearLayout llChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changename);
        ButterKnife.bind(this);
        setView();
    }

    private void setView() {//添加置换列表
        for (int i = 1; i < FIRST_ADD_VIEW_NUMBER; i++) {
        }
    }

    private void addItem(int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_change, null);
        EditText editTextLeft = (EditText) linearLayout.findViewById(R.id.et_left);
        EditText editTextRight = (EditText) linearLayout.findViewById(R.id.et_right);
        editTextLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changedNames.append(position, editable.toString());
            }
        });
        editTextRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeToNames.append(position, editable.toString());
            }
        });
    }

    @OnClick(R.id.tv_change)
    public void changeClick() {

    }
}
