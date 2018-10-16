package esi.univbobo.bf.smartzoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {
        private Button btnConnect;
        private EditText txtPassword;
        private EditText txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        btnConnect=(Button)findViewById(R.id.btnConnect);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtLogin=(EditText)findViewById(R.id.txtLogin);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid(txtLogin.getText().toString(),txtPassword.getText().toString())){
                    Intent intent = new Intent(Connexion.this,MenuActivity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Toast.makeText(Connexion.this, R.string.strWrongLogin,Toast.LENGTH_SHORT).show();
                }
                }
        });
    }

    private boolean isValid(String login,String password)

    {
            login.replace(" ","");
            password.replace(" ","");
            if(login.equals("admin")&& password.equals("admin"))
                return true;
            else
                return false;

    }


}
