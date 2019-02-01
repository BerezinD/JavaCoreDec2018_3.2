package by.epam.localeresourcebundle;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final static String LANGUAGE_KEY = "LANGUAGE_KEY";
    private SharedPreferences preferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        preferences = getPreferences(MODE_PRIVATE);
        String preferencesString = preferences.getString(LANGUAGE_KEY, "en");
        if (preferencesString.equals("ru")){
            menu.getItem(1).setChecked(true);
        } else {
            menu.getItem(0).setChecked(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        preferences = getPreferences(MODE_PRIVATE);
        switch (item.getItemId()){
            case R.id.select_eng:
                preferences.edit().putString(LANGUAGE_KEY, "en").apply();
                item.setChecked(true);
                recreate();
                return true;

            case R.id.select_ru:
                preferences.edit().putString(LANGUAGE_KEY, "ru").apply();
                item.setChecked(true);
                recreate();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getPreferences(MODE_PRIVATE);
        String lang = preferences.getString(LANGUAGE_KEY, "en");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = getApplicationContext().getResources().getConfiguration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        final String[] questions = getResources().getStringArray(R.array.questions);
        final String[] answers = getResources().getStringArray(R.array.answers);

        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_item);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.answer)
                        .setCancelable(false)
                        .setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                switch (i){
                    case 0: builder.setMessage(answers[0]); builder.create().show(); break;
                    case 1: builder.setMessage(answers[1]); builder.create().show(); break;
                    case 2: builder.setMessage(answers[2]); builder.create().show(); break;
                    case 3: builder.setMessage(answers[3]); builder.create().show(); break;
                    case 4: builder.setMessage(answers[4]); builder.create().show(); break;
                    case 5: builder.setMessage(answers[5]); builder.create().show(); break;
                    case 6: builder.setMessage(answers[6]); builder.create().show(); break;
                    case 7: builder.setMessage(answers[7]); builder.create().show(); break;
                    case 8: builder.setMessage(answers[8]); builder.create().show(); break;
                }
            }
        });
    }
}