import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;


public class FeedMessage {

    static boolean anime = false;
    static boolean arbeit = false;

    public static void main(String[] args) {
        FeedMessage feedMessage = new FeedMessage();
        feedMessage.startRead();

    }

    public void startRead() {
        FeedReaders feedreader = new FeedReaders();
        WriteCsv writeCsv = new WriteCsv();
        writeCsv.writeToCsv(feedreader.readRss("https://news.google.com/rss?topic=h&hl=de&gl=DE&ceid=DE:de"));
        if (anime) {
            //System.out.println(readRss("https://www.crunchyroll.com/rss/anime"));
            //https://www.livechart.me/feeds/episodes
            //System.out.println(readRss("https://www.livechart.me/feeds/episodes"));
            //System.out.println(readRss("https://tokyotosho.info/rss.php"));
        }

        if (arbeit) {
            System.out.println(feedreader.readRss("https://www.chip.de/rss/rss_news_handy.xml"));

            System.out.println(feedreader.readRss("https://news.google.com/rss?topic=h&hl=de&gl=DE&ceid=DE:de"));
        }
    }
}