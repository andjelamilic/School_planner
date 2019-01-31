package nsi.schoolplanner.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import nsi.schoolplanner.Activities.AllSubjectsActivity;
import nsi.schoolplanner.Activities.SubjectActivity;
import nsi.schoolplanner.Activities.TimeTableActivity;
import nsi.schoolplanner.Model.DBManagerSingletone;
import nsi.schoolplanner.Model.Subject;
import nsi.schoolplanner.R;

public class ChooseSubjectAdapter extends RecyclerView.Adapter<ChooseSubjectAdapter.ViewHolder> {

    private List<Subject>subjects;
    public static Context context;
    public static RadioButton lastCheckedRB = null;

    public ChooseSubjectAdapter(Context context, List<Subject> subjects){

        this.context=context;
        this.subjects=subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_subject_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.subject.setText(subjects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private RadioButton subject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subject=itemView.findViewById(R.id.radio_subject);

            subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(lastCheckedRB != null){
                        lastCheckedRB.setChecked(false);
                    }
                    lastCheckedRB = subject;
                    TimeTableActivity.subject=lastCheckedRB.getText().toString();
                }
            });


        }

    }
}
