package com.milleniumshopping.app.milleniumshopping.repository.shop.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.milleniumshopping.app.milleniumshopping.conf.databases.DBConstants;
import com.milleniumshopping.app.milleniumshopping.domain.shop.Shop;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShopRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cfebruary on 2016/08/27.
 */
public class ShopRepositoryImpl extends SQLiteOpenHelper implements ShopRepository {

    public static final String TABLE_SHOP = "shop";
    private SQLiteDatabase database;

    public static final String COLUMN_SHOPNUMBER = "shopnumber";
    public static final String COLUMN_SHOPNAME = "shopname";
    public static final String COLUMN_SHOPOWNER = "shopowner";
    public static final String COLUMN_SHOPPHONENUMBER = "shopphonenumber";

    //Database table creation
    private static final String DATABASE_CREATE = " CREATE TABLE IF NOT EXISTS "
            + TABLE_SHOP + "("
            + COLUMN_SHOPNUMBER + " TEXT PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SHOPNAME + " TEXT NOT NULL,"
            + COLUMN_SHOPOWNER + " TEXT NOT NULL,"
            + COLUMN_SHOPPHONENUMBER + " TEXT NOT NULL);";

    public ShopRepositoryImpl(Context context)
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
    public Shop findById(String shopNumber, String shopName) {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(
                TABLE_SHOP,
                new String[]{
                        COLUMN_SHOPNUMBER,
                        COLUMN_SHOPNAME,
                        COLUMN_SHOPOWNER,
                        COLUMN_SHOPPHONENUMBER},
                COLUMN_SHOPNUMBER + " =? ",
                new String[]{String.valueOf(shopNumber)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final Shop shop = new Shop.Builder()
                    .shopNumber(cursor.getString(0))
                    .shopName(cursor.getString(1))
                    .shopOwner(cursor.getString(2))
                    .shopPhoneNumber(cursor.getString(3))
                    .build();

            return shop;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Shop save(Shop shop) {
        open();
        onCreate(database);
        ContentValues values = new ContentValues();

        values.put(COLUMN_SHOPNUMBER, shop.getShopNumber());
        values.put(COLUMN_SHOPNAME, shop.getShopName());
        values.put(COLUMN_SHOPOWNER, shop.getShopOwner());
        values.put(COLUMN_SHOPPHONENUMBER, shop.getShopPhoneNumber());

        Long shopNumber = database.insertOrThrow(TABLE_SHOP, null, values);
        Shop insertedShop = shop;

        return insertedShop;
    }

    @Override
    public Shop update(Shop shop) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_SHOPNUMBER, shop.getShopNumber());
        values.put(COLUMN_SHOPNAME, shop.getShopName());
        values.put(COLUMN_SHOPOWNER, shop.getShopOwner());
        values.put(COLUMN_SHOPPHONENUMBER, shop.getShopPhoneNumber());

        database.update(
                TABLE_SHOP,
                values,
                COLUMN_SHOPNUMBER + " =? ",
                new String[]{String.valueOf(shop.getShopNumber())}
        );

        return shop;
    }

    @Override
    public Shop delete(Shop shop) {
        open();
        database.delete(
                TABLE_SHOP,
                COLUMN_SHOPNUMBER + " =? ",
                new String[]{String.valueOf(shop.getShopNumber())}
        );

        return shop;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = database.delete(TABLE_SHOP, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public Set<Shop> findAll() {
        database = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SHOP;
        Set<Shop> shops = new HashSet<>();
        open();
        Cursor cursor = database.rawQuery(selectAll, null);

        if(cursor.moveToFirst())
        {
            do{
                final Shop shop;

                shop = new Shop.Builder()
                        .shopNumber(cursor.getString(0))
                        .shopName(cursor.getString(1))
                        .shopOwner(cursor.getString(2))
                        .shopPhoneNumber(cursor.getString(3))
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);
        onCreate(db);
    }
}
