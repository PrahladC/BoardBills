package in.peecee.boardbills;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

public class ExtExaminerDetails {

    String  ExName = "";

    String tempstr;

    public void ExternalExaminerdetails(final Context context)
        {

          final Dialog myDialog;
          myDialog =  new Dialog(context);
          myDialog.setContentView(R.layout.content_main);
          myDialog.setCancelable(true);

            final EditText FExaminerNem = (EditText) myDialog.findViewById(R.id.NemExaminer);
            FExaminerNem.setText(ExName);

         }

}