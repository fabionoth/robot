/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabionoth.controller;

import com.fabionoth.model.CardinalPoints;
import com.fabionoth.model.Robot;

/**
 *
 * @author fabio
 */
public class RobotController {

    private final Robot robot;
    private final String commands;

    public RobotController(Robot robot, String commands) throws Exception {
        this.robot = robot;
        this.commands = commands;
        init();
    }

    private void init() throws Exception {

        for (char comand : commands.toCharArray()) {
            // Siga em frente
            switch (comand) {
                case 'M':
                    switch (robot.getC()) {
                        case N: 
                            robot.setY(robot.getY() + 1);
                            break;
                        case S: 
                            robot.setY(robot.getY() - 1);
                            break;
                        case W: 
                            robot.setX(robot.getX() - 1);
                            break;
                        case E: 
                            robot.setX(robot.getX() + 1);
                            break;
                        default:
                            break;
                    }
                    break;
                //vire a esquerda
                case 'L':
                    switch (robot.getC()) {
                        case N:
                            robot.setC(CardinalPoints.W);
                            break;
                        case S:
                            robot.setC(CardinalPoints.E);
                            break;
                        case W: 
                            robot.setC(CardinalPoints.S);
                            break;
                        case E: 
                            robot.setC(CardinalPoints.N);
                            break;
                        default:
                            break;
                    }
                    break;
                //vire a direita
                case 'R':
                    switch (robot.getC()) {
                        case N: 
                            robot.setC(CardinalPoints.E);
                            break;
                        case S: 
                            robot.setC(CardinalPoints.W);
                            break;
                        case W: 
                            robot.setC(CardinalPoints.N);
                            break;
                        case E: 
                            robot.setC(CardinalPoints.S);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    throw new Exception("Comando Inválido");
            }
        }
    }

    public Robot getRobot() throws Exception {
        if (robot.getY() > 0b101 || robot.getX() > 0b101 || robot.getX() < 0x0 || robot.getY() < 0x0) {
            throw new Exception("Robo não pode ultrapassar o limite estabelecido");
        } else {
            return robot;
        }
    }

}
