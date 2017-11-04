package com.example.cryptocurrencyconverter.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.others.BaseCurrencyData;
import com.example.cryptocurrencyconverter.others.GeneralDialogFragment;
import com.example.cryptocurrencyconverter.others.SpinnerAdapter;

import java.util.ArrayList;

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor on 11/3/17.
 * This class implements a dialog fragment that enables user to select data from a spinner
 */

public class BaseCurrencyDialogFragment extends GeneralDialogFragment {
    String currency;
    String [] currency_titles;

    public static BaseCurrencyDialogFragment newInstance(String [] list) {

        Bundle args = new Bundle();
        args.putStringArray(LIST, list);

        BaseCurrencyDialogFragment fragment = new BaseCurrencyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BaseCurrencyDialogFragment() {
        super.alertDialogLayout = R.layout.fragment_base_currency_chooser;
    }

    @Override
    protected void initializeWidgets(View view) {
        // initialize widgets
        Button okButton = view.findViewById(R.id.base_currency_button);
        Spinner spinner = view.findViewById(R.id.base_currency_spinner);
        if (getArguments() != null) currency_titles = getArguments().getStringArray(LIST);

        // set up adapter for given custom row layout
        ArrayList<BaseCurrencyData> list = new ArrayList<>();
        // set currency symbols and images based on available choice
        for (String currency_title : currency_titles) {
            if (currency_title.contains(" "))
                currency_title = currency_title.replace(" ", "_");
            list.add(new BaseCurrencyData(currency_title,
                    activity.getResources()
                            .getIdentifier("icons_" + currency_title.toLowerCase(),
                                    "drawable",
                                    activity.getPackageName())));
        }
        SpinnerAdapter adapter = new SpinnerAdapter(activity,
                R.layout.row, R.id.base_currency_text, list);
        // set adapter to spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup viewGroup = (ViewGroup) view;

                TextView textView = viewGroup.findViewById(R.id.base_currency_text);

                currency = textView.getText().toString();

                Log.e(TAG, "Currency -> " + currency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { currency = ""; }
        });


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "Returning -> " + currency);
                sendResult(Activity.RESULT_OK, currency);
                exitFragment();
            }
        });

        setAlertDialogTitle(R.string.base_currency_title);
    }

    /*
    * Gets the selected currency from spinner and passes it to CryptoCurrencyFragment
    */
    void sendResult(int resultCode, String currency){
        if (!(getTargetFragment() == null)) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_CURRENCY, currency);
            getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
        }
    }
}
