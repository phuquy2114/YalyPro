/*
 * Copyright (C) 2014 The Asian Tech. All rights reserved
 * WeightManagement Project
 */
package asiantech.dev.yalypro.Helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

/**
 * @category BaseActivity
 * @author PhuQuy
 * @version 1.0.0
 */
public abstract class BaseActivity extends FragmentActivity {

	// Fragment Manager
	protected FragmentManager fManager = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void setFragment(int id, Fragment f) {
		fManager.beginTransaction().replace(id, f).commit();
	}

	/**
	 * Function to make image (The girl) to show or hide
	 * @param _img1
	 */
	public void hideImage(ImageView _img1) {
		_img1.setVisibility(View.GONE);
	}
	
	public void showImage(ImageView _img1) {
		_img1.setVisibility(View.VISIBLE);
	}

	/*
	 * Abstract function
	 */
	protected abstract void initialize();

	protected abstract void setValue();

	protected abstract void setEvent();

}
