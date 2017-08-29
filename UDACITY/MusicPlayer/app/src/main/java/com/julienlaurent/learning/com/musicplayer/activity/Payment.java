package com.julienlaurent.learning.com.musicplayer.activity;
/*
    Programmer name:        Julien Laurent
    Date:                   July 26, 2017
    Class Description:      This classs controls the payment activity
                            of the app.
                            customer will enter their credit card info and after validation
                            it will display a toast message for the amout that will be charge on the card

 */

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julienlaurent.learning.com.musicplayer.R;
import com.julienlaurent.learning.com.musicplayer.model.MusicLibraryData;
import com.stripe.android.model.Card;

public class Payment extends AppCompatActivity implements View.OnClickListener{
    private EditText cardNumberEt;
    private EditText cardExpDateEt;
    private EditText cardCvvEt;
    private double totalToPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cardNumberEt = (EditText) findViewById(R.id.card_number_et);
        cardExpDateEt = (EditText) findViewById(R.id.card_exp_date_et);
        cardCvvEt = (EditText) findViewById(R.id.card_cvv_et);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        MusicLibraryData library = new MusicLibraryData();
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.card_info);
        Intent intent = getIntent();
        int position = intent.getIntExtra(getString(R.string.buyingPosition), -1);
        if (position > -1){
            totalToPay = library.mLibrariesData.get(position).getTrackPrice() *
                library.mLibrariesData.get(position).getMusicTracks().length;
            submitButton.setText(String.format("%s %.2f", "Pay", totalToPay));
        }
        submitButton.setOnClickListener(this);

    }

    /**
     * the card will be validated
     * to get a token and charge the customer
     * but the code for getting the token is ommited
     * @param view of the click view
     */
    @Override
    public void onClick(View view) {
        if (cardValidation()){
            Toast.makeText(this, String.format("%s %.2f", "You will be charged", totalToPay), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Invalid card", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * this method will validate the entered
     * card and return a boolean
     * @return a boolean
     */
    private boolean cardValidation() {
        try {
            if (!TextUtils.isEmpty(cardNumberEt.getText().toString())
                &&!TextUtils.isEmpty(cardExpDateEt.getText().toString())
                && !TextUtils.isEmpty(cardCvvEt.getText().toString())){
                Card card = new Card(cardNumberEt.getText().toString(),
                    Integer.parseInt(cardExpDateEt.getText().toString().substring(0, 2)),
                    Integer.parseInt(cardExpDateEt.getText().toString().substring(2)),
                    cardCvvEt.getText().toString());
                card.validateCVC();
                card.validateNumber();
                if (card.validateCard()){
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please check your entry");
        }
        return false;
    }
}
