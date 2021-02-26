import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class money_saver {
    public static void main(String[] args) throws IOException{
        System.out.println("Please input the file path of the CSV file you would like to read");
        String fileName = System.console().readLine();
        file_handling file = new file_handling();

        file.readFile(fileName);
        
        
        
    }
}


/** 
4 files: 

    message service:
        allows notifications to customers for events
        (system.out)
        check against user preferences

    main:
        read file in, display help, call transaction account service, message service
        ask to specify file

    transaction account service:
        add money to savings
        add money to current
        if negative call manage account service

    manage account service:
        do flow chart, move money around
z
*/