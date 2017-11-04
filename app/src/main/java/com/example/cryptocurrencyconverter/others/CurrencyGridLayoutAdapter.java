package com.example.cryptocurrencyconverter.others;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptocurrencyconverter.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.cryptocurrencyconverter.fragments.CryptoCurrencyFragment.getCryptoSymbol;
import static com.example.cryptocurrencyconverter.others.NetworkingClass.GET;
import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor on 10/11/17.
 * Handles the arrangement of views in a grid layout format
 * Courtesy of the http://www.theappguruz.com/blog/learn-recyclerview-with-an-example-in-android
 */

public class CurrencyGridLayoutAdapter extends RecyclerViewAdapter {

    private Activity activity;
    private List<Currency> currencies;
    private int screenWidth;
    private Timer timer;
    private OnBottomReachedListener onBottomReachedListener;

    public CurrencyGridLayoutAdapter(Activity activity, List<Currency> currencies, Timer timer) {
        this.activity = activity;
        this.currencies = currencies;
        this.timer = timer;
        // get size of screen display using WindowManager and Point object class
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    /*
             * Used to create new ViewHolder, along with its returned View to display.
             * Called until a sufficient number of ViewHolders have been created,
             * after which the old ViewHolders are recycled, saving space and time
             */
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.grid_contents, parent, false);
        return new Holder(view);
    }

    /*
     * Used to display the data at the specified position.
     * It updates the content of the itemView to reflect the item at the given position.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder myHolder = (Holder) holder;
        final Currency currency = currencies.get(position);

        /* if last item reached, call bottom reached method */
        if (position == (currencies.size() - 1)) {
            onBottomReachedListener.onBottomReached(position);
        } else {
            onBottomReachedListener.onBottomNotReached(position);
        }

        myHolder.currency_title.setText(currency.getName());
        myHolder.currency_title.setTextColor(activity.getResources().getColor(R.color.black));
        String exchange = (currency.getExchange() != null) ? currency.getExchange() : activity.getString(R.string.exchange_rate);
        myHolder.currency_exchange.setText(exchange);
        loadPictureToImageView(myHolder.currency_image, currency.getThumbnail(), R.drawable.money_4);
        loadPictureToImageView(myHolder.currency_symbol, currency.getIcon(), R.drawable.icons_general_note);

        final String id = (!currency.getID().equals("")) ? currency.getID() : "";
        final String url = "https://min-api.cryptocompare.com/data/price?fsym=" + getCryptoSymbol() +
                "&tsyms=" + id;

        final Handler handler = new Handler();
        // Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GET(url, activity, myHolder.currency_exchange, id);
                        } catch (Exception ex){
                            Log.e(ERROR, "Something went wrong");
                            ex.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 50000);

        if (currency.getID().equals("")) {
            myHolder.currency_overflow.setVisibility(View.GONE);
            myHolder.currency_exchange.setText(activity.getString(R.string.exchange_rate));
        }

        // click listener for overflow menu
        myHolder.currency_overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(myHolder.currency_overflow);
            }
        });
    }

    /*
     * Returns the number of objects in list for preparation of space
     */
    @Override
    public int getItemCount() {
        Log.e("CURRENCIES "  + TAG, "No. of currencies -> " + currencies.size());
        return currencies.size();
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(activity, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_currency, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    private void loadPictureToImageView(ImageView view, int thumbnail, int placeholder) {
        try {
            Picasso.with(activity)
                    .load(thumbnail)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .resize(screenWidth / 2, 300)
                    .centerCrop()
                    .into(view);
        } catch (Exception ex) {
            Log.e("PICTURES "+ERROR, "Something went wrong");
            ex.printStackTrace();
        }
    }

    public class Holder extends RecycleViewHolder {
        ImageView currency_image;
        TextView currency_title;
        ImageView currency_overflow;
        TextView currency_exchange;
        ImageView currency_symbol;

        public TextView getCurrency_title() {
            return currency_title;
        }

        public TextView getCurrency_exchange() {
            return currency_exchange;
        }

        Holder(View itemView) {
            super(itemView);
            currency_image = itemView.findViewById(R.id.currency_image_view);
            currency_title = itemView.findViewById(R.id.currency_title_view);
            currency_overflow = itemView.findViewById(R.id.currency_overflow);
            currency_exchange = itemView.findViewById(R.id.currency_exchange_rate);
            currency_symbol = itemView.findViewById(R.id.currency_symbol);
        }
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {}

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){
                case R.id.action_change_background:
                    Toast.makeText(activity, "Changing currency image", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_remove_currency:
                    Toast.makeText(activity, "Removing currency", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }

        }
    }
}
