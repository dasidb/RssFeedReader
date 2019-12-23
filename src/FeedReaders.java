import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class FeedReaders {
    public String sourceCodeGoogle = "";
    String[] checkArray = {"title>", "pubDate>"};
    public String readRss(String feedUrl) {
        try {
            URL rssUrl = new URL(feedUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            int firstPos;
            String tmp;
            int lastPos;
            String feedLine = "";

            while ((line = reader.readLine()) != null) {
                feedLine = line;
                if (feedUrl == "https://news.google.com/rss?topic=h&hl=de&gl=DE&ceid=DE:de") {
                    sourceCode = readGoogle(line);
                    return sourceCode;
                }

                if (feedLine.contains("<title>")) {

                    firstPos = feedLine.indexOf("<title>");
                    tmp = feedLine.substring(firstPos);
                    tmp = tmp.replace("<title>", "Titel: ");
                    lastPos = tmp.indexOf("</title>");
                    tmp = tmp.substring(0, lastPos);
                    sourceCode += tmp + "\n";
                }

                if (feedLine.contains("<category>")) {
                    firstPos = feedLine.indexOf("<category>");
                    tmp = feedLine.substring(firstPos);
                    tmp = tmp.replace("<category>", "Typ: ");
                    lastPos = tmp.indexOf("</category>");
                    tmp = tmp.substring(0, lastPos);
                    sourceCode += tmp + "\n";

                }
                if (feedLine.contains("<media:keywords>")) {
                    firstPos = feedLine.indexOf("<media:keywords>");
                    tmp = feedLine.substring(firstPos);
                    //System.out.println(tmp + "das ist temp");
                    tmp = tmp.replace("<media:keywords>", "Genre: ");
                    lastPos = feedLine.indexOf("</media:keywords>");
                    tmp = tmp.substring(0, lastPos);
                    sourceCode += tmp + "\n" + "\n";
                }
            }


            reader.close();
            return sourceCode;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
        public String readGoogle (String line){
            boolean googleSucks = true;
            String tmp = line;
            int i = 0;
            while (googleSucks) {
                try {
                    if (i == checkArray.length )
                        i = 0;
                    int start = tmp.indexOf("<"+ checkArray[i]);
                    int end = tmp.indexOf("</" + checkArray[i]);
                    String tmpString = tmp.substring(start, end);
                    tmpString = tmpString.replaceAll("ä", "ae");
                    tmpString = tmpString.replaceAll("ü","ue");
                    tmpString = tmpString.replaceAll("ö","oe");
                    tmpString = tmpString.replaceAll(" \" ","oe");
                    tmpString = tmpString.replaceAll("ß","ss");
                    if(i == 0){
                        tmpString.replaceAll(",", " ");
                    }
                    sourceCodeGoogle += tmpString.replace("<" + checkArray[i], checkArray[i]) + " endline\n";
                    tmp = line.substring(end);
                    line = line.substring(end);

                    i++;

           /*         start = tmp.indexOf("<pubDate>");
                    end = tmp.indexOf("</pubDate>");
                    tmpString = tmp.substring(start, end);
                    tmp = line.substring(end);
                    line = line.substring(end);
                    sourceCodeGoogle += "  " + tmpString.replace("<pubDate>", " Veröffentlicht: ") + "\n";
                    */
                } catch (Exception e) {
                    e.printStackTrace();
                    //googleSucks = false;
                    return sourceCodeGoogle;
                }
            }
            return sourceCodeGoogle;
        }
    }
