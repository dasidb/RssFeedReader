import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class WriteCsv {
    Path csvPath = Paths.get("C:\\Users\\lukas.kapust\\Desktop\\java test\\FeedReader\\test.csv");
    File file = new File("C:\\Users\\lukas.kapust\\Desktop\\java test\\FeedReader\\test.csv");
    FileWriter csvWrite;
    String line;
    boolean duplet;
    int i = 0;
    String tmpLog;



        public void writeToCsv(String log) {
            //System.out.println(log);
            String[] test = log.split(" endline");
       /*     byte data[] = log.getBytes();
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(csvPath, CREATE, APPEND))) {
                InputStream in = new BufferedInputStream(Files.newInputStream(csvPath));
                //System.out.println(in.);


                out.write(data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
            } */


                    System.out.println(i);
                    try(FileWriter csvWrite = new FileWriter(file,true)){
                        BufferedReader csvReader = new BufferedReader(new FileReader(file));
                        System.out.println(test.length);
                        for(String str : test) {
                            //System.out.println(str);
                            String tempStr = str;
                            tempStr += " endline";
                            //System.out.println(str);
                            while ((line = csvReader.readLine()) != null) {
                                //System.out.println("blub");
                                if (line.equals(tempStr)) {
                                    System.out.println("test");
                                    duplet = true;
                                    break;
                                }else{
                                    // tmpLog += str +"\n";
                                   //  System.out.println(tempStr);

                                  //  csvWrite.write(tempStr);
                                }
                            }
                        if(!duplet) {
                            csvWrite.write(tempStr);
                            duplet = false;
                        }
                        }
                        //if(!duplet) {

                            //csvWrite.write(tmpLog);
                            duplet = false;
                       // }else{

                     //   }
                  }catch (Exception e){
                    e.printStackTrace();
                   }
        }
}
