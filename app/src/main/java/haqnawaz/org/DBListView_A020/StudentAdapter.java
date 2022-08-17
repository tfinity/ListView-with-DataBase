package haqnawaz.org.DBListView_A020;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {
    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> ListViewArray) {
        super(context, R.layout.view_list, ListViewArray);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list, parent, false);
        RelativeLayout relative=convertView.findViewById(R.id.relative);
        TextView Name = convertView.findViewById(R.id.Name);
        TextView Rollnum = convertView.findViewById(R.id.RollNum);
        //TextView status = convertView.findViewById(R.id.Status);
        ImageView img = convertView.findViewById(R.id.img);
        Name.setText( list.getName());
        Rollnum.setText( Integer.toString(list.getRollNmber()));
        img.setImageResource(R.drawable.ic_baseline_person_24);
        if(list.isEnroll()){
            //status.setText("Enrolled");
            relative.setBackgroundColor(Color.GREEN);
        }
        else{
            //status.setText("Not Enrolled");
            relative.setBackgroundColor(Color.RED);
        }

        return convertView;
    }
}