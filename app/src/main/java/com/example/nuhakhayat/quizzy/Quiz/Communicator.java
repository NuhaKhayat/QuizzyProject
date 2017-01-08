package com.example.nuhakhayat.quizzy.Quiz;

/**
 * Created by NuhaKhayat on 1/8/17 AD.
 */
public interface Communicator {
	public void onQuestionAdd(String q, String a1, String a2, String a3, String a4, int pos);

	public void onQuizAdd(String title, int numOfq);
}
