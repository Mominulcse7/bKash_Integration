package com.mominulcse7.bkash_integration;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.mominulcse7.bkash_integration.activities.PaymentSuccess_Activity;

//created by Mominul Islam mominulcse7@gmail.com  08/06/2020

public class BkashJavaScriptInterface {
    Context mContext;
    /**
     * Instantiate the interface and set the context
     */
    public BkashJavaScriptInterface(Context c) {
        mContext = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void OnPaymentSuccess(String data) {

        //filtering received data coming from bkash end point

        String[] paymentData = data.split("&");
        String paymentID = paymentData[0].trim().replace("PaymentID= ", "").trim();
        String transactionID = paymentData[1].trim().replace("TransactionID= ", "").trim();
        String amount = paymentData[2].trim().replace("Amount= ", "").trim();

        //payment successful.  Go to another activity and save order data to database
        Intent intent = new Intent(mContext, PaymentSuccess_Activity.class);
        intent.putExtra("TRANSACTION_ID", transactionID);
        intent.putExtra("PAID_AMOUNT", amount);
        intent.putExtra("PAYMENT_SERIALIZE", data);
        mContext.startActivity(intent);
    }

}