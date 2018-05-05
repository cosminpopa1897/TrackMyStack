package com.trackmystack.popacosmin.trackmystack;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Adapters.ShopSpinnerAdapter;
import com.trackmystack.popacosmin.trackmystack.Fragments.DatePickerFragment;
import com.trackmystack.popacosmin.trackmystack.Helpers.DateFormatter;
import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Cosmin on 07-Dec-17.
 */

public class TransactionActivity extends BaseActivity {

    public EditText ProductName;
    public EditText ProductQuantity;
    public EditText ProductReceiver;
    public EditText ProductSender;
    private TextView DateSentTextView;
    private TextView DateReceivedTextView;
    public Button TransactionSubmitButton;
    public Button CreateProductButton;
    private Spinner SenderSpinner;
    protected Navigator Navigator;
    public Transaction Transaction;
    private SqLiteHelper sqLiteHelper;
    private ArrayList<Shop> shopList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        initializeAttributes();
        Navigator = new Navigator();

        this.TransactionSubmitButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                sendNewTransactionToIndex();
            }
        });
        this.CreateProductButton.setOnClickListener((new View.OnClickListener(){
            public void onClick(View v){
               Navigator.NavigateToActivity(TransactionActivity.this, CreateProductActivity.class);
            }
        }));
        this.DateSentTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ShowDatePickerDialog(v, DateSentTextView);
            }
        });

        this.DateReceivedTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ShowDatePickerDialog(v, DateReceivedTextView);
            }
        });
        ShopSpinnerAdapter shopSpinnerAdapter = new ShopSpinnerAdapter(this, this.shopList, null);
        this.SenderSpinner.setAdapter(shopSpinnerAdapter);
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.new_transaction;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.new_transaction);
        item.setVisible(false);
        return true;
    }

    private void ShowDatePickerDialog(View v, TextView dateTextView){
        final TextView textView = dateTextView;
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.SetOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                setDateTextViewValue(textView, year, month, dayOfMonth);
                                            }
                                        }
        );
        datePicker.show(getFragmentManager(),"datePicker");
    }

    private void setDateTextViewValue(TextView dateTextView, int year, int month, int dayOfMonth){
        dateTextView.setText(dayOfMonth + "-" + (month + 1)+ "-" + year);
    }
    private Transaction getTransactionForm() {
        Transaction newTransaction = new Transaction();
        newTransaction.Id = IdentityGenerator.getTransactionId();
        newTransaction.Name = this.ProductName.getText().toString();
        newTransaction.DateReceived = DateFormatter.formatDate(this.DateReceivedTextView.getText().toString());
        newTransaction.DateSent = DateFormatter.formatDate(this.DateSentTextView.getText().toString());
        newTransaction.IsDeleted = false;
        newTransaction.Receiver = this.ProductReceiver.getText().toString();
        newTransaction.Sender = this.ProductSender.getText().toString();
        newTransaction.Quantity = Float.parseFloat(this.ProductQuantity.getText().toString());
        return newTransaction;
    }

    protected  void sendNewTransactionToIndex(){
        this.Transaction = getTransactionForm();
        sqLiteHelper.insertTransaction(this.Transaction);
        //Bundle newState  = Transaction.BundleTransaction(this.Transaction);
        Intent intent= new Intent(TransactionActivity.this, MainActivity.class);
        //intent.putExtras(newState);
        startActivity(intent);
    }


    private Bundle createTransactionBundle(){
        Bundle newBundle = new Bundle();
        newBundle.putString("id", Integer.toString(this.Transaction.Id));
        newBundle.putString("name", this.Transaction.Name);
        newBundle.putString("dateReceived", this.Transaction.DateReceived.toString());
        newBundle.putString("dateSent", this.Transaction.DateSent.toString());
        newBundle.putString("isDeleted", Boolean.toString(this.Transaction.IsDeleted));
        newBundle.putString("receiver", this.Transaction.Receiver);
        newBundle.putString("sender", this.Transaction.Sender);
        newBundle.putString("quantity", Float.toString(this.Transaction.Quantity));

        return newBundle;
    }
    private void initializeAttributes(){
        setContentView(R.layout.activity_edit_transaction);
        this.ProductName = (EditText) findViewById(R.id.Product);
        this.ProductQuantity = (EditText) findViewById(R.id.productQuantity);
        this.ProductReceiver = (EditText) findViewById(R.id.productReceiver);
        this.ProductSender = (EditText) findViewById(R.id.productSender);
        this.DateSentTextView = (TextView) findViewById(R.id.edit_transaction_textView_DateSent);
        this.DateReceivedTextView = (TextView) findViewById(R.id.edit_transaction_textView_receivedDate);
        this.TransactionSubmitButton = (Button) findViewById(R.id.transactionSubmitButton);
        this.CreateProductButton = (Button) findViewById(R.id.createNewProduct);
        this.SenderSpinner = (Spinner) findViewById(R.id.edit_transaction_spinner_senderShop);
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.shopList = this.sqLiteHelper.getAllShops();
    }
}
