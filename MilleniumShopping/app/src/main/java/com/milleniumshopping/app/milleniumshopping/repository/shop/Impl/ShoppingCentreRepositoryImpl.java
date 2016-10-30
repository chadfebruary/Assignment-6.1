package com.milleniumshopping.app.milleniumshopping.repository.shop.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.milleniumshopping.app.milleniumshopping.conf.databases.DBConstants;
import com.milleniumshopping.app.milleniumshopping.domain.shop.ShoppingCentre;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShoppingCentreRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShoppingCentreRepositoryImpl extends SQLiteOpenHelper implements ShoppingCentreRepository {
    public static final String TABLE_SHOPPINGCENTRE = "shoppingcentre";
    private SQLiteDatabase database;

    public static final String COLUMN_CENTRENAME= "centreName";
    public static final String COLUMN_CENTRECODE = "centreCode";
    public static final String COLUMN_PORTFOLIO = "portfolio";
    public static final String COLUMN_ADDRESS = "address";

    //Database table creation
    private static final String DATABASE_CREATE = " CREATE TABLE IF NOT EXISTS "
            + TABLE_SHOPPINGCENTRE + "("
            + COLUMN_CENTRENAME + " TEXT PRIMARY KEY ,"
            + COLUMN_CENTRECODE + " TEXT NOT NULL,"
            + COLUMN_PORTFOLIO + " TEXT NOT NULL,"
            + COLUMN_ADDRESS + " TEXT NOT NULL);";

    public ShoppingCentreRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open()
    {
        database = this.getWritableDatabase();
    }

    public void close()
    {
        this.close();
    }

    @Override
    public ShoppingCentre findById(String shopNumber, String shopName) {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(
                TABLE_SHOPPINGCENTRE,
                new String[]{
                        COLUMN_CENTRENAME,
                        COLUMN_CENTRECODE,
                        COLUMN_PORTFOLIO,
                        COLUMN_ADDRESS},
                COLUMN_CENTRENAME + " =? ",
                new String[]{String.valueOf(shopNumber)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final ShoppingCentre shop = new ShoppingCentre.Builder()
                    .centreName(cursor.getString(0))
                    .centreCode(cursor.getString(1))
                    .portfolio(cursor.getString(2))
                    .address(cursor.getString(3))
                    .build();

            return shop;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ShoppingCentre save(ShoppingCentre shop) {
        open();
        onCreate(database);
        ContentValues values = new ContentValues();

        values.put(COLUMN_CENTRENAME, shop.getCentreName());
        values.put(COLUMN_CENTRECODE, shop.getCentreCode());
        values.put(COLUMN_PORTFOLIO, shop.getPortfolio());
        values.put(COLUMN_ADDRESS, shop.getAddress());

        Long shopNumber = database.insertOrThrow(TABLE_SHOPPINGCENTRE, null, values);
        ShoppingCentre insertedShop = shop;

        return insertedShop;
    }

    @Override
    public ShoppingCentre update(ShoppingCentre shop) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CENTRENAME, shop.getCentreName());
        values.put(COLUMN_CENTRECODE, shop.getCentreCode());
        values.put(COLUMN_PORTFOLIO, shop.getPortfolio());
        values.put(COLUMN_ADDRESS, shop.getAddress());

        database.update(
                TABLE_SHOPPINGCENTRE,
                values,
                COLUMN_CENTRENAME + " =? ",
                new String[]{String.valueOf(shop.getCentreName())}
        );

        return shop;
    }

    @Override
    public ShoppingCentre delete(ShoppingCentre shop) {
        open();
        database.delete(
                TABLE_SHOPPINGCENTRE,
                COLUMN_CENTRENAME + " =? ",
                new String[]{String.valueOf(shop.getCentreName())}
        );

        return shop;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = database.delete(TABLE_SHOPPINGCENTRE, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public Set<ShoppingCentre> findAll() {
        database = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SHOPPINGCENTRE;
        Set<ShoppingCentre> shops = new HashSet<>();
        open();
        Cursor cursor = database.rawQuery(selectAll, null);

        if(cursor.moveToFirst())
        {
            do{
                final ShoppingCentre shop;

                shop = new ShoppingCentre.Builder()
                        .centreName(cursor.getString(0))
                        .centreCode(cursor.getString(1))
                        .portfolio(cursor.getString(2))
                        .address(cursor.getString(3))
                        .build();

                shops.add(shop);

            }while(cursor.moveToNext());
        }

        return shops;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPINGCENTRE);
        onCreate(db);
    }
}
