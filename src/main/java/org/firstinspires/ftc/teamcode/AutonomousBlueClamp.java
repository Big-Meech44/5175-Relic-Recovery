package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robot on 2/12/2018.
 */

@Autonomous(name = "Autonomous Blue Clamp", group = "LinearOpMode")
@Disabled
public class AutonomousBlueClamp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftTread;
    DcMotor rightTread;
    Servo armServo;
    Servo clampServo;
    ColorSensor colorSensor;


    @Override
    public void runOpMode() {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        leftTread = hardwareMap.dcMotor.get("Left");
        rightTread = hardwareMap.dcMotor.get("Right");
        rightTread.setDirection(DcMotor.Direction.REVERSE);
        armServo = hardwareMap.get(Servo.class, "Left Servo");
        clampServo = hardwareMap.get(Servo.class, "Right Servo");
        colorSensor = hardwareMap.get(ColorSensor.class, "ColorSensor0");
        int b = 1;

        waitForStart();
        clampServo.setPosition(1);
        armServo.setPosition(-1);
        colorSensor.enableLed(true);
        while (opModeIsActive()) {
            sleep(2000);
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            telemetry.addData("Red ", colorSensor.red());
            telemetry.addData("Green ", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.update();
            if (colorSensor.blue() >= 2 && b == 1) {
                leftTread.setPower(.5);
                rightTread.setPower(-.5);
                sleep(500);
                leftTread.setPower(-.5);
                rightTread.setPower(.5);
                sleep(500);
                b = 2;
            }
            else if (colorSensor.blue() < 2 && b == 1) {
                leftTread.setPower(-.5);
                rightTread.setPower(.5);
                sleep(500);
                leftTread.setPower(.5);
                rightTread.setPower(-.5);
                sleep(500);
                b = 2;

            } else {
                armServo.setPosition(1);
            }
            telemetry.update();
            idle();

        }
    }
}
