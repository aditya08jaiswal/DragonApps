package com.iam844.finddragonapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder>{

    Context context1;
    List<String> stringList;

    public AppsAdapter(Context context, List<String> list){
        context1 = context;
        stringList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public ImageView imageView;
        public TextView textViewAppName;
        public ImageButton buttonRemoveApk;

        public ViewHolder (View view){
            super(view);
            cardView = view.findViewById(R.id.card_view);
            imageView = view.findViewById(R.id.image_view);
            textViewAppName = view.findViewById(R.id.apk_name);
            buttonRemoveApk = view.findViewById(R.id.remove_apk);
        }
    }

    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view2 = LayoutInflater.from(context1).inflate(R.layout.cardview_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position){

        ApkInfoExtractor apkInfoExtractor = new ApkInfoExtractor(context1);

        final String applicationPackageName = stringList.get(position);
        final String applicationLabelName = apkInfoExtractor.getAppName(applicationPackageName);
        Drawable drawable = apkInfoExtractor.getAppIconByPackageName(applicationPackageName);

        viewHolder.textViewAppName.setText(applicationLabelName);
        viewHolder.imageView.setImageDrawable(drawable);
        viewHolder.buttonRemoveApk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+applicationPackageName));
                if(intent != null){
                    context1.startActivity(intent);
                }
                else {
                    Toast.makeText(context1,applicationPackageName + " Error, Please Try Again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return stringList.size();
    }

}
