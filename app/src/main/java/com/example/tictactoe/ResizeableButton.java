package com.example.tictactoe;

import android.content.Context;
import android.widget.Button;

public class ResizeableButton extends Button {
    public ResizeableButton(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }
}
