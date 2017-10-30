package com.example.cryptocurrencyconverter.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.cryptocurrencyconverter.fragments.ConversionFragment;

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

public class ConversionActivity extends MainFragmentActivity{


    @Override
    protected Fragment createFragment() {
        String currency1 = getIntent().getStringExtra(FIRST_CURRENCY);
        String currency2 = getIntent().getStringExtra(SECOND_CURRENCY);
        Float exchange_rate = getIntent().getFloatExtra(EXCHANGE_RATE, 0);
        return ConversionFragment.newInstance(currency1, currency2, exchange_rate);
    }

    public static Intent newIntent(Context packageContext, String currency1, String currency2,
                                   String exchange_rate){
        Intent intent = new Intent(packageContext, ConversionActivity.class);
        try {
            intent.putExtra(FIRST_CURRENCY, currency1);
            intent.putExtra(SECOND_CURRENCY, currency2);
            intent.putExtra(EXCHANGE_RATE, Float.parseFloat(exchange_rate));
        } catch (Exception ex){
            Log.e("CONVERSION "+ERROR, "Something went wrong");
            ex.printStackTrace();
            Toast.makeText(packageContext, "Cannot carry out conversion.\n No internet connection",
                    Toast.LENGTH_SHORT).show();
            return null;
        }

        return intent;
    }
}
