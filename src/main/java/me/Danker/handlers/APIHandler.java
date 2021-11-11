package me.Danker.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import me.Danker.DankersSkyblockMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class APIHandler {
    public static JsonObject getResponse(String urlString) {
        EntityPlayerSP entityPlayerSP = (Minecraft.getMinecraft()).thePlayer;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String input;
                while ((input = in.readLine()) != null)
                    response.append(input);
                in.close();
                Gson gson = new Gson();
                return (JsonObject)gson.fromJson(response.toString(), JsonObject.class);
            }
            if (urlString.startsWith("https://api.hypixel.net/")) {
                InputStream errorStream = conn.getErrorStream();
                try (Scanner scanner = new Scanner(errorStream)) {
                    scanner.useDelimiter("\\Z");
                    String error = scanner.next();
                    Gson gson = new Gson();
                    return (JsonObject)gson.fromJson(error, JsonObject.class);
                }
            }
            if (urlString.startsWith("https://api.mojang.com/users/profiles/minecraft/") && conn.getResponseCode() == 204) {
                entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: Player does not exist."));
            } else {
                entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Request failed. HTTP Error Code: " + conn.getResponseCode()));
            }
        } catch (IOException ex) {
            entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "An error has occured. See logs for more details."));
            ex.printStackTrace();
        }
        return new JsonObject();
    }

    public static JsonArray getArrayResponse(String urlString) {
        EntityPlayerSP entityPlayerSP = (Minecraft.getMinecraft()).thePlayer;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String input;
                while ((input = in.readLine()) != null)
                    response.append(input);
                in.close();
                Gson gson = new Gson();
                return (JsonArray)gson.fromJson(response.toString(), JsonArray.class);
            }
            entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Request failed. HTTP Error Code: " + conn.getResponseCode()));
        } catch (IOException ex) {
            entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "An error has occured. See logs for more details."));
            ex.printStackTrace();
        }
        return new JsonArray();
    }

    public static String getUUID(String username) {
        JsonObject uuidResponse = getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
        return uuidResponse.get("id").getAsString();
    }

    public static String getLatestProfileID(String UUID, String key) {
        EntityPlayerSP entityPlayerSP = (Minecraft.getMinecraft()).thePlayer;
        System.out.println("Fetching profiles...");
        JsonObject profilesResponse = getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
        if (!profilesResponse.get("success").getAsBoolean()) {
            String reason = profilesResponse.get("cause").getAsString();
            entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
            return null;
        }
        if (profilesResponse.get("profiles").isJsonNull()) {
            entityPlayerSP.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "This player doesn't appear to have played SkyBlock."));
            return null;
        }
        System.out.println("Looping through profiles...");
        String latestProfile = "";
        long latestSave = 0L;
        JsonArray profilesArray = profilesResponse.get("profiles").getAsJsonArray();
        for (JsonElement profile : profilesArray) {
            JsonObject profileJSON = profile.getAsJsonObject();
            long profileLastSave = 1L;
            if (profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().has("last_save"))
                profileLastSave = profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong();
            if (profileLastSave > latestSave) {
                latestProfile = profileJSON.get("profile_id").getAsString();
                latestSave = profileLastSave;
            }
        }
        return latestProfile;
    }
}
