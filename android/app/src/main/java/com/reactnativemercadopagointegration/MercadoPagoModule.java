// package com.reactnativemercadopagointegration;

// import android.app.Activity;
// import android.content.Intent;
// import android.support.annotation.NonNull;

// import com.facebook.react.bridge.ReactApplicationContext;
// import com.facebook.react.bridge.ReactContextBaseJavaModule;
// import com.facebook.react.bridge.ReactMethod;
// import com.facebook.react.bridge.Promise;

// import com.mercadopago.android.px.core.MercadoPagoCheckout;
// import com.mercadopago.android.px.preferences.CheckoutPreference;
// // import com.mercadopago.android.px.preferences.DecorationPreference;

// public class MercadoPagoModule extends ReactContextBaseJavaModule {
//     private MercadoPagoEventListener eventResultListener;

//     public MercadoPagoModule(ReactApplicationContext context) {
//         super(context);
//         init(context);
//     }

//     private void init(@NonNull ReactApplicationContext context) {
//         eventResultListener = new MercadoPagoEventListener();
//         context.addActivityEventListener(eventResultListener);
//     }

//     @Override
//     public String getName() {
//         return "MercadoPagoModule";
//     }

//     public void onNewIntent(Intent intent) { }

//     @ReactMethod
//     public void startCheckoutForPayment(@NonNull String publicKey, @NonNull String hexColor, @NonNull Boolean enableDarkFont, @NonNull Promise promise) {
//         this.setCurrentPromise(promise);

//         //Create a decoration preference
//         // final CheckoutPreference checkoutPreference = new CheckoutPreference(checkoutPreferenceId);
//         final Activity currentActivity = this.getCurrentActivity();

//         new MercadoPagoCheckout.Builder()
//                 .setDecorationPreference(decorationPreference)
//                 .setActivity(currentActivity)
//                 .setPublicKey(publicKey)
//                 .startPayment();
                
//     }

//     @ReactMethod
//     public void startCheckoutForPaymentData(@NonNull String publicKey, @NonNull String checkoutPreferenceId, @NonNull String hexColor, @NonNull Boolean enableDarkFont, @NonNull Promise promise) {
//         this.setCurrentPromise(promise);

//         //Create a decoration preference
//         // final DecorationPreference decorationPreference = this.createDecorationPreference(hexColor, enableDarkFont);
//         // final CheckoutPreference checkoutPreference = new CheckoutPreference(checkoutPreferenceId);
//         final Activity currentActivity = this.getCurrentActivity();

//         new MercadoPagoCheckout.Builder()
//                 .setDecorationPreference(decorationPreference)
//                 .setCheckoutPreference(checkoutPreference)
//                 .setActivity(currentActivity)
//                 .setPublicKey(publicKey)
//                 .startPayment();
//                 // .startForPaymentData();
//     }

//     // private DecorationPreference createDecorationPreference(@NonNull String color, @NonNull Boolean enableDarkFont) {
//     //     // final DecorationPreference.Builder preferenceBuilder = new DecorationPreference.Builder().setBaseColor(color);

//     //     if (enableDarkFont) {
//     //         preferenceBuilder.enableDarkFont();
//     //     }

//     //     return preferenceBuilder.build();
//     // }

//     private void setCurrentPromise(@NonNull Promise promise) {
//         eventResultListener.setCurrentPromise(promise);
//     }
// }