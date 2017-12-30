package ckclo2.scm.drum;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Drum extends AppCompatActivity {

    private DrumView dv;

    private SensorManager mgr;
    private Sensor sensor_a;
    private Sensor sensor_m;
    private float[] values_a = new float[3];
    private float[] values_m = new float[3];
    private boolean aReady = false;
    private boolean mReady = false;



    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                values_a = event.values.clone();
                aReady = true;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                values_m = event.values.clone();
                mReady = true;
            }

            if (aReady && mReady) {
                calculateOrientation();
                aReady = mReady = false;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        sensor_a = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_m = mgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        dv = new DrumView(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(dv);



    }

    @Override
    protected void onResume() {
        if (sensor_a != null)
            mgr.registerListener(listener, sensor_a, SensorManager.SENSOR_DELAY_GAME);
        if (sensor_m != null)
            mgr.registerListener(listener, sensor_m, SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (sensor_a != null) mgr.unregisterListener(listener, sensor_a);
        if (sensor_m != null) mgr.unregisterListener(listener, sensor_m);
        super.onPause();
    }

    private void calculateOrientation() {
        float[] values = new float[3];
        float[] I = new float[9];
        float[] R = new float[9];
        float[] outR = new float[9];
        SensorManager.getRotationMatrix(R, I, values_a, values_m);
        SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_MINUS_X,SensorManager.AXIS_MINUS_Z,outR);
        SensorManager.getOrientation(outR, values);
        values[0] = (float) Math.toDegrees(-values[0]);
        values[1] = (float) Math.toDegrees(-values[1]);
        values[2] = (float) Math.toDegrees(values[2]);


        Log.d("Drum", " orientation: "
                + values[0] + ", " + values[1] + ", " + values[2]);

        dv.setOrientation(values);

    }
}
