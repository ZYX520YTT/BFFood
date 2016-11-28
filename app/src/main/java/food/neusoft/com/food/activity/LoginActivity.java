package food.neusoft.com.food.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import food.neusoft.com.food.MainActivity;
import food.neusoft.com.food.R;
import food.neusoft.com.food.thread.HttpUtils;
import food.neusoft.com.food.thread.Url;
import food.neusoft.com.food.thread.User;

import static food.neusoft.com.food.NApplication.ImageUrl;
import static food.neusoft.com.food.NApplication.nickname;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {


    private AsyncHttpResponseHandler login_handler;


    private Context context;
    private final String APP_ID = "1105843660";// 测试时使用，真正发布的时候要换成自己的APP_ID
    public static String mAppid;
    public static QQAuth mQQAuth;
    private UserInfo mInfo;
    private Tencent mTencent;


    @ViewInject(R.id.et_number)
    private EditText et_number;
    @ViewInject(R.id.et_password)
    private EditText et_password;
    @ViewInject(R.id.bt_login)
    private Button bt_login;
    @ViewInject(R.id.rl_register)
    private RelativeLayout rl_register;
    @ViewInject(R.id.iv_weixin)
    private ImageView iv_weixin;
    @ViewInject(R.id.iv_qq)
    private ImageView iv_qq;
    @ViewInject(R.id.iv_weibo)
    private ImageView iv_weibo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }

    @Override
    protected void onStart() {
        final Context context = LoginActivity.this;
        final Context ctxContext = context.getApplicationContext();
        mAppid = APP_ID;
        mQQAuth = QQAuth.createInstance(mAppid, ctxContext);
        mTencent = Tencent.createInstance(mAppid, LoginActivity.this);
        super.onStart();
    }


    private void Init() {

        //设置提示的颜色
        et_number.setHintTextColor(getResources().getColor(R.color.white));
        et_password.setHintTextColor(getResources().getColor(R.color.white));


        //测试的时候我的默认手机号和密码都是1
        et_number.setText("1");
        et_password.setText("1");


        //登录
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judge()) {
                    bt_login.setEnabled(false);//点了登录后不可以再点，避免用户乱点
                    loginInfo();
                }
            }
        });

        //注册
        rl_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "注册", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //微信
        iv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
            }
        });

        //qq
        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "qq", Toast.LENGTH_SHORT).show();

                onClickQQLogin();
            }
        });

        //微博
        iv_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "微博", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 登录
     */
    private void loginInfo() {
        RequestParams params = new RequestParams();
        params.put("userId", et_number.getText().toString());
        params.put("userPassword", et_password.getText().toString());
        HttpUtils.get(context, Url.login, params, login_handler);
    }

    //判断登录信息是否合法
    private boolean judge() {
        if (TextUtils.isEmpty(et_number.getText().toString())) {
            Toast.makeText(context, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_password.getText().toString())) {
            Toast.makeText(context, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //qq登录
    private void onClickQQLogin() {
        if (!mQQAuth.isSessionValid()) {
            IUiListener listener = new BaseUiListener() {
                @Override
                protected void doComplete(JSONObject values) {
                    updateUserInfo();
                }
            };
            mQQAuth.login(this, "all", listener);
            mTencent.login(this, "all", listener);
        }
    }


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }


    private void updateUserInfo() {
        if (mQQAuth != null && mQQAuth.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {

                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            try {
                                ImageUrl = json.getString("figureurl_qq_2");
                                nickname = json.getString("nickname");
//                                System.out.println("网址:"+ImageUrl);
//                                System.out.println("昵称："+nickname);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();


                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mQQAuth.getQQToken());
            mInfo.getUserInfo(listener);

        }
    }


    private void dohandler() {
        login_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                System.out.println(result);
                if (result.equals("ERROR")) {
                    Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String userId = jsonObject.getString("userId");
                        User user = new User(context);
                        user.saveUserNumber(userId);
                        Toast.makeText(context, "登录成功!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "网络连接错误，请检查网络设置后重试。", Toast.LENGTH_SHORT).show();
            }
        };
    }


}
