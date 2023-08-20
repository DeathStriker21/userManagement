# userManagement

SecureUserManagement

# The app use H2 Database, there are only to Roles "ADMIN" and "USER".

# Need to create roles then create the User:

Example: POST http://{{localhost}}/api/roles/add Content-Type: application/json

{ "rolesType": ["ADMIN", "USER"] }

Example: POST http://{{localhost}}/api/auth/create Content-Type: application/json

{ "username": "Test22", "password": "test", "rolesType": "ADMIN" }

# In order to authenticate you need to login we a user that was created:

Example: POST http://{{localhost}}/api/auth/login Content-Type: application/json

{ "username": "Test22", "password": "test" }
