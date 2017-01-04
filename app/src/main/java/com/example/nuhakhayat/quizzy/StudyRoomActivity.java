package com.example.nuhakhayat.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nuhakhayat.quizzy.Discussion.AddDiscussionActivity;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListAdapter;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListListener;

import java.util.List;

public class StudyRoomActivity extends AppCompatActivity {


	ArrayAdapter<String> quizAdapter, discussionAdapter;
	List<String> allQuizzes, allDiscussions;
	ListView quizListView, discussionListView;
	ImageView addQuiz, addDiscussion;
	Database database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_room);

		database = new Database(getApplicationContext());
		//allQuizzes = dbHelper.getAllQuizzes();
		allDiscussions = database.getAllDiscussions();


		quizListView = (ListView)findViewById(R.id.quizListView);
		/*quizAdapter = new QuizDiscussionListAdapter(getApplicationContext(),R.layout.list_view_item,
				allQuizzes);
		quizListView.setAdapter(quizAdapter);
		quizListView.setOnItemClickListener(new QuizListListener
				(StudyRoomActivity.this,allQuizzes));*/

		discussionListView = (ListView)findViewById(R.id.discussionListView);
		discussionAdapter = new DiscussionListAdapter(getApplicationContext(),
				R.layout.list_view_item,allDiscussions);

		discussionListView.setAdapter(discussionAdapter);
		discussionListView.setOnItemClickListener(new DiscussionListListener
				(StudyRoomActivity.this,allDiscussions));

		/*addQuiz = (ImageView)findViewById(R.id.addQuizImageView);
		addQuiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudyRoomActivity.this,AddQuizActivity.class);
				//Add Study room title to intent
				startActivity(intent);
			}
		});*/

		addDiscussion = (ImageView)findViewById(R.id.addDicussionImageView);
		addDiscussion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudyRoomActivity.this,AddDiscussionActivity.class);
				intent.putExtra("RoomID",1);
				startActivity(intent);
			}
		});

	}
}

