package com.volkanunlu.softtech_task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.volkanunlu.softtech_task.databinding.RecyclerRowBinding;
import com.volkanunlu.softtech_task.model.UserEntityModel;
import com.volkanunlu.softtech_task.model.UserResponseModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    List<UserEntityModel> userList;

    public UserAdapter(List<UserEntityModel> userList){ //constructor metodu
        this.userList=userList;
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        RecyclerRowBinding recyclerRowBinding;

        public UserHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;

        }
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //UserHolder dönecek olan alan

        //xml ile kodu bağlama olayımız. Bunun için de Layout Inflater kullanıyoruz.
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {  //Recycler tarafında kayıtları göstermek istiyorum.

        holder.recyclerRowBinding.textName.setText(userList.get(position).userName);
        holder.recyclerRowBinding.textSurname.setText(userList.get(position).userSurname);
        holder.recyclerRowBinding.textEmail.setText(userList.get(position).email);
        holder.recyclerRowBinding.textAge.setText(userList.get(position).age);
        holder.recyclerRowBinding.textCity.setText(userList.get(position).city);
    }

    @Override
    public int getItemCount() {
        return userList.size();  //kaç kullanıcı varsa dönecek
    }

}

