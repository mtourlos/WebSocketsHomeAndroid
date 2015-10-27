package com.example.websocketshome;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends Activity {
	private WebSocketClient mWebSocketClient;

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
	
	private void connectWebSocket() {
		  URI uri;
		  try {
		    uri = new URI("ws://websockethost:8080");
		  } catch (URISyntaxException e) {
		    e.printStackTrace();
		    return;
		  }

		  mWebSocketClient = new WebSocketClient(uri) {
			  
		    @Override
		    public void onOpen(ServerHandshake serverHandshake) {
		      Log.i("Websocket", "Opened");
		      //mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
		    }

		    @Override
		    public void onMessage(String s) {
		      final String message = s;
		      /*runOnUiThread(new Runnable() {
		        @Override
		        public void run() {
		          TextView textView = (TextView)findViewById(R.id.messages);
		          textView.setText(textView.getText() + "\n" + message);
		        }
		      });*/
		      Log.i("Websocket", "messageReceived");
		    }

		    @Override
		    public void onClose(int i, String s, boolean b) {
		      Log.i("Websocket", "Closed " + s);
		    }

		    @Override
		    public void onError(Exception e) {
		      Log.i("Websocket", "Error " + e.getMessage());
		    }
		  };
		  mWebSocketClient.connect();
		}

}
