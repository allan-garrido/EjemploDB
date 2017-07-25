package miumg.gt.edu.ejemplodb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Asignatura> asignaturas;
    private ArrayAdapter<Asignatura> adapterAsignaturas;
    private AdminSQLiteOpenHelper adminAsignaturas;

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        lv1 = (ListView) findViewById(R.id.lv1);

        asignaturas = new ArrayList<>();
        adapterAsignaturas = new ArrayAdapter<Asignatura>(this, android.R.layout.simple_list_item_1, asignaturas);
        lv1.setAdapter(adapterAsignaturas);
        adminAsignaturas = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        showAll();
    }

    public void add(View view) {
        SQLiteDatabase bd = adminAsignaturas.getWritableDatabase();

        int Codigo = Integer.parseInt(et1.getText().toString());
        String nombreAsignatura = et2.getText().toString();
        int cantEstudiantes = Integer.parseInt(et3.getText().toString());

        ContentValues tupla = new ContentValues();
        tupla.put("codigo",Codigo);
        tupla.put("nombreasignatura", nombreAsignatura);
        tupla.put("cantestudiantes", cantEstudiantes);

        bd.insert("asignatura",null,tupla);
        bd.close();

        Toast.makeText(this, "Asignatura insertada", Toast.LENGTH_SHORT).show();
        showAll();
    }

    public void showAll() {
        String query = "select * from asignatura;";

        SQLiteDatabase bd = adminAsignaturas.getReadableDatabase();

        asignaturas.clear();

        Cursor c = bd.rawQuery(query, null);

        while (c.moveToNext()) {
            Asignatura aTemp = new Asignatura();

            aTemp.setCodigo(c.getInt(c.getColumnIndex("codigo")));
            aTemp.setNombre(c.getString(c.getColumnIndex("nombreasignatura")));
            aTemp.setCantEstudiantes(c.getInt(c.getColumnIndex("cantestudiantes")));

            asignaturas.add(aTemp);
        }
        adapterAsignaturas.notifyDataSetChanged();
    }


    public void del(View view) {
        SQLiteDatabase bd = adminAsignaturas.getWritableDatabase();

        String codigo;
        codigo = et1.getText().toString();

        int cant = bd.delete("asignatura","codigo="+codigo, null);

        if (cant==1)
            Toast.makeText(this, "Asignatura eliminada", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe la asignatura " + codigo, Toast.LENGTH_SHORT).show();

        bd.close();

        showAll();
    }
}
