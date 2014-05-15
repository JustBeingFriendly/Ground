package com.stephen.ground;

import java.lang.reflect.Array;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AnimationView extends SurfaceView implements Runnable, SurfaceHolder.Callback{
	
	private Thread animation = null;
	private boolean running;
	float height;
	float width;
	Point centre;
	Paint paint = new Paint();
	
	float bottomThirdY;
	
	float x[] = {  0,200,190,218,260,275,298,309,327,336,368,382,448,462,476,498,527,1200,1200,  0,  0};
	float y[]=  {616,540,550,605,605,594,530,520,520,527,626,636,636,623,535,504,481, 481, 750,750,616};
	
	//DisplayMetrics dm;
	
	public AnimationView(Context context) {
		super(context);
		//dm = new DisplayMetrics();		 
		getHolder().addCallback(this);
		/*height = this.getHeight();
		width = this.getWidth();*/

	}
	
	public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
      //  dm = new DisplayMetrics();
        getHolder().addCallback(this);
/*        height = this.getHeight();
		width = this.getWidth();*/
    }


	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format,
			int width, int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// start the animation thread once the surface has been created		
		animation = new Thread(this);
		running = true;		
		animation.start(); // start a new thread to handle this activities animation		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		running = false;
		if(animation != null)
		{
			try {
				animation.join();  // finish the animation thread and let the animation thread die a natural death
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public void run() {
/*		shipYPos =  50;
		int gravity = 0;
		int verticalThrust = 0;*/
		//Paint paint = new Paint(Color.BLACK);
		//paint.setStrokeWidth(5);
		while(running)
		{
			Canvas canvas = null;
			SurfaceHolder holder = getHolder();

			synchronized(holder){
				//convertDpArrayToPx();
				canvas = holder.lockCanvas();
				canvas.drawColor(Color.WHITE);
				groundCreate(canvas);
				//canvas.scale(1, 1);
				//canvas.drawColor(Color.WHITE);
				/////////////////////////////////////
				/*
				Path path = new Path();
				path.moveTo(x[0], y[0]);
				
				for(int i = 0; i <x.length; i++){
					path.lineTo(x[i], y[i]);
				}
				paint.setColor(Color.RED);
				canvas.drawPath(path, paint);
				*/
				////////////////////////////////////
				//canvas.scale(1, 1);
				//Paint paint = new Paint(Color.BLACK);
				//paint.setStrokeWidth(5);
				/*paint.setColor(Color.RED);
				canvas.drawPath(path, paint);*/
				//canvas.drawColor(Color.WHITE);
				//groundCreate();
			}
			
			holder.unlockCanvasAndPost(canvas);			
		}		
	}
		
	//int x[] = {  0,200,190,218,260,275,298,309,327,336,368,382,448,462,476,498,527,1200,1200,  0,  0};
	//int y[]=  {616,540,550,605,605,594,530,520,520,527,626,636,636,623,535,504,481, 481, 750,750,616};
	
/*	private void terrainManipulation(){
		height = this.getHeight();
		width = this.getWidth();
		float thirdHeight = height /3;
		bottomThirdY = (int) (thirdHeight * 2);
	}*/
	
	private void groundCreate(Canvas canvas) {
		
		height = this.getHeight();
		width = this.getWidth();
		float thirdHeight = height /3;
		bottomThirdY =  (thirdHeight * 2);
		
		//Path lastPath = new Path();
		Path path = new Path();
		path.moveTo(0, bottomThirdY);
		
		
		/*for(int i = 0; i <x.length; i++){
			path.lineTo(x[i], y[i]);
		}*/
		path.lineTo(0, bottomThirdY); //Start position
		//HillFeature(path);

		path.lineTo(width, bottomThirdY); //Draw to right 
		path.lineTo(width, height); //Draw to bottom right 
		path.lineTo(0, height);  //Draw to bottom left
		path.lineTo(0, bottomThirdY); //Draw to start position
		paint.setColor(Color.RED);
		canvas.drawPath(path, paint);
		
		HillFeature1(canvas);
		ValleyFeature1(canvas);
		InversePyramidFeature1(canvas);
		PyramidFeature1(canvas);
		/*Path path = new Path();
		path.moveTo(x[0], y[0]);
		
		for(int i = 0; i <x.length; i++){
			path.lineTo(x[i], y[i]);
		}

		paint.setColor(Color.RED);
		canvas.drawPath(path, paint);*/
		
		
		/*height = this.getHeight();
		width = this.getWidth();
		
		float thirdHeight = height /3;
		float bottomThirdY = thirdHeight * 2;*/
		//float cWidth = width /2;
		
		//float tHeight = convertPxtoDp(cHeight);
		//float tWidth = convertPxtoDp(cWidth);
		
		
		
	}
	
	 private void HillFeature1(Canvas canvas){
		float radius = 200;
		//float circleX = radius;
		canvas.drawCircle(radius, bottomThirdY, radius, paint);
	}
	
	private void ValleyFeature1(Canvas canvas){
		float radius = 200;
		paint.setColor(Color.WHITE);
		canvas.drawCircle(width - radius, bottomThirdY, radius, paint);
	}
	
	private void PyramidFeature1(Canvas canvas){
				Path path2 = new Path();
				path2.moveTo(800, bottomThirdY);		
				path2.lineTo(700, (bottomThirdY - 200));
				path2.lineTo(600, bottomThirdY);
				path2.lineTo(800, bottomThirdY);
				paint.setColor(Color.RED);
				canvas.drawPath(path2, paint);
				
			}
	
	private void InversePyramidFeature1(Canvas canvas){
/*		float xPos = 400;
		float pyramidLength = width / 10;
		Path path2 = new Path();
		xPos += pyramidLength;
		path2.moveTo(xPos , bottomThirdY);
		xPos -= pyramidLength / 2;		
		path2.lineTo(xPos, (bottomThirdY + pyramidLength));
		xPos -= pyramidLength /2 ;		
		path2.lineTo(xPos, bottomThirdY);
		xPos += pyramidLength;
		path2.setLastPoint(xPos , bottomThirdY);*/
		
		Path path2 = new Path();
		path2.moveTo(600, bottomThirdY);		
		path2.lineTo(500, (bottomThirdY + 200));
		path2.lineTo(400, bottomThirdY);
		path2.lineTo(600, bottomThirdY);
		paint.setColor(Color.WHITE);
		//paint.setColor(Color.BLACK);
		canvas.drawPath(path2, paint);
		
	}
	
	private void HillFeature(Path path){
		
		//pathStart.addArc(new RectF(0,0,100,100), 180, 0);
		float [] xHill = {       (0),      ( 200),      (150),      (125)};
		float [] yHill = {(bottomThirdY - 400), (bottomThirdY - 375), (bottomThirdY - 350), (bottomThirdY - 325)};
		//Path pathEnd = null;
		for(int i = 0; i < xHill.length; i++){
			path.lineTo(xHill[i], yHill[i]);
		}
		/**
		 * Need a constant number running though to add and subtract numbers from along the path
		 */
		
		//return pathStart;
	}
	
	private float convertPxtoDp(float px){
		float dp = (px - 0.5f / this.getResources().getDisplayMetrics().density);
		return dp;
		//Resources res = this.getResources();
		//DisplayMetrics dm = this.getResources().getDisplayMetrics();
	}
	
	private float convertDpToPx(float dp){
		float px = (dp * this.getResources().getDisplayMetrics().density + 0.5f);
		return px;
		/*float scale = this.getResources().getDisplayMetrics().density;
		return (dp * scale + 0.5f);*/
	}
	
	private int[] convertDpArrayToPx(){
		int []ar = new int[y.length];
		
		for(int i = 0; i < ar.length; i++){
			ar[i] = (int) convertDpToPx(y[i]);
		}
			
		return ar;
	}
}

