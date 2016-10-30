package com.milleniumshopping.app.milleniumshopping.repository.employee.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.milleniumshopping.app.milleniumshopping.conf.databases.DBConstants;
import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cfebruary on 2016/08/18.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {

    public static final String TABLE_EMPLOYEE = "employee";
    private SQLiteDatabase database;

    public static final String COLUMN_EMPLOYEEID = "employeeid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_DATEOFBIRTH = "dateofbirth";
    public static final String COLUMN_ROLE = "role";

    //Database table creation
    private static final String DATABASE_CREATE = " CREATE TABLE IF NOT EXISTS "
            + TABLE_EMPLOYEE + "("
            + COLUMN_EMPLOYEEID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_SURNAME + " TEXT NOT NULL,"
            + COLUMN_DATEOFBIRTH + " TEXT NOT NULL,"
            + COLUMN_ROLE + " TEXT NOT NULL);";

    public EmployeeRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public EmployeeRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EmployeeRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
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
    public Employee findById(String employeeID, String role)
    {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                TABLE_EMPLOYEE,
                new String[]{
                        COLUMN_EMPLOYEEID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_DATEOFBIRTH,
                        COLUMN_ROLE},
                COLUMN_EMPLOYEEID + " =? ",
                new String[]{String.valueOf(employeeID)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
                final Employee employee = new Employee.Builder()
                        .employeeID(cursor.getString(0))
                        .name(cursor.getString(1))
                        .surname(cursor.getString(2))
                        .dateOfBirth(cursor.getString(3))
                        .role(cursor.getString(4))
                        .build();
                return employee;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Employee save(Employee employee)
    {
        open();
        onCreate(database);
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMPLOYEEID, employee.getEmployeeID());
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_SURNAME, employee.getSurname());
        values.put(COLUMN_DATEOFBIRTH, employee.getDateOfBirth());
        values.put(COLUMN_ROLE, employee.getEmployeeRole());

        Long employeeID = database.insertOrThrow(TABLE_EMPLOYEE, null, values);

            final Employee insertedEntity = new Employee.Builder()
                    .copy(employee)
                    .employeeID(employeeID.toString())
                    .build();

        return insertedEntity;
    }

    @Override
    public Employee update(Employee employee)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMPLOYEEID, employee.getEmployeeID());
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_SURNAME, employee.getSurname());
        values.put(COLUMN_DATEOFBIRTH, employee.getDateOfBirth());
        values.put(COLUMN_ROLE, employee.getEmployeeRole());

        database.update(
                TABLE_EMPLOYEE,
                values,
                COLUMN_EMPLOYEEID + " =? ",
                new String[]{String.valueOf(employee.getEmployeeID())}
        );

        return employee;
    }

    @Override
    public Employee delete(Employee employee)
    {
        open();
        database.delete(
                TABLE_EMPLOYEE,
                COLUMN_EMPLOYEEID + " =? ",
                new String[]{String.valueOf(employee.getEmployeeID())}
        );

        return employee;
    }

    @Override
    public Set<Employee> findAll()
    {
        open();
        String selectAll = " SELECT * FROM " + TABLE_EMPLOYEE;
        Set<Employee> employees = new HashSet<>();
        //open();
        Cursor cursor = database.rawQuery(selectAll, null);

        if(cursor.moveToFirst())
        {
            do {

                final Employee employee = new Employee.Builder()
                            .employeeID(cursor.getString(0))
                            .name(cursor.getString(1))
                            .surname(cursor.getString(2))
                            .dateOfBirth(cursor.getString(3))
                            .role(cursor.getString(4))
                            .build();

                employees.add(employee);

            }while(cursor.moveToNext());
        }

        return employees;
    }

    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = database.delete(TABLE_EMPLOYEE, null, null);
        //close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        //database.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);

    }
}