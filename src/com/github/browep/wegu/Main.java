package com.github.browep.wegu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends Activity
{
    LinearLayout mLinearLayout;

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

        super.onCreate(savedInstanceState);

		// http://i.imgur.com/pOBdK.jpg
	    // Create a LinearLayout in which to add the ImageView
	    mLinearLayout = new LinearLayout(this);

	    // download and store to SD card
//	    try{
//	    	final InputStream is = new URL("http://i.imgur.com/pOBdK.jpg").openStream();
//	    }catch(IOException e){
//	    	e.printStackTrace(System.err);
//	    }


	    // Instantiate an ImageView and define its properties
	    ImageView i = new ImageView(this);
//        i.setImageResource(R.drawable.two);
	    i.setImageDrawable(getDrawableFromUrl("http://i.imgur.com/pOBdK.jpg"));
	    i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
	    i.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));

	    // Add the ImageView to the layout and set the layout as the content view
	    mLinearLayout.addView(i);
	    setContentView(mLinearLayout);
    }
}
