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
    public static String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sub_study_rooms,container,false);

        // Initialize the layout elements
        ListView listView = (ListView)view.findViewById(R.id.listViewall);

        db = new Database(getActivity().getApplicationContext());
        userID = UserName.Username;


        final List<String> subRoomList = db.getSubscribedRoom(userID);
        //Log.d("The Rooms are:", subRoomList.get(1));

        listView.setAdapter(new ArrayAdapter(view.getContext().getApplicationContext(),android.R.layout.simple_list_item_1, subRoomList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext().getApplicationContext(),StudyRoomActivity.class);
                intent.putExtra("RoomID", subRoomList.get(position));
                startActivity(intent);
                //Log.d("RoomID",subRoomList.get(position));
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
}
