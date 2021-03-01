import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.RecyclerHolder>
{
    public List<Response_data> responses;
    Context context;


    public CountryRecyclerViewAdapter(List<Response_data> responses, Context context) {
        this.responses = responses;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        final Response_data temp=responses.get(position);
        holder.text_View.setText(responses.get(position).name);
        holder.text_View.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,temp.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView text_View;
        public RecyclerHolder(@NonNull View view) {
            super(view);
            text_View=view.findViewById(R.id.textview);
        }
    }
}
