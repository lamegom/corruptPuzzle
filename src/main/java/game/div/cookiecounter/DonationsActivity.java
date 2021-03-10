///*
// * Copyright (C) 2011-2015 Dominik Schürmann <dominik@dominikschuermann.de>
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package game.div.cookiecounter;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import game.div.monymony.R;
//
//public class DonationsActivity extends FragmentActivity {
//
//    /**
//     * Google
//     */
//    private static final String GOOGLE_PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnHxyVzjrLDkwcklSia2Fu4c0K+1eyALzUnntIEXB9ggCqbZEYcT1+eeRl7ZZs8U6gBNse2yLcbcHwwNo2IndidoWum9mwGPhpmxJmTLvJNijgYOlX49bvWIwq0LbyY9x7ZwueX1llHe3bXJ2X445DcKHItNzhY8hPbxydLu0VocctTJ5ik4BlXsMAQsFoBPIHqHK2Ku/17ePOb8bPma1TzBGW70At3rKbuJBEKboJQH6FEhPVHAgfBRibnvunL5me6j2xKppKFonI4sgUKq9kPk4ZHYDlvkKKsVuwKrU1R4Dt0eppEMP89LCHmollW2PnnkaSMbaytLWjQwQCGqdWwIDAQAB";
//    private static final String[] GOOGLE_CATALOG = new String[]{"ntpsync.donation.1",
//            "ntpsync.donation.2", "ntpsync.donation.3", "ntpsync.donation.5", "ntpsync.donation.8",
//            "ntpsync.donation.13"};
//
////    /**
////     * PayPal
////     */
////    private static final String PAYPAL_USER = "dominik@dominikschuermann.de";
////    private static final String PAYPAL_CURRENCY_CODE = "EUR";
////
////    /**
////     * Flattr
////     */
////    private static final String FLATTR_PROJECT_URL = "https://github.com/dschuermann/android-donations-lib/";
////    // FLATTR_URL without http:// !
////    private static final String FLATTR_URL = "flattr.com/thing/712895/dschuermannandroid-donations-lib-on-GitHub";
////
////    /**
////     * Bitcoin
////     */
////    private static final String BITCOIN_ADDRESS = "1CXUJDMaXNed69U42okCxeMyiGHjboVw1j";
//
//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.donations_activity);
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////        DonationsFragment donationsFragment;
////
////            donationsFragment = DonationsFragment.newInstance(false, true, GOOGLE_PUBKEY, GOOGLE_CATALOG,
////                    getResources().getStringArray(R.array.donation_google_catalog_values), false, null, null,
////                    null, false, null, null, false, null);
////
////
////        ft.replace(R.id.donations_activity_container, donationsFragment, "donationsFragment");
//        ft.commit();
//    }
//
//    /**
//     * Needed for Google Play In-app Billing. It uses startIntentSenderForResult(). The result is not propagated to
//     * the Fragment like in startActivityForResult(). Thus we need to propagate manually to our Fragment.
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag("donationsFragment");
//        if (fragment != null) {
//            fragment.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//}
