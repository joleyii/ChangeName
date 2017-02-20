package scispirit.com.changename.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import scispirit.com.changename.R;

/**
 * Created by yanjun on 2017/2/20.
 */

public class MainAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Integer> arrayList = new ArrayList<>();

    public MainAdapter(Context context, ArrayList<Integer> arrayList) {

        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ddddd", "eeeeeeeee");
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("ddddd", position + "");
        ((MyViewHolder) holder).tvFilename.setText(arrayList.get(position) + "");

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) TextView tvFilename;

        MyViewHolder(View view) {
            super(view);
            tvFilename = (TextView) view.findViewById(R.id.tv_filename);
        }
    }
}
