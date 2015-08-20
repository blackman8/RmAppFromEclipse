package com.bmb;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.InstallException;


public class UninstallCommand implements Command{

	@Override
    public boolean run(IDevice device, String packageName) {
        try {
            String errorCode = device.uninstallPackage(packageName);
            if (errorCode == null) {
            	System.out.println(String.format("<b>%s</b> uninstalled on %s", packageName, device.getName()));
                return true;
            } else {
            	System.out.println(String.format("<b>%s</b> is not installed on %s", packageName, device.getName()));
            }
        } catch (InstallException e1) {
            System.out.println("Uninstall fail... " + e1.getMessage());
        }
        return false;
    }

}
