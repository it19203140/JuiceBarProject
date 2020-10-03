package com.example.inquiry300;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InquiryAdapterClass extends RecyclerView.Adapter<InquiryAdapterClass.ViewHolder> {

    List<InquiryModelClass> inquiry;
    Context context;
    InquiryDatabaseHelperClass databaseHelperClass;

    public InquiryAdapterClass(List<InquiryModelClass> inquiry, Context context) {
        this.inquiry = inquiry;
        this.context = context;
        databaseHelperClass = new InquiryDatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inquiry_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final InquiryModelClass inquiryModelClass = inquiry.get(position);

        holder.textViewID.setText(Integer.toString(inquiryModelClass.getId()));
        holder.editText_Inquiry.setText(inquiryModelClass.getInquiry());
        holder.editText_Email.setText(inquiryModelClass.getEmail());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringInquiry = holder.editText_Inquiry.getText().toString();
                String stringEmail = holder.editText_Email.getText().toString();

                databaseHelperClass.updateInquiry(new InquiryModelClass(inquiryModelClass.getId(),stringInquiry,stringEmail));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteInquiry(inquiryModelClass.getId());
                inquiry.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return inquiry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Inquiry;
        EditText editText_Email;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Inquiry = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
