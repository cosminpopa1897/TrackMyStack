package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;

import org.w3c.dom.Text;

/**
 * Created by Cosmin on 01-May-18.
 */

public class DisplayTransactionActivity extends BaseActivity {

    private Transaction transaction;
    private TextView transactionNameTextView;
    private TextView receiverNameTextView;
    private TextView senderNameTextView;
    private TextView dateSentTextView;
    private TextView dateReceivedTextView;
    private TextView quantityTextView;
    private Button editTransactionButton;
    private Button deleteTransactionButton;
    private SqLiteHelper sqLiteHelper;

    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_display_transaction);
        initializeAttributes();
        mapTransactionToDisplay(transaction);
        this.editTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BundleHelpers.Pack(transaction, transaction.getClass(), Constants.BundleKeys.TransactionKey);
                Intent intent = new Intent(DisplayTransactionActivity.this, TransactionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        this.deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.deleteTransaction(transaction);
                Intent intent = new Intent(DisplayTransactionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.view_transactions;
    }

    private void initializeAttributes(){
        this.transactionNameTextView = (TextView) findViewById(R.id.display_transaction_textView_TransactionName);
        this.receiverNameTextView = (TextView) findViewById(R.id.display_transaction_TextView_ReceiverName);
        this.senderNameTextView = (TextView) findViewById(R.id.display_transaction_textView_SenderName);
        this.dateReceivedTextView = (TextView) findViewById(R.id.display_transaction_textView_DateReceived);
        this.dateSentTextView = (TextView) findViewById(R.id.display_transaction_textView_DateSent);
        this.quantityTextView = (TextView) findViewById(R.id.display_transaction_textView_quantity);
        this.editTransactionButton = (Button) findViewById(R.id.display_transaction_button_editTransaction);
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.deleteTransactionButton = (Button) findViewById(R.id.display_transaction_button_deleteTransaction);
        Intent intent = getIntent();
        this.transaction = getTransactionFromIntent(intent);
    }

    private Transaction getTransactionFromIntent(Intent intent){
        Bundle bundle = intent.getExtras();
        return Transaction.UnpackTransaction(bundle);
    }

    private void mapTransactionToDisplay(Transaction transaction){
        this.transactionNameTextView.setText(transaction.Name);
        this.receiverNameTextView.setText(transaction.Receiver);
        this.senderNameTextView.setText(transaction.Sender);
        this.dateReceivedTextView.setText(transaction.DateReceived.toString());
        this.dateSentTextView.setText(transaction.DateSent.toString());
        this.quantityTextView.setText(Float.toString(transaction.Quantity));
    }
}
