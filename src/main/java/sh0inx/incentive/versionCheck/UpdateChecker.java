package sh0inx.incentive.versionCheck;

import sh0inx.incentive.Incentive;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;


public class UpdateChecker {

    //TODO: Update method to load async
    public static String getCurrentVersion() {
        Gson gson = new Gson();
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("https://api.modrinth.com/v2/project/" + Incentive.pluginID + "/version");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println(Incentive.debugPrefix + "RESPONSE: " + responseCode);

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
            }

            jsonModel[] mapVersion = gson.fromJson(response.toString(), jsonModel[].class);
            return mapVersion[0].currentVersion;

        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to check for current version.";
        }
    }
}