package com.example.actionbartest;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements ActionBar.TabListener{
	
	private static final String SELECTED_ITEM = "selected_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//将应用程序图标设置为可点击的按钮，并在图标上添加向左的箭头
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//设置ActionBar的导航方式：Tab导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//依次添加三个Tab页，并为三个Tab标签添加事件监听器
		Tab tab = actionBar.newTab();
		tab.setIcon(R.drawable.ic_launcher);
		tab.setText("第七页");
		actionBar.addTab(actionBar.newTab().setText("第一页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第二页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第三页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第四页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第五页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("第六页").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setCustomView(R.layout.tab).setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onTabUnselected", 0).show();
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onTabSelected", 0).show();
				//创建一个新的Fragment对象
				Fragment fragment = new DummyFragment();
				//创建一个Bundle对象，用于向Fragment传入参数
				Bundle args = new Bundle();
				args.putInt(DummyFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
				//向fragment传入参数
				fragment.setArguments(args);
				//获取FragmentTransaction对象
				FragmentTransaction ft1 = getFragmentManager().beginTransaction();
				//使用fragment代替该Activity中的container组件
				ft1.replace(R.id.container, fragment);
				//提交事务
				ft1.commit();	
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onTabReselected", 0).show();
			}
		}));
		actionBar.addTab(tab.setTabListener(this));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            openSearch();
	            return true;
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void openSearch(){
		
	}
	
	public void openSettings(){
		
	}

	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState.containsKey(SELECTED_ITEM)){
			//选中前面保存的索引对应的Fragment页
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(SELECTED_ITEM));
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//将当前选中的Fragment页的索引保存到Bundle中
		outState.putInt(SELECTED_ITEM, getActionBar().getSelectedNavigationIndex());
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		//创建一个新的Fragment对象
		Fragment fragment = new DummyFragment();
		//创建一个Bundle对象，用于向Fragment传入参数
		Bundle args = new Bundle();
		args.putInt(DummyFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
		//向fragment传入参数
		fragment.setArguments(args);
		//获取FragmentTransaction对象
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		//使用fragment代替该Activity中的container组件
		ft.replace(R.id.container, fragment);
		//提交事务
		ft.commit();	
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
	}
}
