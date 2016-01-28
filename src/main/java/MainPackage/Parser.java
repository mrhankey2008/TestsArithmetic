package MainPackage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Kovalev Denis on 19.01.2016.
 */
public class Parser
{
    private  List<Parameters> arithmeticOperations;

    public Parser(){
        arithmeticOperations =  new ArrayList<Parameters>();
    }

   public List<Parameters> getArithmeticOperations(String dataFilePath) throws Exception {
       try {
           Scanner sc = null;
           sc = new Scanner(new FileInputStream(dataFilePath), "windows-1251");

           while (sc.hasNextLine())
           {
               String currentStr = sc.next();
               String[] parametersStrings = currentStr.split(";");
               Parameters parameters = new Parameters();
               parameters.operand1 = parametersStrings[0];
               parameters.operand2 = parametersStrings[1];
               parameters.operation = parametersStrings[2].charAt(0);
               parameters.result =  parametersStrings[3];
               arithmeticOperations.add(parameters);
           }
           return arithmeticOperations;
       }
       catch (FileNotFoundException e)
       {
            e.printStackTrace();
           return null;
       }
   }

    public static boolean isDoubleParseInt(String str) {
        try {
           Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }

    public int countOperation(char operation)
    {
        int countOperation = 0;
        for(Parameters i:arithmeticOperations)
        {
            if(i.operation == operation)
            {
              countOperation++;
            }
        }
        return countOperation;
    }


}
