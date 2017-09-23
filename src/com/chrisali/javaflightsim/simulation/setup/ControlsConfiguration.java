/*******************************************************************************
 * Copyright (C) 2016-2017 Christopher Ali
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  If you have any questions about this project, you can visit
 *  the project's GitHub repository at: http://github.com/chris-ali/j6dof-flight-sim/
 ******************************************************************************/
package com.chrisali.javaflightsim.simulation.setup;

import java.util.EnumMap;
import java.util.Map;

import net.java.games.input.Component.Identifier.Key;
import net.java.games.input.Component.POV;

/**
 * Contains user-defined key, button (and eventually axis) bindings for each controller for jinput 
 * to control the aircraft in the simulation 
 * 
 * @author Christopher
 *
 */
public class ControlsConfiguration {

	private EnumMap<KeyCommand, Key> keyboardAssignments;
	
	/**
	 * String key is the name of the joystick discovered by jinput
	 */
	private Map<String, JoystickAxis[]> joystickAxisAssignments;
	
	/**
	 * String key is the name of the joystick discovered by jinput
	 */
	private Map<String, EnumMap<KeyCommand, String>> joystickButtonAssignments;
	
	/**
	 * String key is the name of the joystick discovered by jinput
	 */
	private Map<String, EnumMap<KeyCommand, POV>> joystickHatAssignments;

	public ControlsConfiguration() { }

	public EnumMap<KeyCommand, Key> getKeyboardAssignments() { return keyboardAssignments; }

	public void setKeyboardAssignments(EnumMap<KeyCommand, Key> keyboardAssignments) { this.keyboardAssignments = keyboardAssignments; }

	public Map<String, JoystickAxis[]> getJoystickAxisAssignments() { return joystickAxisAssignments; }

	public void setJoystickAxisAssignments(Map<String, JoystickAxis[]> joystickAxisAssignments) { this.joystickAxisAssignments = joystickAxisAssignments; }

	public Map<String, EnumMap<KeyCommand, String>> getJoystickButtonAssignments() { return joystickButtonAssignments;	}

	public void setJoystickButtonAssignments(Map<String, EnumMap<KeyCommand, String>> joystickButtonAssignments) { this.joystickButtonAssignments = joystickButtonAssignments;	}

	public Map<String, EnumMap<KeyCommand, POV>> getJoystickHatAssignments() { return joystickHatAssignments;}

	public void setJoystickHatAssignments(Map<String, EnumMap<KeyCommand, POV>> joystickHatAssignments) { this.joystickHatAssignments = joystickHatAssignments;	}
}