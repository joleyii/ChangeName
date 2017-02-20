package scispirit.com.changename.base.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import scispirit.com.changename.R;

import static scispirit.com.changename.base.tools.SystemBarManager.setStutasBar;

/**
 * 主界面，两个大的圆形按钮
 * 上边的按钮点击之后 搜索本地的 txt
 * 下边的按钮点击之后 sd卡根目录
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStutasBar(this);

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
    }

    private void searchFile(String keyword, File filepath) {
        int index = 0;
        HashMap<String, Object> rowItem;
        ArrayList<HashMap<String, Object>> bookList = null;
        //判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File[] files = filepath.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //如果目录可读就执行（一定要加，不然会挂掉）
                        if (file.canRead()) {
                            searchFile(keyword, file);  //如果是目录，递归查找
                        }
                    } else {
                        //判断是文件，则进行文件名判断
                        try {
                            if (file.getName().contains(keyword) ||
                                    file.getName().contains(keyword.toUpperCase())) {
                                rowItem = new HashMap<>();
                                rowItem.put("number", index);    // 加入序列号
                                rowItem.put("bookName", file.getName());// 加入名称
                                rowItem.put("path", file.getPath());  // 加入路径
                                rowItem.put("size", file.length());   // 加入文件大小
                                bookList.add(rowItem);
                                index++;
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "查找发生错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
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
                            searchFile(keyword, file);  //如果是目录，递归查找
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

    ArrayList<String> list;

    private Observable<Integer> scanObserver() {
        return Observable.fromArray(1).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }


    public Flowable<ArrayList<String>> scan() {
        return Flowable
                .fromArray(list)
                .scan(new BiFunction<ArrayList<String>, ArrayList<String>, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> list, ArrayList<String> list2) throws Exception {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void ddd() {

        Observable<ArrayList<String>> source =Observable.fromArray(list);

        Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return source.scan(new ArrayList<>(), new BiFunction<ArrayList<Object>, ArrayList<String>, ArrayList<Object>>() {
                    @Override
                    public ArrayList<Object> apply(ArrayList<Object> objects, ArrayList<String> list) throws Exception {
                        return null;
                    }
                });
            }
        });


        // alternatively, by using compose to stay fluent

        source.compose(o ->
                Observable.defer(() -> o.scan(new ArrayList<>(), (list, item) -> list.add(item)))
        );
    }


}
