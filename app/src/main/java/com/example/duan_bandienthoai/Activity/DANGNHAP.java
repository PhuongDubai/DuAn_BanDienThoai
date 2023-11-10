package com.example.duan_bandienthoai.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.Util.SendMail;
import com.example.duan_bandienthoai.dao.NguoiDungDao;


public class DANGNHAP extends AppCompatActivity {

    private SendMail sendMail;
    private boolean passwordShowing = false;
    String strUser, strPass;
    NguoiDungDao nguoiDungDao;
    CheckBox chkRememberPass;
    EditText edtUsername, edtPassword;
    TextView txtSignUp, txtForgot;
    String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        Button btnDangnhap = findViewById(R.id.btnDangNhap);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        ImageView passwordIcon = findViewById(R.id.passwordIcon);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtForgot = findViewById(R.id.txtForgot);


        nguoiDungDao = new NguoiDungDao(this);
        sendMail = new SendMail();
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);
        edtUsername.setText(user);
        edtPassword.setText(pass);
        chkRememberPass.setChecked(rem);
        Toast.makeText(this, "Chào mừng đến với ứng dụng", Toast.LENGTH_SHORT).show();


        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordShowing) {

                    passwordShowing = false;
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                } else {
                    passwordShowing = true;

                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });


        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUser = edtUsername.getText().toString();
                strPass = edtPassword.getText().toString();
                if (nguoiDungDao.checkLogin(strUser, strPass)) {
                    rememberUser(strUser, strPass, chkRememberPass.isChecked());
//                    startActivity(new Intent(dangNhap.this, navigationDrawer.class));
                    Intent intent = new Intent(DANGNHAP.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                    Toast.makeText(DANGNHAP.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else if (!strUser.matches(checkEmail)) {
                    Toast.makeText(DANGNHAP.this, "Email chưa đúng định dạng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DANGNHAP.this, "Username or Password không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DANGNHAP.this, DANGKI.class));
            }
        });
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogForgot();
            }
        });

    }

    private void showdialogForgot() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);

        EditText edtEmail = view.findViewById(R.id.edtEmail);
        Button btnSend = view.findViewById(R.id.btnSend);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String matkhau = nguoiDungDao.ForgotPassword(email);
//                Toast.makeText(DANGNHAP.this, matkhau, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DANGNHAP.this, "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(checkEmail)) {
                    Toast.makeText(DANGNHAP.this, "Email chưa đúng định dạng! ", Toast.LENGTH_SHORT).show();
                } else if (matkhau.equals("")) {
                    Toast.makeText(DANGNHAP.this, "Người dùng chưa đăng ký!", Toast.LENGTH_SHORT).show();
                } else {
                    sendMail.Send(DANGNHAP.this, email, "Lấy lại mật khẩu", "Mật khẩu của bạn là: " + matkhau);
                    alertDialog.dismiss();
                    Toast.makeText(DANGNHAP.this, "Đã gửi mật khẩu hãy kiểm tra gmail của bạn", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if (!status) {
            //Xóa tình trạng lưu trữ trước đó
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }
}