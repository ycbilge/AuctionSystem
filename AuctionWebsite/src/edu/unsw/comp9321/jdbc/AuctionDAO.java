package edu.unsw.comp9321.jdbc;

import java.util.List;

//ekle buraya yeni gelen functionlari
public interface AuctionDAO {
	public void storeUser(UserDTO user);
	public void storeUserTemp(UserDTO user, int val);
	public UserDTO getUserDetailsTemp(int val);
	public List<UserDTO> getAllUsers();
	public UserDTO getUserDetails(String userName);
	public void storeItem(ItemDTO item);
	public List<ItemDTO> getAllItems();
	public void updateUserDetails(UserDTO user);
	public ItemDTO getItemDetails(String itemTitle);
	public void storeBid(BidDTO bid);
	public List<BidDTO> getAllBidsForItem(String itemTitle);
	public void deleteUserFromUsersTable(String userName);
	public void deleteUserFromUsersTempTable(String userName);
	public void deleteItemAccordingToUserName(String userName);
	public void deleteBidsAccordingToUserName(String userName);
	public void deleteItemsAccordingToItemName(String itemName);
	public void deleteBidsAccordingToItemName(String itemName);
}
