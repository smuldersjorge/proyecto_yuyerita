package net.smulders.tesis.yuyeritaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class HerbActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herb);

        String herbId = getIntent().getExtras().getString("id");
        Herb h = YuyeraSQLiteHelper.getHerb(herbId,this);

        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(h.name);
        TextView tv4 = (TextView) findViewById(R.id.textView4);
        tv4.setText(h.properties);
        TextView tv6 = (TextView) findViewById(R.id.textView6);
        tv6.setText(h.posology);

        // Indicaciones
        final List<Indication> indications = YuyeraSQLiteHelper.getIndications(herbId,this);
        final LinearLayout layoutIndications = (LinearLayout) findViewById(R.id.layoutIndications);
        for(final Indication i : indications){
            TextView tv = new TextView(this);
            String tvText = i.name;
            SpannableString tvSpan = new SpannableString(tvText);
            tvSpan.setSpan(new UnderlineSpan(),0,tvText.length(),0);
            tv.setText(tvSpan);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setTextColor(Color.DKGRAY);
            tv.setTextSize(18);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HerbActivity.this, IndicationActivity.class);
                    intent.putExtra("id",i.id);
                    HerbActivity.this.startActivity(intent);
                }
            });
            layoutIndications.addView(tv);
        }

        // Formas de consumo
        final List<Ingest> ingests = YuyeraSQLiteHelper.getIngests(herbId,this);
        StringBuilder sbIngests = new StringBuilder();
        for(Ingest i : ingests){
            sbIngests.append(i);
            if(i != ingests.get(ingests.size() - 1)){
                sbIngests.append(", ");
            }
        }
        TextView tv10 = (TextView) findViewById(R.id.textView10);
        tv10.setText(sbIngests.toString());

        // Puestos de Venta
        final List<Place> places = YuyeraSQLiteHelper.getPlaces(herbId,this);
        final LinearLayout layoutPlaces = (LinearLayout) findViewById(R.id.layoutPlaces);
        for(final Place i : places){
            TextView tv = new TextView(this);
            String tvText = i.name;
            SpannableString tvSpan = new SpannableString(tvText);
            tvSpan.setSpan(new UnderlineSpan(),0,tvText.length(),0);
            tv.setText(tvSpan);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setTextColor(Color.DKGRAY);
            tv.setTextSize(18);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HerbActivity.this, PlaceActivity.class);
                    intent.putExtra("id",i.id);
                    HerbActivity.this.startActivity(intent);
                }
            });
            layoutPlaces.addView(tv);
        }

        int imageId = this.getResources().getIdentifier(h.image, "drawable", this.getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(imageId);
    }
}
