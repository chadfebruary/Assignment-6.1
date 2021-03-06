package com.milleniumshopping.app.milleniumshopping.repository.internet.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.milleniumshopping.app.milleniumshopping.conf.databases.DBConstants;
import com.milleniumshopping.app.milleniumshopping.domain.internet.Mobile;
import com.milleniumshopping.app.milleniumshopping.repository.internet.MobileRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class MobileRepositoryImpl extends SQLiteOpenHelper implements MobileRepository {
    public static final String TABLE_INTERNET = "internet";
    private SQLiteDatabase database;

    private static final String COLUMN_IPADDRESS = "ipaddress";
    private static final String COLUMN_ISP = "isp";
    private static final String COLUMN_PLANNAME = "planname";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DATAALLOWANCE = "dataallowance";
    private static final String COLUMN_TYPE = "type";

    //Database table creation
    private static final String DATABASE_CREATE = " CREATE TABLE IF NOT EXISTS "
            + TABLE_INTERNET + "("
            + COLUMN_IPADDRESS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ISP + " TEXT NOT NULL,"
            + COLUMN_PLANNAME + " TEXT NOT NULL,"
            + COLUMN_PRICE + " TEXT NOT NULL,"
            + COLUMN_DATAALLOWANCE + " TEXT NOT NULL,"
            + COLUMN_TYPE + " TEXT NOT NULL);";

    public MobileRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public MobileRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MobileRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void open()
    {
        database = this.getWritableDatabase();
        onCreate(database);
    }

    public void close()
    {
        this.close();
    }

    @Override
    public Mobile findById(String ipAddress, String type)
    {
        SQLiteDatabase database =  this.getReadableDatabase();
        Cursor cursor = database.query(
                TABLE_INTERNET,
                new String[]{
                        COLUMN_IPADDRESS,
                        COLUMN_ISP,
                        COLUMN_PLANNAME,
                        COLUMN_PRICE,
                        COLUMN_DATAALLOWANCE,
                        COLUMN_TYPE
                },
                COLUMN_IPADDRESS + " =? ",
                new String[]{String.valueOf(ipAddress)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            final Mobile internet = new Mobile.Builder()
                    .ipAddress(cursor.getString(0))
                    .ISP(cursor.getString(1))
                    .planName(cursor.getString(2))
                    .price(cursor.getString(3))
                    .dataAllowance(cursor.getString(4))
                    .type(cursor.getString(5))
                    .build();

            return internet;
        }
        else {
            return null;
        }
    }

    @Override
    public Mobile save(Mobile internet) {
        open();
        onCreate(database);
        ContentValues values = new ContentValues();

        values.put(COLUMN_IPADDRESS, internet.getIPAddress());
        values.put(COLUMN_ISP, internet.getISP());
        values.put(COLUMN_PLANNAME, internet.getPlanName());
        values.put(COLUMN_PRICE, internet.getPrice());
        values.put(COLUMN_DATAALLOWANCE, internet.getDataAllowance());
        values.put(COLUMN_TYPE, internet.getType());

        Long ipAddress = database.insertOrThrow(TABLE_INTERNET, null, values);

        final Mobile insertedEntity = new Mobile.Builder()
                .copy(internet)
                .ipAddress(ipAddress.toString())
                .build();

        return insertedEntity;
    }

    @Override
    public Mobile update(Mobile internet) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IPADDRESS, internet.getIPAddress());
        values.put(COLUMN_ISP, internet.getISP());
        values.put(COLUMN_PLANNAME, internet.getPlanName());
        values.put(COLUMN_PRICE, internet.getPrice());
        values.put(COLUMN_DATAALLOWANCE, internet.getDataAllowance());
        values.put(COLUMN_TYPE, internet.getType());
        database.update(
                TABLE_INTERNET,
                values,
                COLUMN_IPADDRESS + " =? ",
                new String[]{String.valueOf(internet.getIPAddress())}
        );

        return internet;
    }

    @Override
    public Mobile delete(Mobile internet){
        open();
        database.delete(
                TABLE_INTERNET,
                COLUMN_IPADDRESS + " =? ",
                new String[]{String.valueOf(internet.getIPAddress())}
        );

        return internet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = database.delete(TABLE_INTERNET, null, null);
        return rowsDeleted;
    }

    @Override
    public Set<Mobile> findAll() {
        open();
        String selectAll = " SELECT * FROM " + TABLE_INTERNET;
        Set<Mobile>internetServices = new HashSet<>();

        Cursor cursor = database.rawQuery(selectAll, null);

        if(cursor.moveToFirst())
        {
            do{
                final Mobile internet = new Mobile.Builder()
                        .ipAddress(cursor.getString(0))
                        .ISP(cursor.getString(1))
                        .planName(cursor.getString(2))
                        .price(cursor.getString(3))
                        .dataAllowance(cursor.getString(4))
                        .type(cursor.getString(5))
                        .build();

                internetServices.add(internet);

            }while (cursor.moveToNext());
        }

        return internetServices;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        //database.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERNET);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERNET);
        onCreate(db);
    }
}
