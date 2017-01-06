package com.example.nuhakhayat.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Button button = (Button)findViewById(R.id.buttonup);
		Button logout =(Button)findViewById(R.id.logout);
		button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(SettingsActivity.this,EditProfileActivity.class));
			}
		});
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(SettingsActivity.this, EditProfileActivity.class));
			}
		});
	}
}
