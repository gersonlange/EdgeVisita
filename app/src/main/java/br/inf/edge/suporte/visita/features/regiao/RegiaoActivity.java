package br.inf.edge.suporte.visita.features.regiao;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.inf.edge.android.visita.model.Regiao;
import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.dao.DadosDAO;
import br.inf.edge.suporte.visita.data.Database;
import br.inf.edge.suporte.visita.data.Session;
import br.inf.edge.suporte.visita.features.login.LoginActivity;
import br.inf.edge.suporte.visita.model.Dados;
import br.inf.edge.suporte.visita.web.WebTaskDados;

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
                if  (item.getItemId()==R.id.btn_sair) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int choice) {
                            switch (choice) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    new Session(getApplicationContext())
                                            .set(Session.USUARIO_TOKEN, null)
                                            .set(Session.USUARIO_NOME, null);

                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);

                                    RegiaoActivity.this.finish();

                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegiaoActivity.this);
                    builder.setMessage(getString(R.string.sistema_sair))
                            .setTitle(getString(R.string.app_name))
                            .setPositiveButton(android.R.string.yes, dialogClickListener)
                            .setNegativeButton(android.R.string.cancel, dialogClickListener).show();

                }

                return false;
            }
        });

        mSwipeRefreshLayout = findViewById(R.id.swipe_regiao);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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

        Database.init(this);
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

        List<Regiao> regioes = new DadosDAO().getRegioes();

        if ( forcar || regioes == null || regioes.size() == 0 ) {

            if ( forcar )
                Database.drop();

            WebTaskDados taskDados = new WebTaskDados(this, new Session(this).get(Session.USUARIO_TOKEN));
            taskDados.execute();
        } else {
            setDados(regioes);
        }

        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void setDados(List<Regiao> regioes) {
        if ( regioes == null || regioes.size() == 0 ) {
            findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.swipe_regiao).setVisibility(View.GONE);
        } else {
            findViewById(R.id.swipe_regiao).setVisibility(View.VISIBLE);
            findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapter.regiaoList = regioes;
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onEvent(Dados dados) {

        DadosDAO dao = new DadosDAO();
        dao.insertRegiao(dados.getRegioes());
        dao.insertCliente(dados.getClientes());

        setDados(dao.getRegioes());
    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(findViewById(R.id.container), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }
}