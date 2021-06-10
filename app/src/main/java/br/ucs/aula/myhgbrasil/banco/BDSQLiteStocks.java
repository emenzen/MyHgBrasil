package br.ucs.aula.myhgbrasil.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ucs.aula.myhgbrasil.model.Stocks;
import br.ucs.aula.myhgbrasil.model.Taxes;

public class BDSQLiteStocks extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MyHgBrasil";
    private static final String TABELA_STOCKS = "stocks";
    private static final String KEY = "key";
    private static final String NAME = "name";
    private static final String LOCATION = "location";
    private static final String POINTS = "points";
    private static final String VARIATION = "variation";
    private static final String[] COLUNAS = {KEY, NAME, LOCATION, POINTS, VARIATION};

    public BDSQLiteStocks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE stocks ("+
                "key TEXT,"+
                "name TEXT,"+
                "location TEXT,"+
                "points REAL,"+
                "variation REAL)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stocks");
        this.onCreate(db);
    }

    public void addStocks(String key, Stocks stocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put(NAME, stocks.getName());
        values.put(LOCATION, stocks.getLocation());
        values.put(POINTS, stocks.getPoints());
        values.put(VARIATION, stocks.getVariation());
        db.insert(TABELA_STOCKS, null, values);
        db.close();
    }

    public void addStocks(Map<String,Stocks> stocksMap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Set<String> keys = stocksMap.keySet();
        for(String key : keys){
            values.put(KEY, key);
            values.put(NAME, stocksMap.get(key).getName());
            values.put(LOCATION, stocksMap.get(key).getLocation());
            values.put(POINTS, stocksMap.get(key).getPoints());
            values.put(VARIATION, stocksMap.get(key).getVariation());
            db.insert(TABELA_STOCKS, null, values);
        }
        db.close();
    }


    public int deleteStocks(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_STOCKS, //tabela
                KEY+" = ?", // colunas para comparar
                new String[] { key });
        db.close();
        return i; // número de linhas excluídas
    }

    public void deleteAllStocks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_STOCKS,null,null);
        db.close();
    }

    public int updateStocks(Stocks stocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, stocks.getKey());
        values.put(NAME, stocks.getName());
        values.put(LOCATION, stocks.getLocation());
        values.put(POINTS, stocks.getPoints());
        values.put(VARIATION, stocks.getVariation());

        int i = db.update(TABELA_STOCKS, //tabela
                values, // valores
                KEY+" = ?", // colunas para comparar
                new String[] { stocks.getKey() }); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }

    private Stocks cursorToStocks(Cursor cursor) {
        Stocks stocks = new Stocks();
        stocks.setKey(cursor.getString(0));
        stocks.setName(cursor.getString(1));
        stocks.setLocation(cursor.getString(2));
        stocks.setPoints(Double.parseDouble(cursor.getString(3)));
        stocks.setVariation(Double.parseDouble(cursor.getString(4)));
        return stocks;
    }

    public ArrayList<Stocks> getAllStocks() {
        ArrayList<Stocks> stocksArrayList = new ArrayList<Stocks>();
        String query = "SELECT * FROM " + TABELA_STOCKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Stocks stocks = cursorToStocks(cursor);
                stocksArrayList.add(stocks);
            } while (cursor.moveToNext());
        }
        return stocksArrayList;
    }
}
