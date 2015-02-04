package com.example.androidpreferencemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected SharedPreferences mSharedPreferences;
	protected String skey = "PREF_CHECKBOX_STRING";
	protected TextView mTextView;
	protected EditText mEditText;
	protected Button mButton;
	protected MainActivity mMainActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        mTextView = (TextView) findViewById(R.id.textView1);
        mButton = (Button) findViewById(R.id.button1);
        mEditText = (EditText) findViewById(R.id.editText);
        
        mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext()
						.getApplicationContext());
        String text = mSharedPreferences.getString(skey, "default");
        
        mTextView.setText(text);
        
        mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SharedPreferences sharedPreferences = PreferenceManager
						.getDefaultSharedPreferences(mMainActivity.getApplicationContext());
				Editor editor = sharedPreferences.edit();
				editor.putString(skey, mEditText.getText().toString());
				editor.commit();
				Log.d("setOnClickListener","click");
                // volvemos a buscar del archivo de preferencias.
                String text = mSharedPreferences.getString(skey, "default");
                mTextView.setText(text);
			}
		});
        
    }
}
