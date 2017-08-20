package com.julienlaurent.learning.com.mybudget.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.julienlaurent.learning.com.mybudget.R;
import com.julienlaurent.learning.com.mybudget.model.Budget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BudgetAdapter extends ArrayAdapter<Budget> {

    private List<Budget> mBudgets;
    private Context mContext;

    public BudgetAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Budget> budgets) {
        super(context, 0, budgets);
        this.mContext = context;
        this.mBudgets = budgets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Budget budget = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.budget_sample, parent, false);
            viewHolder.billName = (TextView) convertView.findViewById(R.id.bill_name);
            viewHolder.billAmount = (TextView) convertView.findViewById(R.id.bill_amount);
            viewHolder.billMessage = (TextView) convertView.findViewById(R.id.bill_message);
            viewHolder.billContainer = convertView.findViewById(R.id.bill_container);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (budget != null) {
            viewHolder.billName.setText(budget.getItemName());
        }
        final View finalConvertView = convertView;
        viewHolder.billContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (budget != null) {
                    if (budget.getBillAmount() <= 0) {
                        AlertDialog.Builder alertBuilder = addAmount(budget, viewHolder, finalConvertView);
                        alertBuilder.show();
                    } else {
                        AlertDialog.Builder editBillBuilder = new AlertDialog.Builder(mContext, R.style.myAlertitle);
                        editBillBuilder.setTitle("Choose an action");
                        editBillBuilder.setCancelable(false);
                        editBillBuilder.setItems(R.array.edit_a_bill, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(mContext, "set the status", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        AlertDialog.Builder alertBuilder = addAmount(budget, viewHolder, finalConvertView);
                                        alertBuilder.show();
                                        break;
                                }
                            }
                        });
                        editBillBuilder.create().show();
                    }
                }
            }
        });
        return convertView;
    }

    @NonNull
    private AlertDialog.Builder addAmount(final Budget budget, final ViewHolder viewHolder, final View finalConvertView) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.bill_amount_prompt, null);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext, R.style.myAlertitle);
        alertBuilder.setTitle(budget.getItemName());
        alertBuilder.setView(promptView);
        final EditText amount = (EditText) promptView.findViewById(R.id.promt_amount);
        final EditText message = (EditText) promptView.findViewById(R.id.promt_message);
        if (budget.getBillAmount() > 0) {
            amount.setText(String.valueOf(budget.getBillAmount()));
            if (budget.getBillNotes().length() > 0) {
                message.setText(budget.getBillNotes());
            }
        }
        alertBuilder.setCancelable(false);
        final AlertDialog.Builder builder = alertBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(amount.getText().toString())) {
                    viewHolder.billAmount.setError("No amount set");
                    return;
                }
                viewHolder.billAmount.setError(null);
                budget.setBillAmount(Double.parseDouble(amount.getText().toString()));
                budget.setBillNotes(message.getText().toString());
                viewHolder.billAmount.setText(String.valueOf(budget.getBillAmount()));
                viewHolder.billMessage.setText(budget.getBillNotes());
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return alertBuilder;
    }

    public static class ViewHolder {
        TextView billName;
        TextView billAmount;
        TextView billMessage;
        LinearLayout billContainer;
    }


}
