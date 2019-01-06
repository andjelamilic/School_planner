package nsi.schoolplanner.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nsi.schoolplanner.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setNavigationView();
        setFloatingActionButton();
        setExams();
    }

    private void setNavigationView(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        toggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFloatingActionButton(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NewExamActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setExams(){

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.timetable:{
                Intent intent = new Intent(this, TimeTableActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.calendar:{
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.grades:{
                Intent intent = new Intent(this, GradesActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.subjects:{
                Intent intent = new Intent(this, SubjectActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.newSemestar:{
                startDeleteDialog();
                break;
            }

            case R.id.weekPlans:{
                Intent intent = new Intent(this, WeekPlanActivity.class);
                startActivity(intent);
                break;
            }
        }

        return true;
    }

    private void startDeleteDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.delete_dialog);
        TextView txt = dialog.findViewById(R.id.txtText);
        TextView txtOk = dialog.findViewById(R.id.txtOk);
        TextView txtCancel = dialog.findViewById(R.id.txtCancel);

        txt.setText(getResources().getString(R.string.areYouSureAll));

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
            setExams();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
