package com.stephen.ground;

import android.content.Context;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AnimationView extends SurfaceView implements Runnable, SurfaceHolder.Callback{
	
	private Thread animation = null;
	private boolean running;
	int height;
	int width;
	Point centre;
	//private Bitmap spaceShip;
	
/*	protected boolean rightThrusterFiring;
	protected boolean leftThrusterFiring;
	protected boolean mainRocketFiring;*/
	
/*	private int shipXPos = 0;
	private int shipYPos = 0;*/
	
	
	public AnimationView(Context context) {
		super(context);
		//spaceShip = BitmapFactory.decodeResource(getResources(), R.drawable.craftmain);		 
		getHolder().addCallback(this);
		/*height = this.getHeight();
		width = this.getWidth();*/
		//groundCreate();
/*		rightThrusterFiring = false;
		leftThrusterFiring = false;
		mainRocketFiring = false;*/
		//(SurfaceView) findViewById(R.id.animationView1)
		//getHolder().addCallback(super.getApplicationContext());
	}
	
	public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //spaceShip = BitmapFactory.decodeResource(getResources(), R.drawable.craftmain);
        getHolder().addCallback(this);
        /*height = this.getHeight();
		width = this.getWidth();*/
		//groundCreate();
/*		rightThrusterFiring = false;
		leftThrusterFiring = false;
		mainRocketFiring = false;*/
        // TODO Auto-generated constructor stub
    }

 /*   public AnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        spaceShip = BitmapFactory.decodeResource(getResources(), R.drawable.craftmain);
        getHolder().addCallback(this);
        // TODO Auto-generated constructor stub
   } */

	
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
		while(running)
		{
			Canvas canvas = null;
			SurfaceHolder holder = getHolder();
			
			
			synchronized(holder)
			{
				
				/*if(rightThrusterFiring){
					shipXPos-=6;
				}
				if(leftThrusterFiring){
					shipXPos+=6;
				}
				
				if(mainRocketFiring){
					if(verticalThrust <= 1){
						verticalThrust += 1;
					}
					shipYPos -= verticalThrust;
					if(gravity > 3){
						gravity -= verticalThrust;
					}
					//shipYPos += gravity;
				}
				else{
					verticalThrust = 0;
					
					if( gravity < 8) {					
						gravity++;
					}
					shipYPos += gravity;
				}*/

				
				canvas = holder.lockCanvas();								
				canvas.scale(1, 1);
				canvas.drawColor(Color.BLACK);
				groundCreate();
				//canvas.drawBitmap(spaceShip, shipXPos, shipYPos, null);

			}
			
			/*try 
			{
				Thread.sleep(40);
			}		catch(InterruptedException e)
			{
				e.printStackTrace();
			}*/
			holder.unlockCanvasAndPost(canvas);			
		}		
	}
	
	private Point groundCreate() {
		height = this.getHeight();
		width = this.getWidth();

		Point centreT = null;
		centreT.set(width / 2, height /2);
		
		
		
		return centreT;
	}
	
	
	
/*	protected boolean getRightThruster() {
		rightThrusterFiring = true;
		return rightThrusterFiring;
	}
	
	protected boolean leftThruster() {
		leftThrusterFiring = true;
		return leftThrusterFiring;
	}
	
	protected boolean mainRocket() {
		mainRocketFiring = true;
		return mainRocketFiring;
	}*/
	
}

