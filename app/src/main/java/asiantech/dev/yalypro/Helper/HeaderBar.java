package asiantech.dev.yalypro.Helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class HeaderBar extends RelativeLayout {

    private Context mContext;
    private View mRootView;


    public void init(Context mContext) {
        this.mContext = mContext;
        addView(mRootView);


    }

    public HeaderBar(Context context) {
        super(context);
        init(context);
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);


    }
}
