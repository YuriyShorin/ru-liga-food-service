CREATE TABLE IF NOT EXISTS OAUTH2_registered_client (
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    client_id  VARCHAR(100) NOT NULL,
    client_id_issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret VARCHAR(200) DEFAULT NULL::CHARACTER VARYING,
    client_secret_expires_at TIMESTAMP,
    client_name VARCHAR(200) NOT NULL,
    client_authentication_methods VARCHAR(1000) NOT NULL,
    authorization_grant_types VARCHAR(1000) NOT NULL,
    redirect_uris VARCHAR(1000) DEFAULT NULL::CHARACTER VARYING,
    scopes VARCHAR(1000) NOT NULL,
    client_settings VARCHAR(2000) NOT NULL,
    token_settings  VARCHAR(2000) NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Authorities (
   username  VARCHAR(50) NOT NULL REFERENCES Users,
   authority VARCHAR(50) NOT NULL
);