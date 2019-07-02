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

    String  College="SIWS College",
            Index="J-31.04.005",MonthYear="Feb-2020", Internal = "Internal Examiner",
            AddLine1 = "Address Line 1", AddLine2 = "Address Line 2",
            Strim="Science", Subject="Mathematics",SubjectCode="40",
            Type="Practical",  Email1="",Email2="";

    String tempstr;

    void SetIntlCollegeDetails(final Context context)
    {
        final Dialog myDialog;
        myDialog =  new Dialog(context);
        myDialog.setContentView(R.layout.collegedetails_main);
        myDialog.setCancelable(true);
//        myDialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;


        final EditText FCollege = (EditText) myDialog.findViewById(R.id.COLLEGE);
        FCollege.setText(College);

        final EditText FIndex = (EditText) myDialog.findViewById(R.id.INDEX);
        FIndex.setText(Index);

        final EditText FMonthyear = (EditText) myDialog.findViewById(R.id.MONTHYEAR);
        FMonthyear.setText(MonthYear);

        final EditText FStrim = (EditText) myDialog.findViewById(R.id.STRIM);
        FStrim.setText(Strim);

        final EditText FSubject = (EditText) myDialog.findViewById(R.id.SUBJECT);
        FSubject.setText(Subject);

        final EditText FInternal = (EditText) myDialog.findViewById(R.id.INTERNAL);
        FInternal.setText(Internal);

        final EditText FAddLine1 = (EditText) myDialog.findViewById(R.id.ADDRESSLINE1);
        FAddLine1.setText(AddLine1);

        final EditText FAddLine2 = (EditText) myDialog.findViewById(R.id.ADDRESSLINE2);
        FAddLine2.setText(AddLine2);

        final EditText FEmail1 = (EditText) myDialog.findViewById(R.id.EMAIL1);
        FEmail1.setText(Email1);

        final EditText FEmail2 = (EditText) myDialog.findViewById(R.id.EMAIL2);
        FEmail2.setText(Email2);

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

                tempstr=FCollege.getText().toString(); College=tempstr;
                tempstr=FIndex.getText().toString();  Index=tempstr;
                tempstr=FMonthyear.getText().toString(); MonthYear=tempstr;
                tempstr=FStrim.getText().toString();  Strim=tempstr;
                tempstr=FSubject.getText().toString();  Subject=tempstr;
                tempstr=FInternal.getText().toString(); Internal=tempstr;
                tempstr=FAddLine1.getText().toString(); AddLine1=tempstr;
                tempstr=FAddLine2.getText().toString(); AddLine2=tempstr;
                tempstr=FEmail1.getText().toString();  Email1=tempstr;
                tempstr=FEmail2.getText().toString();  Email2=tempstr;

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
                imm.hideSoftInputFromWindow(FCollege.getWindowToken(),0);

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
