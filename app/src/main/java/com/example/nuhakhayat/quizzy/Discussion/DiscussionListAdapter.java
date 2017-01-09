package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.R;

import java.util.List;

/**
 * Created by NuhaKhayat on 1/2/17 AD.
 * This class is used to set the view for the discussion item in list view
 */
public class DiscussionListAdapter extends ArrayAdapter<String>{

	List<String> allListItem;

	public DiscussionListAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		allListItem = objects;
	}


	//This method is used to retrieve the item view and set the text
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.list_view_item_layout, null);

		TextView title = (TextView)view.findViewById(R.id.quiz_discussion_title);

		if(title != null){
			title.setText(allListItem.get(position));
			Log.d("title",allListItem.get(position));
		}else{
			Log.d("title","null");
		}
		return view;
	}
}
