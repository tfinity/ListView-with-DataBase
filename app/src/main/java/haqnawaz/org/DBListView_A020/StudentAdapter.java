package haqnawaz.org.DBListView_A020;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {
    private final Context context;
    int count=0;
    public StudentAdapter(@NonNull Context context, ArrayList<StudentModel> ListViewArray) {
        super(context, R.layout.view_list, ListViewArray);
        this.context=context;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        StudentModel list = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list, parent, false);
        RelativeLayout relative=convertView.findViewById(R.id.relative);
        TextView Name = convertView.findViewById(R.id.Name);
        TextView Rollnum = convertView.findViewById(R.id.RollNum);
        Button delete=convertView.findViewById(R.id.deleteButton);
        Button update=convertView.findViewById(R.id.updateButton);
        Button cancel=convertView.findViewById(R.id.cancelButton);
        EditText nameInput=convertView.findViewById(R.id.nameInput);
        Switch enrolledSwitch=convertView.findViewById(R.id.enrolledfSwitch);
        //TextView status = convertView.findViewById(R.id.Status);
        ImageView img = convertView.findViewById(R.id.img);
        Name.setText( list.getName());

        Rollnum.setText( Integer.toString(list.getRollNmber()));
        img.setImageResource(R.drawable.ic_baseline_person_24);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper  = new DBHelper(context);
                dbHelper.removeStudent(list);
                remove(list);
                //MainActivity ma = new MainActivity();
                //Log.d("error check", ma.toString());
                //ma.callSet();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count++;
                Log.d("count check", "onClick: "+count);
                if(count==1){
                    nameInput.setText(list.getName());
                    if (list.isEnroll()) {
                        enrolledSwitch.setChecked(true);
                    } else {
                        enrolledSwitch.setChecked(false);
                    }
                    delete.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    Name.setVisibility(View.GONE);
                    Rollnum.setVisibility(View.GONE);
                    nameInput.setVisibility(View.VISIBLE);
                    enrolledSwitch.setVisibility(View.VISIBLE);
                }else if(count==2){
                    DBHelper dbHelper  = new DBHelper(context);
                    int enrolled=enrolledSwitch.isChecked()?1:0;
                    dbHelper.updateStudent(list,String.valueOf(nameInput.getText()),enrolled);
                    Name.setText(String.valueOf(nameInput.getText()));
                    if(enrolledSwitch.isChecked()){
                        relative.setBackgroundColor(Color.GREEN);
                        delete.setBackgroundColor(Color.RED);
                        update.setBackgroundColor(Color.WHITE);
                        list.setEnroll(true);
                    }else{
                        relative.setBackgroundColor(Color.RED);
                        delete.setBackgroundColor(Color.GREEN);
                        update.setBackgroundColor(Color.WHITE);
                        list.setEnroll(false);
                    }

                    delete.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    Name.setVisibility(View.VISIBLE);
                    Rollnum.setVisibility(View.VISIBLE);
                    nameInput.setVisibility(View.GONE);
                    enrolledSwitch.setVisibility(View.GONE);
                    count=0;
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=0;
                cancel.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                Rollnum.setVisibility(View.VISIBLE);
                nameInput.setVisibility(View.GONE);
                enrolledSwitch.setVisibility(View.GONE);

            }
        });
        if(list.isEnroll()){
            //status.setText("Enrolled");
            relative.setBackgroundColor(Color.GREEN);
            delete.setBackgroundColor(Color.RED);
            update.setBackgroundColor(Color.WHITE);


        }
        else{
            //status.setText("Not Enrolled");
            relative.setBackgroundColor(Color.RED);
            delete.setBackgroundColor(Color.GREEN);
            update.setBackgroundColor(Color.WHITE);

        }

        return convertView;
    }
}