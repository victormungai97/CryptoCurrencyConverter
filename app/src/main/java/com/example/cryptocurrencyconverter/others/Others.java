package com.example.cryptocurrencyconverter.others;

import com.example.cryptocurrencyconverter.R;

/**
 * Created by victor on 10/28/17.
 * This class is used to hold constants and extras
 */

public class Others {

    public static class Currencies {
        // array of resIds of images of currencies saved in drawable folder
        public static final int [] currency_images = new int[] {
                R.drawable.us_dollar, // US Dollar
                R.drawable.euro, // Euro
                R.drawable.pound_sterling, // British Pound Sterling
                R.drawable.swiss_franc, // Swiss Franc
                R.drawable.dinar, // Dinar, used in 9 countries
                R.drawable.indian_rupee, // Indian rupee
                R.drawable.japanese_yen, // Japanese yen
                R.drawable.australian_dollar, // Australian dollar
                R.drawable.canadian_dollar, // Canadian dollar
                R.drawable.kuwaiti_dinar, // Kuwaiti dinar
                R.drawable.renminbi, // Chinese yuan
                R.drawable.russian_ruble, // Russian ruble
                R.drawable.nz_dollar, // New Zealand dollar
                R.drawable.sa_rand, // South African rand
                R.drawable.omani_rial, // Omani rial
                R.drawable.singapore_dollar, // Singapore dollar
                R.drawable.bahraini_dinar, // Bahraini dinar
                R.drawable.hong_kong_dollar, // Hong Kong dollar
                R.drawable.brazilian_real, // Brazilian real
                R.drawable.nigerian_naira, // Nigerian naira
        };

        // array of resIds of icons of currencies
        public static int [] icons = new int[] {
                R.drawable.icons_us_dollar, // 0
                R.drawable.icons_euro, // 1
                R.drawable.icons_british_pound, // 2
                R.drawable.icons_swiss_franc, // 3
                R.drawable.icons_rupee, // 4
                R.drawable.icons_japanese_yen, // 5
                R.drawable.icons_australian_dollar_filled, // 6
                R.drawable.icons_canadian_dollar, // 7
                R.drawable.icons_kuwaiti_dinar, // 8
                R.drawable.icons_renminbi, // 9
                R.drawable.icons_ruble, // 10
                R.drawable.icons_nz_dollar, // 11
                R.drawable.icons_rand, // 12
                R.drawable.icons_omani_rial, // 13
                R.drawable.icons_singapore_dollar, // 14
                R.drawable.icons_bahraini_dinar, // 15
                R.drawable.icons_hong_kong_dollar, // 16
                R.drawable.icons_brazilian_real, // 17
                R.drawable.icons_naira, // 18
        };
    }

    public class Constants {
        public static final String ERROR = "ERROR";
        public static final String LIST_STATE_KEY = "state of list";
        public static final String FIRST_CURRENCY = "FIRST CURRENCY";
        public static final String SECOND_CURRENCY = "SECOND CURRENCY";
        public static final String EXCHANGE_RATE = "EXCHANGE RATE";
    }
}
