package br.inf.edge.android.visita.features.regiao;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.inf.edge.android.visita.R;
import br.inf.edge.android.visita.model.Regiao;

public class RegiaoAdapter extends RecyclerView.Adapter<RegiaoViewHolder> {

    List<Regiao> regiaoList;
    Context context;

    public RegiaoAdapter(List<Regiao> regiaoList, Context context){
        this.regiaoList = regiaoList;
        this.context = context;
    }

    @Override
    public RegiaoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_regiao, parent, false);

        RegiaoViewHolder myViewHolder = new RegiaoViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RegiaoViewHolder holder, int position) {
        final Regiao regiao = regiaoList.get(position);

        holder.labelCodigo.setText(Integer.toString(regiao.getCodigo()));
        holder.labelRegiao.setText(regiao.getRegiao());
        holder.labelObservacao.setText(regiao.getObservacao());
    }

    @Override
    public int getItemCount() {
        return regiaoList.size();
    }
}
