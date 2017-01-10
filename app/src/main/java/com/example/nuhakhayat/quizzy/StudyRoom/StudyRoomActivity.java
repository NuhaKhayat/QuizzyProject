package com.example.nuhakhayat.quizzy.StudyRoom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.Discussion.AddDiscussion;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListAdapter;
import com.example.nuhakhayat.quizzy.Discussion.DiscussionListListener;
import com.example.nuhakhayat.quizzy.Quiz.AddQuizActivity;
import com.example.nuhakhayat.quizzy.Quiz.QuizListAdapter;
import com.example.nuhakhayat.quizzy.Quiz.QuizListListener;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

public class StudyRoomActivity extends AppCompatActivity {

	ArrayAdapter<String> quizAdapter, discussionAdapter;
	List<String> allQuizzes, allDiscussions, subRoomList;;
	ListView quizListView, discussionListView;
	ImageView addQuiz, addDiscussion;
	Database database;
	TextView titel;
	String roomName;
	LikeButton subRoomBtn;
	broadcastResever UserName;
	// just fucking push it motherfucker

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_room);

		roomName = getIntent().getStringExtra("RoomName");
		database = new Database(getApplicationContext());
		allQuizzes = database.getAllQuizzes(roomName);
		allDiscussions = database.getAllDiscussions(roomName);
		subRoomList = database.getSubscribedRoom(UserName.Username);

		titel = (TextView)findViewById(R.id.room_title_textView);
		titel.setText(roomName);


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
				intent.putExtra("RoomName",roomName);
				startActivity(intent);
			}
		});

		addDiscussion = (ImageView)findViewById(R.id.addDicussionImageView);
		addDiscussion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StudyRoomActivity.this, AddDiscussion.class);
				intent.putExtra("RoomName",roomName);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});


		subRoomBtn = (LikeButton)findViewById(R.id.sub_room_button);
		if(subRoomList.contains(roomName)){
			subRoomBtn.setLiked(true);
		}else{
			subRoomBtn.setLiked(false);
		}
		subRoomBtn.setCircleEndColorRes(R.color.Yellow);
		subRoomBtn.setCircleStartColorRes(R.color.MediumGreen);
		subRoomBtn.setExplodingDotColorsRes(R.color.DarkGreen,R.color.Yellow);
		//Set like button click listener to update the liked value is database
		subRoomBtn.setOnLikeListener(new OnLikeListener() {
			@Override
			public void liked(LikeButton likeButton) {
				subRoomBtn.setLiked(true);
				database.insertSubRoom(UserName.Username, roomName);
			}
			@Override
			public void unLiked(LikeButton likeButton) {
				subRoomBtn.setLiked(false);
				database.deleteSubRoom(UserName.Username, roomName);
			}
		});

	}


	@Override
	public void onBackPressed() {
		finish();
	}
}

