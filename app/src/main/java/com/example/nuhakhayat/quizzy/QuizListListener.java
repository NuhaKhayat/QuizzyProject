package com.example.nuhakhayat.quizzy;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NuhaKhayat on 1/3/17 AD.
 */
public class QuizListListener implements AdapterView.OnItemClickListener{

	List<ArrayList<String>> allItems;
	Activity activity;

	public QuizListListener(Activity activity, List<ArrayList<String>> allItems) {
		this.activity = activity;
		this.allItems = allItems;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ArrayList<String> item = allItems.get(position);
		Intent intent = new Intent(activity.getApplicationContext(),QuizActivity.class);
		intent.putExtra("title",item.get(0));
		activity.startActivity(intent);
	}
}


