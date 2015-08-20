package com.bmb;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.lang.Thread;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

public class RmHandler extends AbstractHandler implements IHandler {

	Date date = new Date(System.currentTimeMillis());
	String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	
	private static void waitDeviceList(AndroidDebugBridge bridge) {
		int count = 0;
		while (bridge.hasInitialDeviceList() == false) {
			try {
				Thread.sleep(100); // 如果没有获得设备列表，则等待
				count++;
			} catch (InterruptedException e) {
			}
			if (count > 100) { // 设定时间超过100×100 ms的时候为连接超时
				System.err.print("Time out");
				break;
			}
		}
	}

	public Object execute(ExecutionEvent event){
		boolean initStatus = StringConstants.initProperties();
		if(!initStatus || null == StringConstants.adb_path || null == StringConstants.packageName){
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(
			        event).getShell(), "Tips", "Make sure you have a man named rm.ini in eclipes root directory!");
			return null;
		}
		
		AndroidDebugBridge.initIfNeeded(false);
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge(StringConstants.adb_path,
				false);// E:\\android-sdk\\platform-tools\\adb.exe
		waitDeviceList(bridge);
		
		IDevice devices[] = bridge.getDevices();
		if(null == devices || devices.length == 0){
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(
			        event).getShell(), "Tips", "no devices found!");
		}else{
			IDevice device = devices[0];
			UninstallCommand command = new UninstallCommand();
			if(command.run(device, StringConstants.packageName)){
				MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(
				        event).getShell(), "Tips", "device name : " + device.getName());
			}
		}
		return null;
	}

}
