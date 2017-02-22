package scispirit.com.changename.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by 123 on 2017/2/19.
 * 顶部的旋转的bar
 */

public class BarbershopBar extends View {
    public BarbershopBar(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#5cc3ff"));
        canvas.drawColor(Color.BLUE);
        canvas.translate(100, 100);
        canvas.drawRect(new Rect(0, 0, 400, 400), paint);
    }
}
