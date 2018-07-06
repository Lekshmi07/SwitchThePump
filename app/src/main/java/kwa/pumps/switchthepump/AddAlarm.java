package kwa.pumps.switchthepump;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAlarm extends AppCompatActivity {


    TimePicker setTime;
    Button bt_ON,bt_OFF;
    EditText Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        final Context context = getApplicationContext();

        bt_ON=findViewById(R.id.ON);
        bt_OFF=findViewById(R.id.OFF);

        Calendar now=Calendar.getInstance();
        setTime=findViewById(R.id.PickTime);
        setTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        setTime.setCurrentMinute(now.get(Calendar.MINUTE));


        try {
            bt_ON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Phone = findViewById(R.id.Phone);
                    Calendar cal = Calendar.getInstance();

                    cal.set(Calendar.HOUR_OF_DAY, setTime.getCurrentHour());
                    cal.set(Calendar.MINUTE, setTime.getCurrentMinute());
                    Toast.makeText(context, "Alarm is set @" + cal.getTime(), Toast.LENGTH_SHORT).show();

                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


                    Intent myIntent = new Intent(context, AlarmReceiver.class);

                    String PhNo = Phone.getText().toString()+",1";
                    myIntent.putExtra("Number", PhNo);
                    myIntent.putExtra("flag", 1);
                    int alarmID = (int) cal.getTimeInMillis();
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, myIntent, 0);


                    assert manager != null;
                    //manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                    manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

                    Toast.makeText(context, "Shift set", Toast.LENGTH_SHORT).show();
                }
            });

            bt_OFF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Phone = findViewById(R.id.Phone);
                    Calendar cal = Calendar.getInstance();

                    cal.set(Calendar.HOUR_OF_DAY, setTime.getCurrentHour());
                    cal.set(Calendar.MINUTE, setTime.getCurrentMinute());
                    Toast.makeText(context, "Alarm is set @" + cal.getTime(), Toast.LENGTH_SHORT).show();

                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


                    Intent myIntent = new Intent(context, AlarmReceiver.class);

                    String PhNo = Phone.getText().toString()+",2";
                    myIntent.putExtra("Number", PhNo);

                    int alarmID = (int) cal.getTimeInMillis();
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmID, myIntent, 0);


                    assert manager != null;
                    manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


                    Toast.makeText(context, "Shift set", Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch (NullPointerException e) {
            Toast.makeText(this, "Null value", Toast.LENGTH_SHORT).show();
        }


    }
}