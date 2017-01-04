package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NuhaKhayat on 1/2/17 AD.
 */
public class DiscussionListAdapter extends ArrayAdapter<String>{

	List<String> allListItem;

	public DiscussionListAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		allListItem = objects;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("adapter","here2");
		View view;
		view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_view_item, null);
			Log.d("adapter","here3");
		}

		TextView title = (TextView)view.findViewById(R.id.quiz_discussion_title);
		//TextView other = (TextView)view.findViewById(R.id.difficult_comment);

		if(title != null){
			title.setText(allListItem.get(position));
			Log.d("title",allListItem.get(position));
		}else{
			Log.d("title","null");
		}
		/*if (other != null){
			other.setText(quizDiscussion.get(1));
			Log.d("other",quizDiscussion.get(1));
		}else{
			Log.d("other","null");
		}*/
		return view;
	}
}
