package in.peecee.boardbills;


import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileInputOutput extends AppCompatActivity
{   private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}
    collegedetails CD = new collegedetails();
    ExtExaminerDetails EED = new ExtExaminerDetails();
    String fileName="BoardBills.rmb";


    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

/*    void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //
        if (canWrite) {
            this.WriteFile();
        }
    }

    void askPermissionAndReadFile() {
        boolean canRead = this.askPermission(REQUEST_ID_READ_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        //
        if (canRead) {
            this.readFile();
        }
    }

    // With Android Level >= 23, you have to ask the user
    // for permission with device (For example read/write data on the device).
    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);


            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }

    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        // Note: If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_ID_READ_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        readFile();
                    }
                }
                case REQUEST_ID_WRITE_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        WriteFile();
                    }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }
*/

    void WriteFile()
    {
        String FileNameWithPath=Environment.getExternalStorageDirectory().getPath();
        FileNameWithPath+="/"+fileName;

        String txtData = "\n";
        txtData +="Internal Examiner and College Details";                  txtData += '\n';
        txtData += "College Name         : "; txtData += MA.CD.College;     txtData += '\n';
        txtData += "Index Number         : "; txtData += MA.CD.Index;       txtData += '\n';
        txtData += "Month and Year       : "; txtData += MA.CD.MonthYear;   txtData += '\n';
        txtData += "Stream               : "; txtData += MA.CD.Strim;       txtData += '\n';
        txtData += "Subject              : "; txtData += MA.CD.Subject;     txtData += '\n';
        txtData += "Internal examiner    : "; txtData += MA.CD.Internal;    txtData += '\n';
        txtData += "Address Line 1       : "; txtData += MA.CD.AddLine1;    txtData += '\n';
        txtData += "Address Line 2       : "; txtData += MA.CD.AddLine2;    txtData += '\n';
        txtData += "E-mail Address 1     : "; txtData += MA.CD.Email1;      txtData += '\n';
        txtData += "E-mail Address 2     : "; txtData += MA.CD.Email2;      txtData += '\n';
        txtData+="\n";
        txtData+="=== Reserved Line ====\n";
        txtData+="\n";
        txtData +="External Examiner and College Details";                  txtData += '\n';
        txtData += "External Examiner Name  : "; txtData += MA.EED.ExName;  txtData += '\n';
        txtData += "College Name  : ";           txtData += MA.EED.ColNem;  txtData += '\n';


        try {
            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtData);
            myOutWriter.close();
            fOut.close();
            Msg.Show("Saved on SD card", MA);


        } catch (Exception e)
        {
            Msg.Show(e.getMessage(),MA);
        }
    }




    private void readFile() {

        File extStore = Environment.getExternalStorageDirectory();
        // ==> /storage/emulated/0/MyTT.txt
        String path = extStore.getAbsolutePath() + "/" + fileName;
        Log.i("ExternalStorageDemo", "Read file: " + path);

        String s = "";
        String fileContent = "";
        try {
            File myFile = new File(path);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((s = myReader.readLine()) != null) {
                fileContent += s + "\n";
            }
            myReader.close();
            //  MA.show(fileContent);
            // this.textView.setText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //   local toast does not work here so use Main activity show
        MA.show(fileContent);
    }
}
