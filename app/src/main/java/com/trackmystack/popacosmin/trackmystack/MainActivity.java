package com.trackmystack.popacosmin.trackmystack;

import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Adapters.TransactionAdapter;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.PreferenceFilesRepository;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BaseActivity {

    public ListView TransactionListView;
    public ArrayList<Transaction> TransactionList;
    public HashMap<Integer, Integer> itemIdentityDictionary;
    public PreferenceFilesRepository<ArrayList<Transaction>> PreferenceFileRepository;
    public SqLiteHelper sqLiteHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeAttributes();
        Intent intent = getIntent();
        if(intent.hasExtra("transaction")){
            Transaction newTransaction = getTransactionFromIntent(intent);

            if(this.TransactionList == null){
                this.TransactionList = new ArrayList<Transaction>();
            }

            this.TransactionList.add(newTransaction);
        }

        if(this.TransactionList == null)
            return;

        String[] itemsDiplaysNames = new String[this.TransactionList.size()];
        for(int i = 0; i < this.TransactionList.size(); i++){
            itemsDiplaysNames[i] = this.TransactionList.get(i).Name;
            itemIdentityDictionary.put(i, this.TransactionList.get(i).Id);
        }

        ArrayAdapter adapter = new TransactionAdapter(this.TransactionList, this);
        this.TransactionListView.setAdapter(adapter);
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.view_transactions;
    }

    @Override
    public void onStop(){
        this.PreferenceFileRepository.saveObject(
                getPreferences(Context.MODE_PRIVATE),
                this.TransactionList, new TypeToken<ArrayList<Transaction>>() {}.getType(),
                Constants.PreferenceFilesKeys.TransactionList);
        super.onStop();
    }

    private Transaction getTransactionFromIntent(Intent intent){
        Bundle bundle = intent.getExtras();
        Transaction transaction = Transaction.UnpackTransaction(bundle);
        return transaction;
    }
    private void initializeAttributes(){
        this.TransactionListView = (ListView) findViewById(R.id.transactionsListView);
        this.PreferenceFileRepository = new PreferenceFilesRepository<ArrayList<Transaction>>();
        Type objectType = new TypeToken<ArrayList<Transaction>>(){}.getType();
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.TransactionList = sqLiteHelper.getAllTranasactions();
        this.itemIdentityDictionary = new HashMap<Integer, Integer>();

    }

}
