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
//    collegedetails CD = new collegedetails();
    Msg msg =new Msg();

    String fileName="BoardBills.rmb";

    String  College="S. I. W. S. College", Index="J-31.04.005",
            MonthYear="Feb-2020", Strim="Science", Subject="Mathematics",
            Internal = "Internal Examiner", AddLine1 = "Address Line 1",
            AddLine2 = "Address Line 2", Email1="pc@gmail,com",Email2="mo@gmail.com";

    String  EExaminerName, EcolNem, EIndexNo, EAdressLine1, EAdressLine2,
            EStartDet, EEndDet, ENoExam, EStudentNo, ERemperStudent;

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
        txtData += "Internal Examiner and College Details";                 txtData += '\n';
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

        txtData += "Examiner Name  : ";           txtData += MA.EExaminerName.getText().toString(); txtData += '\n';
        txtData += "College Name  : ";            txtData += MA.EcolNem.getText().toString();  txtData += '\n';
        txtData += "ExtCollege Index No.  : ";    txtData += MA.EIndexNo.getText().toString(); txtData += '\n';
        txtData += "Address Line 1  : ";          txtData += MA.EAdressLine1.getText().toString();  txtData += '\n';
        txtData += "Address Line 2  : ";          txtData += MA.EAdressLine2.getText().toString(); txtData += '\n';
        txtData += "Start Date  : ";              txtData += MA.EStartDet.getText().toString();  txtData += '\n';
        txtData += "End Date  : ";                txtData += MA.EEndDet.getText().toString(); txtData += '\n';
        txtData += "Exam not conducted on  : ";   txtData += MA.ENoExam.getText().toString();  txtData += '\n';
        txtData += "No of students examined  : "; txtData += MA.EStudentNo.getText().toString(); txtData += '\n';
        txtData += "Remuneration perStudent  : "; txtData += MA.ERemperStudent.getText().toString();  txtData += '\n';

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


     void LoadFile() {

         String FileNameWithPath=Environment.getExternalStorageDirectory().getPath();
         FileNameWithPath+="/"+fileName;

         File extStore = Environment.getExternalStorageDirectory();
        // ==> /storage/emulated/0/MyTT.txt
//        String path = extStore.getAbsolutePath() + "/" + fileName;
//        Log.i("ExternalStorageDemo", "Read file: " + FileNameWithPath);

        String s = "";
        String fileContent = "";
        try {
            File myFile = new File(FileNameWithPath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

//  (aDataRow = myReader.readLine())
//            while ((aDataRow = myReader.readLine()) != null) {
//                fileContent += aDataRow + "\n";
//            }

            String aDataRow = "";
            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// Internal Examiner and College Details

            String temp[],stemp;

            stemp= myReader.readLine();
            temp=stemp.split(":");
            College=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Index=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            MonthYear=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Strim=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Subject=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Internal=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            AddLine1=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            AddLine2=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email1=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email2=temp[1].trim();

            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// Reserved Line
            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// External Examiner and College Details


            stemp= myReader.readLine();
            temp=stemp.split(":");
            EExaminerName=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EcolNem=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EIndexNo=temp[1].trim();
            stemp= myReader.readLine();
            temp=stemp.split(":");
            EAdressLine1=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EAdressLine2=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EStartDet=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EEndDet=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            ENoExam=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            EStudentNo=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            ERemperStudent=temp[1].trim();




            msg.Show(EExaminerName, MA);
            msg.Show(EcolNem, MA);
            msg.Show(EIndexNo,MA);
            msg.Show(EAdressLine1,MA);
            msg.Show(EAdressLine2, MA);
            msg.Show(EStartDet, MA);
            msg.Show(EEndDet,MA);
            msg.Show(ENoExam,MA);
            msg.Show(EStudentNo,MA);
            msg.Show(ERemperStudent,MA);




            while ((aDataRow = myReader.readLine()) != null) {
                fileContent += aDataRow + "\n";
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
