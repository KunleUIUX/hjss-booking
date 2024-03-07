package org.hjss.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NameGenerator {
    private static final List<String> ALL_NAMES = Arrays.asList(
            "Liam", "Olivia", "Noah", "Emma", "Sophia", "Ava", "Isabella", "Mia", "Elijah", "Amelia",
            "Oliver", "Harper", "Benjamin", "Aria", "Lucas", "Evelyn", "Mason", "Abigail", "Logan", "Emily",
            "Alexander", "Scarlett", "Ethan", "Luna", "Aiden", "Chloe", "James", "Zoe", "Carter", "Mila",
            "Jackson", "Lily", "Sebastian", "Ella", "Michael", "Grace", "Daniel", "Nora", "Joseph", "Avery",
            "David", "Sofia", "Samuel", "Madison", "Henry", "Layla", "Owen", "Eleanor", "Wyatt", "Hannah",
            "Matthew", "Ellie", "Leo", "Aubrey", "Jack", "Lillian", "Leo", "Addison", "Nicholas", "Aurora",
            "Emma", "Liam", "Olivia", "Noah", "Ava", "Sophia", "Isabella", "Mia", "Jackson", "Lucas",
            "Aiden", "Ella", "Oliver", "Avery", "Evelyn", "Mila", "Liam", "Lily", "James", "Zoe",
            "Benjamin", "Charlotte", "Amelia", "Harper", "Ethan", "Aria", "Ellie", "Grace", "Abigail", "Leo",
            "Ella", "Scarlett", "Nora", "Isla", "Riley", "Zoe", "Hazel", "Madeline", "Claire", "Luna",
            "Layla", "Eva", "Aubrey", "Chloe", "Lucy", "Liam", "Emma", "Olivia", "Noah", "Ava",
            "Sophia", "Isabella", "Mia", "Jackson", "Lucas", "Aiden", "Ella", "Oliver", "Avery", "Evelyn",
            "Mila", "Liam", "Lily", "James", "Zoe", "Benjamin", "Charlotte", "Amelia", "Harper", "Ethan",
            "Aria", "Ellie", "Grace", "Abigail", "Leo", "Ella", "Scarlett", "Nora", "Isla", "Riley",
            "Zoe", "Hazel", "Madeline", "Claire", "Luna", "Layla", "Eva", "Aubrey", "Chloe", "Lucy"
    );

    public static String generateRandomCoachName() {
        Random random = new Random();
        return ALL_NAMES.get(random.nextInt(ALL_NAMES.size()));
    }

    public static String generateRandomLearnerName() {
        Random random = new Random();
        return ALL_NAMES.get(random.nextInt(ALL_NAMES.size()));
    }
}
