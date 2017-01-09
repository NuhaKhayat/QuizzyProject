package com.example.nuhakhayat.quizzy.StudyRoom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.Discussion.AddDiscussion;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListAdapter;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListListener;
import com.example.nuhakhayat.quizzy.Quiz.AddQuizActivity;
import com.example.nuhakhayat.quizzy.Quiz.QuizListAdapter;
import com.example.nuhakhayat.quizzy.Quiz.QuizListListener;
import com.example.nuhakhayat.quizzy.R;

import java.util.List;

public class StudyRoomActivity extends AppCompatActivity {


	ArrayAdapter<String> quizAdapter, discussionAdapter;
	List<String> allQuizzes, allDiscussions;
	ListView quizListView, discussionListView;
	ImageView addQuiz, addDiscussion;
	Database database;
	// just fucking push it motherfucker

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_room);

		database = new Database(getApplicationContext());
		allQuizzes = database.getAllQuizzes();
		allDiscussions = database.getAllDiscussions();


		quizListView = (ListView)findViewById(R.id.quizListView);
		quizAdapter = new QuizListAdapter(getApplicationContext(),R.layout.list_view_item_layout,
				allQuizzes);
		quizListView.setAdapter(quizAdapter);
		quizListView.setOnItemClickListener(new QuizListListener
				(StudyRoomActivity.this,allQuizzes));

		discussionListView = (ListView)findViewById(R.id.discussionListView);
		discussionAdapter = new DiscussionListAdapter(getApplicationContext(),
				R.layout.list_view_item_layout,allDiscussions);

		discussionListView.setAdapter(discussionAdapter);
		discussionListView.setOnItemClickListener(new DiscussionListListener
				(StudyRoomActivity.this,allDiscussions));

		addQuiz = (ImageView)findViewById(R.id.addQuizImageView);
		addQuiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudyRoomActivity.this,AddQuizActivity.class);
				intent.putExtra("RoomID","1");
				startActivity(intent);
			}
		});

		addDiscussion = (ImageView)findViewById(R.id.addDicussionImageView);
		addDiscussion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudyRoomActivity.this, AddDiscussion.class);
				intent.putExtra("RoomID","1");
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});



	}
}

