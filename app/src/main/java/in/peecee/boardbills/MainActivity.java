package in.peecee.boardbills;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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

    String FileNameWithPath = "/sdcard/";
    SaveDetails SD = new SaveDetails();
    collegedetails CD = new collegedetails();
    ExtExaminerDetails EED = new ExtExaminerDetails();
    /////////////Show Msg Functions /////////////////////////////////////

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

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });   */

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

        switch(id)
        {
            case R.id.action_new  : show("We shall start with New Questionair"); return true;
            case R.id.action_load : show("File Loaded"); ; return true;
            case R.id.action_save : show("File Saved"); SD.SaveToFile(); return true;
        }

/*        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            show("We have to start with New Questionair");
            return true;
        }

        if (id == R.id.action_load) {
            show("File Loaded");
            return true;
        }

        if (id == R.id.action_save) {
            SD.SaveToFile();
            show("File Saved");
            return true;
        }
  */

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

    private void SaveToFile(){
        String FileNameWithPath="/sdcard/";
        FileNameWithPath+="BoardBills.rmb";
        SaveList(FileNameWithPath);
        show("TEST");
        if(!StoragePermissionGranted()) ;
        if(!StoragePermissionGranted()) finish();

    }
    private void SaveList(String fwithpath) {
        String tmpStr;
//        String txtData = "Hello World Super World !!!";
        EditText name = (EditText) findViewById(R.id.NemExaminer);

        try {
            File myFile = new File(fwithpath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
//            myOutWriter.append(txtData);
            myOutWriter.append(name.getText());
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    /////////////////// Storage Permission //////////////////////////////////////

    public  boolean StoragePermissionGranted() {
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
            //    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }


    void EditSettings()
    {
        CD.SetIntlCollegeDetails(this);

    }


}
