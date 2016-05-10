package code.service;

import code.model.DistancePK;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class RaceCostService {

    public static final double DISTANCE_COEFFICIENT = 300;

    public double calculateCost(double trainTypeCoefficient, int stationIdFrom, int stationIdTo) {
        DistancePK pk = new DistancePK(stationIdFrom, stationIdTo);
        int distance = new DistanceService().getDistance(pk);
        return calculateCost(trainTypeCoefficient, distance);
    }

    public double calculateCost(double trainTypeCoefficient, int distance) {
        return distance * DISTANCE_COEFFICIENT * trainTypeCoefficient;
    }

}
