package tm.service.schedule;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<MatchToSchedule> matchSchedule = new ArrayList<>();

    public ArrayList<MatchToSchedule> getMatchSchedule() {
        return matchSchedule;
    }

    public void getSchedule(ArrayList<Integer> arr){
        int counterId=0;
        int counterRounds=0;
        int last = arr.size()-1;
        while(counterRounds < arr.size()-1){
            for(int i=0; i<arr.size()/2; i++){
                matchSchedule.add(new MatchToSchedule(counterId, arr.get(i), arr.get(last-i)));
                counterId++;
            }
            counterRounds++;
            int tempValue = arr.get(last);
            arr.remove(last);
            arr.add(1, tempValue);
        }
        System.out.println(matchSchedule);
    }

}
