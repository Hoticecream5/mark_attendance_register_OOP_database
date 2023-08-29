package joe.com.jiujitsu_nwu;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<UserHelperClass> mList;
    Context context;

    public MyAdapter(){

    }

    public MyAdapter(Context context , ArrayList<UserHelperClass> mList){

        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserHelperClass userHelperClass = mList.get(position);

        holder.studentNumber.setText(userHelperClass.getSTUDENT_NUMBER());
        holder.date.setText(userHelperClass.getDATE());
        holder.time.setText(userHelperClass.getTIME());

    }

    @Override
    public int getItemCount() {return mList.size();
    }

    public void searchDataList(ArrayList<UserHelperClass> searchList){
        mList = searchList;
        notifyDataSetChanged();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView studentNumber,date,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            studentNumber = itemView.findViewById(R.id.student_NumTxtVw);
            time = itemView.findViewById(R.id.timeTxtVw);
            date = itemView.findViewById(R.id.dateTxtVw);
        }
    }
}

