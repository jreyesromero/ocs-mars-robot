package com.ocs.marsrobot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ocs.marsrobot.exception.MaterialDoesNotValidException;
import com.ocs.marsrobot.json.MarsRobotJsonResponse;
import com.ocs.marsrobot.model.Position;
import com.ocs.marsrobot.model.Location;

import java.util.ArrayList;

@Component
public class MarsRobotErrorHandler {

    private static final String DEFAULT = "There is an exception while treating input request";
    private static final String MATERIAL_NOT_VALID = "Terrain includes a non valid material";

    public MarsRobotJsonResponse handler(Exception exception) {

        MarsRobotJsonResponse marsRobotJsonResponse = configureResponseByDefault();
        ArrayList<String> samplesCollected = new ArrayList<String>();

        if (exception instanceof MaterialDoesNotValidException) {
            samplesCollected.add(MATERIAL_NOT_VALID);
            //return ResponseEntity.badRequest().body(MATERIAL_NOT_VALID);
        }

        marsRobotJsonResponse.setSamplesCollected(samplesCollected);
        System.out.println("ErrorHandler. Exception returned:\n" + marsRobotJsonResponse);
        return marsRobotJsonResponse;
    }

    private MarsRobotJsonResponse configureResponseByDefault() {
        ArrayList<Location> visitedCells = new ArrayList<Location>();
        visitedCells.add(new Location(0,0));
        ArrayList<String> samplesCollected = new ArrayList<String>();
        samplesCollected.add(DEFAULT);
        int battery = 0;
        Position finalPosition = new Position(new Location(0,0), "DEFAULT");

        MarsRobotJsonResponse response = new MarsRobotJsonResponse(visitedCells,
                samplesCollected, battery, finalPosition);
        return response;
    }
}