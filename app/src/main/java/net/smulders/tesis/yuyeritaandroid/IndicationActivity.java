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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class IndicationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indication);

        String indicationId = getIntent().getExtras().getString("id");
        Indication i = YuyeraSQLiteHelper.getIndication(indicationId,this);

        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(i.name);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText(i.description);

        // Hierbas
        final List<Herb> herbs = YuyeraSQLiteHelper.getHerbsByIndication(indicationId, this);
        final LinearLayout layoutHerbs = (LinearLayout) findViewById(R.id.layoutHerbs);
        for(final Herb h : herbs){
            TextView tv = new TextView(this);
            String tvText = h.name;
            SpannableString tvSpan = new SpannableString(tvText);
            tvSpan.setSpan(new UnderlineSpan(),0,tvText.length(),0);
            tv.setText(tvSpan);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setTextColor(Color.DKGRAY);
            tv.setTextSize(18);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IndicationActivity.this, HerbActivity.class);
                    intent.putExtra("id",h.id);
                    IndicationActivity.this.startActivity(intent);
                }
            });
            layoutHerbs.addView(tv);
        }
    }
}
