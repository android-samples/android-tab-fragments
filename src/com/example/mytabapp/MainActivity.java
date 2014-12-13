package com.example.mytabapp;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FragmentA()).commit();
		}
		*/
		// Fragment
		Fragment fragmentA = new FragmentA();
		Fragment fragmentB = new FragmentB();
		getFragmentManager().beginTransaction()
			.add(R.id.container, fragmentA).hide(fragmentA)
			.add(R.id.container, fragmentB).hide(fragmentB)
			.commit();
		
		// タブモード
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// タブ
		Tab tabA = getActionBar().newTab().setText("Tab1").setTabListener(new MyTabListener(fragmentA));
		getActionBar().addTab(tabA);
		Tab tabB = getActionBar().newTab().setText("Tab2").setTabListener(new MyTabListener(fragmentB));
		getActionBar().addTab(tabB);
	}
	

	// タブ切替
	public class MyTabListener implements ActionBar.TabListener{
		private Fragment mFragment;
		public MyTabListener(Fragment fragment){
			mFragment = fragment;
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// ft.add(R.id.container, mFragment);
			// ft.attach(mFragment);
			ft.show(mFragment);
		}
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// ft.remove(mFragment);
			// ft.detach(mFragment);
			ft.hide(mFragment);
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}

	// 画面Ａ
	public static class FragmentA extends Fragment {
		private int mCount = 0;
		private TextView mTextView;

		public FragmentA() {
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_a, container, false);
			return rootView;
		}
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			mTextView = (TextView)view.findViewById(R.id.textView1);
			mTextView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mCount++;
					mTextView.setText("A" + mCount);
				}
			});
		}
		
		@Override
		public void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);
			Log.i("test", "A save");
		}
		
		@Override
		public void onViewStateRestored(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewStateRestored(savedInstanceState);
			Log.i("test", "A restore");
		}
		
		
	}
	
	// 画面Ｂ
	public static class FragmentB extends Fragment {
		private int mCount = 0;
		private TextView mTextView;
		
		public FragmentB() {
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_b, container, false);
			return rootView;
		}
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			mTextView = (TextView)view.findViewById(R.id.textView1);
			mTextView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mCount++;
					mTextView.setText("B" + mCount);
				}
			});
		}
		
	}
	
}
