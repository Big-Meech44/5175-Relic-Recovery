package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robot on 2/6/2018.
 */

@Autonomous(name = "Autonomous Red Left", group = "LinearOpMode")

public class AutonomousRedLeft extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftTread;
    DcMotor rightTread;
    DcMotor Lift;
    DcMotor Clamp;
    Servo armServo;
    ColorSensor colorSensor;


    @Override
    public void runOpMode() {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        leftTread = hardwareMap.dcMotor.get("Left");
        rightTread = hardwareMap.dcMotor.get("Right");
        Clamp = hardwareMap.dcMotor.get("Clamp");
        Lift = hardwareMap.dcMotor.get("Lift");
        rightTread.setDirection(DcMotor.Direction.REVERSE);
        armServo = hardwareMap.get(Servo.class, "Arm Servo");
        colorSensor = hardwareMap.get(ColorSensor.class, "Color Sensor");
        int b = 1;

        waitForStart();
        armServo.setPosition(-1);
        colorSensor.enableLed(true);
        while (opModeIsActive()) {
            sleep(2000);
            colorSensor.enableLed(true);
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            telemetry.addData("Red ", colorSensor.red());
            telemetry.addData("Green ", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.update();
            if (colorSensor.red() > colorSensor.blue() && b == 1) {
                Clamp.setPower(.5);
                sleep(500);
                Clamp.setPower(0);
                sleep(200);
                Lift.setPower(-.5);
                sleep(600);
                Lift.setPower(0);
                sleep(200);
                colorSensor.enableLed(true);
                leftTread.setPower(.5);
                rightTread.setPower(-.5);
                sleep(500);
                armServo.setPosition(-1);
                sleep(1000);
                leftTread.setPower(.5);
                rightTread.setPower(-.5);
                sleep(3000);
                leftTread.setPower(0);
                rightTread.setPower(0);
                sleep(500);
                leftTread.setPower(.5);
                rightTread.setPower(.5);
                sleep(3000);
                leftTread.setPower(0);
                rightTread.setPower(0);
                sleep(200);
                b = 2;
            }
            else if (colorSensor.blue() > colorSensor.red() && b == 1) {
                Clamp.setPower(.5);
                sleep(500);
                Clamp.setPower(0);
                sleep(200);
                Lift.setPower(-.5);
                sleep(600);
                Lift.setPower(0);
                sleep(200);
                colorSensor.enableLed(true);
                leftTread.setPower(-.5);
                rightTread.setPower(.5);
                sleep(500);
                armServo.setPosition(-1);
                sleep(1000);
                leftTread.setPower(-.5);
                rightTread.setPower(.5);
                sleep(3000);
                leftTread.setPower(0);
                rightTread.setPower(0);
                sleep(500);
                leftTread.setPower(.5);
                rightTread.setPower(.5);
                sleep(3000);
                leftTread.setPower(0);
                rightTread.setPower(0);
                sleep(200);
                b = 2;

            } else {
                armServo.setPosition(1);
            }
            telemetry.update();
            idle();

        }
    }
}

