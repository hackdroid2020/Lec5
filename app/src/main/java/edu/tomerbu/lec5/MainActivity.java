package edu.tomerbu.lec5;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.AlarmClock;
import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    private Button btnMessage;
    private Button btnSettings;
    private Button btnTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMessage = findViewById(R.id.button_message);
        btnSettings = findViewById(R.id.button_settings);
        btnTimer = findViewById(R.id.button_timer);

        btnMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "The Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello, Mom!");

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else {
                Toast.makeText(this, "No App can open", Toast.LENGTH_SHORT).show();
            }
        });

        btnSettings.setOnClickListener(v -> {
            //TODO: settings:
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Toast.makeText(this, "No Settings App", Toast.LENGTH_SHORT).show();
        });

        btnTimer.setOnClickListener(v -> {
            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);

            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Cake is ready!")
                    .putExtra(AlarmClock.EXTRA_LENGTH, 25)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true); //length in seconds


            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Toast.makeText(this, "No Timer", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
