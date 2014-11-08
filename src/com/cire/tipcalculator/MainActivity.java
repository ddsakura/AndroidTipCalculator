
package com.cire.tipcalculator;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private TextView tvTip;

    private EditText etTotal;

    private String tipString;

    private Button btTen;

    private Button btFifteen;

    private Button btTwenty;

    private double curTipPercent = 0.1d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTipCalculator();
    }

    private void initTipCalculator() {
        btTen = (Button)findViewById(R.id.btTen);
        btTen.setOnClickListener(this);

        btFifteen = (Button)findViewById(R.id.btFifteen);
        btFifteen.setOnClickListener(this);

        btTwenty = (Button)findViewById(R.id.btTwenty);
        btTwenty.setOnClickListener(this);

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
    public void onClick(View v) {
        if (!etTotal.getText().equals("")) {
            BigDecimal total = new BigDecimal(etTotal.getText().toString());
            switch (v.getId()) {
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
            tvTip.setText(tipString + " $" + (new BigDecimal(Double.toString(curTipPercent))).multiply(total));
        } else {
            tvTip.setText(tipString);
        }

    }
}
