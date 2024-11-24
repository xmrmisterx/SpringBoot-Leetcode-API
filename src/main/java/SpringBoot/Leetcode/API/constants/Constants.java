package SpringBoot.Leetcode.API.constants;

import SpringBoot.Leetcode.API.problem.Problem;
import SpringBoot.Leetcode.API.problem.ProblemType;

import java.time.LocalDateTime;

public class Constants {
    public static final Problem PROBLEM1 = new Problem(
            1,
            "Remove Duplicates from Sorted Array II",
            "https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150",
            ProblemType.ARRAY,
            LocalDateTime.now(),
            "Note 1"
    );

    public static final Problem PROBLEM2 = new Problem(
            2,
            "3Sum",
            "https://leetcode.com/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150",
            ProblemType.TWO_POINTER,
            LocalDateTime.now(),
            "Note 2"
    );

    public static final Problem PROBLEM3 = new Problem(
            3,
            "Reverse Linked List II",
            "https://leetcode.com/problems/reverse-linked-list-ii/description/?envType=study-plan-v2&envId=top-interview-150",
            ProblemType.LINKED_LIST,
            LocalDateTime.now(),
            "Note 3"
    );

    public static final Problem UPDATED_PROBLEM1 = new Problem(
            1,
            "Remove Duplicates from Sorted Array II",
            "https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150",
            ProblemType.ARRAY,
            LocalDateTime.now(),
            "Note 5"
    );

    public static final String BASE_ROUTE = "/api/problems";
}
