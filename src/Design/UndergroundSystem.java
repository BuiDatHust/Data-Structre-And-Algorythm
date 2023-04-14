package Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UndergroundSystem {
    class Pair<T,V>{
        public T first;
        public V second;

        Pair(T first, V second){
            this.first= first;
            this.second= second;
        }
    }
    public Map<Integer, Pair<String,Integer>> checkInStations = new HashMap<>();
    public Map<String,List<Integer>> routes = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        checkInStations.put(id, new Pair<>(stationName,t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String,Integer> checkIn = checkInStations.get(id);
        checkInStations.remove(id);

        String startStation = checkIn.first;
        Integer startTime = checkIn.second;
        String routeName = startStation+","+stationName;

        routes.putIfAbsent(routeName, new ArrayList<>(List.of(0,0)));
        List<Integer> route = routes.get(routeName);
        route.set(0, route.get(0)+1);
        route.set(1, route.get(1)+t-startTime);
        routes.put(routeName,route);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeName = startStation+","+endStation;
        List<Integer> route = routes.get(routeName);
        return (double) route.get(1) /route.get(0);
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }
}
