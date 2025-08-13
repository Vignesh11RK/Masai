package org.example.model.users;


import org.example.model.enums.UserRole;

public class SystemAdmin extends User {
    public SystemAdmin(String id, String name) {
        super(id, name, UserRole.SYSTEM_ADMIN);
    }
}
