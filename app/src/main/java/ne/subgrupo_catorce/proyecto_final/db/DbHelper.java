package ne.subgrupo_catorce.proyecto_final.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "turismo.db";
    public static final String TABLA_EVENTOS = "t_eventos";

//
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLA_EVENTOS+"(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT," +
                "Titulo TEXT," +
                "Fecha INTEGER," +
                "Descripcion TEXT," +
                "Latitud REAL," +
                "Longitud REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE "+TABLA_EVENTOS);
        onCreate(sqLiteDatabase);
    }
}
