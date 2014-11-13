package com.stacksync.syncservice.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stacksync.syncservice.rmiclient.DeviceRMI;
import com.stacksync.syncservice.rmiserveri.DeviceDAORMIIfc;

public class DeviceDAORMISer extends UnicastRemoteObject implements DeviceDAORMIIfc {
	
	private static final long serialVersionUID = -3705523490008780379L;
	
	List<DeviceRMI> list;

	protected DeviceDAORMISer() throws RemoteException {
		list = new ArrayList<DeviceRMI>();
	}

	public DeviceRMI get(UUID deviceID) throws RemoteException {

		DeviceRMI device = null;

		for (DeviceRMI d : list) {
			if (d.getId().equals(deviceID)) {
				device = d;
			}
		}

		return device;
	}

	public void add(DeviceRMI device) throws RemoteException {
		if (!device.isValid()) {
			throw new IllegalArgumentException("Device attributes not set");
		}

		boolean exist = false;

		for (DeviceRMI d : list) {
			if (d.getId().equals(device.getId())) {
				exist = true;
			}
		}

		if (!exist) {
			list.add(device);
			System.out.println("ADDED");
		} else
			System.out.println("EXISTING DEVICE ID");
	}

	public void update(DeviceRMI device) throws RemoteException {
		if (device.getId() == null || !device.isValid()) {
			throw new IllegalArgumentException("Device attributes not set");
		}

		boolean exist = false;
		DeviceRMI d1 = null;

		for (DeviceRMI d : list) {
			if (d.getId().equals(device.getId())) {
				exist = true;
				d1 = d;
			}
		}

		if (exist) {
			list.remove(d1);
			list.add(device);
			System.out.println("UPDATED");
		} else
			System.out.println("DEVICE ID DOESN'T EXIST");
	}

	public void delete(UUID deviceID) throws RemoteException {
		boolean exist = false;
		DeviceRMI d1 = null;

		for (DeviceRMI d : list) {
			if (d.getId().equals(deviceID)) {
				exist = true;
				d1 = d;
			}
		}

		if (exist) {
			list.remove(d1);
			System.out.println("DELETED");
		} else
			System.out.println("DEVICE ID DOESN'T EXIST");
	}

}
