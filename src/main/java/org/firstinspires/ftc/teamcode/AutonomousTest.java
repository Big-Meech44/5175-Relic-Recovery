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
 * Created by robot on 2/6/2018.
 */

@Autonomous(name = "Autonomous Test", group = "LinearOpMode")
@Disabled

public class AutonomousTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftTread;
    DcMotor rightTread;
    Servo armServo;
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
        colorSensor = hardwareMap.get(ColorSensor.class, "ColorSensor0");
        int b = 1;

        waitForStart();
        armServo.setPosition(-.5);
        colorSensor.enableLed(true);
        sleep(2000);
        armServo.setPosition(.5);
        sleep(2000);
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Red ", colorSensor.red());
        telemetry.addData("Green ", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.update();

    }
}
