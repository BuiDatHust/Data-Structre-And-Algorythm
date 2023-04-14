package HashTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashProblem {
    public static String findItinerary(Map<String, String> dataSet) {
        String result = "";
        String start = "";
        Map<String, String> reverseDataset = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : dataSet.entrySet()) {
            reverseDataset.put(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<String, String> entry : reverseDataset.entrySet()) {
            if (!reverseDataset.containsKey(entry.getValue())) {
                start = entry.getValue();
                break;
            }
        }

        if (start == "") {
            System.out.println("Invalid input");
            return "";
        }
        String to = dataSet.get(start);
        while (to != null) {
            result += start + "->" + to + ",";
            start = to;
            to = dataSet.get(to);
        }

        return result;
    }

//    public static Map<String, Integer> findNumberOfEmployee(Map<String, String> dataSet) {
//        Map<String, Integer> result = new HashMap<String, Integer>();
//        Map<String, List<String>> employees = new HashMap<String, List<String>>();
//
//        for(Map.Entry<String,String> entry : dataSet.entrySet()){
//            List<String> employeeOfManagers = findEmployees(entry.getValue(), entry.getKey(), employees);
//            result.put(entry.getValue(), employeeOfManagers.size());
//        }
//        return result;
//    }

//    public static List<String> findEmployees(String manager, String employee, Map<String, List<String>> employeess){
//        List<String> employeesOfEmployee = employeess.get(employee);
//        List<String> employeesOfManager = employeess.get(manager);
//
//        for(String name: employeesOfEmployee){
//
//        }
//    }

    public static void main(String[] args) {
        Map<String, String> dataSet = new HashMap<String, String>();
        dataSet.put("A", "C");
        dataSet.put("B", "C");
        dataSet.put("C", "F");
        dataSet.put("D", "E");
        dataSet.put("E", "F");
        dataSet.put("F", "F");

//        System.out.println(findItinerary(dataSet));
//        System.out.println(findNumberOfEmployee(dataSet));
    }
}
