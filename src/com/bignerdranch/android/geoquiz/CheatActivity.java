package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
    
    public static final String EXTRA_ANSWER_IS_TRUE =
        "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
        "com.bignerdranch.android.geoquiz.answer_shown";
    
    public static final String KEY_IS_CHEATER = "IS_CHEATER";

    private boolean mAnswerIsTrue;
    private boolean mIsCheater = false;
    
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsCheater);
        setResult(RESULT_OK, data);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER);
        }
        
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        
        if (mIsCheater) {
            showAnswer();
        }
        
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                mIsCheater = true;
                showAnswer();
            }
        });
    }
    
    protected void showAnswer() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
        setAnswerShownResult();
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_IS_CHEATER, mIsCheater);
    }
    
}
