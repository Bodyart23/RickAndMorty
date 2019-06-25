package com.example.demo.service;

import com.example.demo.model.Character;
import com.example.demo.model.Location;
import com.example.demo.model.Origin;
import com.example.demo.repository.CharacterRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

@EnableScheduling
@Service
public class Parser {
    @Autowired
    private CharacterRepository characterRepository;

//    @Scheduled(cron = "0 * * ? * *")//every minute
    @Scheduled(cron = "0 0 * ? * *") //every hour
    @Autowired
//    @Scheduled(cron = "0 0 0 * * ?") //Every day at midnight - 12am
    public void GetCharacter() throws JSONException {
        System.out.println("Start schedule");
        BufferedReader reader = null;
        String json = null;
        try {
            URL url = new URL("https://rickandmortyapi.com/api/character/");
            HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setReadTimeout(10000);
            c.connect();
            reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            json = buf.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        JSONObject userJson = new JSONObject(json);
        String characters = userJson.getString("results");
        JSONArray array = new JSONArray(characters);
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            JSONObject objOrigin = obj.getJSONObject("origin");
            JSONObject objLocation = obj.getJSONObject("location");

            Location location = new Location();
            location.setName(objLocation.getString("name"));
            location.setUrl(objLocation.getString("url"));

            Origin origin = new Origin();
            origin.setName(objOrigin.getString("name"));
            origin.setUrl(objOrigin.getString("url"));

            Character character = new Character();
            character.setName(obj.getString("name"));
            character.setStatus(obj.getString("status"));
            character.setSpecies(obj.getString("species"));
            character.setType(obj.getString("type"));
            character.setGender(obj.getString("gender"));
            character.setOrigin(origin);
            character.setLocation(location);
            character.setImage(obj.getString("image"));
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = (obj.getJSONArray("episode"));
            if (jArray != null) {
                for (int j = 0; j < jArray.length(); j++) {
                    listdata.add(jArray.getString(j));
                }
            }
            character.setEpisode(listdata);
            character.setUrl(obj.getString("url"));
            character.setCreated(obj.getString("created"));
            characterRepository.save(character);
        }
    }

}
