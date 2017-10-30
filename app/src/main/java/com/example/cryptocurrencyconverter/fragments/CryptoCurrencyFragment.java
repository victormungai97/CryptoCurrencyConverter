package com.example.cryptocurrencyconverter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.activities.ConversionActivity;
import com.example.cryptocurrencyconverter.others.Currency;
import com.example.cryptocurrencyconverter.others.CurrencyGridLayoutAdapter;
import com.example.cryptocurrencyconverter.others.OnBottomReachedListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;
import static com.example.cryptocurrencyconverter.others.Others.Currencies.*;

/**
 * Created by victor mungai on 10/27/17.
 * This fragment will inflate the layout for conversion between currency and crypto currency, kinda
 * CREDITS
 * Card view: https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
 * Currency images: Google image search and https://icons8.com
 */

public class CryptoCurrencyFragment extends Fragment {

    List<Currency> currencies = new ArrayList<>();
    String currency1;
    GridLayoutManager mLayoutManager;
    CurrencyGridLayoutAdapter currencyGridLayoutAdapter;
    RecyclerView recyclerViewMain;
    Parcelable mListState;
    LinearLayout menu_layout;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout
        view = inflater.inflate(R.layout.fragment_crypto_currency, container,false);

        // inflate widgets
        initCollapsingToolbar();
        menu_layout = view.findViewById(R.id.menu_linear_layout);
        recyclerViewMain = view.findViewById(R.id.card_recycler_view);

        currency1 = "Bitcoin";

        setAdapter();
        setGridLayoutManager();

        // check if last element reached
        currencyGridLayoutAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                menu_layout.setVisibility(View.GONE);
            }

            @Override
            public void onBottomNotReached(int position) {
                menu_layout.setVisibility(View.VISIBLE);
            }
        });

        // set image for crypto currency to image view in collapsing toolbar
        Picasso.with(getContext())
                .load(R.drawable.bitcoin)
                .resize(300, 300)
                .centerInside()
                .into((ImageView) view.findViewById(R.id.backdrop));

        initializeCurrencies();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save recycler view state
        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // retrieve contents on screen before rotation
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();

        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = view.findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.backdrop_title));
                    collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Gets the images of currencies saved in drawable folder
     * and pass them to an instance of Currency class
     * which is then saved a list of Currencies
     */
    private void initializeCurrencies() {

        // create instance of Currency class and add to list
        currencies.add(new Currency("US dollar", currency_images[0], icons[0], "0.0"));
        currencies.add(new Currency("Euro", currency_images[1], icons[1]));
        currencies.add(new Currency("Pound sterling", currency_images[2], icons[2]));
        currencies.add(new Currency("Swiss franc", currency_images[3], icons[3]));
        currencies.add(new Currency("Dinar", currency_images[4]));
        currencies.add(new Currency("Indian rupee", currency_images[5], icons[4]));
        currencies.add(new Currency("Japanese yen", currency_images[6], icons[5]));
        currencies.add(new Currency("Australian dollar", currency_images[7], icons[6]));
        currencies.add(new Currency("Canadian dollar", currency_images[8], icons[7]));
        currencies.add(new Currency("Kuwaiti dinar", currency_images[9], icons[8]));
        currencies.add(new Currency("Chinese yuan", currency_images[10], icons[9]));
        currencies.add(new Currency("Russian ruble", currency_images[11], icons[10]));
        currencies.add(new Currency("New Zealand dollar", currency_images[12], icons[11]));
        currencies.add(new Currency("South African rand", currency_images[13], icons[12]));
        currencies.add(new Currency("Omani rial", currency_images[14], icons[13]));
        currencies.add(new Currency("Singapore dollar", currency_images[15], icons[14]));
        currencies.add(new Currency("Bahraini dinar", currency_images[16], icons[15]));
        currencies.add(new Currency("Hong Kong dollar", currency_images[17], icons[16]));
        currencies.add(new Currency("Brazilian real", currency_images[18], icons[17]));
        currencies.add(new Currency("Nigerian naira", currency_images[19], icons[18]));

    }

    /**
     * Used to create or update the adapter for the recycler view
     */
    private void setAdapter() {
        if (currencyGridLayoutAdapter == null) {
            currencyGridLayoutAdapter = new CurrencyGridLayoutAdapter(getActivity(), currencies);
            recyclerViewMain.setAdapter(currencyGridLayoutAdapter);
        } else {
            currencyGridLayoutAdapter.setImages(currencies);
            currencyGridLayoutAdapter.notifyDataSetChanged();
        }
    }

    private void setGridLayoutManager() {
        recyclerViewMain.setHasFixedSize(true); // tell RecyclerView that size will remain constant
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewMain.setLayoutManager(mLayoutManager);

        currencyGridLayoutAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RecyclerView.ViewHolder holder = recyclerViewMain.findViewHolderForAdapterPosition(i);
                CurrencyGridLayoutAdapter.Holder myHolder = (CurrencyGridLayoutAdapter.Holder) holder;
                String currency2 = myHolder.getCurrency_title().getText().toString();
                String exchange_rate = myHolder.getCurrency_exchange().getText().toString();
                Intent intent = ConversionActivity.newIntent(getContext(), currency1, currency2,
                        exchange_rate);
                if (intent != null) startActivity(intent);
            }
        });
        currencyGridLayoutAdapter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }
}
