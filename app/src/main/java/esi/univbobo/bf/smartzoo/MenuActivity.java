package esi.univbobo.bf.smartzoo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    private ImageView imageEnregistrer;
    private     ImageView imageListe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imageEnregistrer=(ImageView) findViewById(R.id.imageEnregistrer);
        imageListe=(ImageView) findViewById(R.id.imageListe);
        imageEnregistrer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Intent intent1 = new Intent(MenuActivity.this,EnregistreActivity.class);
                  //startActivity(intent1);
                Intent intent = new Intent(MenuActivity.this,ActivitySave.class);
                startActivity(intent);
            Log.i("DEBUG","OK CA PASSE");
            }
        });




        imageListe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuActivity.this,ListeActivity.class);

                startActivity(intent);

                Log.i("DEBUG","OK CA PASSE");

            }
        });


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
        AlertDialog alertDialog = new AlertDialog.Builder(MenuActivity.this).create();
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
