package com.liyu.lauchericonmodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ComponentName defaultComponent;
    private ComponentName icon2Component;
    private ComponentName icon3Component;
    private EditText mEtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultComponent = new ComponentName(this, getPackageName()+".MainActivity");
        icon2Component = new ComponentName(this, getPackageName()+".icon2");
        icon3Component = new ComponentName(this, getPackageName()+".icon3");

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn_change_et).setOnClickListener(this);
        mEtPwd = findViewById(R.id.et_pwd);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1){
            updateAlias(true, defaultComponent);
            updateAlias(false, icon2Component);
            updateAlias(false, icon3Component);
        }else if (v.getId() == R.id.btn2){
            updateAlias(false, defaultComponent);
            updateAlias(true, icon2Component);
            updateAlias(false, icon3Component);
        }else if (v.getId() == R.id.btn3){
            updateAlias(false, defaultComponent);
            updateAlias(false, icon2Component);
            updateAlias(true, icon3Component);
        }else if (v.getId() == R.id.btn_change_et){
            mEtPwd.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    /**
     * 更新别名显示
     * @param componentName componentName
     * @param enable 是否启用
     */
    private void updateAlias(Boolean enable, ComponentName componentName) {
        int newState;
        if (enable){
            newState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        }else {
            newState = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        }
        getPackageManager().setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP);
    }
}