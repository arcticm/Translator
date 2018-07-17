import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONParser;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class Translator {
    private static String key = "trnsl.1.1.20180710T135956Z.b995e46594e1e423.a29dd72f352d45252df33d3dd04716c729da5c5f";
    private static String adress = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            System.out.println(translate(input));
            input = scanner.nextLine();   
        }
    }

    public static String translate(String input) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(input, headers);
        String url = adress + key + "&text=" + input + "&lang=en-ru";
        JSONObject answer = restTemplate.postForObject(url, entity, JSONObject.class);
        String result = answer.getJsonString("text");
        return answer;
    }

}