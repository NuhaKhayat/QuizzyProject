package com.example.nuhakhayat.quizzy.Discussion;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by NuhaKhayat on 1/3/17 AD.
 * This class is used as a click listener for the discussion list view
 */
public class DiscussionListListener implements AdapterView.OnItemClickListener{

	List<String> allItems;
	Activity activity;

	public DiscussionListListener(Activity activity, List<String> allItems) {
		this.activity = activity;
		this.allItems = allItems;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//Get the clicked item an start discussion activity
		String item = allItems.get(position);
		Intent intent = new Intent(activity.getApplicationContext(),DiscussionActivity.class);
		intent.putExtra("title",item);
		activity.startActivity(intent);
	}
}
