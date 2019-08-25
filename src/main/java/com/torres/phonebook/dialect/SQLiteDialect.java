package com.torres.phonebook.dialect;

import org.hibernate.dialect.identity.IdentityColumnSupport;

public class SQLiteDialect extends org.hibernate.dialect.SQLiteDialect{
	public SQLiteDialect() {
		super();
	}
	
	public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }
}
