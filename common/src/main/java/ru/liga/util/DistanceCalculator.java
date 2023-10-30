package ru.liga.util;

import ru.liga.dto.CoordinatesDTO;

public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371;

    public static Double calculate(CoordinatesDTO startPointCoordinatesDTO, CoordinatesDTO finishPointCoordinatesDTO) {
        double startPointLongitude = startPointCoordinatesDTO.getLongitude();
        double startPointLatitude = startPointCoordinatesDTO.getLatitude();
        double finishPointLongitude = finishPointCoordinatesDTO.getLongitude();
        double finishPointLatitude = finishPointCoordinatesDTO.getLatitude();

        double longitudeDifference = Math.toRadians(finishPointLongitude - startPointLongitude);
        double latitudeDifference = Math.toRadians(finishPointLatitude - startPointLatitude);

        double squareHalfDistance = Math.sin(latitudeDifference / 2) * Math.sin(latitudeDifference / 2) +
                Math.cos(Math.toRadians(startPointLatitude)) * Math.cos(Math.toRadians(finishPointLatitude)) *
                        Math.sin(longitudeDifference / 2) * Math.sin(longitudeDifference / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(squareHalfDistance), Math.sqrt(1 - squareHalfDistance));

        return EARTH_RADIUS * centralAngle;
    }
}
