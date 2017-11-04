package com.example.cryptocurrencyconverter.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptocurrencyconverter.R;

import static com.example.cryptocurrencyconverter.others.NetworkingClass.GET;
import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor mungai on 10/29/17.
 * This class will convert one currency value to another using prevail exchange rates
 */

public class ConversionFragment extends Fragment {

    String currency1, currency2, crypto_symbol, curr_symbol;
    Float exchange_rate;
    boolean tapped = false;
    TextView currency1TextView;
    TextView currency2TextView;
    EditText currency1EditText;
    EditText currency2EditText;
    TextWatcher currency1TextWatcher;
    String [] currency_titles;
    String [] currency_symbols;

    public static ConversionFragment newInstance(String currency1, String currency2, Float exchange_rate,
                             String crypto_symbol, String currency_symbol,
                             String [] currency_titles, String [] currency_symbols) {

        Bundle args = new Bundle();
        args.putString(FIRST_CURRENCY, currency1);
        args.putString(SECOND_CURRENCY, currency2);
        args.putFloat(EXCHANGE_RATE, exchange_rate);
        args.putString(CRYPTO_SYMBOL, crypto_symbol);
        args.putString(CURRENCY_SYMBOL, currency_symbol);
        args.putStringArray(TITLES, currency_titles);
        args.putStringArray(CODES, currency_symbols);

        ConversionFragment fragment = new ConversionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CURRENCY && resultCode == Activity.RESULT_OK){
            Log.e(TAG, "Received");
            currency1 = data.getStringExtra(EXTRA_CURRENCY);
            for (int i = 0; i < currency_titles.length; i++)
                if (currency1.equals(currency_titles[i])) {
                    crypto_symbol = currency_symbols[i];
                    break;
                }
            String url = "https://min-api.cryptocompare.com/data/price?fsym=" + crypto_symbol +
                    "&tsyms=" + curr_symbol;
            GET(url, getActivity(), currency2EditText, curr_symbol);
            exchange_rate = Float.parseFloat(currency2EditText.getText().toString());
            Toast.makeText(getContext(),""+exchange_rate, Toast.LENGTH_SHORT).show();
            currency1EditText.setText(String.valueOf(1));
            currency1TextView.setText(currency1);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversion, container, false);

        // initialize widgets
        currency1TextView = view.findViewById(R.id.conversion_currency1_text_view);
        currency2TextView = view.findViewById(R.id.conversion_currency2_text_view);
        currency1EditText = view.findViewById(R.id.conversion_currency1_edit_view);
        currency2EditText = view.findViewById(R.id.conversion_currency2_edit_view);

        if (getArguments() != null){
            currency1 = getArguments().getString(FIRST_CURRENCY);
            currency2 = getArguments().getString(SECOND_CURRENCY);
            exchange_rate = getArguments().getFloat(EXCHANGE_RATE);
            crypto_symbol = getArguments().getString(CRYPTO_SYMBOL);
            curr_symbol = getArguments().getString(CURRENCY_SYMBOL);
            currency_titles = getArguments().getStringArray(TITLES);
            currency_symbols = getArguments().getStringArray(CODES);
        }

        currency1EditText.setText(String.valueOf(1));
        currency2EditText.setText(String.valueOf(1 * exchange_rate));
        currency1TextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    currency2EditText.setText(currency2);
                    currency1EditText.setText(String.valueOf(0));
                } else {
                    String result = String.valueOf(Double.parseDouble(editable.toString()) * exchange_rate);
                    currency2EditText.setText(result);
                }
            }
        };

        currency1EditText.addTextChangedListener(currency1TextWatcher);

        currency1TextView.setText(currency1);
        currency2TextView.setText(currency2);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // populate Menu instance with items defined in file
        inflater.inflate(R.menu.fragment_conversion_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            // interchange currencies
            case R.id.menu_item_invert_currencies:

                if (!tapped) {
                    currency1TextView.setText(currency2);
                    currency2TextView.setText(currency1);
                    tapped = true;
                } else {
                    currency1TextView.setText(currency1);
                    currency2TextView.setText(currency2);
                    tapped = false;
                }

                exchange_rate = 1 / exchange_rate;
                currency1EditText.setText(String.valueOf(1));
                currency2EditText.setText(String.valueOf(1 * exchange_rate));

                return true;

            case R.id.menu_item_edit_currency:

                FragmentManager fm = getFragmentManager();
                BaseCurrencyDialogFragment dialog = BaseCurrencyDialogFragment.newInstance(currency_titles);
                // create connection btn fragments by making CryptoCurrencyFragment
                // the target of BaseCurrencyDialogFragment
                dialog.setTargetFragment(ConversionFragment.this, REQUEST_CURRENCY);
                assert fm != null;
                dialog.show(fm, INTERNET_PICKER);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
