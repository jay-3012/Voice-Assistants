import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.net.*;
import javax.naming.directory.*;
import java.awt.*;

public class App { @Deprecated
    public static void main(String[] args)
    {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\4274.dic");
        config.setLanguageModelPath("src\\4274.lm");
        try
        {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true);
            SpeechResult speechResult = null;
            Desktop desk = Desktop.getDesktop();
            while((speechResult =speech.getResult() ) != null) {
                String result = speechResult.getHypothesis();
                System.out.println("Voice Command is : " + result);

                if (result.equalsIgnoreCase("tab"))
                {
                    //Runtime.getRuntime().exec("cmd.exe start brave www.google.com");
                    URI uri = new URI("www.google.com");
                    desk.browse(uri);
                }
                else if(result.equalsIgnoreCase("close"))
                {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM brave.exe");
                }
                else if(result.equalsIgnoreCase("apna"))
                {
                    URI uri = new URI("www.apnacollege.in/?msg=not-logged-in");
                    desk.browse(uri);
                }
                else if (result.equalsIgnoreCase("shutdown"))
                {
                    Runtime.getRuntime().exec("shutdown -s -t 5");
                }
                else if(result.equalsIgnoreCase("restart"))
                {
                    Runtime.getRuntime().exec("shutdown -r -t 5");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
