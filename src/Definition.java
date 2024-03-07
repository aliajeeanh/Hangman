import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

/**
 * Class for retrieving definition of the unknown word in the Hangman game.
 * 
 * @author Sheikh Ali Ajeenah
 * @author Karim Haque
 * @version 2023-05-11
 */
public class Definition {
    // The String which will contain the definition after the method
    // definitionOfWord(String word) has been invoked
    public static String definition;

    /**
     * Retrieves the definition of a given word using the Dictionary API.
     * 
     * @param word the word to retrieve the definition for
     * @return the definition of the word
     * @throws Exception if the word cannot be found in the dictionary API
     */
    public static String definitionOfWord(String word) throws Exception {

        URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response = in.lines().collect(Collectors.joining());
            in.close();

            // Parse the JSON response into a Java object
            JSONArray jsonArray = (JSONArray) JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            // Extract the first definition from the Java object
            JSONArray meaningsArray = (JSONArray) jsonObject.get("meanings");
            JSONObject firstMeaning = (JSONObject) meaningsArray.get(0);
            JSONArray definitionsArray = (JSONArray) firstMeaning.get("definitions");
            JSONObject firstDefinition = (JSONObject) definitionsArray.get(0);
            definition = "Definition: " + (String) firstDefinition.get("definition");

        } catch (Exception e) {
            definition = "Unfortunately the word could not be found in the dictionary we are using :( Please consider looking the word up on the Internet!";
        }
        return definition;
    }
}