package me.myTp;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class TelegramMessageSender {
    private String botToken;
    private String chatId;

    public TelegramMessageSender(String botToken, String chatId) {
        this.botToken = botToken;
        this.chatId = chatId;
    }

    public void sendMessage(String message, Plugin plugin) {
        BukkitRunnable r = new BukkitRunnable() {

            @Override
            public void run() {

                try {
                    URL url = new URL("https://api.telegram.org/" + botToken + "/sendMessage");

                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    con.setDoOutput(true);
                    con.setDoInput(true);

                    DataOutputStream output = new DataOutputStream(con.getOutputStream());
                    output.writeBytes("chat_id=" + chatId + "&text=" + message);
                    output.flush();

                    output.flush();
                    output.close();

                    DataInputStream input = new DataInputStream(con.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    String line;

//                    Main.getMainLogger().info("Data sent successfully!");
//                    Main.getMainLogger().info("Resp Code:"+con.getResponseCode());
                    System.out.println("Resp Message:"+con.getResponseMessage());

//                    while ((line = reader.readLine()) != null) {
  //                      Main.getMainLogger().info("Report: "+line);
  //                  }

                    input.close();

                } catch (UnsupportedEncodingException e) {

    //                Main.getMainLogger().log(Level.SEVERE, "Encoding error. Maybe string have invalid caracters.", e);
                } catch (MalformedURLException e) {

      //              Main.getMainLogger().log(Level.SEVERE, "Invalid URL. Verify your URL and try again.", e);
                } catch (ProtocolException e) {

        //            Main.getMainLogger().log(Level.SEVERE, "Error on HttpPOST request.", e);
                } catch (IOException e) {

          //          Main.getMainLogger().log(Level.SEVERE, "Error on HTTP connection.", e);
                }
            }
        };
        r.runTaskAsynchronously(plugin);
    }
}
