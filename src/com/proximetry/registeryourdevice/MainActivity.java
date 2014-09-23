package com.proximetry.registeryourdevice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

	static final int GET_QR_CODE = 0;  // The request code
	static final String VER_STRING1 = "AirSync5";  // The request code

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/* Called when the user clicks the Register Your Device */
	public void getQRCode(View view) {
		
		TextView text1 = (TextView) findViewById(R.id.textView1);
		text1.setText("Loading QR Scanner App...");
				
		//show progress circle
		ProgressBar progBar = (ProgressBar)findViewById(R.id.progressBar1);
		progBar.setVisibility(View.VISIBLE);

		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		  if (scanResult != null) {
			  TextView devText = (TextView) findViewById(R.id.deviceText);
			  String [] result = scanResult.getContents().split("\n");
			  //verify if result comes from AirSync5 QR Code
			  if (result[0].equalsIgnoreCase(VER_STRING1)){
				  devText.setText(result[1]);
			  } else {
				  devText.setText("Unrecognized QR Code");
			  }
		  }
	}
		  

		

}
