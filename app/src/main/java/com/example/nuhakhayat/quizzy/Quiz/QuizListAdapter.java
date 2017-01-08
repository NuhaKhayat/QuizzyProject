package com.example.nuhakhayat.quizzy.Quiz;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

import java.util.List;

/**
 * Created by NuhaKhayat on 1/8/17 AD.
 */
public class QuizListAdapter extends ArrayAdapter<String> {

	List<String> allListItem;
	Database database;

	public QuizListAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		allListItem = objects;
		database = new Database(context);
	}


	//This method is used to retrieve the item view and set the text
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.list_view_item_layout, null);

		TextView title = (TextView) view.findViewById(R.id.quiz_discussion_title);
		TextView num = (TextView) view.findViewById(R.id.num_of_q_textView);


		title.setText(allListItem.get(position));
		//num.setText(String.valueOf(getQuizQCount(allListItem.get(position))));

		return view;
	}

	public int getQuizQCount(String title){
		Cursor cursor = database.getQuiz(title);
		return cursor.getInt(cursor.getColumnIndex(Database.COLUMN_QUIZ_Q_NUM));
	}
}