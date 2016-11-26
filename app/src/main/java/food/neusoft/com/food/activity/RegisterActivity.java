package food.neusoft.com.food.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import food.neusoft.com.food.R;

public class RegisterActivity extends AppCompatActivity {

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
        context=this;
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
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
                Toast.makeText(context,"获取验证码",Toast.LENGTH_SHORT).show();
            }
        });


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"注册",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });




    }
}
