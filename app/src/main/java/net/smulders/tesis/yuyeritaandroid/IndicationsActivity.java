package net.smulders.tesis.yuyeritaandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class IndicationsActivity extends ActionBarActivity {

    private ListView list;
    private EditText inputSearch;
    private ArrayAdapter<Indication> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indications);

        list = (ListView) findViewById(R.id.listView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        final List<Indication> indications = YuyeraSQLiteHelper.getAllIndications(this);
        final Indication[] indicationsArray = indications.toArray(new Indication[indications.size()]);
        adapter = new ArrayAdapter<Indication>(this, android.R.layout.simple_list_item_1, indicationsArray);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Indication indication = adapter.getItem(position);
                Intent i = new Intent(IndicationsActivity.this, IndicationActivity.class);
                i.putExtra("id", indication.id);
                IndicationsActivity.this.startActivity(i);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                IndicationsActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}