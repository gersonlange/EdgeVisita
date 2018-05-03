package br.inf.edge.suporte.visita.features.regiao;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import br.inf.edge.suporte.visita.R;
import br.inf.edge.suporte.visita.features.cliente.ClienteActivity;

class RegiaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView labelRegiao;
    TextView labelObservacao;
    TextView labelCodigo;
    TextView labelData;
    Integer codigoRegiao;

    public RegiaoViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        labelRegiao = itemView.findViewById(R.id.label_regiao);
        labelData = itemView.findViewById(R.id.label_data);
        labelObservacao = itemView.findViewById(R.id.label_observacao);
        labelCodigo = itemView.findViewById(R.id.label_codigo);
    }

    @Override
    public void onClick(View view) {
        EventBus.getDefault().postSticky( codigoRegiao );

        Intent intent = new Intent(view.getContext(), ClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }
}
