package com.bhcnav.jialin.geoquiz;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    //private Button mNextButton;
    private TextView mQuestionTextView;
    //private Button mPrevButton;
    private ImageButton NextButton;
    private ImageButton PrevButton;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, false),
            new TrueFalse(R.string.question_mideast, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        Log.w(TAG, "this is a message!");
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        mFalseButton= (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){ //使用匿名内部类为false按键创建监听器
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        NextButton=(ImageButton)findViewById(R.id.next_button);
        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        PrevButton=(ImageButton)findViewById(R.id.prev_button);
        PrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mCurrentIndex == 0)
                    mCurrentIndex = mQuestionBank.length;
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });


        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstancesState){
        super.onSaveInstanceState(savedInstancesState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstancesState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //public boolean onCreateOptionsMenu(Menu menu){
    //    getMenuInflater().inflate(R.menu.activity_quiz, menu);
    //    return true;
    //}
}