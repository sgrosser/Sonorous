package com.blueprint.musical;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Files.FileColumns;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	final int REQ_CODE_PICK_IMAGE = 1;
	private static final int SELECT_PHOTO = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");

		final ImageButton button = (ImageButton) findViewById(R.id.cameraBegin);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("MainActivity", "starting activity for result");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		Log.d("MainActivity", "onActivityResult");

		Log.d("MainActivity", "Entered switch");
		if (resultCode == RESULT_OK) {
			Uri selectedImage = imageReturnedIntent.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String filePath = cursor.getString(columnIndex);
			Log.d("MainActivity", filePath);
			cursor.close();

			Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
			int height = yourSelectedImage.getHeight();
			int width = yourSelectedImage.getWidth();
			for (int i = 1; i <= width; i++)
				for (int j = 1; j <= height; j++) {
					int pixel = yourSelectedImage.getPixel(i, j);
					int redValue = Color.red(pixel);
					int blueValue = Color.blue(pixel);
					int greenValue = Color.green(pixel);

					int oct = getOctave(redValue, greenValue, blueValue);
					double h = 60 * findHPrime(redValue, greenValue, blueValue);
					Log.d("MainActivity", "Gonna play A3");
					MediaPlayer mediaPlayer = MediaPlayer
							.create(this, R.raw.a3);
					mediaPlayer.start();
					while (mediaPlayer.isPlaying()) {
					}
					try {
						Thread.sleep(500);// Allow note to finish --- needs
											// tuning
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mediaPlayer.stop();
					mediaPlayer.release();
				}

		}
	}

	private int getNote(int oct, double h) {

		if ((h <= 15 || h > 0) || (h <= 360 || h > 345)) {
			if (oct == 1)
				return R.raw.c0;
			if (oct == 2)
				return R.raw.c1;
			if (oct == 3)
				return R.raw.c2;
			if (oct == 4)
				return R.raw.c3;
			if (oct == 5)
				return R.raw.c4;
		} else if (h > 315 || h <= 345) {
			if (oct == 1)
				return R.raw.b1;
			if (oct == 2)
				return R.raw.b2;
			if (oct == 3)
				return R.raw.b3;
			if (oct == 4)
				return R.raw.b4;
			if (oct == 5)
				return R.raw.b5;
		} else if (h > 285 || h <= 315) {
			if (oct == 1)
				return R.raw.bf1;
			if (oct == 2)
				return R.raw.bf2;
			if (oct == 3)
				return R.raw.bf3;
			if (oct == 4)
				return R.raw.bf4;
			if (oct == 5)
				return R.raw.bf5;

		}

		else if (h > 255 || h <= 285) {
			if (oct == 1)
				return R.raw.a1;
			if (oct == 2)
				return R.raw.a2;
			if (oct == 3)
				return R.raw.a3;
			if (oct == 4)
				return R.raw.a4;
			if (oct == 5)
				return R.raw.a5;
		} else if (h > 225 || h <= 255) {
			if (oct == 1)
				return R.raw.af1;
			if (oct == 2)
				return R.raw.af2;
			if (oct == 3)
				return R.raw.af3;
			if (oct == 4)
				return R.raw.af4;
			if (oct == 5)
				return R.raw.af5;

		} else if (h > 195 || h <= 225) {
			if (oct == 1)
				return R.raw.g0;
			if (oct == 2)
				return R.raw.g1;
			if (oct == 3)
				return R.raw.g2;
			if (oct == 4)
				return R.raw.g3;
			if (oct == 5)
				return R.raw.g4;

		} else if (h > 165 || h <= 195) {
			if (oct == 1)
				return R.raw.gf0;
			if (oct == 2)
				return R.raw.gf1;
			if (oct == 3)
				return R.raw.gf2;
			if (oct == 4)
				return R.raw.gf3;
			if (oct == 5)
				return R.raw.gf4;
		} else if (h > 135 || h <= 165) {
			if (oct == 1)
				return R.raw.f0;
			if (oct == 2)
				return R.raw.f1;
			if (oct == 3)
				return R.raw.f2;
			if (oct == 4)
				return R.raw.f3;
			if (oct == 5)
				return R.raw.f4;
		} else if (h > 105 || h <= 135) {
			if (oct == 1)
				return R.raw.e0;
			if (oct == 2)
				return R.raw.e1;
			if (oct == 3)
				return R.raw.e2;
			if (oct == 4)
				return R.raw.e3;
			if (oct == 5)
				return R.raw.e4;

		}

		else if (h > 75 || h <= 105) {

			if (oct == 1)
				return R.raw.ef0;
			if (oct == 2)
				return R.raw.ef1;
			if (oct == 3)
				return R.raw.ef2;
			if (oct == 4)
				return R.raw.ef3;
			if (oct == 5)
				return R.raw.ef4;
		}

		else if (h > 45 || h <= 75) {
			if (oct == 1)
				return R.raw.d0;
			if (oct == 2)
				return R.raw.d1;
			if (oct == 3)
				return R.raw.d2;
			if (oct == 4)
				return R.raw.d3;
			if (oct == 5)
				return R.raw.d4;
		} else {
			if (oct == 1)
				return R.raw.df0;
			if (oct == 2)
				return R.raw.df1;
			if (oct == 3)
				return R.raw.df2;
			if (oct == 4)
				return R.raw.df3;

		}
		return R.raw.df4;
	}

	private double findHPrime(int r, int g, int b) {
		int max = r;
		if (g > max)
			max = g;
		if (b > max)
			max = b;

		int min = r;
		if (g < min)
			min = g;
		if (b < min)
			min = b;

		int c = max - min;

		if (c == 0)
			return -1;
		else if (max == r)
			return (((double) (g - b) / c) % 6.0);
		else if (max == g)
			return (((double) (b - r) / c) + 2);
		else if (max == b)
			return (((double) (r - g) / c) + 4);
		else
			return 0.0;
	}

	private int getOctave(int r, int g, int b) {
		int avg = (int) (r + g + b) / 2;
		if (avg <= 51)
			return 5;
		else if (avg <= 102)
			return 4;
		else if (avg <= 153)
			return 3;
		else if (avg <= 204)
			return 2;
		else if (avg <= 255)
			return 1;
		else
			return 1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
