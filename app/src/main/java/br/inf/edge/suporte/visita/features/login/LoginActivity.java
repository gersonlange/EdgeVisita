package br.inf.edge.suporte.visita.features.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.data.Session;
import br.inf.edge.suporte.visita.features.regiao.RegiaoActivity;
import br.inf.edge.suporte.visita.model.Usuario;
import br.inf.edge.suporte.visita.web.WebTaskLogin;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Session session = new Session(this);

        Log.d("DEBUG", "Token: " + session.get(Session.USUARIO_TOKEN));

        if ( ! session.get(Session.USUARIO_TOKEN).trim().isEmpty() ) {
            goToHome();
            return;
        }

        Button buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tryLogin();
                    }
                }
        );

        String usuario = session.get(Session.USUARIO);

        EditText edUsuario = findViewById(R.id.input_usuario);
        edUsuario.setText(usuario);

        EditText edPassword = findViewById(R.id.input_password);

        if ( usuario == null || usuario.trim().isEmpty() )
            edUsuario.requestFocus();
        else
            edPassword.requestFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void tryLogin(){
        EditText inputUsuario = findViewById(R.id.input_usuario);
        String usuario = inputUsuario.getText().toString();

        EditText inputPassword = findViewById(R.id.input_password);
        String senha = inputPassword.getText().toString();

        showDialog();

        new WebTaskLogin(this, usuario, senha)
            .execute();
    }

    @Subscribe
    public void onEvent(Usuario user){

        hideDialog();

        new Session(this)
            .set(Session.USUARIO, user.getUsuario())
            .set(Session.USUARIO_NOME, user.getNome())
            .set(Session.USUARIO_TOKEN, user.getToken());

        goToHome();
    }

    @Subscribe
    public void onEvent(Error error){

        hideDialog();

        Snackbar.make(findViewById(R.id.container),
                error.getMessage(), Snackbar.LENGTH_LONG)
                .show();
    }


    private void goToHome(){
        Intent intent = new Intent(this, RegiaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        startActivity(intent);
//        finish();
//    }

    public void showDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(
                R.string.message_wait));
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideDialog(){
        if(progressDialog != null &&
                progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}
