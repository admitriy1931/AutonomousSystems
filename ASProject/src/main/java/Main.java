import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public class JsonFields {
        String ip;
        String city;
        String region;
        String country;
        String loc;
        String org;
        String postal;
    }
    private static String Regex1 = "((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))";
    private static String Regex2 = "[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,5}";
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String ip = in.nextLine();
        in.close();
        if(Pattern.matches(Regex1,ip) || Pattern.matches(Regex2,ip)){
            String command = "tracert -d ";
            ArrayList<String> lines = getArrayLines(command, ip);
            ArrayList<String> ipAdrasses = getArrayIpAdrasses(lines);
            for (int i = 1; i < ipAdrasses.size(); i++) {
                String json = readUrl("https://ipinfo.io/" + ipAdrasses.get(i) + "?token=930655cd47dcde");
                Gson gson = new Gson();
                JsonFields page = gson.fromJson(json, JsonFields.class);
                System.out.println(i + "| " + page.ip + " | " + page.org + " | " + page.ip + " | " + page.country);
            }
        }
        else{
            System.out.println("Invalid data");
        }
    }

    public static ArrayList<String> getArrayLines(String command, String ip) throws IOException {
        Process process = Runtime.getRuntime().exec(command + ip);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        ArrayList<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public static ArrayList<String> getArrayIpAdrasses(ArrayList<String> lines) {
        ArrayList<String> ipAdrasses = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            String input = lines.get(i);
            Pattern pattern = Pattern.compile(Regex1);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find())
                ipAdrasses.add(matcher.group(1));
        }
        return ipAdrasses;
    }
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}

