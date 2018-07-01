package qianye.pda.presentation.controller.model;

import java.util.Timer;

public class PushTimer extends Timer {
	private static PushTimer instance;

	private PushTimer() {
	}

	public static synchronized PushTimer getInstance() {
		if (instance == null) {
			instance = new PushTimer();
		}
		return instance;
	}
}
