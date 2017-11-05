package com.example.cryptocurrencyconverter.others;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptocurrencyconverter.R;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.cryptocurrencyconverter.others.NetworkingClass.GET;
import static com.example.cryptocurrencyconverter.others.Others.Constants.ERROR;

/**
 * Created by victor on 11/5/17.
 */

public class HomeLinearLayoutAdapter extends RecyclerViewAdapter {

    private Activity activity;
    private String [] currency_titles;
    private String [] currency_images;
    private String [] currencies_list;
    private String [] currencies_symbols;
    private Timer timer;

    public HomeLinearLayoutAdapter(Activity activity) {
        this.activity = activity;
        this.currency_titles = activity.getResources().getStringArray(R.array.crypto_currencies);
        this.currency_images = activity.getResources().getStringArray(R.array.crypto_currencies_symbols);
        this.currencies_list = activity.getResources().getStringArray(R.array.currencies);
        this.currencies_symbols = activity.getResources().getStringArray(R.array.currencies_symbols);
        timer = new Timer();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.fragment_home_page, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder myHolder = (Holder) holder;
        myHolder.baseCurrencyText.setText(currencies_list[position]);
        loadPictureToImageView(myHolder.digitalImageView, Others.Currencies.digital_currency_images[position], R.drawable.bitcoin);
        loadPictureToImageView(myHolder.currencyIcon, Others.Currencies.digital_icons[position], R.drawable.icons_bitcoin);
        final String url = "https://min-api.cryptocompare.com/data/generateAvg?fsym=" + currency_images[position]
                + "&tsym=" + currencies_symbols[position] + "&e=CCCAGG";

        final Handler handler = new Handler();
        // Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GET(url, activity, myHolder.priceCurrencyText, "PRICE");
                            GET(url, activity, myHolder.volumeCurrencyText, "VOLUME");
                        } catch (Exception ex){
                            Log.e(ERROR, "Something went wrong");
                            ex.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 50000);
    }

    @Override
    public int getItemCount() {
        return currency_titles.length;
    }

    /*
     * Method to successfully load image to view with right scale
     */
    private void loadPictureToImageView(ImageView view, int thumbnail, int placeholder) {
        try {
            Picasso.with(activity)
                    .load(thumbnail)
                    .placeholder(placeholder)
                    .error(placeholder).fit().centerInside().into(view);
        } catch (Exception ex) {
            Log.e("PICTURES "+ERROR, "Something went wrong");
            ex.printStackTrace();
        }
    }

    public class Holder extends RecycleViewHolder {

        ImageView digitalImageView;
        TextView baseCurrencyTitleText;
        TextView baseCurrencyText;
        TextView priceCurrencyTitleText;
        TextView priceCurrencyText;
        TextView volumeCurrencyTitleText;
        TextView volumeCurrencyText;
        ImageView currencyIcon;

        Holder(View itemView) {
            super(itemView);
            digitalImageView = itemView.findViewById(R.id.home_fragment_digital_currency_image);
            baseCurrencyTitleText = itemView.findViewById(R.id.home_fragment_base_currency_title_view);
            baseCurrencyText = itemView.findViewById(R.id.home_fragment_base_currency_text_view);
            priceCurrencyTitleText = itemView.findViewById(R.id.home_fragment_price_currency_title_view);
            priceCurrencyText = itemView.findViewById(R.id.home_fragment_price_currency_text_view);
            volumeCurrencyTitleText = itemView.findViewById(R.id.home_fragment_volume_currency_title_view);
            volumeCurrencyText = itemView.findViewById(R.id.home_fragment_volume_currency_text_view);
            currencyIcon = itemView.findViewById(R.id.home_fragment_currency_icon);
        }
    }
}
