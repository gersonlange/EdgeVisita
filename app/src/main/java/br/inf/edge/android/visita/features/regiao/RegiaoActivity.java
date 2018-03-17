package br.inf.edge.android.visita.features.regiao;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;

import java.util.ArrayList;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.data.Session;
import br.inf.edge.android.visita.features.login.LoginActivity;
import br.inf.edge.android.visita.model.Dados;
import br.inf.edge.android.visita.model.Regiao;
import br.inf.edge.android.visita.web.WebTaskDados;

public class RegiaoActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private RegiaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiao);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.btn_sair) {
                    new Session(getApplicationContext())
                            .set(Session.USUARIO_TOKEN, null)
                            .set(Session.USUARIO_NOME, null);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                    finish();
                }
                return false;
            }
        });

        mSwipeRefreshLayout = findViewById(R.id.swipe_regiao);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("DEBUG", "refresh");

                atualizaDados(true);
            }
        });



        recyclerView = findViewById(R.id.recycler_regiao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        adapter = new RegiaoAdapter(new ArrayList<Regiao>(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_regiao, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

        atualizaDados(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void atualizaDados(boolean forcar) {
        mSwipeRefreshLayout.setRefreshing(true);

        Session mySession = new Session(this);

        if ( forcar || mySession.get(Session.DADOS).trim().isEmpty() ) {
            WebTaskDados taskDados = new WebTaskDados(this, mySession.get(Session.USUARIO_TOKEN));
            taskDados.execute();
        } else {
            Dados dados = null;
            try {
                dados = new Dados(mySession.get(Session.DADOS));
            } catch (JSONException e) {

            }
            setDados(dados);
        }

        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void setDados(Dados dados) {
        if ( dados == null || dados.getRegiao().size() == 0 ) {
            findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.swipe_regiao).setVisibility(View.GONE);
        } else {
            findViewById(R.id.swipe_regiao).setVisibility(View.VISIBLE);
            findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapter.regiaoList = dados.getRegiao();
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onEvent(Dados dados) {
        setDados(dados);
    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(findViewById(R.id.container), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }
}