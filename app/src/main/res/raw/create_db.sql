CREATE TABLE Indications(indication_id INTEGER PRIMARY KEY, name TEXT NOT NULL, description TEXT NOT NULL)
CREATE TABLE Indications_by_herbs(herb_id INTEGER NOT NULL, indication_id INTEGER NOT NULL)
CREATE TABLE Herbs(herb_id INTEGER PRIMARY KEY, name TEXT NOT NULL, properties TEXT NOT NULL, posology TEXT NOT NULL, image TEXT NOT NULL)
CREATE TABLE Ingests(ingest_id INTEGER PRIMARY KEY, name TEXT NOT NULL)
CREATE TABLE Ingests_by_herbs(ingest_id INTEGER NOT NULL, herb_id INTEGER NOT NULL)
CREATE TABLE Places(place_id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT NOT NULL, gps_lat TEXT NOT NULL, gps_lon TEXT NOT NULL, phone TEXT NOT NULL)
CREATE TABLE Places_by_herbs(herb_id INTEGER NOT NULL, place_id INTEGER NOT NULL)