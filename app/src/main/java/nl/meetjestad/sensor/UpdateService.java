package nl.meetjestad.sensor;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created for meetjestad. 1-2-2021
 *
 * Used file created by yutku on 29/11/16.
 * (see: https://medium.com/android-bits/android-widgets-ad3d166458d3)
 *
 *
 */
public class UpdateService extends Service {
    private ReadSensor reader = null;

    @Nullable
    @Override


    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setReader(ReadSensor reader) {
        this.reader = reader;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // generates random number
        Random random = new Random();
        int randomInt = random.nextInt(35);

        this.setReader(new ReadSensor("24"));
        if (reader != null) {
            String response = reader.getSensorReading();
            Log.d("UpdateService", response );
        }

        String lastUpdate = randomInt + "gr.C";
        // Reaches the view on widget and displays the number
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.toondata_widget);
        view.setTextViewText(R.id.tvWidget, lastUpdate);
        ComponentName theWidget = new ComponentName(this, ToonSensorData.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);

        return super.onStartCommand(intent, flags, startId);
    }
}
