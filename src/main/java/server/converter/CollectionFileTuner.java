package server.converter;

import server.CollectionManager;
import utility.element.Worker;
import utility.exceptions.EnvException;
import utility.exceptions.IncorrectCollectionException;
import server.commands.ActionInvoker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Scanner;

public class CollectionFileTuner {

    public void connectCollection(String envName){
        Scanner scan=new Scanner(System.in);
        while (true) {
            try {
                ActionInvoker.getInstance().setManager(getCollectionManager(envName));
                break;
            } catch (IncorrectCollectionException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите что-нибудь, чтобы переподключить коллекцию");
                scan.nextLine();
            }
        }
        scan.close();
    }

    public CollectionManager getCollectionManager(String env) throws IOException {
        CollectionConverter converter=new CollectionConverter();
        String address=System.getenv(env);
        if (address==null){
            throw new EnvException("Такой переменной окружения не существует");
        }
        Path file= Paths.get(address);

        Hashtable<String,Worker> collection= new Hashtable(converter.readFile(file));
        return new CollectionManager(collection,file);
    }
}