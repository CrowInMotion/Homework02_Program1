package com.example.homework02_program1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<SavedColor> listOfColors;

    //overloaded constructor
    public ColorListAdapter(Context c, ArrayList<SavedColor> lc)
    {
        context = c;
        listOfColors = lc;
    }

    @Override
    public int getCount()
    {
        return listOfColors.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.colorlistviewcell, null);
        }

        TextView redText = view.findViewById(R.id.tv_v_cellRed);
        TextView greenText = view.findViewById(R.id.tv_v_cellGreen);
        TextView blueText = view.findViewById(R.id.tv_v_cellBlue);
        TextView hexText = view.findViewById(R.id.tv_v_cellHex);
        View cView = view.findViewById(R.id.v_v_cellView);

        SavedColor savedColor = listOfColors.get(i);

        int curRed = savedColor.getRed();
        int curGreen = savedColor.getGreen();
        int curBlue = savedColor.getBlue();
        String curHexStr = savedColor.getHexColorStr();
        Drawable curDrawable = savedColor.getColor();

        cView.setBackground(curDrawable);

        redText.setText("Red: " + curRed);
        greenText.setText("Green: " + curGreen);
        blueText.setText("Blue: " + curBlue);
        hexText.setText("Hex: " + curHexStr);

        if (curRed < 125 || curGreen < 125 || curBlue < 125)
        {
            if (curRed < 125 && curGreen < 125)
            {
                redText.setTextColor(Color.WHITE);
                greenText.setTextColor(Color.WHITE);
                blueText.setTextColor(Color.WHITE);
                hexText.setTextColor(Color.WHITE);
            }
            else
            {
                redText.setTextColor(Color.BLACK);
                greenText.setTextColor(Color.BLACK);
                blueText.setTextColor(Color.BLACK);
                hexText.setTextColor(Color.BLACK);
            }
        }

        else
        {
            redText.setTextColor(Color.BLACK);
            greenText.setTextColor(Color.BLACK);
            blueText.setTextColor(Color.BLACK);
            hexText.setTextColor(Color.BLACK);
        }

        return view;
    }
}
