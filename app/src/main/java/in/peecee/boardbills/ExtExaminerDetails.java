package in.peecee.boardbills;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

public class ExtExaminerDetails {

    String  ExName = "Dilip Singh", ColNem = "M.D. College";

    String tempstr;

    public void ExternalExaminerdetails(final Context context)
        {

          final Dialog myDialog;
          myDialog =  new Dialog(context);
          myDialog.setContentView(R.layout.content_main);
          myDialog.setCancelable(true);

//            final EditText EExaminerNem = (EditText) myDialog.findViewById(R.id.NemExaminer);
//            EExaminerNem.setText(ExName);
            EditText ExaminerName = (EditText) myDialog.findViewById(R.id.NemExaminer);
//            final EditText ECollegename = (EditText) findViewById(R.id.ExaminerColNem);
//            ECollegename.setText(ExName);

            EditText ECollegename = (EditText) myDialog.findViewById(R.id.ExaminerColNem);


            SharedPreferences settings = context.getSharedPreferences("EXAMINER DETAILS", 0);
            SharedPreferences.Editor editor = settings.edit();


            tempstr=ExaminerName.getText().toString(); ExName=tempstr;
            tempstr=ECollegename.getText().toString(); ColNem=tempstr;

            editor.putString("Examiner Name",ExName);
            editor.putString("College Name", ColNem);

            editor.apply();

        }

}