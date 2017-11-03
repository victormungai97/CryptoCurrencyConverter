package com.example.cryptocurrencyconverter.others;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptocurrencyconverter.R;

import java.util.ArrayList;

/**
 * Created by victor mungai on 10/28/17.
 * This class extends the Array Adapter class as it will inflate the row layout
 * which provides a custom view for each row in the spinner
 * Courtesy of http://www.worldbestlearningcenter.com/tips/Android-Spinner-customized-to-show-image-and-text.htm
 */

public class SpinnerAdapter extends ArrayAdapter<BaseCurrencyData> {
    private int group_id;
    private ArrayList<BaseCurrencyData> list;
    private LayoutInflater inflater;

    public SpinnerAdapter(Activity context, int group_id, int id, ArrayList<BaseCurrencyData>
            list){
        super(context,id,list);
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.group_id = group_id;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent ){
        ViewHolder mViewHolder;

        if (convertView == null){
            mViewHolder = new ViewHolder();
            convertView = inflater.inflate(group_id, parent, false);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.currencyImageView = convertView.findViewById(R.id.base_currency_image);
        mViewHolder.currencyImageView.setImageResource(list.get(position).getImageId());
        mViewHolder.currencyTextView = convertView.findViewById(R.id.base_currency_text);
        mViewHolder.currencyTextView.setText(list.get(position).getText());

        return convertView;
    }

    public View getDropDownView(int position, View convertView, @NonNull ViewGroup
            parent){
        return getView(position,convertView,parent);

    }

    private static class ViewHolder {

        private TextView currencyTextView;
        private ImageView currencyImageView;
    }
}