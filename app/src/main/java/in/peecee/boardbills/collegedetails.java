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

    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;

    String  College="SIWS College",
            Index="J-31.04.005",MonthYear="Feb-2020",
            Strim="Science", Subject="Mathematics",SubjectCode="40",
            Type="Practical",  Email1="",Email2="";

    String tempstr;

    void SetIntlCollegeDetails(final Context context)
    {
        final Dialog myDialog;
        myDialog =  new Dialog(context);
        myDialog.setTitle("Save Batch Preferences");
        myDialog.setContentView(R.layout.collegedetails_main);
        myDialog.setCancelable(true);
        myDialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;


        final EditText FSchool = (EditText) myDialog.findViewById(R.id.COLLEGE);
        FSchool.setText(College);

        final EditText FIndex = (EditText) myDialog.findViewById(R.id.INDEX);
        FIndex.setText(Index);

        final EditText FMonthyear = (EditText) myDialog.findViewById(R.id.MONTHYEAR);
        FMonthyear.setText(MonthYear);

        final EditText FStrim = (EditText) myDialog.findViewById(R.id.STRIM);
        FStrim.setText(Strim);

        final EditText FSubject = (EditText) myDialog.findViewById(R.id.SUBJECT);
        FSubject.setText(Subject);

        final EditText FSubcode = (EditText) myDialog.findViewById(R.id.SUBCODE);
        FSubcode.setText(SubjectCode);

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

                tempstr=FMonthyear.getText().toString(); MonthYear=tempstr;
                tempstr=FSchool.getText().toString(); College=tempstr;
                tempstr=FIndex.getText().toString();  Index=tempstr;
                tempstr=FStrim.getText().toString();  Strim=tempstr;
                tempstr=FSubject.getText().toString();  Subject=tempstr;
                tempstr=FSubcode.getText().toString(); SubjectCode=tempstr;
                tempstr=FEmail1.getText().toString();  Email1=tempstr;
                tempstr=FEmail2.getText().toString();  Email2=tempstr;

                SharedPreferences settings = context.getSharedPreferences("COLLEGE DETAILS", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("MonthYear",MonthYear);
                editor.putString("College",College);
                editor.putString("Index", Index);
                editor.putString("Strim", Strim);
                editor.putString("Subject", Subject);
                editor.putString("SubjectCode", SubjectCode);
                editor.putString("Type", Type);
                editor.putString("Email1", Email1);
                editor.putString("Email2", Email2);
                editor.commit();


                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FSchool.getWindowToken(),0);

                myDialog.dismiss();

            }
        });

        myDialog.show();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


    void LoadPreferrences(final Context context)
    {
        ////load preferences
        SharedPreferences settings = context.getSharedPreferences("COLLEGEDETAILS", 0);

//        Zone=settings.getString("Zone",Zone);
        MonthYear=settings.getString("MonthYear",MonthYear);
        College=settings.getString("School",College);
        Index=settings.getString("Index", Index);
        Strim=settings.getString("Strim", Strim);
//        Standard=settings.getString("Standard", Standard);
        Subject=settings.getString("Subject", Subject);
        SubjectCode=settings.getString("SubjectCode", SubjectCode);
//        Medium=settings.getString("Medium", Medium);
//        Type=settings.getString("Type", Type);
        Email1=settings.getString("Email1", Email1);
        Email2=settings.getString("Email2", Email2);
//        BatchCreator=settings.getString("BatchCreator", BatchCreator);
    }


}
