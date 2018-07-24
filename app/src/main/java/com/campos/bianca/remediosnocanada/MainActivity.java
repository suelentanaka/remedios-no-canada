package com.campos.bianca.remediosnocanada;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private Context mContext;
    private ArrayList<Medicine> mMedicine;
    private ArrayAdapter<Medicine> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = (ListView) findViewById(R.id.medicine_LV);
        ImageButton imgButton = (ImageButton) findViewById(R.id.favoriteButton);
        mContext = this;

        DataBaseAdapter mDbHelper = new DataBaseAdapter(this);
        mDbHelper.createDatabase();
        mMedicine = (ArrayList<Medicine>) mDbHelper.getMainPage();

        loadListView();

    }

    private void loadListView() {
        listView = findViewById(R.id.medicine_LV);
        TextviewAdapter textviewAdapter = new TextviewAdapter(this, R.layout.list_layout, mMedicine);
        listView.setAdapter(textviewAdapter);

    }

    public void displayDetails(View view){
        startActivity(new Intent(this, DetailsActivity.class));
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
