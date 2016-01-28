/**
 * Created by Kovalev Denis on 19.01.2016.
 */
import MainPackage.Arithmetic;
import MainPackage.Parameters;
import MainPackage.Parser;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Parameter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(value = Parameterized.class)
public class DifferenceTest extends TestCase
{
    @Parameter("Operand1")
    private String operand1;
    @Parameter("Operand2")
    private String operand2;
    @Parameter("Operation")
    private char operation;
    @Parameter("Result")
    private String expected;

    public DifferenceTest(String operand1, String operand2,char operation, String result)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation  = operation;
        this.expected = result;
    }


  @Parameterized.Parameters
  public static List<Object[]> data() throws Exception {

      Parser parser = new Parser();
      List<Parameters> parameters = parser.getArithmeticOperations("dataFile.txt");
       List<Object[]> list = new ArrayList<Object[]>();
      for(Parameters i:parameters)
      {
          // 255 - буква 'я' в ASCII таблице, компилятор не хочет билдить проект в командной, когда прописана \
          // следующая конструкция  i.operation <= 'я'
          if(i.operation >= '!' && i.operation <= 255 && i.operation != '+' &&  i.operation != '*' &&  i.operation != '/')
          {
              Object[] objects = new Object[4];
              objects[0] = i.operand1;
              objects[1] = i.operand2;
              objects[2] = i.operation;
              objects[3] = i.result;
              list.add(objects);
          }
      }
      return list;
  }


    @Test
    public void differenceTest() throws Exception {
        if (operation == '-')
        {
            boolean isParseOperand1 = Parser.isDoubleParseInt(operand1);
            boolean isParseOperand2 = Parser.isDoubleParseInt(operand2);
            boolean isParseExpected = Parser.isDoubleParseInt(expected);
            if(isParseOperand1 &&
               isParseOperand2 &&
               isParseExpected
               ){
                double result = Arithmetic.difference(Double.parseDouble(operand1),Double.parseDouble(operand2));
                assertEquals(Double.parseDouble(expected),result,0.00001);
            }
            else
            {
                String errorMsg = "";
                if (!isParseOperand1){
                    errorMsg += "operand1, ";
                }
                if (!isParseOperand2){
                    errorMsg += "operand2, ";
                }
                if(!isParseExpected){
                    errorMsg += "expected, ";
                }
                errorMsg += "has an incorrect format";
                throw new Exception(errorMsg);
            }
        }
        else {
            throw new Exception("Incorrect input arithmetic operation");
        }
    }

}
