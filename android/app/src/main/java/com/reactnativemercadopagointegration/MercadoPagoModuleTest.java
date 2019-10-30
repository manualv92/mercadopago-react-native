package com.reactnativemercadopagointegration;

import android.widget.Toast;
import android.app.Activity;
import android.util.Log;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.mercadopago.android.px.preferences.CheckoutPreference;
import com.mercadopago.android.px.core.MercadoPagoCheckout.Builder;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Sites;

import com.mercadopago.android.px.model.exceptions.*;


import com.mercadopago.android.px.model.*;
import com.mercadopago.android.px.model.CardToken;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.Payer;
import com.mercadopago.android.px.model.Card;
import com.mercadopago.android.px.model.Cardholder;
import com.mercadopago.android.px.model.Identification;
import com.mercadopago.android.px.model.Token;

import com.mercadopago.android.px.services.*;
import com.mercadopago.android.px.services.MercadoPagoServices;
import com.mercadopago.android.px.services.Callback;



public class MercadoPagoModuleTest extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    MercadoPagoModuleTest(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @Override
    public String getName() {
        return "MercadoPagoTest";
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void getCardToken() {

        Log.d("Notification","Notify App");

        // final List<Item> items = new ArrayList<>();
        // final Item item =
        //     new Item.Builder("Product title", 1, new BigDecimal(123))
        //         .build();
        // items.add(item);
        // CheckoutPreference checkoutPref =  new CheckoutPreference.Builder(Sites.ARGENTINA,
        //     "prueba@gmail.com", items)
        //     .setDefaultInstallments(1)
        //     .build();

        Payment payment = new Payment();
        Payer payer = new Payer();
        Card card = new Card();
        Cardholder cardHolder = new Cardholder();
        Identification identification = new Identification();
        identification.setNumber("12345678");
        identification.setType("DNI");
        cardHolder.setIdentification(identification);
        cardHolder.setName("APRO");
        // card.setCardholder(cardHolder);
        
        CardToken cardToken = new CardToken("4509953566233704", 12, 2020, "123", "APRO", "DNI", "12345678");
        // cardToken.setCardNumber("4509953566233704");
        // cardToken.setSecurityCode("123");
        // cardToken.setExpirationMonth(12);
        // cardToken.setExpirationYear(2020);
        // cardToken.setCardholder(cardHolder);

        Log.d("Notification", "CARD NUMBER" + cardToken.getCardNumber());        
        MercadoPagoServices mercadoPago = new MercadoPagoServices(reactContext, "TEST-d286d734-2b5c-4371-befd-524d2a43051d", "TEST-3986155898527090-092415-8efe733163b38a8f648ef69b8d144269-157063361");
        mercadoPago.createToken(cardToken, new Callback<Token>() {
            @Override
            public void success(Token token) {
                // Intent returnIntent = new Intent();
                // setResult(RESULT_OK, returnIntent);
                // returnIntent.putExtra("token", token.getId());
                // returnIntent.putExtra("paymentMethod", JsonUtil.getInstance().toJson(mPaymentMethod));
                // finish();
                Log.d("Notification", "TOKEN" + token.toJson());
            }
            @Override
            public void failure(ApiException error) {
                Log.d("Notification",error.getMessage());

                // MercadoPagoUtil.finishWithApiException(mActivity, error);
            }
    });
        // payment.set
        
        // final MercadoPagoCheckout checkout = new MercadoPagoCheckout.Builder("TEST-3986155898527090-092415-8efe733163b38a8f648ef69b8d144269-157063361", checkoutPref)
        // .build();
        // final Activity currentActivity = this.getCurrentActivity();

        // checkout.startPayment(currentActivity, 1);
        // Toast.makeText(getReactApplicationContext(), message, duration).show();
    }
}