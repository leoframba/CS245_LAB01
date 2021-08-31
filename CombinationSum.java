
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombinationSum {

    public CombinationSum() {
    }

    public static void main(String[] args) {

        //Create Scanner
        Scanner rdr = new Scanner(System.in);

        //Input the target value
        System.out.println("Input Target Value");
        int target = rdr.nextInt();
        rdr.nextLine();

        //Input candidates
        String input;
        String[] inputArray;
        System.out.println("Input candidates separated by one or more spaces:");
        input = rdr.nextLine();
        inputArray = input.split("\\s+");
        int[] candidates = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            candidates[i] = Integer.parseInt(inputArray[i]);
        }

        //Sort Array from min to max + remove duplicates
        candidates = Arrays.stream(candidates).sorted().distinct().toArray();

        //Run solution method/Print
        CombinationSum cs = new CombinationSum();
        System.out.println(cs.combinationSum(candidates, target).toString());
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //List containing the combinations that sum up to the target
        List<List<Integer>> result = new ArrayList<>();
        //Solve method uses the list of candidates, the target, a templist to hold the attempted
        // solutions, an index tracking which candidate we are trying next, and adds correct
        // combinations to the result list
        solve(candidates, target, new ArrayList<>(), 0, result);
        return result;
    }

    private void solve(int[] candidates, int target, List<Integer> tempList, int index,
                       List<List<Integer>> result) {
        //base case 1. If the the target drops below 0 then that combination is incorrect and
        // must return to the previous combination
        if (target < 0) {
            return;
        }
        //if the target reaches 0 then that combination is correct and gets added to the list of
        // results
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
        }
        //loop that runs through the values of candidates starting from the saved index
        for (int i = index; i < candidates.length; i++) {
            //add the next candidate to the test list
            tempList.add(candidates[i]);
            //recursive call to check if the templist is a correct combination based off
            // subtracting the new candidate from the current target
            solve(candidates, target - candidates[i], tempList, i, result);
            //remove the last candidate added
            tempList.remove(tempList.size() - 1);
        }
    }
}

