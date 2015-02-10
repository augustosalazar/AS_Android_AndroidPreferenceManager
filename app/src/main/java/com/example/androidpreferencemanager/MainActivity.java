package com.example.androidpreferencemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	protected SharedPreferences mSharedPreferences;
	protected String skey = "keyParametroTexto";
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
        String text = mSharedPreferences.getString(skey, "default Value");
        
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
                String text = mSharedPreferences.getString(skey, "default Value");
                mTextView.setText(text);
			}
		});
        
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(mMainActivity.getApplicationContext());

        // volvemos a buscar del archivo de preferencias.
        String text = mSharedPreferences.getString(skey, "default Value");
        mTextView.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
