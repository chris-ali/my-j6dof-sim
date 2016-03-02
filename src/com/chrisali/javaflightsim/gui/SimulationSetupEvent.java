package com.chrisali.javaflightsim.gui;

import java.util.EnumSet;
import java.util.EventObject;

import com.chrisali.javaflightsim.aircraft.AircraftBuilder;
import com.chrisali.javaflightsim.setup.Options;

public class SimulationSetupEvent extends EventObject {

	private static final long serialVersionUID = -2043346783301969287L;

	private EnumSet<Options> options;
	private AircraftBuilder ab;
	
	public SimulationSetupEvent(Object source, AircraftBuilder ab, EnumSet<Options> options) {
		super(source);
		this.ab = ab;
		this.options = options;
	}

	public EnumSet<Options> getOptions() {
		return options;
	}

	public AircraftBuilder getAb() {
		return ab;
	}
}
