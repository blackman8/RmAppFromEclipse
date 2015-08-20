package com.bmb;

import com.android.ddmlib.IDevice;

public interface Command {
	public boolean run(IDevice device, String packageName);
}
