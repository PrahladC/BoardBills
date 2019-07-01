package in.peecee.boardbills;

import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SaveDetails {

    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}

    public void show(String tempstring)
    {
        Toast.makeText(MA,tempstring,Toast.LENGTH_SHORT).show();
    }

    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;

    String  College="SIWS College",
            Index="J-31.04.005",MonthYear="Feb-2020",
            Strim="Science", Subject="Mathematics",SubjectCode="40",
            Type="Practical",  Email1="",Email2="";

    String tempstr;

    int i;
    String tmpStr;
    String txtData = "\n";

//    txtData+="School        : ";txtData+=MA.CD.College;         txtData+='\n';
    private void SaveToFile(){
        String FileNameWithPath="/sdcard/";
        FileNameWithPath+="test.rmb";
        SaveList(FileNameWithPath);
        show("TEST");
    }
    private void SaveList(String fwithpath) {
        String tempstr;

        int i;
        String tmpStr;
        String txtData = "\n";

       txtData+="School        : ";txtData+=MA.CD.College;         txtData+='\n';

    }




}
