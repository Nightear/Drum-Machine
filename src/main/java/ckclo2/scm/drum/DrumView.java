package ckclo2.scm.drum;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;


public class DrumView extends View {
    private Bitmap hihat;
    private int hihat_X;
    private int hihat_Y;
    private MediaPlayer mp_hihat;

    private Bitmap chinese;
    private int chinese_X;
    private int chinese_Y;
    private MediaPlayer mp_chinese;

    private Bitmap crash;
    private int crash_X;
    private int crash_Y;
    private MediaPlayer mp_crash;

    private Bitmap ride;
    private int ride_X;
    private int ride_Y;
    private MediaPlayer mp_ride;

    private Bitmap tom1;
    private int tom1_X;
    private int tom1_Y;
    private MediaPlayer mp_tom1;

    private Bitmap tom2;
    private int tom2_X;
    private int tom2_Y;
    private MediaPlayer mp_tom2;

    private Bitmap tom3;
    private int tom3_X;
    private int tom3_Y;
    private MediaPlayer mp_tom3;

    private Bitmap kick;
    private int kick_X;
    private int kick_Y;
    private MediaPlayer mp_kick;

    private Bitmap snare;
    private int snare_X;
    private int snare_Y;
    private MediaPlayer mp_snare;

    private float orientation [] = {0,0,0};

    private int plusW = 100;
    private int plusH = 100;


    public DrumView(Context context) {
        super(context);
        init();
    }

    public DrumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        hihat = BitmapFactory.decodeResource(getResources(),R.drawable.hihat);
        chinese = BitmapFactory.decodeResource(getResources(),R.drawable.chinese);
        crash = BitmapFactory.decodeResource(getResources(),R.drawable.crash);
        ride = BitmapFactory.decodeResource(getResources(),R.drawable.ride);
        tom1 = BitmapFactory.decodeResource(getResources(),R.drawable.drumhead);
        tom2 = BitmapFactory.decodeResource(getResources(),R.drawable.drumhead);
        tom3 = BitmapFactory.decodeResource(getResources(),R.drawable.drumhead);
        kick = BitmapFactory.decodeResource(getResources(),R.drawable.bass);
        snare = BitmapFactory.decodeResource(getResources(),R.drawable.snare);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);

                //hihat = getResizedBitmap(hihat, hihat_X,hihat_Y,getWidth(),getHeight());
                hihat = getResizedBitmap(hihat, hihat_X,hihat_Y, 300+plusW,300+plusH);

                chinese = getResizedBitmap(chinese, chinese_X,chinese_Y, 320+plusW,320+plusH);

                crash = getResizedBitmap(crash, crash_X,crash_Y, 300+plusW,300+plusH);

                ride = getResizedBitmap(ride, ride_X, ride_Y, 300+plusW, 300+plusH);

                tom1 = getResizedBitmap(tom1, tom1_X, tom1_Y, 250+plusW, 250+plusH);

                tom2 = getResizedBitmap(tom2, tom2_X, tom2_Y, 270+plusW, 270+plusH);

                tom3 = getResizedBitmap(tom3, tom3_X, tom3_Y, 300+plusW, 300+plusH);

                kick = getResizedBitmap(kick, kick_X, kick_Y, 300+plusW, 300+plusH);

                snare = getResizedBitmap(snare, snare_X, snare_Y, 300+plusW, 300+plusH);

                hihat_X=180;
                hihat_Y=720;


                chinese_X=1900;
                chinese_Y=730;


                crash_X=470;
                crash_Y=230;


                ride_X=1670;
                ride_Y=210;


                tom1_X=920;
                tom1_Y=370;


                tom2_X=1280;
                tom2_Y=370;


                tom3_X=1450;
                tom3_Y=750;


                kick_X=1030;
                kick_Y=790;


                snare_X=610;
                snare_Y=750;

    /*      Nexus 5 size

        hihat_X=110;
        hihat_Y=450;
        chinese_X=1380;
        chinese_Y=450;
        crash_X=320;
        crash_Y=50;
        ride_X=1200;
        ride_Y=35;
        tom1_X=640;
        tom1_Y=200;
        tom2_X=900;
        tom2_Y=200;
        tom3_X=1070;
        tom3_Y=480;
        kick_X=750;
        kick_Y=490;
        snare_X=440;
        snare_Y=480;
        */

            }
        });
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(hihat,hihat_X,hihat_Y,null);
        canvas.drawBitmap(chinese,chinese_X,chinese_Y,null);
        canvas.drawBitmap(crash,crash_X,crash_Y,null);
        canvas.drawBitmap(ride,ride_X,ride_Y,null);
        canvas.drawBitmap(tom1,tom1_X,tom1_Y,null);
        canvas.drawBitmap(tom2,tom2_X,tom2_Y,null);
        canvas.drawBitmap(tom3,tom3_X,tom3_Y,null);
        canvas.drawBitmap(kick,kick_X,kick_Y,null);
        canvas.drawBitmap(snare,snare_X,snare_Y,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int x1 = 0, y1 = 0;

        switch (action){

            case MotionEvent.ACTION_POINTER_DOWN:
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                x1 = (int) f.x;
                y1 = (int) f.y;
                break;

        }


        if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN){

            if ((x >= hihat_X && x < (hihat_X + hihat.getWidth())
                    && y >= hihat_Y && y < (hihat_Y + hihat.getHeight())) ||
                    (x1 >= hihat_X && x1 < (hihat_X + hihat.getWidth())
                    && y1 >= hihat_Y && y1 < (hihat_Y + hihat.getHeight()))){

                if(orientation[1] < 20 && orientation[1] > -20)
                    mp_hihat = MediaPlayer.create(DrumView.this.getContext(),R.raw.openhihat);
                else if(orientation[1] < -20 && orientation[1] > -65)
                    mp_hihat = MediaPlayer.create(DrumView.this.getContext(),R.raw.pedalhihat);
                else
                    mp_hihat = MediaPlayer.create(DrumView.this.getContext(),R.raw.closedhihat);
                if(mp_hihat.isPlaying()){
                    mp_hihat.stop();
                    mp_hihat.release();
                }
                mp_hihat.start();
                mp_hihat.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }


            if ((x >= crash_X && x < (crash_X + crash.getWidth())
                    && y >= crash_Y && y < (crash_Y + crash.getHeight())) ||
                    (x1 >= crash_X && x1 < (crash_X + crash.getWidth())
                            && y1 >= crash_Y && y1 < (crash_Y + crash.getHeight()))){


                mp_crash = MediaPlayer.create(DrumView.this.getContext(),R.raw.crash);
                if(mp_crash.isPlaying()){
                    mp_crash.stop();
                    mp_crash.release();
                }
                mp_crash.start();
                mp_crash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }


            if ((x >= ride_X && x < (ride_X + ride.getWidth())
                    && y >= ride_Y && y < (ride_Y + ride.getHeight())) ||
                    (x1 >= ride_X && x1 < (ride_X + ride.getWidth())
                            && y1 >= ride_Y && y1 < (ride_Y + ride.getHeight()))){

                mp_ride = MediaPlayer.create(DrumView.this.getContext(),R.raw.ride);
                if(mp_ride.isPlaying()){
                    mp_ride.stop();
                    mp_ride.release();
                }
                mp_ride.start();
                mp_ride.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= chinese_X && x < (chinese_X + chinese.getWidth())
                    && y >= chinese_Y && y < (chinese_Y + chinese.getHeight())) ||
                    (x1 >= chinese_X && x1 < (chinese_X + chinese.getWidth())
                            && y1 >= chinese_Y && y1 < (chinese_Y + chinese.getHeight()))){

                mp_chinese = MediaPlayer.create(DrumView.this.getContext(),R.raw.chinese);
                if(mp_chinese.isPlaying()){
                    mp_chinese.stop();
                    mp_chinese.release();
                }
                mp_chinese.start();
                mp_chinese.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= snare_X && x < (snare_X + snare.getWidth())
                    && y >= snare_Y && y < (snare_Y + snare.getHeight())) ||
                    (x1 >= snare_X && x1 < (snare_X + snare.getWidth())
                            && y1 >= snare_Y && y1 < (snare_Y + snare.getHeight()))){

                mp_snare = MediaPlayer.create(DrumView.this.getContext(),R.raw.snare);
                if(mp_snare.isPlaying()){
                    //mp_snare.stop();
                }
                mp_snare.start();
                mp_snare.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= tom1_X && x < (tom1_X + tom1.getWidth())
                    && y >= tom1_Y && y < (tom1_Y + tom1.getHeight())) ||
                    (x1 >= tom1_X && x1 < (tom1_X + tom1.getWidth())
                            && y1 >= tom1_Y && y1 < (tom1_Y + tom1.getHeight()))){

                mp_tom1 = MediaPlayer.create(DrumView.this.getContext(),R.raw.tom1);
                if(mp_tom1.isPlaying()){
                    mp_tom1.stop();
                    mp_tom1.release();
                }
                mp_tom1.start();
                mp_tom1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= tom2_X && x < (tom2_X + tom2.getWidth())
                    && y >= tom2_Y && y < (tom2_Y + tom2.getHeight())) ||
                    (x1 >= tom2_X && x1 < (tom2_X + tom2.getWidth())
                            && y1 >= tom2_Y && y1 < (tom2_Y + tom2.getHeight()))){

                mp_tom2 = MediaPlayer.create(DrumView.this.getContext(),R.raw.tom2);
                if(mp_tom2.isPlaying()){
                    mp_tom2.stop();
                    mp_tom2.release();
                }
                mp_tom2.start();
                mp_tom2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= tom3_X && x < (tom3_X + tom3.getWidth())
                    && y >= tom3_Y && y < (tom3_Y + tom3.getHeight())) ||
                    (x1 >= tom3_X && x1 < (tom3_X + tom3.getWidth())
                            && y1 >= tom3_Y && y1 < (tom3_Y + tom3.getHeight()))){

                mp_tom3 = MediaPlayer.create(DrumView.this.getContext(),R.raw.tom3);
                if(mp_tom3.isPlaying()){
                    mp_tom3.stop();
                    mp_tom3.release();
                }
                mp_tom3.start();
                mp_tom3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

            if ((x >= kick_X && x < (kick_X + kick.getWidth())
                    && y >= kick_Y && y < (kick_Y + kick.getHeight())) ||
                    (x1 >= kick_X && x1 < (kick_X + kick.getWidth())
                            && y1 >= kick_Y && y1 < (kick_Y + kick.getHeight()))){

                mp_kick = MediaPlayer.create(DrumView.this.getContext(),R.raw.kick);
                if(mp_kick.isPlaying()){
                    //mp_kick.stop();
                }
                mp_kick.start();
                mp_kick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }

        }

        return true;
    }

    private Bitmap getResizedBitmap(Bitmap bitmap,int left, int top, int reqWidth, int reqHeight){
        Matrix m = new Matrix();
        RectF src = new RectF(left, top, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(left, top, reqWidth, reqHeight);
        m.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap, left, top, bitmap.getWidth(), bitmap.getHeight(), m, true);

    }

    public void setOrientation(float [] _o){
        orientation[0] = _o[0];
        orientation[1] = _o[1];
        orientation[2] = _o[2];
    }
}
