package com.chrisali.javaflightsim.controls.hidcontrollers;

import java.util.ArrayList;
import java.util.EnumMap;

import com.chrisali.javaflightsim.controls.FlightControls;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Mouse extends SimulationController {
	
	// Since mouse axes are measured relative to the stopped position, these fields store the control deflection, 
	// and the mouse axis value is added to these
	private double tempElev  = 0.0;
	private double tempAil   = 0.0;
	private double tempThrot = 0.0;

	// Constructor for Joystick class creates list of controllers using
	// searchForControllers()
	public Mouse(EnumMap<FlightControls, Double> controls) {
		this.controllerList = new ArrayList<>();

		// Get initial trim values from initial values in controls EnumMap (rad)
		trimElevator = controls.get(FlightControls.ELEVATOR);
		trimAileron = controls.get(FlightControls.AILERON);
		trimRudder = controls.get(FlightControls.RUDDER);

		searchForControllers();
	}

	@Override
	protected void searchForControllers() {
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		for (Controller controller : controllers) {
			if (controller.getType() == Controller.Type.MOUSE)
				controllerList.add(controller);
		}

		// If no mice available, exit function
		if (controllerList.isEmpty()) {
			System.err.println("No mice found!");
			return;
		}

	}
	
	// Get button, mouse wheel and axis values from mouse, and return a Double array for updateFlightControls
	// in SimulationController class
	@Override
	protected EnumMap<FlightControls, Double> calculateControllerValues(EnumMap<FlightControls, Double> controls) {
		// Iterate through all controllers connected
		for (Controller controller : controllerList) {
			
			// Poll controller for data; if disconnected, break out of componentIdentification loop
			if(!controller.poll()) 
				break;
			
			// Iterate through all components of the controller.
			for(Component component : controller.getComponents()) {
				Identifier componentIdentifier = component.getIdentifier();

				// Buttons
				if(componentIdentifier.getName().matches("^[0-9]*$")) { // If the component identifier contains only numbers, it is a button
					if(component.getPollData() == 1.0f) {
						// Button index (nothing implemented yet)
					}
					continue; // Go to next component
				}

				// Mouse Axes - Read raw mouse relative value, add relative value to temp* variable, and add trim value
				// to control deflection
				if(component.isRelative()){
					double axisValue = (double)component.getPollData()/10000;
					
					// Y axis (Elevator)
					if(componentIdentifier == Component.Identifier.Axis.Y) {
						if(axisValue != 0) {
							tempElev += axisValue;
							controls.put(FlightControls.ELEVATOR, tempElev+trimElevator);
						}
						continue; // Go to next component
					}
					// X axis (Aileron)
					if(componentIdentifier == Component.Identifier.Axis.X) {
						if(axisValue != 0) {
							tempAil += axisValue;
							controls.put(FlightControls.AILERON, tempAil+trimAileron);
						}
						continue; // Go to next component
					}
					// Z axis (Throttle)
					if(componentIdentifier == Component.Identifier.Axis.Z) {
						if(axisValue != 0) {
							tempThrot += axisValue;
							controls.put(FlightControls.THROTTLE_L, tempThrot*250);
							controls.put(FlightControls.THROTTLE_R, tempThrot*250);
						}
						continue; // Go to next component
					}
				}
			}
		}
		
		return controls;
	}

}
