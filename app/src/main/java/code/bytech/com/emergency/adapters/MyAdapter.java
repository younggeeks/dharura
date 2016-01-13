package code.bytech.com.emergency.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import code.bytech.com.emergency.R;
import code.bytech.com.emergency.events.DrawerItemClicked;
import code.bytech.com.emergency.utils.EventBus;

/**
 * Created by samjunior on 7/15/15.
 */
public class MyAdapter extends RecyclerView.Adapter< MyAdapter.ViewHolder> {


    private Context context;
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;

    private String navTitles[];
    private int icons[];

    private String name;
    private int profile;
    private String email;

    public MyAdapter(String[] navTitles, int[] icons, String name, String email,int profile,Context context) {
        this.navTitles = navTitles;
        this.icons = icons;
        this.name = name;
        this.profile = profile;
        this.email = email;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType==TYPE_ITEM){
           View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
           ViewHolder vhItem=new ViewHolder(v,viewType,context);
           return vhItem;
       }
        else if(viewType==TYPE_HEADER){
           View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);
           ViewHolder vhItem=new ViewHolder(v,viewType,context);
           return vhItem;
       }

        return  null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder.HolderId==1){
            holder.textView.setText(navTitles[position-1]);
            holder.imageView.setImageResource(icons[position-1]);
        }else{
            holder.profile.setImageResource(profile);
            holder.email.setText(email);
            holder.name.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return navTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
                }

    private boolean isPositionHeader(int position) {
        return position==0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int HolderId;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView name;
        TextView email;

        public ViewHolder(View itemView,int viewType,Context c) {
            super(itemView);
            context=c;

            if (viewType==TYPE_ITEM){
                textView=(TextView)itemView.findViewById(R.id.rowText);
                imageView=(ImageView)itemView.findViewById(R.id.rowIcon);
                HolderId=1;
                textView.setOnClickListener(this);
                imageView.setOnClickListener(this);
                itemView.setOnClickListener(this);
            }
            else{
                name=(TextView)itemView.findViewById(R.id.name);
                email=(TextView)itemView.findViewById(R.id.email);
                profile=(ImageView)itemView.findViewById(R.id.circleView);
            }
        }

        @Override
        public void onClick(View view) {
            EventBus.getInstance().post(new DrawerItemClicked((String)textView.getText()));
        }
    }
}
