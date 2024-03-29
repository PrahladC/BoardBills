package in.peecee.boardbills;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    collegedetails CD = new collegedetails();
  //  ExtExaminerDetails EED = new ExtExaminerDetails();
    FileInputOutput FIO=new FileInputOutput();
    Msg msg =new Msg();
    /////////////Show Msg Functions /////////////////////////////////////


    EditText EExaminerName, EcolNem, EIndexNo, EAdressLine1, EAdressLine2;
    EditText EStartDet, EEndDet, ENoExam, EStudentNo, ERemperStudent;


    public void show(int tempnum)
    {
        Toast.makeText(getBaseContext(),String.valueOf(tempnum),Toast.LENGTH_SHORT).show();
    }

    public void show(String tempstring)
    {
        Toast.makeText(getBaseContext(),tempstring,Toast.LENGTH_SHORT).show();
    }

//////////////////////////////////////////////////////////////////

    boolean modified=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FIO.SetMA(this);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });   */

    EExaminerName = (EditText) findViewById(R.id.NemExaminer);
    EcolNem = (EditText) findViewById(R.id.ExaminerColNem);
    EIndexNo = (EditText) findViewById(R.id.ExaminerColIndex);
    EAdressLine1 = (EditText) findViewById(R.id.PostalAddressLine1);
    EAdressLine2 = (EditText) findViewById(R.id.PostalAddressLine2);
    EStartDet = (EditText) findViewById(R.id.ExamStartDet);
    EEndDet = (EditText) findViewById(R.id.ExamEndDet);
    ENoExam = (EditText) findViewById(R.id.NoExamDet);
    EStudentNo = (EditText) findViewById(R.id.NumOfStudents);
    ERemperStudent = (EditText) findViewById(R.id.RemPerStudent);

        Button Exit = (Button) findViewById(R.id.button);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Closing Application !")
                        .setMessage("The file you have opened is altered. Would you like to " +
                                "close this with out saving ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });


        CD.LoadCollegeDetails(this);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(!StoragePermissionGranted()) ;
        if(!StoragePermissionGranted()) finish();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new)
        {   Msg.Show("We have to start with new questionaire ",this);

            return true;
        }

        if (id == R.id.action_load) {
//            if(!StoragePermissionGranted())
//                Msg.Show("No Read Permission",this);
//            else
                FIO.LoadFile();
            show("File Loaded");
            return true;
        }

        if (id == R.id.action_save)
        {  if(!StoragePermissionGranted())
            Msg.Show("No Write Permission",this);
          else
            FIO.WriteFile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

/*        if (id == R.id.nav_header) {
            // Handle the camera action
        } else if (id == R.id.nav_header) { EditSettings();

        }  else if (id == R.id.nav_internal) { show("Internal Examiner's Remuneration Bill Created");

        } else if (id == R.id.nav_external) { show("External Examiner's Remuneration Bill Created");

        } else if (id == R.id.nav_tada) {  show("TA, DA Form Created");

        } else if (id == R.id.nav_relieve) { show("Relieveing Order Created");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }  */

        switch(id)
        {
            case R.id.nav_header:  EditSettings();  break;
            case R.id.nav_internal : show("Internal Examiner's Remuneration Bill Created"); break;  // PDF
            case R.id.nav_external : show("External Examiner's Remuneration Bill Created");  break;  // PDF
            case R.id.nav_tada :     show("TA, DA Form Created");    break;  // PDF
            case R.id.nav_relieve :  show("Relieveing Order Created");  break;  // PDF
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /////////////////// Storage Permission //////////////////////////////////////

    public  boolean StoragePermissionGranted()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Msg.Show("Permission Granted",this);
            //  Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    void EditSettings()
    {
        CD.SetIntlCollegeDetails(this);

    }


}
