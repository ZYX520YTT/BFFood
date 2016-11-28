package food.neusoft.com.food.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.R;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;

public class RegisterActivity extends AppCompatActivity {

    private AsyncHttpResponseHandler signIn_handler;


    private Context context;
    @ViewInject(R.id.et_number)
    private EditText et_number;
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @ViewInject(R.id.bt_code)
    private Button bt_code;
    @ViewInject(R.id.et_password1)
    private EditText et_password1;
    @ViewInject(R.id.et_password2)
    private EditText et_password2;
    @ViewInject(R.id.check)
    private CheckBox check;
    @ViewInject(R.id.bt_register)
    private Button bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        dohandler();
        Init();
    }

    private void Init() {
        //设置提示颜色
        et_number.setHintTextColor(getResources().getColor(R.color.white));
        et_code.setHintTextColor(getResources().getColor(R.color.white));
        et_password1.setHintTextColor(getResources().getColor(R.color.white));
        et_password2.setHintTextColor(getResources().getColor(R.color.white));


        bt_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "获取验证码", Toast.LENGTH_SHORT).show();
            }
        });


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFinish()) {
                    setPersonal();
                }
            }
        });
    }

    //判断输入格式是否正确,这里没有进行验证码判断(没有手机验证码)
    private boolean isFinish() {
        if (TextUtils.isEmpty(et_number.getText().toString())
                || TextUtils.isEmpty(et_password1.getText().toString())
                || TextUtils.isEmpty(et_password2.getText().toString())) {
            Toast.makeText(context, "请输入完整信息", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!et_password1.getText().toString().equals(et_password2.getText().toString())) {
            Toast.makeText(context, "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!check.isChecked()) {
            Toast.makeText(context, "您未同意注册协议，无法注册", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void setPersonal() {
        RequestParams params = new RequestParams();
        params.put("userId", et_number.getText().toString());
        params.put("userPassword", et_password1.getText().toString());
        HttpUtils.post(context, Url.signIn, params, signIn_handler);
    }


    private void dohandler() {
        signIn_handler = new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    if (result.equals("SUCCESS")) {
                        Toast.makeText(context, "注册成功!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(context, "用户名已存在",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "网络连接错误，请检查网络设置后重试。",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

}
