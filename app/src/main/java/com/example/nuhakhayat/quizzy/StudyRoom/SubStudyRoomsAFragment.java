package com.example.nuhakhayat.quizzy.StudyRoom;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;

import java.util.List;

/**
 * Created by henda on 1/9/2017.
 */

public class SubStudyRoomsAFragment extends Fragment {

    View view;
    Database db;
    broadcastResever UserName;
	List<String> subRoomList;
	ListView listView;
    public static String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sub_study_rooms,container,false);

        // Initialize the layout elements
        listView = (ListView)view.findViewById(R.id.listViewall);

        db = new Database(getActivity().getApplicationContext());
        userID = UserName.Username;


        subRoomList = db.getSubscribedRoom(userID);


        listView.setAdapter(new ArrayAdapter(view.getContext(),
				android.R.layout.simple_list_item_1, subRoomList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity().getApplicationContext(),
						StudyRoomActivity.class);
				intent.putExtra("RoomName",subRoomList.get(position));
				startActivity(intent);
            }
        });
        return view;
    }


	@Override
	public void onResume() {
		subRoomList = db.getSubscribedRoom(userID);
		listView.setAdapter(new ArrayAdapter(view.getContext(),android.R.layout.simple_list_item_1,
				subRoomList));
		super.onResume();

	}
}
