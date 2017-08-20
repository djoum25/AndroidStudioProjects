package com.julienlaurent.learning.com.mybudget.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.julienlaurent.learning.com.mybudget.R;
import com.julienlaurent.learning.com.mybudget.adapter.BudgetAdapter;
import com.julienlaurent.learning.com.mybudget.model.Budget;
import com.julienlaurent.learning.com.mybudget.model.BudgetData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class CreateBudget extends Fragment{

    ArrayList<Budget> bills;
    private BudgetData mBudgetData;
    private ListView mListView;
    private TextView billsTotal;
    private LinearLayout budgetTotalContainer;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CreateBudget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateBudget.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateBudget newInstance() {
        //        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return new CreateBudget();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mBudgetData = new BudgetData();
        bills = mBudgetData.getBills();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final MenuItem save = menu.add("Save Budget");
        save.setShowAsAction(1);
        save.setIcon(R.drawable.check);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        saveBudgetToDb();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_budget, container, false);

        //// FIXME: 8/14/17
       Collections.sort(bills, new Comparator<Budget>() {
           @Override
           public int compare(Budget budget, Budget t1) {
               return String.valueOf(budget.getBillAmount()).compareTo(String.valueOf(t1.getBillAmount()));
           }
       });
        mListView = (ListView) view.findViewById(R.id.budgetList);
        BudgetAdapter adapter = new BudgetAdapter(getContext(), R.layout.budget_sample, bills);
        mListView.setAdapter(adapter);
        billsTotal = view.findViewById(R.id.billsTotal);
        budgetTotalContainer = view.findViewById(R.id.budget_total_container);
        budgetTotalContainer.setVisibility(View.GONE);
        return  view;
    }

    public void saveBudgetToDb(){
            if (mBudgetData.budgetTotal() > 0){
                budgetTotalContainer.setVisibility(View.VISIBLE);
                billsTotal.setText(currencyFormat.format(mBudgetData.budgetTotal()));
                for (Budget aBill: bills) {
                   if (aBill.getBillAmount() > 0){
                       // TODO: 8/20/17 add the bill to the db right here
                   }
                }
            }
    }
}
