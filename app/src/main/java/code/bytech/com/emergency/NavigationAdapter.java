package code.bytech.com.emergency;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import code.bytech.com.emergency.events.DrawerItemClicked;
import code.bytech.com.emergency.utils.EventBus;


/**
 * Created by samjunior on 8/14/15.
 * this
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private String navTitles[];
    private int icons[];
    private String name;
    private int profile;
    private String email;
    private Context context;

    private LayoutInflater inflater;
    public NavigationAdapter(String[] navTitles, int[] icons, String name, String email,int profile,Context context) {

        this.navTitles = navTitles;
        this.icons = icons;
        this.name = name;
        this.profile = profile;
        this.email = email;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(navTitles[position]);
        holder.imageView.setImageResource(icons[position]);
   //    holder.profile.setImageResource(profile);
//        holder.email.setText(email);
 //       holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return navTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView name;
        TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            profile=(ImageView)itemView.findViewById(R.id.circleView);
            textView=(TextView)itemView.findViewById(R.id.rowText);
            imageView=(ImageView)itemView.findViewById(R.id.rowIcon);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
