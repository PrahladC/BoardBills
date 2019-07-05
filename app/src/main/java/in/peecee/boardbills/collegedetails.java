package in.peecee.boardbills;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class collegedetails {

//    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;

    String  College="S. I. W. S. College", Index="J-31.04.005",
            MonthYear="Feb-2020", Strim="Science", Subject="Mathematics",
            Internal = "Internal Examiner", AddLine1 = "Address Line 1",
            AddLine2 = "Address Line 2", Email1="pc@gmail,com",Email2="mo@gmail.com";

    String tempstr;

    void SetIntlCollegeDetails(final Context context)
    {
        final Dialog myDialog;
        myDialog =  new Dialog(context);
        myDialog.setContentView(R.layout.collegedetails_main);
        myDialog.setCancelable(true);
//        myDialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;


        final EditText PCollege = (EditText) myDialog.findViewById(R.id.COLLEGE);
        PCollege.setText(College);

        final EditText PIndex = (EditText) myDialog.findViewById(R.id.INDEX);
        PIndex.setText(Index);

        final EditText PMonthyear = (EditText) myDialog.findViewById(R.id.MONTHYEAR);
        PMonthyear.setText(MonthYear);

        final EditText PStrim = (EditText) myDialog.findViewById(R.id.STRIM);
        PStrim.setText(Strim);

        final EditText PSubject = (EditText) myDialog.findViewById(R.id.SUBJECT);
        PSubject.setText(Subject);

        final EditText PInternal = (EditText) myDialog.findViewById(R.id.INTERNAL);
        PInternal.setText(Internal);

        final EditText PAddLine1 = (EditText) myDialog.findViewById(R.id.ADDRESSLINE1);
        PAddLine1.setText(AddLine1);

        final EditText PAddLine2 = (EditText) myDialog.findViewById(R.id.ADDRESSLINE2);
        PAddLine2.setText(AddLine2);

        final EditText PEmail1 = (EditText) myDialog.findViewById(R.id.EMAIL1);
        PEmail1.setText(Email1);

        final EditText PEmail2 = (EditText) myDialog.findViewById(R.id.EMAIL2);
        PEmail2.setText(Email2);

        Button buttoncancel = (Button) myDialog.findViewById(R.id.BtnCancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {myDialog.dismiss();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });

        Button buttonok = (Button) myDialog.findViewById(R.id.BtnUpdate);
        buttonok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                tempstr=PCollege.getText().toString(); College=tempstr;
                tempstr=PIndex.getText().toString();  Index=tempstr;
                tempstr=PMonthyear.getText().toString(); MonthYear=tempstr;
                tempstr=PStrim.getText().toString();  Strim=tempstr;
                tempstr=PSubject.getText().toString();  Subject=tempstr;
                tempstr=PInternal.getText().toString(); Internal=tempstr;
                tempstr=PAddLine1.getText().toString(); AddLine1=tempstr;
                tempstr=PAddLine2.getText().toString(); AddLine2=tempstr;
                tempstr=PEmail1.getText().toString();  Email1=tempstr;
                tempstr=PEmail2.getText().toString();  Email2=tempstr;

                SharedPreferences settings = context.getSharedPreferences("COLLEGE DETAILS", 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("College",College);
                editor.putString("Index", Index);
                editor.putString("MonthYear",MonthYear);
                editor.putString("Strim", Strim);
                editor.putString("Subject", Subject);
                editor.putString("Internal Examiner", Internal);
                editor.putString("Address Line1", AddLine1);
                editor.putString("Address Line2", AddLine2);
                editor.putString("Email1", Email1);
                editor.putString("Email2", Email2);
                editor.commit();


                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(PCollege.getWindowToken(),0);

                myDialog.dismiss();

            }
        });

        myDialog.show();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


    void LoadCollegeDetails(final Context context)
    {
        ////load preferences
        SharedPreferences settings = context.getSharedPreferences("COLLEGEDETAILS", 0);

        College=settings.getString("College",College);
        Index=settings.getString("Index", Index);
        MonthYear=settings.getString("MonthYear",MonthYear);
        Strim=settings.getString("Strim", Strim);
        Subject=settings.getString("Subject", Subject);
        Internal=settings.getString("Internal Examiner", Internal);
        AddLine1=settings.getString("Address LIne 1", AddLine1);
        AddLine2=settings.getString("Address LIne 2", AddLine2);
        Email1=settings.getString("Email1", Email1);
        Email2=settings.getString("Email2", Email2);



    }


}
