package com.julienlaurent.learning.com.mybudget.fragment;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.julienlaurent.learning.com.mybudget.R;
import com.julienlaurent.learning.com.mybudget.model.BudgetData;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewBudget extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private String date;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //view
    private TextView budgetStartDate;
    private TextView budgetEndDate;
    private TextView julienIncomeAmount;
    private TextView natachaIncomeAmount;
    private TextView otherIncomeAmount;
    private TextView totalIncomeAmount;
    CoordinatorLayout newBudgetCoordinatorLayout;

    //data
    private BudgetData mBudgetData;

    public NewBudget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewBudget.
     */
    // TODO: Rename and change types and number of parameters
    public static NewBudget newInstance() {
        NewBudget fragment = new NewBudget();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        mBudgetData = new BudgetData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final MenuItem next = menu.add("Next");
        next.setIcon(R.drawable.save);
        next.setShowAsAction(1);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addItemsToBudget();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_budget, container, false);
        budgetStartDate =  view.findViewById(R.id.budget_start_date);
        budgetStartDate.setOnClickListener(this);
        budgetEndDate =  view.findViewById(R.id.budget_end_date);
        budgetEndDate.setOnClickListener(this);
        LinearLayout julienIncomeContainer = view.findViewById(R.id.julien_income_container);
        julienIncomeContainer.setOnClickListener(this);
        julienIncomeAmount =  view.findViewById(R.id.julien_income_amount);
        LinearLayout natachaIncomeContainer = view.findViewById(R.id.natacha_income_container);
        natachaIncomeContainer.setOnClickListener(this);
        natachaIncomeAmount =  view.findViewById(R.id.natacha_income_amount);
        LinearLayout otherIncomeContainer = view.findViewById(R.id.other_income_container);
        otherIncomeContainer.setOnClickListener(this);
        otherIncomeAmount =  view.findViewById(R.id.other_income_amount);
        totalIncomeAmount = view.findViewById(R.id.total_income_amount);
        CardView totalAmountContainer = view.findViewById(R.id.income_total_container);
        totalAmountContainer.setOnClickListener(this);
        newBudgetCoordinatorLayout = view.findViewById(R.id.newBudget_coordinator_layout);
        return view;
    }

    public void addItemsToBudget() {
        if (saveAndDisplay()) {
            Fragment fragment = CreateBudget.newInstance();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.budget_start_date:
                datePicker(view);
                break;
            case R.id.budget_end_date:
                datePicker(view);
                break;
            case R.id.julien_income_container:
                getSalary(view);
                break;
            case R.id.natacha_income_container:
                getSalary(view);
                break;
            case R.id.other_income_container:
                getSalary(view);
                break;
            default:
                Toast.makeText(getContext(), "nothing to show", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveAndDisplay() {
        if (mBudgetData.getBudgetStartDate() != null
            && mBudgetData.getBudgetEndDate() != null
            && mBudgetData.getSalary1() > 0 && mBudgetData.getSalary2() > 0){
            Toast.makeText(getContext(), "remember to add to db", Toast.LENGTH_SHORT).show();
            // TODO: 8/19/17 Add everything to db here
            return true;
        }
        return false;
    }

    private void getSalary(final View view) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View promptView = inflater.inflate(R.layout.salary_amount_prompt, null);
        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(getContext());
        alertbuilder.setView(promptView);
        final EditText amount = promptView.findViewById(R.id.income_amount);
        TextView whichSalary = promptView.findViewById(R.id.which_salary);
        String whichSalaryString = null;
        switch (view.getId()) {
            case R.id.julien_income_container:
                whichSalaryString = getContext().getString(R.string.julien_s_income);
                break;
            case R.id.natacha_income_container:
                whichSalaryString = getContext().getString(R.string.natacha_s_income_value);
                break;
            case R.id.other_income_container:
                whichSalaryString = getContext().getString(R.string.other_income_value);
                break;
        }
        if (whichSalaryString != null) {
            whichSalary.setText(whichSalaryString);
        }
        alertbuilder.setCancelable(false);
        alertbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertbuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!TextUtils.isEmpty(amount.getText())) {
                    switch (view.getId()) {
                        case R.id.julien_income_container:
                            mBudgetData.setSalary1(checkAmount(amount.getText().toString()));
                            julienIncomeAmount.setText(currencyFormat.format(mBudgetData.getSalary1()));
                            totalIncomeAmount.setText(currencyFormat.format(mBudgetData.totalIncome()));
                            break;
                        case R.id.natacha_income_container:
                            mBudgetData.setSalary2(checkAmount(amount.getText().toString()));
                            natachaIncomeAmount.setText(currencyFormat.format(mBudgetData.getSalary2()));
                            totalIncomeAmount.setText(currencyFormat.format(mBudgetData.totalIncome()));
                            break;
                        case R.id.other_income_container:
                            mBudgetData.setSalary3(checkAmount(amount.getText().toString()));
                            otherIncomeAmount.setText(currencyFormat.format(mBudgetData.getSalary3()));
                            totalIncomeAmount.setText(currencyFormat.format(mBudgetData.totalIncome()));
                            break;
                    }
                }
            }
        });
        alertbuilder.create().show();
    }

    private double checkAmount(String amountIn) {
        double amount = 0;
        try {
            amount += Double.parseDouble(amountIn);
        } catch (NumberFormatException e) {
            Snackbar.make(newBudgetCoordinatorLayout, "Please enter a valid income", Snackbar.LENGTH_LONG).show();
        }
        return amount;
    }

    public void datePicker(final View view) {
        final Calendar cal = Calendar.getInstance();
        DatePickerDialog picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                switch (view.getId()) {
                    case R.id.budget_start_date:
                        mBudgetData.setBudgetStartDate(String.format(Locale.US, "%s/%s/%s", day, month+1, year));
                        budgetStartDate.setText(mBudgetData.getBudgetStartDate());
                        break;
                    case R.id.budget_end_date:
                        mBudgetData.setBudgetEndDate(String.format(Locale.US, "%s/%s/%s", day, month+1, year));
                        budgetEndDate.setText(mBudgetData.getBudgetEndDate());
                        break;
                }
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        picker.show();
    }

    public boolean checkStartDate(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final Date today = new Date();
        Date startDate = null;
        try {
            startDate = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate != null && (startDate.equals(today));
    }

}
