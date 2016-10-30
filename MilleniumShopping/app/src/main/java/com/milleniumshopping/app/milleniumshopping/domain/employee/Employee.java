package com.milleniumshopping.app.milleniumshopping.domain.employee;

import java.io.Serializable;

/**
 * Created by 208023429 on 4/14/2016.
 */
public class Employee implements Serializable{

    private String name, surname;
    private String dateOfBirth;
    private String employeeID;
    private String role = "";

    private Employee(Builder builder)
    {
        this.name = builder.name;
        this.surname = builder.surname;
        this.dateOfBirth = builder.dateOfBirth;
        this.employeeID = builder.employeeID;
        this.role = builder.role;
    }

    public String getName()
    {
        return name;
    }


    public String getSurname()
    {
        return surname;
    }


    public String getDateOfBirth()
    {
        return dateOfBirth;
    }


    public String getEmployeeID()
    {
        return employeeID;
    }


    public String getEmployeeRole()
    {
        return role;
    }

    public String toString(){
        return "EmployeeID: " + getEmployeeID() + "\nName: " + getName() + "\nSurname: " + getSurname() + "\nDate of birth: " + getDateOfBirth() + "\nRole: " + getEmployeeRole();
    }

    public static class Builder
    {
        private String name, surname;
        private String dateOfBirth;
        private String employeeID;
        private String role;

        public Builder(){}

        public Builder employeeID(String value)
        {
            this.employeeID = value;
            return this;
        }

        public Builder name(String value)
        {
            this.name = value;
            return this;
        }

        public Builder surname(String value)
        {
            this.surname = value;
            return this;
        }

        public Builder dateOfBirth(String value)
        {
            this.dateOfBirth = value;
            return this;
        }

        public Builder role(String role)
        {
            this.role = role;
            return this;
        }

        public Builder copy(Employee value)
        {
            this.name = value.getName();
            this.surname = value.getSurname();
            this.dateOfBirth = value.getDateOfBirth();
            this.employeeID = value.getEmployeeID();
            this.role = value.getEmployeeRole();

            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return employeeID != null ? employeeID.equals(employee.employeeID) : employee.employeeID == null;

    }

    @Override
    public int hashCode()
    {
        return employeeID != null ? employeeID.hashCode() : 0;
    }
}
