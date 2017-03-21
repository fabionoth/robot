package com.fabionoth.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fabionoth.controller.RobotController;
import com.fabionoth.model.CardinalPoints;
import com.fabionoth.model.Robot;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotRest {

    /**
     * Específicações gerais Eu não sei se é para iniciar um novo Robo a cada
     * chamada, ou quando eu inicio o servidor.
     */


    /**
     *
     * @param command
     * @return
     */
    @RequestMapping(value = {"rest/mars/", "/rest/mars/{command}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> sendCommand(@PathVariable Optional<String> command) {
        Robot robot;
        robot = new Robot(new Long(1), 0, 0, CardinalPoints.N);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        
        if (command.isPresent()) {
            try {
                robot = new RobotController(robot, command.get()).getRobot();
            } catch (Exception ex) {
                Logger.getLogger(RobotRest.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>("Erro",responseHeaders,HttpStatus.BAD_REQUEST);
            }
        }
        
        String response = "(" + robot.getX() + ", " + robot.getY() + ", " + robot.getC().toString() + ")";
        return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);

    }
}
