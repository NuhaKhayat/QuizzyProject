package com.example.nuhakhayat.quizzy.StudyRoom;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

import java.util.List;

public class AllStudyRoomsFragment extends Fragment {

	View view;
	Database db;
	List<String> courseList;
	ListView listView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_all_study_rooms,container,false);
		listView = (ListView)view.findViewById(R.id.listViewall);

		db = new Database(getActivity().getApplicationContext());
		courseList = db.getAllCourses();

		listView.setAdapter(new ArrayAdapter(view.getContext(),android.R.layout.simple_list_item_1, courseList));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity().getApplicationContext(),StudyRoomActivity.class);
				intent.putExtra("RoomName",courseList.get(position));
				startActivity(intent);
			}
		});
		ImageView imageView = (ImageView)view.findViewById(R.id.imageViewp);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(view.getContext(),AddStudyRoomActivity.class));
			}
		});
		return view;
	}

	@Override
	public void onResume() {
		courseList = db.getAllCourses();
		listView.setAdapter(new ArrayAdapter(view.getContext(),android.R.layout.simple_list_item_1, courseList));
		super.onResume();

	}
}


