package com.project.walkingrobot;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QATestRobot {

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	@BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
	
	
	@Test
	public void testInitializeSystem_Floor() {
		Robot robot = new Robot();
		// test case 1: smaller floor
		Floor floor = new Floor(3);
		int[][] expectedFloor = {{0,0,0},{0,0,0},{0,0,0}};
		Assertions.assertArrayEquals(expectedFloor, floor.getFloorArray());
		
		// test case 2: larger floor
		Floor floor2 = new Floor(10);
		int[][] expectedFloor2 = {{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}};
		Assertions.assertArrayEquals(expectedFloor2, floor2.getFloorArray());
		
	}
	
	
	@Test
	public void testInitializeSystem_Robot() {
		// assert that the robot is in position [0, 0], facing north, and with pen up after system initialization
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		Assertions.assertEquals(0, robot.getX());
		Assertions.assertEquals(0, robot.getY());
		Assertions.assertFalse(robot.isPenDown());
		Assertions.assertEquals(Direction.NORTH, robot.getDirection());
	}
	
	
	@Test
	public void testSetPenUp() {
		// assert that robot has the pen up after initialization + when the setPen(boolean) function is called with "false" as input
		Robot robot = new Robot();
		Assertions.assertFalse(robot.isPenDown());
		robot.setPenDown(false);
		Assertions.assertFalse(robot.isPenDown());
	}
	
	
	@Test
	public void testNoDrawing_PenUP() {
		// test that the robot will not draw on the floor as long as the pen is up
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		
		robot.setPenDown(false);
		robot.move(2, floor);
		robot.turnRight();
		robot.move(1, floor);
		
		int[][] expectedOutput = {{0,0,0},{0,0,0},{0,0,0}};
		
		Assertions.assertArrayEquals(expectedOutput, floor.getFloorArray());
	}
	
	
	@Test
	public void testSetPenDown() {
		Robot robot = new Robot();
		Assertions.assertFalse(robot.isPenDown());
		robot.setPenDown(true);
		Assertions.assertTrue(robot.isPenDown());
	}
	
	
	@Test
	public void testTurnRight() {
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		
		robot.turnRight();
		Assertions.assertEquals(Direction.EAST, robot.getDirection());
		
		robot.turnRight();
		Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
		
		robot.turnRight();
		Assertions.assertEquals(Direction.WEST, robot.getDirection());
		
		robot.turnRight();
		Assertions.assertEquals(Direction.NORTH, robot.getDirection());
	}
	
	
	@Test
	public void testTurnLeft() {
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		
		robot.turnLeft();
		Assertions.assertEquals(Direction.WEST, robot.getDirection());
		
		robot.turnLeft();
		Assertions.assertEquals(Direction.SOUTH, robot.getDirection());
		
		robot.turnLeft();
		Assertions.assertEquals(Direction.EAST, robot.getDirection());
		
		robot.turnLeft();
		Assertions.assertEquals(Direction.NORTH, robot.getDirection());
	}
	
	
	@Test
	public void testMove() {
		// test case 1: smaller system, smaller movements
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		robot.setPenDown(true);
		robot.move(2, floor);
		Assertions.assertEquals(0, robot.getX());
		Assertions.assertEquals(2, robot.getY());
		robot.turnRight();
		robot.move(1, floor);
		Assertions.assertEquals(1, robot.getX());
		Assertions.assertEquals(2, robot.getY());
		
		// test case 2: larger system, larger movements
		Robot robot2 = new Robot();
		Floor floor2 = new Floor(10);
		robot2.setPenDown(false);
		robot2.move(6, floor2);
		Assertions.assertEquals(0, robot2.getX());
		Assertions.assertEquals(6, robot2.getY());
		robot2.turnRight();
		robot2.move(2, floor2);
		Assertions.assertEquals(2, robot2.getX());
		Assertions.assertEquals(6, robot2.getY());
		robot2.turnRight();
		robot2.move(3, floor2);
		robot2.move(1, floor2);
		Assertions.assertEquals(2, robot2.getX());
		Assertions.assertEquals(2, robot2.getY());
		
		// test case 3: untested movements
		Robot robot3 = new Robot();
		Floor floor3 = new Floor(5);
		robot3.setPenDown(true);
		robot3.turnRight();
		robot3.move(3, floor3);
		robot3.turnLeft();
		robot3.move(2, floor3);
		robot3.turnLeft();
		robot3.move(1, floor3);
		Assertions.assertEquals(2, robot3.getX());
		Assertions.assertEquals(2, robot3.getY());
		robot3.turnLeft();
		robot3.move(1, floor3);
		Assertions.assertEquals(2, robot3.getX());
		Assertions.assertEquals(1, robot3.getY());
		
	}
	
	
	@Test
	public void testPrintFloor() {
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		Main main = new Main();
		robot.setPenDown(true);
		robot.move(2, floor);
		robot.turnRight();
		robot.move(1, floor);
		
		int[][] expectedOutput = { {1,1,0},
								   {1,0,0},
								   {1,0,0} };
		
		// NO EXPLICIT PRINT FUNCTION FOR THE FLOOR
		// DOES NOT FOLLOW THE SPECIFIC PRINT MODEL GIVEN IN THE PROJECT DESCRIPTION (with "*" replacing 1 and " " replacing 0)
		// USES A GUI INSTEAD
		
		Assertions.assertEquals(expectedOutput, floor.getFloorArray());
		
		
	}
	
	
	@Test
	public void testPrintCurrentPosition() {
		// test case 1: print current position after initialization
		Robot robot = new Robot();
		Floor floor = new Floor(3);
		robot.printPosition();
		Assertions.assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH", outputStream.toString().trim());
		
		// test case 2: same system, movement is performed, but pen is up
		outputStream.reset();
		robot.setPenDown(false);
		robot.turnRight();
		robot.move(2, floor);
		robot.printPosition();
		Assertions.assertEquals("Position: 2, 0 - Pen: up - Facing: EAST", outputStream.toString().trim());
		
		// test case 3: new system, movement is performed, with pen down
		outputStream.reset();
		Robot robot2 = new Robot();
		Floor floor2 = new Floor(3);
		robot2.setPenDown(true);
		robot2.turnRight();
		robot2.move(1, floor2);
		robot2.printPosition();
		Assertions.assertEquals("Position: 1, 0 - Pen: down - Facing: EAST", outputStream.toString().trim());
		
		// test case 4: new larger system, larger movements performed, with pen down
		outputStream.reset();
		Robot robot3 = new Robot();
		Floor floor3 = new Floor(10);
		robot3.setPenDown(true);
		robot3.move(4, floor3);
		robot3.turnRight();
		robot3.move(7, floor3);
		robot3.turnRight();
		robot3.printPosition();
		Assertions.assertEquals("Position: 7, 4 - Pen: down - Facing: SOUTH", outputStream.toString().trim());
	}
	
	
	@Test
	public void testEndProgramCommand() {
		Main main = new Main();
		boolean endProgram = main.executeCommand("Q", true);
		Assertions.assertEquals("End of program.", outputStream.toString().trim());
		Assertions.assertFalse(endProgram);
	}
	
	
	@Test
	public void testInvalidCommand() {
		Platform.startup(() -> {
		String invalidExample = "invalid";
		Main main = new Main();
		main.executeCommand(invalidExample, true);
		Assertions.assertEquals(outputStream.toString(), "Invalid command. Please try again!");
		});
	}
	
	//	// Data flow testing
//
//	@Test
//	public void DataFlow_MoveTest1() {
//		Robot robot1 = new Robot();
//		Floor floor1 = new Floor(15);
//		robot1.move (10, floor1);
//		Assertions.assertEquals(0, robot1.getX());
//		Assertions.assertEquals(10, robot1.getY());
//	}
//
//	@Test
//	public void DataFlow_MoveTest2() {
//		Platform.startup(() -> {
//			Robot robot1 = new Robot();
//			Floor floor1 = new Floor(5);
//			robot1.move(-2, floor1);
//			Assertions.assertEquals("Invalid number of steps: -2", outputStream.toString().trim());
//		});
//	}
//
//	@Test
//	public void DataFlow_MoveTest3() {
//		Platform.startup(() -> {
//			Robot robot1 = new Robot();
//			Floor floor1 = new Floor(5);
//			robot1.move(7, floor1);
//			Assertions.assertEquals("Invalid number of steps: 7", outputStream.toString().trim());
//		});
//	}
}
