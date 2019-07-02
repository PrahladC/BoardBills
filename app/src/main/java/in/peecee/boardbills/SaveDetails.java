package in.peecee.boardbills;

import android.os.Environment;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SaveDetails {

    private MainActivity MA;
    collegedetails CD = new collegedetails();

    void SetMA(MainActivity MA) { this.MA = MA; }

    public void show(String tempstring) {
        Toast.makeText(MA, tempstring, Toast.LENGTH_SHORT).show();
    }

    boolean modified = false, NewNow = false, selectall = false, end = false, OpenNow = false;

    String College = "SIWS College",
            Index = "J-31.04.005", MonthYear = "Feb-2020",
            Strim = "Science", Subject = "Mathematics", SubjectCode = "40",
            Email1 = "", Email2 = "";

    String FileNameWithPath ;

    String tempstr;

    int i;
    String tmpStr;
    String txtData = "\n";

    public  void SaveToFile() {

        FileNameWithPath= Environment.getExternalStorageDirectory().getPath();
//              String FileNameWithPath = "/sdcard/";
        FileNameWithPath += "/"+"Bills.rmb";

//        String FileNameWithPath = "/sdcard/";
//        FileNameWithPath += "Bills.rmb";
        SaveList();
    }

    public void SaveList() {
        int i;
        String tmpStr;
        String txtData = "\n";

        txtData += "College Name        : "; txtData += CD.College;     txtData += '\n';

        txtData += "College Name        : "; txtData += MA.CD.College;     txtData += '\n';
//        txtData += "Index Number        : "; txtData += MA.CD.Index;       txtData += '\n';
//        txtData += "Month and Year      : "; txtData += MA.CD.MonthYear;   txtData += '\n';
//        txtData += "Stream              : "; txtData += MA.CD.Strim;       txtData += '\n';
//        txtData += "Subject             : "; txtData += MA.CD.Subject;     txtData += '\n';
//        txtData += "Subject Code        : "; txtData += MA.CD.SubjectCode; txtData += '\n';
//        txtData+="\n";
//        txtData+="=== Reserved Line ====\n";
//        txtData+="\n";



      try
          {
//            FileNameWithPath= Environment.getExternalStorageDirectory().getPath();
//              String FileNameWithPath = "/sdcard/";
//            FileNameWithPath += "/"+"Bills.rmb";

            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtData);
            myOutWriter.close();
            fOut.close();
            MA.modified = false;
            show("Saved on SD card");

          } catch (FileNotFoundException e) {
              e.printStackTrace();
            }

            catch (Exception e){
            Toast.makeText(MA, e.getMessage(),
                Toast.LENGTH_SHORT).show();
            }
        MA.finish();
  }

}
