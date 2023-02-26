// b)	Write multithreaded web crawler

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QN7b {

    private static final Pattern LINK_PATTERN = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1");

    private Set<URL> visited = new HashSet<>();
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void crawl(URL url) {
        visited.add(url);

        System.out.println("Crawling " + url);

        executor.execute(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = LINK_PATTERN.matcher(line);
                    while (matcher.find()) {
                        String link = matcher.group(2);
                        URL nextUrl = new URL(url, link);
                        if (!visited.contains(nextUrl)) {
                            crawl(nextUrl);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) throws IOException {
        QN7b crawler = new QN7b();
        crawler.crawl(new URL("https://www.example.com"));
        crawler.shutdown();
    }
}
