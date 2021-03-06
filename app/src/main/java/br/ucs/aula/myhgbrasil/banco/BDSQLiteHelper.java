package br.ucs.aula.myhgbrasil.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ucs.aula.myhgbrasil.model.Currencies;
import br.ucs.aula.myhgbrasil.model.Stocks;
import br.ucs.aula.myhgbrasil.model.Taxes;
import br.ucs.aula.myhgbrasil.model.Geoip;

public class BDSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyHgBrasil";

    private static final String TABELA_TAXES = "taxes";
    private static final String IDTAXES = "idtaxes";
    private static final String DATE = "date";
    private static final String CDI = "cdi";
    private static final String SELIC = "selic";
    private static final String DAILY_FACTOR = "daily_factor";
    private static final String SELIC_DAILY = "selic_daily";
    private static final String CDI_DAILY = "cdi_daily";
    private static final String[] COLUNAS = {IDTAXES, DATE, CDI, SELIC, DAILY_FACTOR, SELIC_DAILY, CDI_DAILY};

    private static final String TABELA_STOCKS = "stocks";
    private static final String IDSTOCKS = "idstocks";
    private static final String NAMESTOCKS = "namestocks";
    private static final String LOCATION = "location";
    private static final String POINTS = "points";
    private static final String VARIATIONSTOCKS = "variationstocks";
    private static final String[] COLUNAS2 = {IDSTOCKS, NAMESTOCKS, LOCATION, POINTS, VARIATIONSTOCKS};

    private static final String TABELA_CURRENCIES = "currencies";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BUY = "buy";
    private static final String SELL = "sell";
    private static final String VARIATION = "variation";
    private static final String[] COLUNAS3 = {ID, NAME, BUY, SELL, VARIATION};

    private static final String TABELA_GEOLOCALIZATIONS = "geolocalizations";
    private static final String IDGEOIP= "idgeoip";
    private static final String COUNTRY_NAME = "country_name";
    private static final String REGION_CODE = "region_code";
    private static final String CITY = "city";
    private static final String[] COLUNAS4 = {ID, COUNTRY_NAME, REGION_CODE, CITY};

    public BDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE taxes ("+
                "idtaxes INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "date TEXT,"+
                "cdi REAL DEFAULT 0.0,"+
                "selic REAL DEFAULT 0.0,"+
                "daily_factor REAL DEFAULT 0.0,"+
                "selic_daily REAL DEFAULT 0.0,"+
                "cdi_daily REAL DEFAULT 0.0)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE stocks ("+
                "idstocks TEXT,"+
                "namestocks TEXT,"+
                "location TEXT,"+
                "points REAL DEFAULT 0.0,"+
                "variationstocks REAL DEFAULT 0.0)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE currencies ("+
                "id TEXT,"+
                "name TEXT,"+
                "buy REAL DEFAULT 0.0,"+
                "sell REAL DEFAULT 0.0,"+
                "variation REAL DEFAULT 0.0)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE geolocalizations ("+
                "idgeoip INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "country_name TEXT,"+
                "region_code TEXT,"+
                "city TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS taxes");
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS stocks");
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS currencies");
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS geolocalizations");
        this.onCreate(db);
    }

    // Taxes ################################################################
    public Taxes getTaxes(int idTaxes) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_TAXES, // a. tabela
                COLUNAS, // b. colunas
                " idtaxes = ?", // c. colunas para comparar
                new String[] { String.valueOf(idTaxes) }, // d. par??metros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Taxes taxes = cursorToTaxes(cursor);
            return taxes;
        }
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
                IDTAXES+" = ?", // colunas para comparar
                new String[] { String.valueOf(taxes.getIdTaxes()) });
        db.close();
        return i; // n??mero de linhas exclu??das
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
        values.put(CDI, new Double(taxes.getCdi()));
        values.put(SELIC,  new Double(taxes.getSelic()));
        values.put(DAILY_FACTOR,  new Double(taxes.getDailyFactor()));
        values.put(SELIC_DAILY,  new Double(taxes.getSelicDaily()));
        values.put(CDI_DAILY,  new Double(taxes.getCdiDaily()));

        int i = db.update(TABELA_TAXES, //tabela
                values, // valores
                IDTAXES+" = ?", // colunas para comparar
                new String[] { String.valueOf(taxes.getIdTaxes()) }
                ); //par??metros
        db.close();
        return i; // n??mero de linhas modificadas
    }

    private Taxes cursorToTaxes(Cursor cursor) {
        Taxes taxes = new br.ucs.aula.myhgbrasil.model.Taxes();
        taxes.setIdTaxes(Integer.parseInt(cursor.getString(0)));
        taxes.setDate(cursor.getString(1));
        taxes.setCdi(Double.parseDouble(cursor.getString(2)));
        taxes.setSelic(Double.parseDouble(cursor.getString(3)));
        taxes.setDailyFactor(Double.parseDouble(cursor.getString(4)));
        taxes.setSelicDaily(Double.parseDouble(cursor.getString(5)));
        taxes.setCdiDaily(Double.parseDouble(cursor.getString(6)));
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

    // Stocks ################################################################
    public Stocks getStocks(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_STOCKS, // a. tabela
                COLUNAS2, // b. colunas
                " idstocks = ?", // c. colunas para comparar
                new String[] { id }, // d. par??metros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Stocks stocks = cursorToStocks(cursor);
            return stocks;
        }
    }

    public void addStocks(String id, Stocks stocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IDSTOCKS, id);
        values.put(NAMESTOCKS, stocks.getName());
        values.put(LOCATION, stocks.getLocation());
        values.put(POINTS, stocks.getPoints());
        values.put(VARIATIONSTOCKS, stocks.getVariation());
        db.insert(TABELA_STOCKS, null, values);
        db.close();
    }

    public void addStocks(Map<String,Stocks> stocksMap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Set<String> ids = stocksMap.keySet();
        for(String id : ids){
            values.put(IDSTOCKS, id);
            values.put(NAMESTOCKS, stocksMap.get(id).getName());
            values.put(LOCATION, stocksMap.get(id).getLocation());
            values.put(POINTS, stocksMap.get(id).getPoints());
            values.put(VARIATIONSTOCKS, stocksMap.get(id).getVariation());
            db.insert(TABELA_STOCKS, null, values);
        }
        db.close();
    }


    public int deleteStocks(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_STOCKS, //tabela
                IDSTOCKS +" = ?", // colunas para comparar
                new String[] { id });
        db.close();
        return i; // n??mero de linhas exclu??das
    }

    public void deleteAllStocks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_STOCKS,null,null);
        db.close();
    }

    public int updateStocks(Stocks stocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IDSTOCKS, stocks.getId());
        values.put(NAMESTOCKS, stocks.getName());
        values.put(LOCATION, stocks.getLocation());
        values.put(POINTS, stocks.getPoints());
        values.put(VARIATIONSTOCKS, stocks.getVariation());

        int i = db.update(TABELA_STOCKS, //tabela
                values, // valores
                IDSTOCKS +" = ?", // colunas para comparar
                new String[] { stocks.getId() }); //par??metros
        db.close();
        return i; // n??mero de linhas modificadas
    }

    private Stocks cursorToStocks(Cursor cursor) {
        Stocks stocks = new Stocks();
        stocks.setId(cursor.getString(0));
        stocks.setName(cursor.getString(1));
        stocks.setLocation(cursor.getString(2));
        if(cursor.getString(3) != null)
            stocks.setPoints(Double.parseDouble(cursor.getString(3)));
        if(cursor.getString(4) != null)
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
        db.close();
        return stocksArrayList;
    }

    // Currencies ################################################################
    public Currencies getCurrencies(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_CURRENCIES, // a. tabela
                COLUNAS3, // b. colunas
                " id = ?", // c. colunas para comparar
                new String[] { id }, // d. par??metros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Currencies currencies = cursorToCurrencies(cursor);
            return currencies;
        }
    }
    public void addCurrencies(String id, Currencies currencies) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, currencies.getName());
        values.put(BUY, currencies.getBuy());
        values.put(SELL, currencies.getSell());
        values.put(VARIATION, currencies.getVariation());
        db.insert(TABELA_CURRENCIES, null, values);
        db.close();
    }

    public void addCurrencies(Map<String,?> currenciesMap) {
        SQLiteDatabase db = this.getWritableDatabase();


        for(Map.Entry<String, ?> entry : currenciesMap.entrySet()){
            ContentValues values = new ContentValues();
            values.put(ID,  entry.getKey());

            Map<String,Currencies> cur = (Map<String, Currencies>) entry.getValue();
            for(Map.Entry<String,?> entry1 : cur.entrySet()){
                if(entry1.getValue() != null) {
                    switch (entry1.getKey()) {
                        case "name":
                            values.put(NAME, String.valueOf(entry1.getValue()));
                            break;
                        case "buy":
                            values.put(BUY, Double.valueOf(String.valueOf(entry1.getValue())));
                            break;
                        case "sell": values.put(SELL, Double.valueOf(String.valueOf(entry1.getValue()))); break;
                        case "variation":
                            values.put(VARIATION, Double.valueOf(String.valueOf(entry1.getValue())));
                    }
                }
            }
            db.insert(TABELA_CURRENCIES, null, values);
        }
        db.close();
    }


    public int deleteCurrencies(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_CURRENCIES, //tabela
                ID +" = ?", // colunas para comparar
                new String[] { id });
        db.close();
        return i; // n??mero de linhas exclu??das
    }

    public void deleteAllCurrencies() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_CURRENCIES,null,null);
        db.close();
    }

    public int updateCurrencies(Currencies currencies) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, currencies.getId());
        values.put(NAME, currencies.getName());
        values.put(BUY, currencies.getBuy());
        values.put(SELL, currencies.getSell());
        values.put(VARIATION, currencies.getVariation());

        int i = db.update(TABELA_CURRENCIES, //tabela
                values, // valores
                ID +" = ?", // colunas para comparar
                new String[] { currencies.getId() }); //par??metros
        db.close();
        return i; // n??mero de linhas modificadas
    }

    private Currencies cursorToCurrencies(Cursor cursor) {
        Currencies currencies = new Currencies();
        currencies.setId(cursor.getString(0));
        currencies.setName(cursor.getString(1));
        if(cursor.getString(2) != null)
            currencies.setBuy(Double.parseDouble(cursor.getString(2)));
        if(cursor.getString(3) != null)
            currencies.setSell(Double.parseDouble(cursor.getString(3)));
        if(cursor.getString(4) != null)
            currencies.setVariation(Double.parseDouble(cursor.getString(4)));

        return currencies;
    }

    public ArrayList<Currencies> getAllCurrencies() {
        ArrayList<Currencies> currenciesArrayList = new ArrayList<Currencies>();
        String query = "SELECT * FROM " + TABELA_CURRENCIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Currencies currencies = cursorToCurrencies(cursor);
                currenciesArrayList.add(currencies);
            } while (cursor.moveToNext());
        }
        db.close();
        return currenciesArrayList;
    }
    //GeoLocalizations ###############################################################
    public void addGeoips(Geoip geoip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME, geoip.getCountryName());
        values.put(REGION_CODE, geoip.getRegionCode());
        values.put(CITY, geoip.getCity());
        db.insert(TABELA_GEOLOCALIZATIONS, null, values);
        db.close();
    }

    public void deleteAllGeoips() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_GEOLOCALIZATIONS,null,null);
        db.close();
    }

    private Geoip cursorToGeoips(Cursor cursor) {
        Geoip Geoip = new br.ucs.aula.myhgbrasil.model.Geoip();
        Geoip.setIdGeolocalizations(Integer.parseInt(cursor.getString(0)));
        Geoip.setCountryName(cursor.getString(1));
        Geoip.setRegionCode(cursor.getString(2));
        Geoip.setCity(cursor.getString(3));
        return Geoip;
    }

    public Geoip getGeoip() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_GEOLOCALIZATIONS, // a. tabela
                COLUNAS4, // b. colunas
                null,// c. colunas para comparar
                null, // d. par??metros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Geoip geoip = cursorToGeoips(cursor);
            return geoip;
        }
    }

    public ArrayList<Geoip> getAllGeoip() {
        ArrayList<Geoip> geoipArrayList = new ArrayList<Geoip>();
        String query = "SELECT * FROM " + TABELA_GEOLOCALIZATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Geoip geoip = cursorToGeoips(cursor);
                geoipArrayList.add(geoip);
            } while (cursor.moveToNext());
        }
        db.close();
        return geoipArrayList;
    }

}
