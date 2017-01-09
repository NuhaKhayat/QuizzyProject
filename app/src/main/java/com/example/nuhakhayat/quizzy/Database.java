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
    public static final String COLUMN_FK_QUIZ_CREATED_BY = "FK_QuizBy";
    public static final String COLUMN_FK_ROOM_QUIZ = "FK_RoomQuiz";

    //Question Table columns
    public static final String COLUMN_PK_QUESTION_ID = "PK_QuestionID";
    public static final String COLUMN_QYESTION = "Question";
    public static final String COLUMN_ANSWER_1 = "Answer1";
    public static final String COLUMN_ANSWER_2 = "Answer2";
    public static final String COLUMN_ANSWER_3 = "Answer3";
    public static final String COLUMN_CORRECT_ANSWER = "CorrectAnswer";
    public static final String COLUMN_FK_QUIZ_ID = "FK_QuizID";

    //Comments Table columns
    public static final String COLUMN_PK_COMMENT_ID = "PK_CommentID";
    public static final String COLUMN_COMMENT = "Comment";
    public static final String COLUMN_LIKES_NUM = "LikesNum";
    public static final String COLUMN_FK_COMMENTED_BY = "FK_CommentedBy";
    public static final String COLUMN_FK_ON_DISCUSSION = "FK_OnDiscussion";

    // Studies_Quizzes Table columns
    public static final String COLUMN_FK_QUIZ_ROOM_ID = "FK_RoomID";
    public static final String COLUMN_FK_ROOM_QUIZ_ID = "FK_RoomQuizID";

    //Queries for creating the tables with their columns
    final String DB_userCREATE = "CREATE TABLE " + USER_TABLE + " ("
            + COLUMN_PK_USERNAME + " text primary key,"
            + COLUMN_FULLNAME + " text not null,"
            + COLUMN_EMAIL + " text not null,"
            + COLUMN_PASSWORD + " text not null,"
            + COLUMN_STARS_NUM + " integer)";

    /*final String DB_roomCREATE="CREATE TABLE "+ STUDY_ROOM_TABLE +" ("
            + COLUMN_PK_ROOM_ID +" integer primary key autoincrement,"
            + COLUMN_COURSE +" text not null)";

    final String DB_majorCREATE="CREATE TABLE "+ MAJORS_TABLE +" ("+ COLUMN_FK_ROOM_MAJOR +" integer,"
            + COLUMN_MAJORS +" text not null)";


    final String DB_discussionCREATE = "CREATE TABLE " + DISCUSSION_TABLE + "(" + 
            COLUMN_DISCUSSION_NAME + " VARCHAR(100) PRIMARY KEY, "+
            COLUMN_DISCUSSION_DESCRIPTION + " TEXT, "+
            FK_ROOM_DISCUS_ID+ " VARCHAR(10))";///*



    
/*    final String DB_quizCREATE="CREATE TABLE "+ QUIZ_TABLE +" ("
            + COLUMN_PK_QUIZ_ID +" integer primary key autoincrement,"
            +COLUMN_QUIZ_TITLE+" text,"
            + COLUMN_FK_ROOM_QUIZ + " integer not null,"
            + COLUMN_FK_QUIZ_CREATED_BY + " text)";

    final String DB_questionCREATE="CREATE TABLE "+ QUESTION_TABLE +" ("
            + COLUMN_PK_QUESTION_ID +" integer primary key autoincrement,"
            +COLUMN_QYESTION+" text not null,"
            +COLUMN_ANSWER_1+ " text,"
            +COLUMN_ANSWER_2+ " text,"
            +COLUMN_ANSWER_3+ " text,"
            + COLUMN_CORRECT_ANSWER + " text,"
            + COLUMN_FK_QUIZ_ID + " integer not null";*/

    final String DB_commentsCREATE="CREATE TABLE "+ COMMENTS_TABLE +" ("
            + COLUMN_PK_COMMENT_ID +" integer primary key autoincrement,"
            + COLUMN_COMMENT +" text,"
            + COLUMN_LIKES_NUM + " integer,"
            + COLUMN_FK_ON_DISCUSSION + " integer not null,"
            + COLUMN_FK_COMMENTED_BY + " text)";

    /*final String DB_roomQuizesCREATE="CREATE TABLE "+ STUDY_ROOM_QUIZ_TABLE +" ("
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
//        db.execSQL(DB_discussionCREATE);
//		  Log.d("Database",DB_discussionCREATE);
//        db.execSQL(DB_quizCREATE);
//        db.execSQL(DB_roomQuizesCREATE);
//        db.execSQL(DB_questionCREATE);
//        db.execSQL(DB_commentsCREATE);
//        db.execSQL(DB_majorCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + USER_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + STUDY_ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + DISCUSSION_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + QUIZ_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + STUDY_ROOM_QUIZ_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + COMMENTS_TABLE);
        db.execSQL("DROP TABLE IF EXIST" + MAJORS_TABLE);
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
    public int numOfLikes(int likes)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.rawQuery("SELECT * FROM " + COMMENTS_TABLE + " WHERE "
                + COLUMN_LIKES_NUM + " = '" + 1 + "'", null);

        return likes;

    }

    public Cursor getDiscussion(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("getDiscussion", "SELECT * FROM " + DISCUSSION_TABLE + " WHERE " + COLUMN_DISCUSSION_NAME + " = '" + name + "'");
        return db.rawQuery("SELECT * FROM " + DISCUSSION_TABLE + " WHERE "
                + COLUMN_DISCUSSION_NAME + " = '" + name + "'", null);
    }

    public List<String> getAllDiscussions() {
            List<String> discussion = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + DISCUSSION_TABLE, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                discussion.add(cursor.getString(cursor.getColumnIndex(COLUMN_DISCUSSION_NAME)));
                cursor.moveToNext();
            }
        return discussion;
    }
    public List<String> getAllCourses() {
        List<String> course = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + STUDY_ROOM_TABLE, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            course.add(cursor.getString(cursor.getColumnIndex(COLUMN_COURSE)));
            cursor.moveToNext();
        }
        return course;
    }

    public int updateProfile(String username, String fullname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PK_USERNAME,username);
        cv.put(COLUMN_FULLNAME, fullname);
        cv.put(COLUMN_EMAIL, email);
        String selection = COLUMN_PK_USERNAME+" = ?";
        String[] selectionArgs = { String.valueOf(username) };
        //db.update(USER_TABLE, cv,COLUMN_PK_USERNAME+"="+username, null);
        int count = db.update(USER_TABLE, cv, selection, selectionArgs);
        return count;

    }
    public int changePass ( String username,String password ){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD,password);
        //String selection = COLUMN_PK_USERNAME+" = ?";
        //String[] selectionArgs = { String.valueOf(username) };
        int count = db.update(USER_TABLE, cv,COLUMN_PK_USERNAME+"="+username, null);
        return count;
    }


}


