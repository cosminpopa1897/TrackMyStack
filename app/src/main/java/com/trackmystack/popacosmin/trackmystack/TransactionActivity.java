package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trackmystack.popacosmin.trackmystack.Helpers.DateFormatter;
import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

/**
 * Created by Cosmin on 07-Dec-17.
 */

public class TransactionActivity extends BaseActivity {

    public EditText ProductName;
    public EditText ProductQuantity;
    public EditText ProductReceiver;
    public EditText ProductSender;
    public EditText DateSend;
    public EditText DateReceived;
    public Button TransactionSubmitButton;
    public Button CreateProductButton;
    protected Navigator Navigator;
    public Transaction Transaction;
    private SqLiteHelper sqLiteHelper;

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


    private Transaction getTransactionForm() {
        Transaction newTransaction = new Transaction();
        newTransaction.Id = IdentityGenerator.getTransactionId();
        newTransaction.Name = this.ProductName.getText().toString();
        newTransaction.DateReceived = DateFormatter.formatDate(this.DateReceived.getText().toString());
        newTransaction.DateSent = DateFormatter.formatDate(this.DateSend.getText().toString());
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
        this.DateSend = (EditText) findViewById(R.id.sentDate);
        this.DateReceived = (EditText) findViewById(R.id.dateReceived);
        this.TransactionSubmitButton = (Button) findViewById(R.id.transactionSubmitButton);
        this.CreateProductButton = (Button) findViewById(R.id.createNewProduct);
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
    }
}
