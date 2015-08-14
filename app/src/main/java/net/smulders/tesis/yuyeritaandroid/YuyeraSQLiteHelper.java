package net.smulders.tesis.yuyeritaandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class YuyeraSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Yuyera";
    private static final int DB_VERSION = 1;
    private final Context _context;

    public YuyeraSQLiteHelper(Context context, String name, int version){
        super(context, name, null, version);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            executeSQL(db, R.raw.create_db);
            executeSQL(db, R.raw.insert_data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            executeSQL(db, R.raw.recreate_db);
            executeSQL(db, R.raw.insert_data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeSQL(SQLiteDatabase db, int resource) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_context.getResources().openRawResource(resource)));

        String data = reader.readLine();
        while(data != null){
            db.execSQL(data);
            data = reader.readLine();
        }
    }

    public static List<Herb> getAllHerbs(Context context){
        List<Herb> allHerbs = new ArrayList<Herb>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Herbs", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String properties = cursor.getString(2);
                String posology = cursor.getString(3);
                String image = cursor.getString(4);
                allHerbs.add(new Herb(id,name,properties,posology,image));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allHerbs;
    }

    public static Herb getHerb(String id, Context context){
        Herb herb = null;
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Herbs WHERE herb_id = ?", new String[] {id});
        if(cursor.moveToFirst()){
            String herb_id = cursor.getString(0);
            String name = cursor.getString(1);
            String properties = cursor.getString(2);
            String posology = cursor.getString(3);
            String image = cursor.getString(4);
            herb = new Herb(herb_id,name,properties,posology,image);
        }

        cursor.close();

        return herb;
    }

    public static List<Indication> getIndications(String herbId, Context context) {
        List<Indication> allIndications = new ArrayList<Indication>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT i.indication_id, i.name, i.description FROM Indications i INNER JOIN Indications_by_herbs ih ON i.indication_id = ih.indication_id WHERE ih.herb_id = ?", new String[] {herbId});
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                allIndications.add(new Indication(id,name,description));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allIndications;
    }

    public static List<Ingest> getIngests(String herbId, Context context) {
        List<Ingest> allIngests = new ArrayList<Ingest>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT i.ingest_id, i.name FROM Ingests i INNER JOIN Ingests_by_herbs ih ON i.ingest_id = ih.ingest_id WHERE ih.herb_id = ?", new String[] {herbId});
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                allIngests.add(new Ingest(id,name));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allIngests;
    }
    public static List<Place> getPlaces(String herbId, Context context) {
        List<Place> allPlaces = new ArrayList<Place>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT p.place_id, p.name, p.address, p.gps_lat, p.gps_lon, p.phone FROM Places p INNER JOIN Places_by_herbs ph ON p.place_id = ph.place_id WHERE ph.herb_id = ?", new String[] {herbId});
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String gps_lat = cursor.getString(3);
                String gps_lon = cursor.getString(4);
                String phone = cursor.getString(5);
                allPlaces.add(new Place(id,name,address,gps_lat,gps_lon,phone));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allPlaces;
    }

    public static List<Herb> getHerbsByIndication(String indicationId, Context context){
        List<Herb> allHerbs = new ArrayList<Herb>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT h.herb_id, h.name, h.properties, h.posology, h.image FROM Herbs h INNER JOIN Indications_by_herbs ih ON h.herb_id = ih.herb_id WHERE ih.indication_id = ?", new String[] {indicationId});
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String properties = cursor.getString(2);
                String posology = cursor.getString(3);
                String image = cursor.getString(4);
                allHerbs.add(new Herb(id,name,properties,posology,image));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allHerbs;
    }

    //Indications
    public static List<Indication> getAllIndications(Context context){
        List<Indication> allIndications = new ArrayList<Indication>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Indications", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                allIndications.add(new Indication(id,name,description));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allIndications;
    }

    public static Indication getIndication(String id, Context context){
        Indication indication = null;
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Indications WHERE indication_id = ?", new String[] {id});
        if(cursor.moveToFirst()){
            String indication_id = cursor.getString(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            indication = new Indication(indication_id,name,description);
        }

        cursor.close();

        return indication;
    }

    //Places
    public static List<Place> getAllPlaces(Context context){
        List<Place> allPlaces = new ArrayList<Place>();
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Places", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String gps_lat = cursor.getString(3);
                String gps_lon = cursor.getString(4);
                String phone = cursor.getString(5);
                allPlaces.add(new Place(id,name,address,gps_lat,gps_lon,phone));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return allPlaces;
    }

    public static Place getPlace(String id, Context context){
        Place place = null;
        YuyeraSQLiteHelper helper = new YuyeraSQLiteHelper(context,DB_NAME,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Places WHERE place_id = ?", new String[] {id});
        if(cursor.moveToFirst()){
            String place_id = cursor.getString(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String gps_lat = cursor.getString(3);
            String gps_lon = cursor.getString(4);
            String phone = cursor.getString(5);
            place = new Place(place_id,name,address,gps_lat,gps_lon,phone);
        }

        cursor.close();

        return place;
    }
}
