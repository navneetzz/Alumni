package com.example.ashish.alumini;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class sanghosti extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider ;
    ScrollView mainScroll;
    TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanghosti);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("e53935")));
        mainScroll=(ScrollView)findViewById(R.id.scroll);
        Description = (TextView) findViewById(R.id.desc);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        //putting text in dscrption
        String text = "A National seminar on Technical Terminology in Engineering & Science by Government of India, Commission for Scientific and Technical Terminology,(Ministry of Human Resource Development Deptt. of higher education) was organized on 14 and 15 May, 2014 in the campus. The Chief Guest in the opening ceremony was Dr. Deepak Kumar, Senior Scientific officer. Well known luminaries from the field shared their views during the seminar.";
        Description.setText(text);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Arya Old Main Campus", R.drawable.san2);
        file_maps.put("Inside View", R.drawable.san3);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }


    public void wayToEvent(View v)
    {
        Uri uri = Uri.parse("http://www.aryacollege.in/national_sangosti.php");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Log.d("Slider Demo", slider.getBundle().get("extra") + "");
        Snackbar snackbar = Snackbar
                .make(mainScroll, "Slide to view more image", Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}

