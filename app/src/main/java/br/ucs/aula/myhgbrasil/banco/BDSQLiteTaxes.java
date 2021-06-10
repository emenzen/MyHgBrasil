package br.ucs.aula.myhgbrasil.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ucs.aula.myhgbrasil.model.Taxes;

public class BDSQLiteTaxes extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyHgBrasil";
    private static final String TABELA_TAXES = "taxes";
    private static final String DATE = "date";
    private static final String CDI = "cdi";
    private static final String SELIC = "selic";
    private static final String DAILY_FACTOR = "daily_factor";
    private static final String SELIC_DAILY = "selic_daily";
    private static final String CDI_DAILY = "cdi_daily";
    private static final String[] COLUNAS = {DATE, CDI, SELIC, DAILY_FACTOR, SELIC_DAILY, CDI_DAILY};

    public BDSQLiteTaxes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE taxes ("+
                "date TEXT,"+
                "cdi REAL,"+
                "selic REAL,"+
                "daily_factor REAL,"+
                "selic_daily REAL,"+
                "cdi_daily REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS taxes");
        this.onCreate(db);
    }

    public void addTaxes(Taxes taxes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, taxes.getDate());
        values.put(CDI, taxes.getCdi());
        values.put(SELIC, taxes.getSelic());
        values.put(DAILY_FACTOR, taxes.getDailyFactor());
        values.put(SELIC_DAILY, taxes.getSelicDaily());
        values.put(CDI_DAILY, taxes.getCdiDaily());
        db.insert(TABELA_TAXES, null, values);
        db.close();
    }

    public void addTaxes(List<Taxes> taxesList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i=0; i<taxesList.size(); i++){
            values.put(DATE, taxesList.get(i).getDate());
            values.put(CDI, taxesList.get(i).getCdi());
            values.put(SELIC, taxesList.get(i).getSelic());
            values.put(DAILY_FACTOR, taxesList.get(i).getDailyFactor());
            values.put(SELIC_DAILY, taxesList.get(i).getSelicDaily());
            values.put(CDI_DAILY, taxesList.get(i).getCdiDaily());
            db.insert(TABELA_TAXES, null, values);
        }
        db.close();
    }


    public int deleteTaxes(Taxes taxes) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_TAXES, //tabela
                DATE+" = ?", // colunas para comparar
                new String[] { taxes.getDate() });
        db.close();
        return i; // número de linhas excluídas
    }

    public void deleteAllTaxes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_TAXES,null,null);
        db.close();
    }

    public int updateTaxes(Taxes taxes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, taxes.getDate());
        values.put(CDI, taxes.getCdi());
        values.put(SELIC, taxes.getSelic());
        values.put(DAILY_FACTOR, taxes.getDailyFactor());
        values.put(SELIC_DAILY, taxes.getSelicDaily());
        values.put(CDI_DAILY, taxes.getCdiDaily());

        int i = db.update(TABELA_TAXES, //tabela
                values, // valores
                DATE+" = ?", // colunas para comparar
                new String[] { taxes.getDate() }); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }

    private Taxes cursorToTaxes(Cursor cursor) {
        Taxes taxes = new br.ucs.aula.myhgbrasil.model.Taxes();
        taxes.setDate(cursor.getString(0));
        taxes.setCdi(Double.parseDouble(cursor.getString(1)));
        taxes.setSelic(Double.parseDouble(cursor.getString(2)));
        taxes.setDailyFactor(Double.parseDouble(cursor.getString(3)));
        taxes.setSelicDaily(Double.parseDouble(cursor.getString(4)));
        taxes.setCdiDaily(Double.parseDouble(cursor.getString(5)));
        return taxes;
    }

    public ArrayList<Taxes> getAllTaxes() {
        ArrayList<Taxes> TaxesList = new ArrayList<br.ucs.aula.myhgbrasil.model.Taxes>();
        String query = "SELECT * FROM " + TABELA_TAXES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Taxes taxes = cursorToTaxes(cursor);
                TaxesList.add(taxes);
            } while (cursor.moveToNext());
        }
        return TaxesList;
    }

}
