package com.example.nuhakhayat.quizzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

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
    static final String SUB_ROOM_TABLE = "subRoom_Table";
    static final String QUIZ_TABLE = "Quiz_Table";
    static final String DISCUSSION_TABLE = "Discussion_Table";
    static final String QUESTION_TABLE = "Question_Table";
    static final String COMMENTS_TABLE = "Comments_Table";


    static final int VersionN = 1;

    //Study Room Table columns
//public static final String COLUMN_PK_ROOM_ID = "PK_RoomID";
    public static final String COLUMN_COURSE = "Course";
//public static final String Fk_createdBy = "FK_createdBy";

    //Majors Table columns
    public static final String COLUMN_FK_ROOM_MAJOR = "FK_RoomID";
    public static final String COLUMN_MAJORS = "Majors";

    //User Subscribed Rooms Table columns
    public static final String COLUMN_FK_USERid_ROOM = "Fk_userName";
    public static final String COLUMN_FK_ROOMid_USER = "Fk_subRoomID";

    //User Table columns
    public static final String COLUMN_PK_USERNAME = "PK_Username";
    public static final String COLUMN_FULLNAME = "fullname";
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
    public static final String COLUMN_FK_QUIZ_NAME = "FK_QuizName";

    //Comments Table columns
    public static final String COLUMN_PK_COMMENT_ID = "commentID";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_COMMENT_LIKED = "likesNum";
    public static final String COLUMN_FK_USERNAME_COMMENT = "FK_username";
    public static final String COLUMN_FK_DISCUSSION_NAME_COMMENT = "FK_discussionName";


    // Queries for creating the tables with their columns
    final String DB_userCREATE="CREATE TABLE "+ USER_TABLE +" ("
            + COLUMN_PK_USERNAME +" TEXT PRIMARY KEY, "
            + COLUMN_EMAIL +" TEXT NOT NULL, "
            + COLUMN_PASSWORD +" TEXT NOT NULL, "
            + COLUMN_STARS_NUM +" INTEGER)";

    final String DB_roomCREATE="CREATE TABLE "+ STUDY_ROOM_TABLE +" ("
            + COLUMN_COURSE +" TEXT PRIMARY KEY)";

    final String DB_majorCREATE="CREATE TABLE "+ MAJORS_TABLE +" ("
			+ COLUMN_FK_ROOM_MAJOR +" TEXT, "
            + COLUMN_MAJORS +" TEXT NOT NULL)";

    final String DB_subscribedRoomCREATE = "CREATE TABLE "+ SUB_ROOM_TABLE +" ("
			+ COLUMN_FK_USERid_ROOM +" TEXT, "
            + COLUMN_FK_ROOMid_USER +" TEXT)";

    final String DB_discussionCREATE = "CREATE TABLE " + DISCUSSION_TABLE + " ("
			+ COLUMN_DISCUSSION_NAME + " VARCHAR(100) PRIMARY KEY, "
			+ COLUMN_DISCUSSION_DESCRIPTION + " TEXT, "
			+ FK_ROOM_DISCUS_ID+ " VARCHAR(10))";

	final String DB_commentsCREATE = "CREATE TABLE "+ COMMENTS_TABLE +" ("
			+ COLUMN_PK_COMMENT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_COMMENT +" TEXT, "
			+ COLUMN_COMMENT_LIKED + " INTEGER, "
			+ COLUMN_FK_DISCUSSION_NAME_COMMENT + " VARCHAR(100), "
			+ COLUMN_FK_USERNAME_COMMENT + " VARCHAR(50))";

	final String DB_quizCREATE="CREATE TABLE "+ QUIZ_TABLE + " ("
			+ COLUMN_PK_QUIZ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
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
			+ COLUMN_FK_QUIZ_NAME + " VARCHAR(100))";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, VersionN);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the queries to create the tables
        db.execSQL(DB_roomCREATE);
        db.execSQL(DB_userCREATE);
        db.execSQL(DB_roomCREATE);
        db.execSQL(DB_discussionCREATE);
        db.execSQL(DB_quizCREATE);
        db.execSQL(DB_questionCREATE);
        db.execSQL(DB_commentsCREATE);
        db.execSQL(DB_majorCREATE);
        db.execSQL(DB_subscribedRoomCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + USER_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + STUDY_ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + DISCUSSION_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + QUIZ_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + COMMENTS_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + MAJORS_TABLE);
        db.execSQL("DROP TABLE IF EXIST"+ SUB_ROOM_TABLE);
        onCreate(db);
    }


    public long insertDisscussion(String name, String discription, String roomId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DISCUSSION_NAME, name);
        contentValues.put(COLUMN_DISCUSSION_DESCRIPTION, discription);
        contentValues.put(FK_ROOM_DISCUS_ID, roomId);
        return db.insert(DISCUSSION_TABLE, null, contentValues);
    }

    public int numOfLikes(int likes) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.rawQuery("SELECT * FROM " + COMMENTS_TABLE + " WHERE "
                + COLUMN_COMMENT_LIKED + " = '" + 1 + "'", null);

        return likes;
    }

	public Cursor getDiscussion(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
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
		Cursor cursor =  db.rawQuery( "SELECT * FROM " + COMMENTS_TABLE + " WHERE "
				+ COLUMN_FK_DISCUSSION_NAME_COMMENT + " ='" + discussionName +"'", null );
		return cursor;
	}

	public long insertcomment(String comment, String discussionName, String username){
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

    public boolean checkUsername (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM "+USER_TABLE+" WHERE "+COLUMN_PK_USERNAME+" = '"+username+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public long signup(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PK_USERNAME, username);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        return db.insert(USER_TABLE, null, contentValues);
    }

    public boolean login (String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM "+USER_TABLE+" WHERE "+COLUMN_PK_USERNAME+" = '"+username
				+"' AND "+ COLUMN_PASSWORD+" = '"+password+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() == 1){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

	public int changePass ( String username,String password ){
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_PASSWORD,password);
		int count = db.update(USER_TABLE, cv,COLUMN_PK_USERNAME+"="+username, null);
		return count;
	}

	public long insertRoom(String courseName){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_COURSE, courseName);
		return db.insert(STUDY_ROOM_TABLE, null, contentValues);
	}

	public void insertMajors(String RoomID, ArrayList<String> majors){
		if ( !(majors.size() == 0)){
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			int length = majors.size();
			for (int i=0 ; i<length ; i++){
				contentValues.put(COLUMN_FK_ROOM_MAJOR, RoomID);
				contentValues.put(COLUMN_MAJORS, majors.get(i));
				db.insert(MAJORS_TABLE, null, contentValues);
			}
		}
	}

	public boolean checkRoom (String Course) {
		SQLiteDatabase db = this.getReadableDatabase();
		String Query = "SELECT * FROM "+STUDY_ROOM_TABLE+" WHERE "+COLUMN_COURSE+" = '"+Course+"'";
		Log.d("Query",Query);
		Cursor cursor = db.rawQuery(Query, null);
		if(cursor.getCount() == 0){
			cursor.close();
			return true;
		}
		cursor.close();
		return false;
	}

	public void test1(String test) {
		Log.d("Query: ",test);
	}

	public List<String> getSubscribedRoom(String username){
		List<String> subRoom = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor =  db.rawQuery( "SELECT * FROM " + SUB_ROOM_TABLE
				+ " WHERE " + COLUMN_FK_USERid_ROOM + "= '" + username + "'", null );
		cursor.moveToFirst();
		while(cursor.isAfterLast() == false){
			subRoom.add(cursor.getString(cursor.getColumnIndex(COLUMN_FK_ROOMid_USER)));
			cursor.moveToNext();
		}
		return subRoom;
	}

    public Cursor getEmail (String username) {
		SQLiteDatabase db = this.getReadableDatabase();
		String Query = "SELECT "+COLUMN_EMAIL+" FROM "+USER_TABLE+" WHERE "+COLUMN_PK_USERNAME+" = '"+username+"'";
		Cursor cursor = db.rawQuery(Query, null);
		return cursor;
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
							   int pos, String quizName){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_QUESTION, question);
		contentValues.put(COLUMN_ANSWER_1,ans1);
		contentValues.put(COLUMN_ANSWER_2,ans2);
		contentValues.put(COLUMN_ANSWER_3,ans3);
		contentValues.put(COLUMN_ANSWER_4,ans4);
		contentValues.put(COLUMN_CORRECT_ANSWER_POSITION, pos);
		contentValues.put(COLUMN_FK_QUIZ_NAME,quizName);
		Log.d("insertQuestion",question + " / " + ans1+ " / " +ans2+ " / " +ans3+ " / " +ans4+ " / " +pos+ " / " +quizName);
		return db.insert(QUESTION_TABLE, null, contentValues);
	}

	public Cursor getQuestions(String QuizName){
		SQLiteDatabase db = this.getReadableDatabase();
		return db.rawQuery( "SELECT * FROM " + QUESTION_TABLE +" WHERE "
				+ COLUMN_FK_QUIZ_NAME +" = '"+ QuizName +"'", null);
	}

    public String getLastAddedRoom(String roomName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ STUDY_ROOM_TABLE +" ORDER BY "+ COLUMN_COURSE
				+" DESC LIMIT 1", null);
        return cursor.getString(0);
    }

    public long insertSubRoom(String username, String RoomName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FK_USERid_ROOM, username);
        contentValues.put(COLUMN_FK_ROOMid_USER, RoomName);
        return db.insert(SUB_ROOM_TABLE, null, contentValues);
    }

}


