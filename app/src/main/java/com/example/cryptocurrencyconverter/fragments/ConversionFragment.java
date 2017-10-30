package com.example.cryptocurrencyconverter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor on 10/29/17.
 * This class will convert one currency value to another using prevail exchange rates
 */

public class ConversionFragment extends Fragment {

    String currency1, currency2;
    Float exchange_rate;
    TextView currency1TextView;
    TextView currency2TextView;
    EditText currency1EditText;
    EditText currency2EditText;
    TextWatcher currency1TextWatcher;

    public static ConversionFragment newInstance(String currency1, String currency2, Float exchange_rate) {

        Bundle args = new Bundle();
        args.putString(FIRST_CURRENCY, currency1);
        args.putString(SECOND_CURRENCY, currency2);
        args.putFloat(EXCHANGE_RATE, exchange_rate);

        ConversionFragment fragment = new ConversionFragment();
        fragment.setArguments(args);
        return fragment;
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
        }

        currency1TextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                currency2EditText.setText(editable.toString());
            }
        };

        currency1EditText.addTextChangedListener(currency1TextWatcher);

        currency1TextView.setText(currency1);
        currency2TextView.setText(currency2);

        Toast.makeText(getContext(), currency1 + ", " + currency2 + ", " + exchange_rate, Toast.LENGTH_LONG)
                .show();

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
            case R.id.menu_item_invert_currencies:
                currency1TextView.setText(currency2);
                currency2TextView.setText(currency1);
                currency1EditText.setText("");
                currency2EditText.setText("");

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
