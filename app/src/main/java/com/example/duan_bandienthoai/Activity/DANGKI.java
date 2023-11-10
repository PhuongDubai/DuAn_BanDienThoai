package com.example.duan_bandienthoai.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan_bandienthoai.R;
import com.example.duan_bandienthoai.dao.NguoiDungDao;
import com.example.duan_bandienthoai.mode.NguoiDung;

public class DANGKI extends AppCompatActivity {
    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;
    String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText edtDKTK, edtDKMK, edtDKNLMK, txtTenNguoiDung;
    Button btnDK;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        ImageView passwordIcon = findViewById(R.id.passwordIcon);
        ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);
        edtDKTK = findViewById(R.id.edtDKTK);
        edtDKMK = findViewById(R.id.edtDKMK);
        edtDKNLMK = findViewById(R.id.edtDKNLMK);

        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDung);
        btnDK = findViewById(R.id.btnDK);
        nguoiDungDao = new NguoiDungDao(DANGKI.this);
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordShowing) {

                    passwordShowing = false;
                    edtDKNLMK.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                } else {
                    passwordShowing = true;

                    edtDKNLMK.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                }
                edtDKNLMK.setSelection(edtDKNLMK.length());
            }
        });
        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conPasswordShowing) {

                    conPasswordShowing = false;
                    edtDKMK.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.hide);
                } else {
                    conPasswordShowing = true;

                    edtDKMK.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.show);
                }
                edtDKMK.setSelection(edtDKMK.length());
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtTen = txtTenNguoiDung.getText().toString();
                String userName = edtDKTK.getText().toString();
                String password = edtDKMK.getText().toString();
                String passwordNL = edtDKNLMK.getText().toString();
                if (TextUtils.isEmpty(txtTen)) {
                    Toast.makeText(DANGKI.this, "vui lòng nhâp vào tên người dùng", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(DANGKI.this, "vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (!userName.matches(checkEmail)) {
                    Toast.makeText(DANGKI.this, "Email chưa đúng định dạng", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(DANGKI.this, "vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passwordNL)) {
                    Toast.makeText(DANGKI.this, "vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(passwordNL)) {
                    Toast.makeText(DANGKI.this, "mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        NguoiDung nguoiDung = new NguoiDung(userName, txtTen, password);
                        if (nguoiDungDao.insert(nguoiDung)) {
                            Toast.makeText(DANGKI.this, "đăng ký thành công", Toast.LENGTH_SHORT).show();
                            // gửi dữ liệu đi
                            Intent intent = new Intent(DANGKI.this, DANGNHAP.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DANGKI.this, "đăng ký thấy bại", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(DANGKI.this, "đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}

