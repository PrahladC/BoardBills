package in.peecee.boardbills;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

public class ExtExaminerDetails {

    String  ExName;

    String tempstr;

    public void ExternalExaminerdetails(final Context context)
        {

          final Dialog myDialog;
          myDialog =  new Dialog(context);
          myDialog.setContentView(R.layout.content_main);
          myDialog.setCancelable(true);

            final EditText EExaminerNem = (EditText) myDialog.findViewById(R.id.NemExaminer);
            EExaminerNem.setText(ExName);



         tempstr=EExaminerNem.getText().toString(); ExName=tempstr;
    }

}