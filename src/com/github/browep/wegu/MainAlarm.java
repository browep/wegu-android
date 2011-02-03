package com.github.browep.wegu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.browep.wegu.util.Log;

import javax.print.attribute.standard.Media;
import java.beans.Visibility;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MainAlarm extends WeguActivity
{
    LinearLayout mLinearLayout;
    Vibrator v;
    MediaPlayer mMediaPlayer;

    private static final String HOSTNAME = "http://wegu.heroku.com";
    private static final String NEXT_IMAGE_PATH = "/images/next";
    private Button offButton;

    private Drawable getDrawableFromUrl(String url) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

    public String convertStreamToString(InputStream is)
            throws IOException {
        /*
         * To convert the InputStream to String we use the
         * Reader.read(char[] buffer) method. We iterate until the
         * Reader return -1 which means there's no more data to
         * read. We use the StringWriter class to produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public String fetchContents(String address) {
        try {
            InputStream is = (InputStream) this.fetch(address);
            return convertStreamToString(is);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }


    }

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Log.i("ALARM FIRE!!!!");


        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        // Turn on the screen unless we are being launched from the AlarmAlert
        // subclass.
        if (!getIntent().getBooleanExtra(Constants.SCREEN_OFF, false)) {
            win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        }

//        mLinearLayout = findViewById(R.layout.main);
        setContentView(R.layout.main);


	    // Instantiate an ImageView and define its properties
	    ImageView i = (ImageView) findViewById(R.id.image_view);
        String url = fetchContents(HOSTNAME + NEXT_IMAGE_PATH);
        i.setImageDrawable(getDrawableFromUrl(url));

        // setup the off button
        offButton = (Button) findViewById(R.id.alarm_off);
        offButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                v.cancel();
                mMediaPlayer.stop();
                offButton.setVisibility(View.INVISIBLE);
            }
        });

        // vibrate the phone

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        long[] pattern = {0, 500, 300, 500, 300, 1000, 300};
        v.vibrate(pattern, 0);

        // play sound

        try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(this, alert);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
