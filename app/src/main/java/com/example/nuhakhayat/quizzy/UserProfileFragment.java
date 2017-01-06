package com.example.nuhakhayat.quizzy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserProfileFragment extends AppCompatActivity {
	int id;
	EditText fullname,email,bdate;
	Button editProfile, changePassword;
	Database db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_user_profile);
		Intent intent=getIntent();
		id=intent.getIntExtra("id", 0);
		db=new Database(getApplicationContext());


		fullname=(EditText)findViewById(R.id.fullname);
		email=(EditText)findViewById(R.id.email);
		bdate=(EditText)findViewById(R.id.birthdate);
		editProfile =(Button)findViewById(R.id.editProfile);
		changePassword = (Button)findViewById(R.id.changepass);
		changePassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(UserProfileFragment.this,EditProfileActivity.class));
			}
		});
		editProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!fullname.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !bdate.getText().toString().isEmpty()){
					//db.open;
					db.updateProfile(id,fullname.getText().toString(),email.getText().toString(),bdate.getText().toString());
					db.close();
					Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getApplicationContext(),"Filed cannot be empty!",Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
