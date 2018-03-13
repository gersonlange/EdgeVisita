package br.inf.edge.android.visita.features.regiao;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;

import java.util.ArrayList;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.data.Session;
import br.inf.edge.android.visita.model.Dados;
import br.inf.edge.android.visita.model.Regiao;
import br.inf.edge.android.visita.web.WebTaskDados;

public class RegiaoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RegiaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiao);

        recyclerView = findViewById(R.id.recycler_regiao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new RegiaoAdapter(new ArrayList<Regiao>(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

        Session mySession = new Session(this);

        if ( mySession.get(Session.DADOS).trim().isEmpty() ) {
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void setDados(Dados dados) {
        if ( dados == null || dados.getRegiao().size() == 0 ) {
            findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.recycler_regiao).setVisibility(View.GONE);
        } else {
            findViewById(R.id.recycler_regiao).setVisibility(View.VISIBLE);
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
