package net.smulders.tesis.yuyeritaandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class HerbsActivity extends ActionBarActivity {

    private ListView list;
    private EditText inputSearch;
    private ArrayAdapter<Herb> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbs);

        list = (ListView) findViewById(R.id.listView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        final List<Herb> herbs = YuyeraSQLiteHelper.getAllHerbs(this);
        final Herb[] herbsArray = herbs.toArray(new Herb[herbs.size()]);
        adapter = new ArrayAdapter<Herb>(this,android.R.layout.simple_list_item_1,herbsArray);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Herb herb = adapter.getItem(position);
                Intent i = new Intent(HerbsActivity.this, HerbActivity.class);
                i.putExtra("id", herb.id);
                HerbsActivity.this.startActivity(i);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HerbsActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
