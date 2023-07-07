package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {

  public static Map<String,String> regresTestDataMethod(String name,String job){
         Map<String ,String > regresTestDataMap=new HashMap<>();
         regresTestDataMap.put(name,"morpheus");
         regresTestDataMap.put(job,"leader");

      return regresTestDataMap;

    }
}
