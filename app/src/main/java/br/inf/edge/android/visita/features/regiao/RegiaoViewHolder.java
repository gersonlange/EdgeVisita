package br.inf.edge.android.visita.features.regiao;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.data.Session;
import br.inf.edge.android.visita.features.cliente.ClienteActivity;
import br.inf.edge.android.visita.model.Dados;

class RegiaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView labelRegiao;
    TextView labelObservacao;
    TextView labelCodigo;

    public RegiaoViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        labelRegiao = itemView.findViewById(R.id.label_regiao);
        labelObservacao = itemView.findViewById(R.id.label_observacao);
        labelCodigo = itemView.findViewById(R.id.label_codigo);
    }

    @Override
    public void onClick(View view) {
        EventBus.getDefault().postSticky(getAdapterPosition());

        Intent intent = new Intent(view.getContext(), ClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }
}
