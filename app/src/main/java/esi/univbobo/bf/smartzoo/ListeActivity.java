package esi.univbobo.bf.smartzoo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListeActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseManager databaseManager;
    private TextView textResult;
    private List<AnimalRecord> listeAnimaux ;
    private TextView voirDabord;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        databaseManager = new DatabaseManager(this);
        listeAnimaux=databaseManager.getAnimal();



        CustomAdapter customAdapter=new CustomAdapter(listeAnimaux);
        listView=(ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);



    }
class CustomAdapter extends BaseAdapter{
    private List<AnimalRecord> element;

    public CustomAdapter(List<AnimalRecord> element) {
        this.element = element;
    }

    @Override
    public int getCount() {
        return listeAnimaux.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=getLayoutInflater().inflate(R.layout.customlayout,null);
        TextView pseudoItem=(TextView)view.findViewById(R.id.pseudoItem);
        TextView especeItem=(TextView)view.findViewById(R.id.especeItem);
      ImageView imageItem=(ImageView)view.findViewById(R.id.imageItem);

//        imageItem=(ImageView)findViewById(R.id.imageItem);


        pseudoItem.setText(listeAnimaux.get(i).getPseudo());
        especeItem.setText(listeAnimaux.get(i).getEspece());
        imageItem.setImageBitmap(BitmapFactory.decodeByteArray(listeAnimaux.get(i).getImage(),
                0, listeAnimaux.get(i).getImage().length));


        return view;
    }
}






    public Bitmap byteToImage(byte[] data)
    {

        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        return bmp;

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
        AlertDialog alertDialog = new AlertDialog.Builder(ListeActivity.this).create();
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
