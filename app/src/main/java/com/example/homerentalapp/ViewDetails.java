package com.example.homerentalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetails extends AppCompatActivity {
    private String Rentername,Flatname,Contactnumber;
    private int rowid;
    private TextView renterNametv,flatNametv,contactNumbertv;
    private DatabaseSource databaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        databaseSource=new DatabaseSource(this);

        renterNametv=(TextView)findViewById(R.id.dettxt1);
        flatNametv=(TextView)findViewById(R.id.dettxt2);
        contactNumbertv=(TextView)findViewById(R.id.dettxt3);

        Rentername=getIntent().getStringExtra("rentername");
        Flatname=getIntent().getStringExtra("flatname");
        Contactnumber=getIntent().getStringExtra("contactnumber");
        rowid=getIntent().getIntExtra("id",0);

        renterNametv.setText(Rentername);
        flatNametv.setText(Flatname);
        contactNumbertv.setText(Contactnumber);
    }
    public void update(View view) {
        startActivity(new Intent(ViewDetails.this,AddRenter.class).putExtra("id",rowid)
                .putExtra("rentername",Rentername).putExtra("flatname",Flatname).
                        putExtra("contactnumber",Contactnumber));
    }

    public void delete(View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Delete item");
        alert.setMessage("Are you sure to delete this item?");
        alert.setPositiveButton("sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean status= databaseSource.deleteRenter(rowid);
                if (status){
                    Toast.makeText(ViewDetails.this,"Item deleted",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ViewDetails.this,ViewRenter.class));
                }else {
                    Toast.makeText(ViewDetails.this,"Couldn't deleted",Toast.LENGTH_SHORT).show();
                }

            }
        });
        alert.setNegativeButton("cancel",null);
        alert.show();



    }
}