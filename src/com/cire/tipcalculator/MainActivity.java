
package com.cire.tipcalculator;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCheckedChangeListener {

    private TextView tvTip;

    private EditText etTotal;

    private String tipString;

    private RadioGroup rgroup;

    private double curTipPercent = 0.1d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTipCalculator();
    }

    private void initTipCalculator() {
        rgroup = (RadioGroup)findViewById(R.id.rgroup);
        rgroup.setOnCheckedChangeListener(this);

        etTotal = (EditText)findViewById(R.id.etTotal);
        etTotal.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    BigDecimal total = new BigDecimal(s.toString());
                    tvTip.setText(tipString + " $" + (new BigDecimal(Double.toString(curTipPercent))).multiply(total));
                } else {
                    tvTip.setText(tipString);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        tvTip = (TextView)findViewById(R.id.tvTip);

        tipString = getResources().getString(R.string.tip_is);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.btTen:
            curTipPercent = 0.1d;
            break;
        case R.id.btFifteen:
            curTipPercent = 0.15d;
            break;
        case R.id.btTwenty:
            curTipPercent = 0.2d;
            break;
        }
        if (!etTotal.getText().toString().equals("")) {
            BigDecimal total = new BigDecimal(etTotal.getText().toString());
            tvTip.setText(tipString + " $" + (new BigDecimal(Double.toString(curTipPercent))).multiply(total));
        } else {
            tvTip.setText(tipString);
        }

    }
}
