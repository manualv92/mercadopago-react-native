package com.reactnativemercadopagointegration;

import android.widget.Toast;
import android.app.Activity;
import android.util.Log;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.*;

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
    public void getCardToken(ReadableMap cardMap, Promise promise) {

        Log.d("Notification","getCardToken method");
        
        // CardToken cardToken = new CardToken("4509953566233704", 12, 2020, "123", "APRO", "DNI", "12345678");
        CardToken cardToken = new CardToken(cardMap.getString("cardNumber"), cardMap.getInt("expMonth"), 
            cardMap.getInt("expYear"), cardMap.getString("secCode"), cardMap.getString("cardHName"), 
            cardMap.getString("cardHIType"), cardMap.getString("cardHIdent"));

        Log.d("Notification", "CARD NUMBER: " + cardToken.getCardNumber());        

        MercadoPagoServices mercadoPago = new MercadoPagoServices(reactContext, "TEST-d286d734-2b5c-4371-befd-524d2a43051d", "TEST-3986155898527090-092415-8efe733163b38a8f648ef69b8d144269-157063361");
        mercadoPago.createToken(cardToken, new Callback<Token>() {
            @Override
            public void success(Token token) {
                // Intent returnIntent = new Intent();
                // setResult(RESULT_OK, returnIntent);
                // returnIntent.putExtra("token", token.getId());
                // returnIntent.putExtra("paymentMethod", JsonUtil.getInstance().toJson(mPaymentMethod));
                // finish();
                Log.d("Notification", "TOKEN: " + token.toJson());
                promise.resolve(token.toJson());
            }
            @Override
            public void failure(ApiException error) {
                Log.d("Notification", "Error: " + error.getMessage());
                promise.reject("error", error.getMessage());
                // MercadoPagoUtil.finishWithApiException(mActivity, error);
            }
        });
    }

    @ReactMethod
    public void getCustomerCardToken(ReadableMap cardMap, Promise promise) {
        Log.d("Notification","getCustomerCardToken method");

        SavedCardToken savedCardToken = new SavedCardToken(cardMap.getString("cardId"), cardMap.getString("secCode"));
        MercadoPagoServices mercadoPago = new MercadoPagoServices(reactContext, "TEST-d286d734-2b5c-4371-befd-524d2a43051d", "TEST-3986155898527090-092415-8efe733163b38a8f648ef69b8d144269-157063361");
        mercadoPago.createToken(savedCardToken, new Callback<Token>() {
            @Override
            public void success(Token token) {
                // Intent returnIntent = new Intent();
                // setResult(RESULT_OK, returnIntent);
                // returnIntent.putExtra("token", token.getId());
                // returnIntent.putExtra("paymentMethod", JsonUtil.getInstance().toJson(mPaymentMethod));
                // finish();
                Log.d("Notification", "TOKEN: " + token.toJson());
                promise.resolve(token.toJson());
            }
            @Override
            public void failure(ApiException error) {
                Log.d("Notification", "Error: " + error.getMessage());
                promise.reject("error", error.getMessage());
                // MercadoPagoUtil.finishWithApiException(mActivity, error);
            }
        });
    }
}