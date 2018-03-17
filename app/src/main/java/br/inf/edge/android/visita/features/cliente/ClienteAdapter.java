package br.inf.edge.android.visita.features.cliente;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.model.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteViewHolder> {

    List<Cliente> clienteList;
    Context context;

    public ClienteAdapter(List<Cliente> clienteList, Context context){
        this.clienteList = clienteList;
        this.context = context;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cliente, parent, false);

        ClienteViewHolder myViewHolder = new ClienteViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        final Cliente cliente = clienteList.get(position);

        holder.labelCliente.setText(cliente.getNome());
        holder.labelObservacao.setText(cliente.getObservacao());
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }
}
