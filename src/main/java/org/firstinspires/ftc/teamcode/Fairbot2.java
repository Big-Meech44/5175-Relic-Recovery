package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Demitri Murel on 1/15/2018.
 */
@TeleOp(name = "Fairbot 2", group = "OpMode")
@Disabled
public class Fairbot2 extends OpMode {
    DcMotor Left;
    DcMotor Right;
    DcMotor Lift;
    DcMotor Clamp;
    Servo LeftServo;

    final static double SERVO_MIN_RANGE  = 0.00;
    final static double SERVO_MAX_RANGE  = 1.00;

    double servoDelta=0.005;
    double LeftServoPosition;

    public Fairbot2() {}

    @Override
    public void init() {
        Left = hardwareMap.dcMotor.get("Left");
        Right = hardwareMap.dcMotor.get("Right");
        Lift = hardwareMap.dcMotor.get("Lift");
        Clamp = hardwareMap.dcMotor.get("Clamp");
        Left.setDirection(DcMotor.Direction.REVERSE);
        LeftServo = hardwareMap.servo.get("Arm Servo");

        LeftServoPosition = 0;
    }

    @Override
    public void loop() {

        float left1=gamepad1.right_stick_y;
        float right1=gamepad1.left_stick_y;
        float lift1=gamepad2.right_stick_y;
        float clamp1=gamepad2.left_stick_x;
        float clamp2=gamepad2.left_stick_x;
        float lift2=gamepad2.right_stick_y;
        float left2=gamepad1.right_stick_y;
        float right2=gamepad1.left_stick_y;

        right1= Range.clip(right1, -1, 1);
        left1 = Range.clip(left1, -1, 1);
        lift1 = Range.clip(lift1, -1, 1);
        clamp1 = Range.clip(clamp1, -1, 1);
        clamp2= Range.clip(clamp2, -1, 1);
        lift2= Range.clip(lift2, -1, 1);
        right2= Range.clip(right2, -1, 1);
        left2 = Range.clip(left2, -1, 1);

        right1 = (float)scaleInput(right1);
        left1 =  (float)scaleInput(left1);
        lift1 =  (float)scaleInput(lift1);
        clamp1 =  (float)scaleInput(clamp1);
        clamp2 = (float)scaleInput(clamp2);
        lift2 = (float)scaleInput(lift2);
        right2 = (float)scaleInput(right2);
        left2 =  (float)scaleInput(left2);

        Left.setPower(Range.clip(left1+left2, -1, 1));
        Right.setPower(Range.clip(right1+right2, -1, 1));
        Lift.setPower(Range.clip(lift1+lift2, -1, 1));
        Clamp.setPower(Range.clip(clamp1+clamp2, -1, 1));

        if(gamepad1.left_bumper){
            if(LeftServoPosition != 0){
                LeftServoPosition -= servoDelta;
            }

        }
        if(gamepad1.left_trigger>0){
            if(LeftServoPosition != SERVO_MAX_RANGE){
                LeftServoPosition += servoDelta;
            }
        }

        LeftServoPosition = Range.clip(LeftServoPosition, SERVO_MIN_RANGE, SERVO_MAX_RANGE);

        // write position values to the wrist and claw servo
        LeftServo.setPosition(LeftServoPosition);
        //set all the driver and gamepad options. this is where the program goes.
    }
    @Override
    public void stop(){
        Left.setPower(0.0);
        Right.setPower(0.0);
        Lift.setPower(0.0);
        Clamp.setPower(0.0);
    }
    //This is for the driving scale as far as this point it is ok without modification
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.00, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

}
