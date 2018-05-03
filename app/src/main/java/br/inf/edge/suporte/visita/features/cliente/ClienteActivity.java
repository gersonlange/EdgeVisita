package br.inf.edge.suporte.visita.features.cliente;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.dao.DadosDAO;
import br.inf.edge.suporte.visita.data.Session;
import br.inf.edge.suporte.visita.model.Cliente;

public class ClienteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        setTitle(getString(R.string.titulo_cliente));

        recyclerView = findViewById(R.id.recycler_cliente);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        adapter = new ClienteAdapter(new ArrayList<Cliente>(),this);
        recyclerView.setAdapter(adapter);

        setCliente( EventBus.getDefault().getStickyEvent(Integer.class) );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void setCliente(Integer codigoRegiao) {

        List<Cliente> clientes = new DadosDAO().getClientes( codigoRegiao );

        if ( clientes == null || clientes.size() == 0 ) {
            findViewById(R.id.container_cliente_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.recycler_cliente).setVisibility(View.GONE);
        } else {
            findViewById(R.id.recycler_cliente).setVisibility(View.VISIBLE);
            findViewById(R.id.container_cliente_empty).setVisibility(View.GONE);
            adapter.clienteList = clientes;
            adapter.notifyDataSetChanged();
       }
    }
}