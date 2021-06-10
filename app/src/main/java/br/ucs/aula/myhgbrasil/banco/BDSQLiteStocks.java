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
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOCATION = "location";
    private static final String POINTS = "points";
    private static final String VARIATION = "variation";
    private static final String[] COLUNAS = {ID, NAME, LOCATION, POINTS, VARIATION};

    public BDSQLiteStocks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE stocks ("+
                "id TEXT,"+
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

    public void addStocks(String id, Stocks stocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
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
        Set<String> ids = stocksMap.keySet();
        for(String id : ids){
            values.put(ID, id);
            values.put(NAME, stocksMap.get(id).getName());
            values.put(LOCATION, stocksMap.get(id).getLocation());
            values.put(POINTS, stocksMap.get(id).getPoints());
            values.put(VARIATION, stocksMap.get(id).getVariation());
            db.insert(TABELA_STOCKS, null, values);
        }
        db.close();
    }


    public int deleteStocks(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_STOCKS, //tabela
                ID +" = ?", // colunas para comparar
                new String[] { id });
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
        values.put(ID, stocks.getId());
        values.put(NAME, stocks.getName());
        values.put(LOCATION, stocks.getLocation());
        values.put(POINTS, stocks.getPoints());
        values.put(VARIATION, stocks.getVariation());

        int i = db.update(TABELA_STOCKS, //tabela
                values, // valores
                ID +" = ?", // colunas para comparar
                new String[] { stocks.getId() }); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }

    private Stocks cursorToStocks(Cursor cursor) {
        Stocks stocks = new Stocks();
        stocks.setId(cursor.getString(0));
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
