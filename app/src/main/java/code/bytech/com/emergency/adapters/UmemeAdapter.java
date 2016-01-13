package code.bytech.com.emergency.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import code.bytech.com.emergency.R;
import code.bytech.com.emergency.model.hospital.NavItem;

import java.util.List;

/**
 * Created by samjunior on 8/23/15.
 */
public class UmemeAdapter extends RecyclerView.Adapter<UmemeAdapter.ViewHolder> {
    private List<NavItem> navItems;
    private int rowItem;
    private Context context;

    public UmemeAdapter(List<NavItem> navItems, int rowItem, Context context) {
        this.navItems = navItems;
        this.rowItem = rowItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowItem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavItem item=navItems.get(position);
        holder.description.setText(item.description);
        holder.title.setText(item.title);
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, item.getImageResourceId(context)));
    }

    @Override
    public int getItemCount() {
        return navItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView imageView;
        private TextView description;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.hospitalName);
            imageView=(ImageView)itemView.findViewById(R.id.hospitalImage);
            description=(TextView)itemView.findViewById(R.id.description);

            title.setOnClickListener(this);
            imageView.setOnClickListener(this);
            description.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent callIntent= new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + navItems.get(getAdapterPosition()).tel));
            context.startActivity(callIntent);
        }
    }
}
