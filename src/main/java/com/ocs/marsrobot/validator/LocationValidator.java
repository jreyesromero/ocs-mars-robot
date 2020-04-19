package com.ocs.marsrobot.validator;

//import com.ocs.marsrobot.exception.MaterialDoesNotValidException;

import org.springframework.stereotype.Component;
import com.ocs.marsrobot.model.Robot;
import com.ocs.marsrobot.model.Location;
import com.ocs.marsrobot.exception.LocationDoesNotValidException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationValidator {

    public LocationValidator() {
    }

    public boolean validateCoordinate(Location newLocation,
                                      Robot robot) {
        Integer robotMaxXCoordinate = robot.getMaxXValueFromTerrain();
        Integer robotMaxYCoordinate = robot.getMaxYValueFromTerrain();

        System.out.println("LocationValidator");

        if (isNewXCoordinateValid(robotMaxXCoordinate, newLocation.getX()) &&
                isNewYCoordinateValid(robotMaxYCoordinate, newLocation.getY())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNewXCoordinateValid(Integer maxXCoordinate,
                                          Integer newXCoordinate) {
        if ((newXCoordinate < maxXCoordinate) &&
                (newXCoordinate >= 0)) {
            return true;
        }

        return false;
    }

    private boolean isNewYCoordinateValid(Integer maxYCoordinate,
                                          Integer newYCoordinate) {
        if ((newYCoordinate < maxYCoordinate) &&
                (newYCoordinate >= 0)) {
            return true;
        }

        return false;
    }

}