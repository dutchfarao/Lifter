package com.example.lifter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    InputStream inputStream;

    public CSVHelper(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public ArrayList<Liftspot> read(){
        ArrayList<Liftspot> liftspots = new ArrayList<>();
        int idCounter = 8;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine = "";

            while ((csvLine = reader.readLine()) != null) {
                //parse line to extract individual fields
                String[] data = csvLine.split(",");


                //Create liftspot object
                Liftspot liftspot = new Liftspot();
                liftspot.setId(idCounter);
                liftspot.setRating(data[0]);
                liftspot.setName(data[1]);
                liftspot.setType(data[2]);
                String lat = String.valueOf(data[3]);
                lat = lat.trim();
                liftspot.setLat(Float.valueOf(lat));
                String lon = String.valueOf(data[4]);
                lon = lon.trim();
                liftspot.setLon(Float.valueOf(lon));

                // add object to list
                liftspots.add(liftspot);
                idCounter ++;
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return liftspots;
    }
}
