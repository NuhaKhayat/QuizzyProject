package com.example.nuhakhayat.quizzy;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nuhakhayat.quizzy.NavDrawerMenu.NavDrawerItem;
import com.example.nuhakhayat.quizzy.NavDrawerMenu.NavDrawerListAdapter;

import java.util.ArrayList;


/**
 * Activity that represents the starting screen of the app
 * Set the menu and menu click listener
 */

//kdfdjkjfkwj
public class MainActivity extends AppCompatActivity {

	private String TAG = "MainActivity";
	private DrawerLayout mDrawerLayout;
	public static ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	public static CharSequence mTitle;

	//Arrays to store slide menu items and icons
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;



	public static ArrayList<NavDrawerItem> navDrawerItems;
	public static NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setSlideMenu();

		if (savedInstanceState == null) {
			mDrawerLayout.openDrawer(mDrawerList);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Toggle slide menu on selecting action bar app icon
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		//Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//Pass any configuration change to the slide menu toggle
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Initialize variable necessary for slide menu
	 * Create slide menu
	 * Set adapter and listener
	 **/
	private void setSlideMenu(){
		mTitle = mDrawerTitle = getTitle();
		//Load slide menu items and icons
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		//Initialize array list to add slide menu items
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));

		//Recycle the typed array
		navMenuIcons.recycle();

		//Set the slide menu list adapter and click listener
		adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		mDrawerList.setAdapter(adapter);

		//Set the slide menu on open and on close listeners
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.app_name,
				R.string.app_name) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
			}
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
			}
		};
		mDrawerLayout.addDrawerListener(mDrawerToggle);

		//Enabling action bar icon and set as toggle button for slide menu
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	/**
	 * Slide menu item click listener to display fragment
	 **/
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Fragment fragmentToDisplay = null;
			switch (position){
				case 0:
					fragmentToDisplay = new UserProfileFragment();
					break;
				case 1:
				case 2:
					fragmentToDisplay = new AllStudyRoomsFragment();
					break;
				case 3:
					//Logout
					break;
				default:
					break;
			}
			if(fragmentToDisplay != null){
				getFragmentManager().beginTransaction()
						.replace(R.id.fragContainer,fragmentToDisplay)
						.commit();
				// Update and close slide menu
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				setTitle(navMenuTitles[position]);
				mDrawerLayout.closeDrawer(mDrawerList);
			}
		}
	}

}