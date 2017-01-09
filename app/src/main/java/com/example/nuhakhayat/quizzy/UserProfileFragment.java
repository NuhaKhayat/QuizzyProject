package com.example.nuhakhayat.quizzy;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class UserProfileFragment extends Fragment {

	TextView username;
	RatingBar rating ;
	EditText fullname,email;
	Button editProfile, changePassword;
	Database db;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	final View view = inflater.inflate(R.layout.fragment_user_profile,container,false);

		db=new Database(getActivity().getApplicationContext());

        username=(TextView)view.findViewById(R.id.usrname);
		rating = (RatingBar)view.findViewById(R.id.rating);
		fullname=(EditText)view.findViewById(R.id.fullname);
		email=(EditText)view.findViewById(R.id.email);
		editProfile =(Button)view.findViewById(R.id.editProfile);
		changePassword = (Button)view.findViewById(R.id.changepass);

		//int stars = (db.numOfLikes()/5);
		//rating.setRating(int);




		changePassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity().getApplicationContext(),EditProfileActivity.class));
			}
		});
		editProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!fullname.getText().toString().isEmpty() && !email.getText().toString().isEmpty()) {
					db.updateProfile(username.getText().toString(), fullname.getText().toString(), email.getText().toString());
					db.close();
					Toast.makeText(getActivity().getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity().getApplicationContext(), "Filed cannot be empty!", Toast.LENGTH_LONG).show();
				}
			}

		});
		return view ;
	}
}


