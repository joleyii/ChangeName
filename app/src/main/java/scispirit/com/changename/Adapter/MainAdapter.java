package scispirit.com.changename.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import scispirit.com.changename.R;
import scispirit.com.changename.activity.ChangeNameActivity;
import scispirit.com.changename.bean.FileBean;

/**
 * Created by yanjun on 2017/2/20.
 */

public class MainAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<FileBean> arrayList = new ArrayList<>();

    public MainAdapter(Context context, ArrayList<FileBean> arrayList) {
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
        ((MyViewHolder) holder).tvFilename.setText(arrayList.get(position).getAddress());
        ((MyViewHolder) holder).tvFilename.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChangeNameActivity.class);
            intent.putExtra("file", arrayList.get(position));
            context.startActivity(intent);
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFilename;
        MyViewHolder(View view) {
            super(view);
            tvFilename = (TextView) view.findViewById(R.id.tv_filename);
        }
    }
}
