package com.stacksync.syncservice.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stacksync.syncservice.rmiclient.ItemMetadataRMI;
import com.stacksync.syncservice.rmiclient.ItemRMI;
import com.stacksync.syncservice.rmiserveri.ItemDAORMIIfc;

public class ItemDAORMISer extends UnicastRemoteObject implements ItemDAORMIIfc {

	private static final long serialVersionUID = 6060476311240054829L;

	List<ItemRMI> list;

	public ItemDAORMISer() throws RemoteException {
		list = new ArrayList<ItemRMI>();
	}

	public ItemRMI findById(Long item1ID) throws RemoteException {
		ItemRMI item = null;

		for (ItemRMI i : list) {
			if (i.getId().equals(item1ID)) {
				item = i;
			}
		}

		return item;
	}

	public void add(ItemRMI item) throws RemoteException {
		if (!item.isValid()) {
			throw new IllegalArgumentException("Item attributes not set");
		}
		if (findById(item.getId()) == null) {
			list.add(item);
			System.out.println("ADDED");
		} else
			System.out.println("EXISTING ITEM ID");
	}

	public void update(ItemRMI item) throws RemoteException {
		if (item.getId() == null || !item.isValid()) {
			throw new IllegalArgumentException("Item attributes not set");
		}

		Long parentId = item.getParentId();
		// If id == 0 means parent is null!
		if (parentId != null && parentId == 0) {
			parentId = null;
		}

		if (findById(item.getId()) != null) {
			list.remove(findById(item.getId()));
			list.add(item);
			System.out.println("UPDATED");
		} else
			System.out.println("ITEM ID DOESN'T EXIST");
	}

	public void put(ItemRMI item) throws RemoteException {
		if (findById(item.getId()) == null) {
			add(item);
		} else
			update(item);
	}

	public void delete(Long id) throws RemoteException {
		if (findById(id) == null) {
			list.remove(findById(id));
			System.out.println("REMOVED");
		} else
			System.out.println("ITEM ID DOESN'T EXIST");
	}

	public List<ItemMetadataRMI> getItemsByWorkspaceId(UUID workspaceId) throws RemoteException {
		List<ItemMetadataRMI> items = null;

		return items;
	}

	public List<ItemMetadataRMI> getItemsById(Long id) throws RemoteException {
		List<ItemMetadataRMI> list = new ArrayList<ItemMetadataRMI>();

		return list;
	}

	public ItemMetadataRMI findById(Long id, Boolean includeList, Long version, Boolean includeDeleted,
			Boolean includeChunks) throws RemoteException {
		ItemMetadataRMI item = null;

		return item;
	}

	public ItemMetadataRMI findByUserId(UUID serverUserId, Boolean includeDeleted) throws RemoteException {
		ItemMetadataRMI rootMetadata = new ItemMetadataRMI();

		return rootMetadata;
	}

	public ItemMetadataRMI findItemVersionsById(Long id) throws RemoteException {
		ItemMetadataRMI rootMetadata = new ItemMetadataRMI();

		return rootMetadata;
	}

	public List<String> migrateItem(Long itemId, UUID workspaceId) throws RemoteException {
		List<String> chunksToMigrate = null;

		return chunksToMigrate;
	}

}
