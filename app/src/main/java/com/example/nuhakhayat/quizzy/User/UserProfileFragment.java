package com.example.nuhakhayat.quizzy.User;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.MainActivity;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;


public class UserProfileFragment extends Fragment {

	TextView username;
	RatingBar rating ;
	EditText email;
	Button editProfile, changePassword, inviteBtn;
	Database db;
	broadcastResever brUsername;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	final View view = inflater.inflate(R.layout.fragment_user_profile,container,false);

		db=new Database(getActivity().getApplicationContext());

        username=(TextView)view.findViewById(R.id.usrname);
		rating = (RatingBar)view.findViewById(R.id.rating);
		email=(EditText)view.findViewById(R.id.email);
		editProfile =(Button)view.findViewById(R.id.editProfile);
		changePassword = (Button)view.findViewById(R.id.changePass);
		inviteBtn = (Button)view.findViewById(R.id.inviteBtn);

		//rating
		int stars = (db.numOfLikes(1)/5);
		rating.setRating(stars);

		//username
		String userId=brUsername.Username;
		username.setText(userId);


		//move to invite friends activity
		inviteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getActivity().getApplicationContext(),InviteFriends.class));
			}
		});

		changePassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity().getApplicationContext(),EditProfileActivity.class));
			}
		});

		return view ;
	}
}


