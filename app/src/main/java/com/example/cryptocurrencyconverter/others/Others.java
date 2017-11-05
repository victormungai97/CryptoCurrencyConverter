package com.example.cryptocurrencyconverter.others;

import com.example.cryptocurrencyconverter.R;

/**
 * Created by victor mungai on 10/28/17.
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
                R.drawable.turkish_lira, // Turkish Lira
                R.drawable.brazilian_real, // Brazilian real
                R.drawable.nigerian_naira, // Nigerian naira
                R.drawable.ic_add_currency, // add new currency
        };

        // array of resIds of images of digital currencies saved in drawable folder
        public static final int [] digital_currency_images = new int[] {
                R.drawable.bitcoin, // Bitcoin
                R.drawable.ethereum, // icons_ethereum
                R.drawable.litecoin, // Litecoin
                R.drawable.ripple, // icons_ripple(XRP)
                R.drawable.zcash, // Zcash
                R.drawable.monero, // monero
                R.drawable.nem, // NEM
                R.drawable.dash, // DASH
                R.drawable.decred, // Decred
                R.drawable.siacoin, // Siacoin
        };

        // array of resIds of icons of currencies
        public static int [] icons = new int[] {
                R.drawable.icons_us_dollar, // 0
                R.drawable.icons_euro, // 1
                R.drawable.icons_pound_sterling, // 2
                R.drawable.icons_swiss_franc, // 3
                R.drawable.icons_indian_rupee, // 4
                R.drawable.icons_japanese_yen, // 5
                R.drawable.icons_australian_dollar, // 6
                R.drawable.icons_canadian_dollar, // 7
                R.drawable.icons_kuwaiti_dinar, // 8
                R.drawable.icons_chinese_yuan, // 9
                R.drawable.icons_russian_ruble, // 10
                R.drawable.icons_new_zealand_dollar, // 11
                R.drawable.icons_south_african_rand, // 12
                R.drawable.icons_omani_rial, // 13
                R.drawable.icons_singapore_dollar, // 14
                R.drawable.icons_bahraini_dinar, // 15
                R.drawable.icons_hong_kong_dollar, // 16
                R.drawable.icons_turkish_lira, // 17
                R.drawable.icons_brazilian_real, // 18
                R.drawable.icons_nigerian_naira, // 19
        };

        // array of resIds of icons of digital currencies
        public static final int [] digital_icons = new int[] {
                R.drawable.icons_bitcoin, // 0
                R.drawable.icons_ethereum, // 1
                R.drawable.icons_litecoin, // 2
                R.drawable.icons_ripple, // 3
                R.drawable.icons_zcash, // 4
                R.drawable.icons_monero, // 5
                R.drawable.icons_nem, // 6
                R.drawable.icons_dash, // 7
                R.drawable.icons_decred, // 8
                R.drawable.icons_siacoin, // 9
        };
    }

    public class Constants {
        public static final String ERROR = "ERROR";
        public static final String TAG = "TAG";
        public static final String LIST_STATE_KEY = "state of list";
        public static final String FIRST_CURRENCY = "FIRST CURRENCY";
        public static final String SECOND_CURRENCY = "SECOND CURRENCY";
        public static final String EXCHANGE_RATE = "EXCHANGE RATE";
        static final int REQUEST_EXTERNAL_STORAGE = 2341;
        static final int REQUEST_MOBILE_NETWORK = 18395;
        public static final int REQUEST_CURRENCY = 2710;
        public static final String EXTRA_CURRENCY = "com.example.cryptocurrencyconverter.currencies";
        public static final String INTERNET_PICKER = "INTERNET_PICKER";
        public static final String LIST = "list";
        public static final String TITLES = "titles";
        public static final String SYMBOLS = "symbols";
        public static final String CODES = "codes";
        public static final String CRYPTO_SYMBOL = "crypto_symbol";
        public static final String CURRENCY_SYMBOL = "currency_symbol";
        public static final String CRYPTO_TO_CURRENCY = "crypto_to_currency";
        public static final String DIGITAL_CURRENCY = "DIGITAL CURRENCY";
    }
}
