package sh0inx.incentive.versionCheck;

import sh0inx.incentive.Incentive;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

import com.google.gson.Gson;


public class UpdateChecker {

    //TODO: Update method to load async
    public static String getCurrentVersion() {
        Gson gson = new Gson();
        StringBuilder response = new StringBuilder();
        Logger log = Incentive.getLog();
        String debugPrefix = Incentive.debugPrefix;

        int responseCode = 0;

        try {
            URL url = new URL("https://api.modrinth.com/v2/project/" + Incentive.getPluginID() + "/version");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            responseCode = connection.getResponseCode();
            log.info(debugPrefix + "RESPONSE: " + responseCode);

            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
            } else {
                log.warning(debugPrefix + "NO CONNECTION ESTABLISHED.");
            }

            connection.disconnect();

            jsonModel[] mapVersion = gson.fromJson(response.toString(), jsonModel[].class);
            return mapVersion[0].currentVersion;

        } catch (Exception e) {
            return "Unable to check for current version. (" + responseCode + ") - " + e;
        }
    }
}