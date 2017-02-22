package scispirit.com.changename.AppBase;

import android.app.Application;

/**
 * Created by 123 on 2017/2/21.
 */

public class MyAppliction extends Application {
    public static MyAppliction appliction;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appliction = this;
    }
}
