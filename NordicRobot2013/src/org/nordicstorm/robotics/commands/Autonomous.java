package org.nordicstorm.robotics.commands;
 
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.nordicstorm.robotics.Robot;
 
/**
 *
 */
public class Autonomous extends CommandGroup {
    private boolean m_parallel = false;
    
    public Autonomous() {
                String returnString = "";
                String successful = "";
                addParallel(new SetActuationVertical());
                addParallel(new DriveClimberToBottom());
                
                boolean shouldRun = true;
        
		int currentIndex = 0;
		String newCommand = Preferences.getInstance().getString("AutonomousCommand", Robot.autonomousDefault).toUpperCase();
                if(newCommand == null || newCommand.trim().equals("")){
                    shouldRun = false;
                }
		while(currentIndex < newCommand.length() && shouldRun){
			System.out.println("Parsing this command " + newCommand);	
			int nextSemi = newCommand.indexOf(';', currentIndex);
			if (nextSemi < 0) {
                            nextSemi = newCommand.length();
			}
			String currentCommand = newCommand.substring(currentIndex, nextSemi).trim();
                        //System.out.println("CurrentCommand = " + currentCommand);
			String commandStart = currentCommand.substring(0,1);
                        m_parallel = false;
                        if ("P".equals(commandStart)){
                            commandStart = currentCommand.substring(1,2);
                            m_parallel = true;
                        }
                        //System.out.println("CommandStart = " + commandStart);
                        String args = "";
                        if (currentCommand.length() > 1){
                            args = currentCommand.substring(1).trim();
                        }
                        System.out.println(args);
			Command command = null;
			String commandText = "";
			String creationText = "";
			try{
                                successful = "Parse Successful     ";
				if ("D".equals(commandStart)){
					creationText = "Created DriveForTime(" + getDoubleArg(args,0) + "," + getDoubleArg(args,1) + "," + getDoubleArg(args,2) + ")";
					commandText = "Autonomous::DriveForTime";
					command = new DriveForTime(getDoubleArg(args,0),getDoubleArg(args,1),getDoubleArg(args,2));
                                }else if ("X".equals(commandStart)){
					creationText = "Created Brake(" + getDoubleArg(args,0) + ")";
					commandText = "Autonomous::Brake";
					command = new DriveForTime(getDoubleArg(args,0),0,0);
                                }else if ("R".equals(commandStart)){
					creationText = "Created RotateToAngle(" + getDoubleArg(args,0) + "," + getDoubleArg(args,1) + "," +getDoubleArg(args,2) + ")";						
					commandText = "Autonomous::RotateToAngle";
					command = new AngleRotate(getDoubleArg(args,0),getDoubleArg(args,1),getDoubleArg(args,2));
				}else if ("G".equals(commandStart)){
					creationText = "Created DriveToGoal(" + getDoubleArg(args,0) + "," + getDoubleArg(args,1) + "," + getDoubleArg(args, 2) + "," + getDoubleArg(args,3) + ")";
					commandText = "Autonomous::DriveToGoal";
					command = new DriveToGoal(getDoubleArg(args,0),getDoubleArg(args,1), getDoubleArg(args,2), getDoubleArg(args,3));
				}else if ("W".equals(commandStart)){
					creationText = "Created WaitCommand(" + getDoubleArg(args,0) + ")";						
					commandText = "Autonomous::WaitCommand";						
					command = new WaitCommand(getDoubleArg(args,0));
				}else if ("S".equals(commandStart)){
					creationText = "Created SquareUpToWall()";						
					commandText = "Autonomous::SquareUpToWall";						
					command = new SquareUpToWall();
				}else if ("A".equals(commandStart)){
					creationText = "Created ApproachGoal()";						
					commandText = "Autonomous::ApproachGoal";						
					command = new ApproachGoal();
				}else if ("Y".equals(commandStart)){
					creationText = "Created DriveToAngle(" + getDoubleArg(args,0) + "," + getDoubleArg(args,1) + "," + getDoubleArg(args,2) + ")";						
					commandText = "Autonomous::DriveToAngle";						
					command = new DriveToAngle(getDoubleArg(args,0),getDoubleArg(args,1),getDoubleArg(args,2));
				}else if ("F".equals(commandStart)){
					creationText = "Created DumpFrisbees()";						
					commandText = "Autonomous::DumpFrisbees";						
					command = new DumpFrisbeesInWall();
				}
				
				if (null != command){
                                    if (m_parallel){
                                        addParallel(command);
                                        commandText = commandText + "--Parallel";
                                        creationText = creationText + "--Parallel";
                                    }else{
                                        addSequential(command);
                                    }
				    addSequential(new PrintCommand(commandText));	
                                    System.out.println(creationText);
                                    returnString = returnString + " " + commandStart;

				}
			}catch (Exception ex){
				System.out.println(ex.getMessage());
                                successful = "Parse not successful     ";
			}

			currentIndex = nextSemi + 1;
		}
		DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, (returnString + "                             "));
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, (successful + "                             "));
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, (Timer.getFPGATimestamp() + "                          "));
                DriverStationLCD.getInstance().updateLCD();
			
			
			
			
			
			
			/*
        addSequential(new PrintCommand("Autonomous::Driving Forward"));
        addSequential(new DriveForTime(2.5, 8.0, 8.0));
        
        //addSequential(new PrintCommand("Autonomous::Rotating"));
        //addSequential(new RotateToAngle(5));
        
        addSequential(new PrintCommand("Autonomous::Driving to Goal"));
        addSequential(new DriveToGoal(1.25));
        
        addSequential(new PrintCommand("Autonomous::Dumping"));
        //addSequential(new AutonomousDump());
        addSequential(new WaitCommand(2)); // Simulate Dump
        
        addSequential(new PrintCommand("Autonomous::Backing Up"));
        addSequential(new DriveForTime(0.5, -8.0, -8.0));
        
        addSequential(new PrintCommand("Autonomous::Rotating"));
        addSequential(new RotateToAngle(-120));
        
        addSequential(new PrintCommand("Autonomous::Driving Away"));
        addSequential(new DriveForTime(2.0, 8.0, 8.0));
		*/
    }
	private double getDoubleArg(String base, int argNumber) throws Exception{
		int currentIndex = 0;
		int argCount = 0;
		while(currentIndex < base.length()){
			int nextComma = base.indexOf(',', currentIndex);
			if (nextComma < 0) {
					nextComma = base.length();
			}
			if (argCount == argNumber){
					return Double.parseDouble(base.substring(currentIndex, nextComma));
			}
			argCount ++;
			currentIndex = nextComma + 1;
		}	
		throw new Exception("Couldn't get arg " + argNumber);
	}

	
}
