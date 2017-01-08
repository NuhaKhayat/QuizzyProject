package com.example.nuhakhayat.quizzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henda on 1/3/2017.
 * This class is used to create and query the application database
 */

public class Database extends SQLiteOpenHelper {
    //The Project Database Name
    static final String DATABASE_NAME = "Quizzy.db";

    //The database tables names
    static final String USER_TABLE = "User_Table";
    static final String STUDY_ROOM_TABLE = "StudyRoom_Table";
    static final String MAJORS_TABLE = "Majors_Table";
    static final String QUIZ_TABLE = "Quiz_Table";
    static final String DISCUSSION_TABLE = "Discussion_Table";
    static final String QUESTION_TABLE = "Question_Table";
    static final String COMMENTS_TABLE = "Comments_Table";
    //This table is from the many-to-many relationship between Quiz & StudyRoom tables
    static final String STUDY_ROOM_QUIZ_TABLE = "StudyRoom_Quiz_Table";

    static final int VersionN = 1;

    //Study Room Table columns
    public static final String COLUMN_PK_ROOM_ID = "PK_RoomID";
    public static final String COLUMN_COURSE = "Course";
    //public static final String Fk_createdBy = "FK_createdBy";

    //Majors Table columns
    public static final String COLUMN_FK_ROOM_MAJOR = "FK_RoomID";
    public static final String COLUMN_MAJORS = "Majors";

    //User Table columns
    public static final String COLUMN_PK_USERNAME = "PK_Username";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_STARS_NUM = "StarsNum";

    //Discussion Table columns
    public static final String COLUMN_PK_DISCUSSION_ID = "PK_DiscussionID";
    public static final String COLUMN_DISCUSSION_NAME = "discussionName";
    public static final String COLUMN_DISCUSSION_DESCRIPTION = "discussionDescription";
    public static final String FK_ROOM_DISCUS_ID = "FK_RoomDiscusID";
    //public static final String Fk_addedBy = "FK_addedBy";

    //Quiz Table columns
    public static final String COLUMN_PK_QUIZ_ID = "PK_QuizID";
    public static final String COLUMN_QUIZ_TITLE = "QuizTitle";
	public static final String COLUMN_QUIZ_Q_NUM = "NumOfQuestion";
    public static final String COLUMN_FK_ROOM_QUIZ = "FK_RoomQuiz";

    //Question Table columns
    public static final String COLUMN_PK_QUESTION_ID = "PK_QuestionID";
    public static final String COLUMN_QUESTION = "Question";
    public static final String COLUMN_ANSWER_1 = "Answer1";
    public static final String COLUMN_ANSWER_2 = "Answer2";
    public static final String COLUMN_ANSWER_3 = "Answer3";
	public static final String COLUMN_ANSWER_4 = "Answer4";
    public static final String COLUMN_CORRECT_ANSWER_POSITION = "CorrectAnswerPos";
    public static final String COLUMN_FK_QUIZ_ID = "FK_QuizID";

    //Comments Table columns
    public static final String COLUMN_PK_COMMENT_ID = "commentID";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_COMMENT_LIKED = "likesNum";
    public static final String COLUMN_FK_USERNAME_COMMENT = "FK_username";
    public static final String COLUMN_FK_DISCUSSION_NAME_COMMENT = "FK_discussionName";

    // Studies_Quizzes Table columns
    public static final String COLUMN_FK_QUIZ_ROOM_ID = "FK_RoomID";
    public static final String COLUMN_FK_ROOM_QUIZ_ID = "FK_RoomQuizID";

   /* // Queries for creating the tables with their columns
    final String DB_userCREATE="CREATE TABLE "+ USER_TABLE +" ("
            + COLUMN_PK_USERNAME +" text primary key,"
            + COLUMN_EMAIL +" text not null,"
            + COLUMN_PASSWORD +" text not null,"
            + COLUMN_STARS_NUM +" integer)";

    final String DB_roomCREATE="CREATE TABLE "+ STUDY_ROOM_TABLE +" ("
            + COLUMN_PK_ROOM_ID +" integer primary key autoincrement,"
            + COLUMN_COURSE +" text not null)";

    final String DB_majorCREATE="CREATE TABLE "+ MAJORS_TABLE +" ("+ COLUMN_FK_ROOM_MAJOR +" integer,"
            + COLUMN_MAJORS +" text not null)";*/


    final String DB_discussionCREATE = "CREATE TABLE " + DISCUSSION_TABLE + "("
			+ COLUMN_DISCUSSION_NAME + " VARCHAR(100) PRIMARY KEY, "
			+ COLUMN_DISCUSSION_DESCRIPTION + " TEXT, "
			+ FK_ROOM_DISCUS_ID+ " VARCHAR(10))";

	final String DB_commentsCREATE = "CREATE TABLE "+ COMMENTS_TABLE +"("
			+ COLUMN_PK_COMMENT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_COMMENT +" TEXT, "
			+ COLUMN_COMMENT_LIKED + " INTEGER, "
			+ COLUMN_FK_DISCUSSION_NAME_COMMENT + " VARCHAR(100), "
			+ COLUMN_FK_USERNAME_COMMENT + " VARCHAR(50))";

	final String DB_quizCREATE="CREATE TABLE "+ QUIZ_TABLE + "("
			+ COLUMN_PK_QUIZ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_QUIZ_TITLE+ " VARCHAR(100), "
			+ COLUMN_QUIZ_Q_NUM+ " INTEGER, "
			+ COLUMN_FK_ROOM_QUIZ + " VARCHAR(10))";

	final String DB_questionCREATE = "CREATE TABLE "+ QUESTION_TABLE + "("
			+ COLUMN_PK_QUESTION_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_QUESTION+ " TEXT, "
			+ COLUMN_ANSWER_1+ " TEXT, "
			+ COLUMN_ANSWER_2+ " TEXT, "
			+ COLUMN_ANSWER_3+ " TEXT, "
			+ COLUMN_ANSWER_4+ " TEXT, "
			+ COLUMN_CORRECT_ANSWER_POSITION + " INTEGER, "
			+ COLUMN_FK_QUIZ_ID + " INTEGER)";



    
/*



    final String DB_roomQuizesCREATE="CREATE TABLE "+ STUDY_ROOM_QUIZ_TABLE +" ("
            + COLUMN_FK_QUIZ_ROOM_ID +" integer primary key,"
            + COLUMN_FK_ROOM_QUIZ_ID + " integer primary key)";*/

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VersionN);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the queries to create the tables
//        db.execSQL(DB_userCREATE);
//        db.execSQL(DB_roomCREATE);
        db.execSQL(DB_discussionCREATE);
		db.execSQL(DB_commentsCREATE);
		db.execSQL(DB_quizCREATE);
		db.execSQL(DB_questionCREATE);
//        db.execSQL(DB_roomQuizesCREATE);
//        db.execSQL(DB_commentsCREATE);
//        db.execSQL(DB_majorCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+ USER_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ STUDY_ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ DISCUSSION_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ QUIZ_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ STUDY_ROOM_QUIZ_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ COMMENTS_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ MAJORS_TABLE);
        onCreate(db);
    }


    public long insertDisscussion(String name, String discription, String roomId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DISCUSSION_NAME, name);
		contentValues.put(COLUMN_DISCUSSION_DESCRIPTION, discription);
		contentValues.put(FK_ROOM_DISCUS_ID,roomId);
		return db.insert(DISCUSSION_TABLE, null, contentValues);
	}

	public Cursor getDiscussion(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Log.d("getDiscussion","SELECT * FROM " + DISCUSSION_TABLE +" WHERE " + COLUMN_DISCUSSION_NAME +" = '"+ name +"'");
		return db.rawQuery( "SELECT * FROM " + DISCUSSION_TABLE +" WHERE "
				+ COLUMN_DISCUSSION_NAME +" = '"+ name +"'", null);
	}

	public List<String> getAllDiscussions() {
		List<String> discussion = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor =  db.rawQuery( "SELECT * FROM " + DISCUSSION_TABLE, null );
		cursor.moveToFirst();
		while(cursor.isAfterLast() == false){
			discussion.add(cursor.getString(cursor.getColumnIndex(COLUMN_DISCUSSION_NAME)));
			cursor.moveToNext();
		}
		return discussion;
	}

	public Cursor getComments(String discussionName){
		SQLiteDatabase db = this.getReadableDatabase();
		Log.d("getComments","SELECT * FROM " + COMMENTS_TABLE + " WHERE "
				+ COLUMN_FK_DISCUSSION_NAME_COMMENT + " ='" + discussionName +"'");
		Cursor cursor =  db.rawQuery( "SELECT * FROM " + COMMENTS_TABLE + " WHERE "
				+ COLUMN_FK_DISCUSSION_NAME_COMMENT + " ='" + discussionName +"'", null );
		return cursor;
	}

	public long insertCommetn(String comment, String discussionName, String username){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_COMMENT, comment);
		contentValues.put(COLUMN_COMMENT_LIKED,0);
		contentValues.put(COLUMN_FK_DISCUSSION_NAME_COMMENT,discussionName);
		contentValues.put(COLUMN_FK_USERNAME_COMMENT,username);
		return db.insert(COMMENTS_TABLE, null, contentValues);
	}

	public long updateCommentLiked(int id, int liked){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_COMMENT_LIKED,liked);
		return db.update(COMMENTS_TABLE,contentValues,COLUMN_PK_COMMENT_ID +" = '"+id+"'",null);
	}


	public long insertQuiz(String title, int numOfQ, String roomId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_QUIZ_TITLE,title);
		contentValues.put(COLUMN_QUIZ_Q_NUM,numOfQ);
		contentValues.put(COLUMN_FK_ROOM_QUIZ,roomId);
		return db.insert(QUIZ_TABLE, null, contentValues);
	}


	public Cursor getQuiz(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.rawQuery( "SELECT * FROM " + QUIZ_TABLE +" WHERE "
				+ COLUMN_QUIZ_TITLE +" = '"+ name +"'", null);
	}

	public List<String> getAllQuizzes() {
		List<String> quiz = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor =  db.rawQuery( "SELECT * FROM " + QUIZ_TABLE, null);
		cursor.moveToFirst();
		while(cursor.isAfterLast() == false){
			quiz.add(cursor.getString(cursor.getColumnIndex(COLUMN_QUIZ_TITLE)));
			cursor.moveToNext();
		}
		return quiz;
	}

	public long insertQuestion(String question, String ans1, String ans2, String ans3, String ans4,
							   int pos, int quizId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_QUESTION, question);
		contentValues.put(COLUMN_ANSWER_1,ans1);
		contentValues.put(COLUMN_ANSWER_2,ans2);
		contentValues.put(COLUMN_ANSWER_3,ans3);
		contentValues.put(COLUMN_ANSWER_4,ans4);
		contentValues.put(COLUMN_CORRECT_ANSWER_POSITION, pos);
		contentValues.put(COLUMN_FK_QUIZ_ID,quizId);
		return db.insert(QUESTION_TABLE, null, contentValues);
	}

}
