//==============================================================================
//Author: Giovanni Cuadra
//Date  :
//Desc  : Homework01 - RGB Background Color Slider
//==============================================================================
package com.example.homework02_program1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    View mainView;
    Button btn_j_saveColor;
    TextView tv_j_red;
    TextView tv_j_green;
    TextView tv_j_blue;
    TextView tv_j_hex;
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;
    ListView lv_j_listOfColors;
    Drawable currentColor;
    Drawable newColor;
    ColorListAdapter clAdapter;

    private ArrayList<SavedColor> listOfColors;
    private Integer curRedVal;
    private Integer curGreenVal;
    private Integer curBlueVal;

    //Defined the color here so I can change it whenever
    private Integer defaultColorNum = Color.WHITE;
    private Integer currentColorNum;
    private String hexColorString;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Pulls each color value from the default color num
        curRedVal = Color.red(defaultColorNum);
        curGreenVal = Color.green(defaultColorNum);
        curBlueVal = Color.blue(defaultColorNum);

        //New color created using the default color num
        currentColor = new ColorDrawable(defaultColorNum);
        currentColorNum = defaultColorNum;

        //Background color is set to the default color
        mainView = findViewById(R.id.v_v_mainView);
        mainView.setBackground(currentColor);

        //This will make sure that newColor is never null
        newColor = currentColor;

        //Stores the hex version of the current color
        hexColorString = String.format("%08X", currentColorNum);

        //Variables are assigned
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);
        tv_j_red = findViewById(R.id.tv_v_red);
        tv_j_green = findViewById(R.id.tv_v_green);
        tv_j_blue = findViewById(R.id.tv_v_blue);
        sb_j_red = findViewById(R.id.sb_v_red);
        sb_j_green = findViewById(R.id.sb_v_green);
        sb_j_blue = findViewById(R.id.sb_v_blue);
        tv_j_hex = findViewById(R.id.tv_v_hex);
        lv_j_listOfColors = findViewById(R.id.lv_v_listOfColors);

        listOfColors = new ArrayList<>();

        //Hexadecimal text is displayed using the current color num
        tv_j_hex.setText(hexColorString);

        //Adjusts text colors based on default values
        changeTextColor(curRedVal, curGreenVal, curBlueVal);

        //For testing purposes only
        //addDummyDataToArrayList();

        //Event functions
        saveColorClickEvent();
        colorChangeEvent();

        clAdapter = new ColorListAdapter(this, listOfColors);
        lv_j_listOfColors.setAdapter(clAdapter);
    }

    //Called to change the color and edit text each time a scroll bar is moved
    public void modifyBackgroundColor(Integer r, Integer g, Integer b)
    {
        //Color text is set to match color number
        tv_j_red.setText("" + r);
        tv_j_green.setText("" + g);
        tv_j_blue.setText("" + b);

        //New color is made using the rgb values set on the scroll bars
        newColor = new ColorDrawable(Color.rgb(r, g, b));

        //Background color is changed based on the new color
        mainView.setBackground(newColor);

        //Current color and currentColorNum is updated to the new color
        currentColor = newColor;
        currentColorNum = ((ColorDrawable) mainView.getBackground()).getColor();

        //Hexadecimal text is updated using the current color num
        hexColorString = String.format("%06X", (0xFFFFFF & currentColorNum));
        tv_j_hex.setText(hexColorString);

        //Passes the current rgb values and changes text colors accordingly
        changeTextColor(r, g, b);
    }

    //Changes the text color to either black or white depending on passed color values
    private void changeTextColor(Integer r, Integer g, Integer b)
    {
        //Text views for RGB were separated from the display values, below grabs those values
        TextView tv_red = findViewById(R.id.textViewRed);
        TextView tv_green = findViewById(R.id.textViewGreen);
        TextView tv_blue = findViewById(R.id.textViewBlue);
        TextView tv_hex = findViewById(R.id.textViewHex);

        //Static values here are based on my personal preference
        //Checks if any of the values are below 125,
        if (r < 125 || g < 125 || b < 125)
        {
            //Only checks if red and green are below 125
            //I think that blue hues looked better with white text
            if (r < 125 && g < 125)
            {
                //Changes seekbars and all text (not in the listOfColors) to white
                tv_red.setTextColor(Color.WHITE);
                tv_green.setTextColor(Color.WHITE);
                tv_blue.setTextColor(Color.WHITE);
                tv_hex.setTextColor(Color.WHITE);
                tv_j_red.setTextColor(Color.WHITE);
                tv_j_green.setTextColor(Color.WHITE);
                tv_j_blue.setTextColor(Color.WHITE);
                tv_j_hex.setTextColor(Color.WHITE);
                sb_j_red.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
                sb_j_green.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
                sb_j_blue.setProgressTintList(ColorStateList.valueOf(Color.WHITE));

            }
            else
            {
                //Changes seekbars and all text (not in the listOfColors) to white
                tv_red.setTextColor(Color.BLACK);
                tv_green.setTextColor(Color.BLACK);
                tv_blue.setTextColor(Color.BLACK);
                tv_hex.setTextColor(Color.BLACK);
                tv_j_red.setTextColor(Color.BLACK);
                tv_j_green.setTextColor(Color.BLACK);
                tv_j_blue.setTextColor(Color.BLACK);
                tv_j_hex.setTextColor(Color.BLACK);
                sb_j_red.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
                sb_j_green.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
                sb_j_blue.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
            }
        }
        else
        {
            tv_red.setTextColor(Color.BLACK);
            tv_green.setTextColor(Color.BLACK);
            tv_blue.setTextColor(Color.BLACK);
            tv_hex.setTextColor(Color.BLACK);
            tv_j_red.setTextColor(Color.BLACK);
            tv_j_green.setTextColor(Color.BLACK);
            tv_j_blue.setTextColor(Color.BLACK);
            tv_j_hex.setTextColor(Color.BLACK);
            sb_j_red.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
            sb_j_green.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
            sb_j_blue.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        }
    }

    private void updateSeekBar(int cNum)
    {
        //Seekbars are updated using the clicked colorNum
        sb_j_red.setProgress(Color.red(cNum));
        sb_j_green.setProgress(Color.green(cNum));
        sb_j_blue.setProgress(Color.blue(cNum));
    }


    //---------------------EVENTS--------------------------
    //Called when the save color button is pressed
    private void saveColorClickEvent()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creates a new saved color and adds it to the list
                SavedColor c = new SavedColor(curRedVal, curGreenVal, curBlueVal, currentColorNum, hexColorString, currentColor);
                listOfColors.add(c);
                Log.d("Save", "Save Color Clicked: " + c.getHexColorStr());

                clAdapter.notifyDataSetChanged();
                modifyBackgroundColor(Color.red(defaultColorNum), Color.green(defaultColorNum), Color.blue(defaultColorNum));

                //Ensure that each bar progress is properly set to default
                updateSeekBar(defaultColorNum);
            }
        });
    }

    //When scroll bars are moved
    private void colorChangeEvent()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser)
            {
                //Color value is updated to match seekbar
                curRedVal = i;
                //Background color is updated accordingly
                modifyBackgroundColor(curRedVal, curGreenVal, curBlueVal);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        sb_j_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser)
            {
                curGreenVal = i;
                modifyBackgroundColor(curRedVal, curGreenVal, curBlueVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        sb_j_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser)
            {
                curBlueVal = i;
                modifyBackgroundColor(curRedVal, curGreenVal, curBlueVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        lv_j_listOfColors.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id)
            {
                //Stores the current item in the list that was clicked
                SavedColor clickedColor = listOfColors.get(i);

                //Sets the current rgb values to the stored values
                curRedVal = clickedColor.getRed();
                curGreenVal = clickedColor.getGreen();
                curBlueVal = clickedColor.getBlue();

                //Seekbars are updated using the clicked colorNum
                updateSeekBar(clickedColor.getColorNum());

                //The main background color is changed to the click color
                modifyBackgroundColor(curRedVal, curGreenVal, curBlueVal);
            }
        });

    }
}