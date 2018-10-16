package esi.univbobo.bf.smartzoo;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ActivitySave extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private final int REQUEST_GALLERY_CODE=999;
    private EditText editPseudo;
    private EditText editEspece;
    private Button btnAdd;
    private ImageView imageView;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__save);
        editPseudo=(EditText)findViewById(R.id.editPseudo);
        editEspece=(EditText)findViewById(R.id.editEspece);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        imageView=(ImageView)findViewById(R.id.imageView);
        databaseManager = new DatabaseManager(this);


        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {


                ActivityCompat.requestPermissions(
                        ActivitySave.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_GALLERY_CODE


                );
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(isValidSave(editPseudo.getText().toString(),editEspece.getText().toString(),uri))
                {



                   byte[] imageDerive=imageViewToText(imageView);
                    AnimalRecord animal= new AnimalRecord(editPseudo.getText().toString(),editEspece.getText().toString(),imageDerive);
                    long test=databaseManager.insertAnimal(animal);
                    Toast.makeText(ActivitySave.this, R.string.strSucces,Toast.LENGTH_SHORT).show();
                    editPseudo.setText("");
                    editEspece.setText("");
                  //  imageView.;
                    Intent intent = new Intent(ActivitySave.this,ListeActivity.class);
                    startActivity(intent);



                }
                else
                {
                    Toast.makeText(ActivitySave.this, R.string.strWarmings,Toast.LENGTH_SHORT).show();



                }






            }
        });




    }



    public boolean isValidSave(String pseudo, String espece,Uri uriparam){
        if(uriparam!=null && !pseudo.equals("") && !espece.equals(""))
        {
            return true;
        }
        return false;
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== REQUEST_GALLERY_CODE)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_GALLERY_CODE);
            }
            else{
                Toast.makeText(this, R.string.strNotPermission,Toast.LENGTH_LONG).show();
            }
        }



        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_GALLERY_CODE && resultCode==RESULT_OK)
        {
             uri =data.getData();
            imageView.setImageURI(uri);

        }



        super.onActivityResult(requestCode, resultCode, data);
    }


    private static byte[] imageViewToText(ImageView image)
    {
        Bitmap bitmap= ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        byte[] byteArray=stream.toByteArray();
        return  byteArray;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_exit:
            {
                finish();
                return true;
            }
            case R.id.menu_about:
            {
                boiteDialog(getString(R.string.strAbout),getString(R.string.strMembre)+"\n"+getString(R.string.app_name));

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void boiteDialog(String title,String mesg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(ActivitySave.this).create();
        alertDialog.setTitle(title);

        alertDialog.setMessage(mesg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
