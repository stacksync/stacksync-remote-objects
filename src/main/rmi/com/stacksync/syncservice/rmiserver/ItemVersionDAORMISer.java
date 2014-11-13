package com.stacksync.syncservice.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.stacksync.syncservice.rmiclient.*;
import com.stacksync.syncservice.rmiserveri.*;

public class ItemVersionDAORMISer extends UnicastRemoteObject implements
		ItemVersionDAORMIIfc {

	private static final long serialVersionUID = 8697773102959828981L;
	
	List<ItemVersionRMI> list;

	public ItemVersionDAORMISer() throws RemoteException {
		list = new ArrayList<ItemVersionRMI>();
	}

	public ItemMetadataRMI findByItemIdAndVersion(Long id, Long version)
			throws RemoteException {

		ItemMetadataRMI metadata = null;

		for (ItemVersionRMI iv : list) {
			if (iv.getVersion().equals(version)
					&& iv.getItem().getId().equals(id)) {
				metadata = new ItemMetadataRMI();
			}
		}

		return metadata;
	}

	public void add(ItemVersionRMI itemVersion) throws RemoteException {
		if (!itemVersion.isValid()) {
			throw new IllegalArgumentException("Item version attributes not set");
		}
		
		boolean exist = false;
		
		for (ItemVersionRMI iv: list){
			if (iv.getId().equals(itemVersion.getId())){
				exist = true;
			}
		}
		
		if (!exist) {
			list.add(itemVersion);
			System.out.println("ADDED");
		} else
			System.out.println("EXISTING ITEM VERSION ID");
	}

	public void update(ItemVersionRMI itemVersion) throws RemoteException {
		if (itemVersion.getId() == null || !itemVersion.isValid()) {
			throw new IllegalArgumentException("Device attributes not set");
		}
		
		boolean exist = false;
		ItemVersionRMI iv1 = null;
		
		for (ItemVersionRMI iv: list){
			if (iv.getId().equals(itemVersion.getId())){
				exist = true;
				iv1 = iv;
			}
		}
		
		if (exist) {
			list.remove(iv1);
			list.add(itemVersion);
			System.out.println("UPDATED");
		} else
			System.out.println("ITEM VERSION ID DOESN'T EXIST");
	}

	public void delete(ItemVersionRMI itemVersion) throws RemoteException {
		boolean exist = false;
		ItemVersionRMI iv1 = null;
		
		for (ItemVersionRMI iv: list){
			if (iv.getId().equals(itemVersion.getId())){
				exist = true;
				iv1 = iv;
			}
		}
		
		if (exist) {
			list.remove(iv1);
			System.out.println("DELETED");
		} else
			System.out.println("ITEM VERSION ID DOESN'T EXIST");
	}

	public void insertChunk(Long itemVersionId, Long chunkId, Integer order)
			throws RemoteException {

	}

	public void insertChunks(List<ChunkRMI> chunks, long itemVersionId)
			throws RemoteException {

	}

	public List<ChunkRMI> findChunks(Long itemVersionId) throws RemoteException {
		List<ChunkRMI> chunks = new ArrayList<ChunkRMI>();

		return chunks;
	}

}
