package com.campos.bianca.remediosnocanada;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.medicine_LV);
        ImageButton imgButton = (ImageButton) findViewById(R.id.favoriteButton);
        mContext = this;

        Adapter mDbHelper = new Adapter(mContext);
        mDbHelper.createDatabase();
        ArrayList<Medicine> testdata = mDbHelper.getTestData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mySearchMenu = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) mySearchMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
