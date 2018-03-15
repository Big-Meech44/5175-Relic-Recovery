package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by robot on January 27, 2018.
 * For licensing info, see the LICENSE file.
 */
@Autonomous(name = "Autonomous Red", group = "LinearOpMode")
@Disabled
public class AutonomousRed extends LinearOpMode {
    DcMotor leftTread;
    DcMotor rightTread;
    Servo armServo;
    ColorSensor colorSensor;

    @Override public void runOpMode()throws InterruptedException {
        leftTread = hardwareMap.dcMotor.get("Left");
        rightTread = hardwareMap.dcMotor.get("Right");
        rightTread.setDirection(DcMotor.Direction.REVERSE);
        armServo = hardwareMap.get(Servo.class, "Left Servo");
        colorSensor = hardwareMap.get(ColorSensor.class, "ColorSensor0");

        waitForStart();

        armServo.setPosition(-1);
        /*
         * Turn on the LED so we can see the light reflected off the ball
         * For more information about the LED:
         * http://modernroboticsinc.com/Content/Images/uploaded/Sensors/Sensor%20Documentation.pdf
         * Page 32, Active and Passive Measurement Mode
         */
        colorSensor.enableLed(true);
        sleep(100);
        if ((colorSensor.red() - colorSensor.blue()) > 10) {
            //ball is red
            leftTread.setPower(0.3);
            rightTread.setPower(-0.3);
            sleep(260);
            leftTread.setPower(0);
            rightTread.setPower(0);
            sleep(200);
            armServo.setPosition(0.5);
            leftTread.setPower(-0.3);
            rightTread.setPower(0.3);
            sleep(260);
            leftTread.setPower(0);
            rightTread.setPower(0);
        } else if ((colorSensor.blue() - colorSensor.red()) > 10) {
            //ball is blue
            leftTread.setPower(-0.3);
            rightTread.setPower(0.3);
            sleep(260);
            leftTread.setPower(0);
            rightTread.setPower(0);
            sleep(200);
            armServo.setPosition(0.5);
            leftTread.setPower(0.3);
            rightTread.setPower(-0.3);
            sleep(260);
            leftTread.setPower(0);
            rightTread.setPower(0);
        }
        colorSensor.enableLed(false);
        armServo.setPosition(1);
        leftTread.setPower(1);
        rightTread.setPower(1);
        sleep(750);


        leftTread.setPower(0);
        rightTread.setPower(0);
        sleep(20000);
    }



}
