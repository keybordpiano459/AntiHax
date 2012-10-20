package me.KeybordPiano459.AntiHax.util;

import java.io.IOException;
import java.util.logging.Logger;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.util.Metrics.Graph;

public class HacksBlocked extends AntiHax {{
	try {
		Metrics metrics = new Metrics(this);
		Graph graph = metrics.createGraph("Hacks Blocked");
		
		graph.addPlotter(new Metrics.Plotter("Cursing") {
			public int getValue() {
				return AntiHax.cursing;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Flying") {
			public int getValue() {
				return AntiHax.flight;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Hit Self") {
			public int getValue() {
				return AntiHax.hitself;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Reach") {
			public int getValue() {
				return AntiHax.reach;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Sprint No Food") {
			public int getValue() {
				return AntiHax.sprintnofood;
			}
		});
		
		graph.addPlotter(new Metrics.Plotter("Walk on Water") {
			public int getValue() {
				return AntiHax.walkonwater;
			}
		});
		
		metrics.start();
	} catch (IOException e) {
		Logger log = Logger.getLogger("Minecraft");
		log.warning(e.getMessage());
	}
}}