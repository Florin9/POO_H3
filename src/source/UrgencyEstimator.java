package source; //import entities.enums.source.IllnessType;
//import entities.enums.source.Urgency;

import java.util.HashMap;
import java.util.Map;


/**
 * Estimates the urgency based on the patient's illness and how severe the illness is manifested.
 */
public final class UrgencyEstimator {

    private static UrgencyEstimator instance;
    private Map<Urgency, HashMap<IllnessType, Integer>> algorithm;

    private UrgencyEstimator() {
        algorithm = new HashMap<Urgency, HashMap<IllnessType, Integer>>() {
            {
                put(Urgency.IMMEDIATE,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN, Numbers.SIXTY);
                                put(IllnessType.ALLERGIC_REACTION, Numbers.FIFTY);
                                put(IllnessType.BROKEN_BONES, Numbers.EIGHTY);
                                put(IllnessType.BURNS, Numbers.FORTY);
                                put(IllnessType.CAR_ACCIDENT, Numbers.THIRTY);
                                put(IllnessType.CUTS, Numbers.FIFTY);
                                put(IllnessType.FOOD_POISONING, Numbers.FIFTY);
                                put(IllnessType.HEART_ATTACK, 0);
                                put(IllnessType.HEART_DISEASE, Numbers.FORTY);
                                put(IllnessType.HIGH_FEVER, Numbers.SEVENTY);
                                put(IllnessType.PNEUMONIA, Numbers.EIGHTY);
                                put(IllnessType.SPORT_INJURIES, Numbers.SEVENTY);
                                put(IllnessType.STROKE, 0);

                            }
                        });

                put(Urgency.URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN, Numbers.FORTY);
                                put(IllnessType.ALLERGIC_REACTION, Numbers.THIRTY);
                                put(IllnessType.BROKEN_BONES, Numbers.FIFTY);
                                put(IllnessType.BURNS, Numbers.TWENTY);
                                put(IllnessType.CAR_ACCIDENT, Numbers.TWENTY);
                                put(IllnessType.CUTS, Numbers.THIRTY);
                                put(IllnessType.HEART_ATTACK, 0);
                                put(IllnessType.FOOD_POISONING, Numbers.TWENTY);
                                put(IllnessType.HEART_DISEASE, Numbers.TWENTY);
                                put(IllnessType.HIGH_FEVER, Numbers.FORTY);
                                put(IllnessType.PNEUMONIA, Numbers.FIFTY);
                                put(IllnessType.SPORT_INJURIES, Numbers.FIFTY);
                                put(IllnessType.STROKE, 0);
                            }
                        });

                put(Urgency.LESS_URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN, Numbers.TEN);
                                put(IllnessType.ALLERGIC_REACTION, Numbers.TEN);
                                put(IllnessType.BROKEN_BONES, Numbers.TWENTY);
                                put(IllnessType.BURNS, Numbers.TEN);
                                put(IllnessType.CAR_ACCIDENT, Numbers.TEN);
                                put(IllnessType.CUTS, Numbers.TEN);
                                put(IllnessType.FOOD_POISONING, 0);
                                put(IllnessType.HEART_ATTACK, 0);
                                put(IllnessType.HEART_DISEASE, Numbers.TEN);
                                put(IllnessType.HIGH_FEVER, 0);
                                put(IllnessType.PNEUMONIA, Numbers.TEN);
                                put(IllnessType.SPORT_INJURIES, Numbers.TWENTY);
                                put(IllnessType.STROKE, 0);
                            }
                        });

            }
        };
    }

    public static UrgencyEstimator getInstance() {
        if (instance == null) {
            instance = new UrgencyEstimator();
        }
        return instance;
    }

    //called by doctors and nurses
    public Urgency estimateUrgency(IllnessType illnessType, int severity) {

        if (severity >= algorithm.get(Urgency.IMMEDIATE).get(illnessType)) {
            return Urgency.IMMEDIATE;
        }
        if (severity >= algorithm.get(Urgency.URGENT).get(illnessType)) {
            return Urgency.URGENT;
        }
        if (severity >= algorithm.get(Urgency.LESS_URGENT).get(illnessType)) {
            return Urgency.LESS_URGENT;
        }
        return Urgency.NON_URGENT;
    }
}
