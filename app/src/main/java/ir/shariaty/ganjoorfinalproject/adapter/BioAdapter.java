package ir.shariaty.ganjoorfinalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.shariaty.ganjoorfinalproject.R;
import ir.shariaty.ganjoorfinalproject.models.Biography;

public class BioAdapter extends RecyclerView.Adapter<BioAdapter.BioViewHolder> {

    private final List<Biography> biographies;
    private Context context;

    public BioAdapter(List<Biography> biographies, Context context) {
        this.context = context;
        this.biographies = biographies;
    }


    @NonNull
    @Override
    public BioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bio_item, parent, false);
        return new BioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BioViewHolder holder, int position) {
        Biography biography = biographies.get(position);

        // get string from resources
        String name = context.getResources().getString(R.string.name);
        String place = context.getResources().getString(R.string.place);
        String birthDate = context.getResources().getString(R.string.birthdate);
        String deathPlace = context.getResources().getString(R.string.deaddate);

        if (biography.getBirthPlace().equals("")) {
            holder.birthPlaceTv.setVisibility(View.GONE);
        } else {
            holder.birthPlaceTv.setVisibility(View.VISIBLE);
            holder.birthPlaceTv.setText(place + biography.getBirthPlace());
        }


        holder.nameTv.setText(name + biography.getName());
        holder.birthDateTv.setText(birthDate + biography.getBirthYearInLHijri());
        holder.deathDateTv.setText(deathPlace + biography.getDeathYearInLHijri());
    }

    @Override
    public int getItemCount() {
        return biographies.size();
    }

    public class BioViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView birthPlaceTv;
        private TextView birthDateTv;
        private TextView deathDateTv;

        public BioViewHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.txt_name);
            birthPlaceTv = itemView.findViewById(R.id.txt_birth_place);
            birthDateTv = itemView.findViewById(R.id.txt_birth_date);
            deathDateTv = itemView.findViewById(R.id.txt_death_date);
        }
    }
}
