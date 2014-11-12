package com.stacksync.syncservice.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stacksync.syncservice.rmiclient.DeviceRMI;
import com.stacksync.syncservice.rmiserveri.DeviceDAORMIIfc;

public class Test extends UnicastRemoteObject implements DeviceDAORMIIfc {

	List<DeviceRMI> list;

	protected Test() throws RemoteException {
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
		// TODO Auto-generated method stub
		
	}

	public void update(DeviceRMI device) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void delete(UUID id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
