package com.example.cryptocurrencyconverter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.activities.ConventionalCurrencyActivity;
import com.example.cryptocurrencyconverter.activities.CryptoCurrencyActivity;
import com.example.cryptocurrencyconverter.activities.DigitalCurrencyActivity;
import com.example.cryptocurrencyconverter.activities.HomePageActivity;
import com.example.cryptocurrencyconverter.others.OnBottomReachedListener;

/**
 * Created by victor on 11/5/17.
 * This abstract class is the parent class for the various fragments;
 */

public abstract class GeneralFragmentClass extends Fragment implements OnBottomReachedListener{
    View view;
    ImageView home_button, currency_button, digital_button, crypto_currency_button;
    LinearLayout menu_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout
        view = inflater.inflate(R.layout.fragment_crypto_currency, container, false);

        // inflate widgets
        initCollapsingToolbar();
        initializeWidgets();

        return view;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     * Also contains the bottom menu layout buttons which enable navigation between the fragments
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

        menu_layout = view.findViewById(R.id.menu_linear_layout);
        home_button = view.findViewById(R.id.menu_home_image_button);
        currency_button = view.findViewById(R.id.menu_currency_image_button);
        digital_button = view.findViewById(R.id.menu_digital_currency_image_button);
        crypto_currency_button = view.findViewById(R.id.menu_crypto_to_currency_image_button);

        // start fragment for home page
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });

        // start fragment for converting between digital and conventional currencies
        crypto_currency_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CryptoCurrencyActivity.class);
                startActivity(intent);
            }
        });

        // start fragment for conversion between conventional currencies
        currency_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConventionalCurrencyActivity.class);
                startActivity(intent);
            }
        });

        // start fragment for conversion between digital currencies
        digital_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DigitalCurrencyActivity.class);
                startActivity(intent);
            }
        });
    }

    public abstract void initializeWidgets();

    @Override
    public void onBottomReached(int position) {
        menu_layout.setVisibility(View.GONE);
    }

    @Override
    public void onBottomNotReached(int position) {
        menu_layout.setVisibility(View.VISIBLE);
    }
}
