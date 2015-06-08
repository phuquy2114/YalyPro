package asiantech.dev.yalypro.Api;

import android.app.Application;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 6/8/15.
 */
public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/ACQUAINT.TTF"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}
