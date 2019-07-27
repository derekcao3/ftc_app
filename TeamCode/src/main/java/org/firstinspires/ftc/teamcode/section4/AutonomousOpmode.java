package org.firstinspires.ftc.teamcode.section4;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Basic: Maze Mode", group = "Iterative Opmode")
public class AutonomousOpmode extends LinearOpMode {

    private DcMotor leftFront = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    static double ticksPerRotation = 537.6;
    static double diameter = 4;

    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initializing");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        //^Reversed normally
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        //^Reversed normally
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Initialized");


        waitForStart();


    }

    static public int getTicks(double distance){
        return (int) (distance/(diameter*Math.PI)*ticksPerRotation);
    }

    public void DriveForDistance(double inches){
        int ticks = getTicks(inches);
        double Y = 0.4;
        leftFront.setPower(-Y);
        leftRear.setPower(-Y);
        rightFront.setPower(Y);
        rightRear.setPower(Y);
    }
}
