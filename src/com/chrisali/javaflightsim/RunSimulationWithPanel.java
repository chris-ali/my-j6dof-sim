package com.chrisali.javaflightsim;

import java.util.EnumSet;

import javax.swing.SwingUtilities;

import com.chrisali.javaflightsim.instrumentpanel.InstrumentPanel;
import com.chrisali.javaflightsim.instrumentpanel.flightdata.FlightData;
import com.chrisali.javaflightsim.simulation.aircraft.AircraftBuilder;
import com.chrisali.javaflightsim.simulation.integration.Integrate6DOFEquations;
import com.chrisali.javaflightsim.simulation.setup.Options;

public class RunSimulationWithPanel {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {runApp();}
		});
	}

	private static void runApp() {
		Integrate6DOFEquations runSim = new Integrate6DOFEquations(new AircraftBuilder("LookupNavion"),
																   EnumSet.of(Options.UNLIMITED_FLIGHT, Options.USE_JOYSTICK));
		FlightData flightData = new FlightData(runSim);

		new Thread(runSim).start();
		new Thread(flightData).start();
		
		InstrumentPanel panel = new InstrumentPanel();
		flightData.setFlightDataListener(panel);
		panel.setVisible(true);
	}
}