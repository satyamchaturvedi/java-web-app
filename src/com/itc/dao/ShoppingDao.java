package com.itc.dao;

import java.util.List;

import com.itc.bean.Card;
import com.itc.bean.Item;
import com.itc.bean.Order;
import com.itc.bean.User;

public interface ShoppingDao {
	
	   public List<Item> getItems();

	public Item getItem(int gid);

	public Order placeOrder(Order order);

	public User addUser(User user);

	public User findUser(User user);

	public Card validateCard(String cardNo);

	public User addAdmin(User user);

	public Item addItem(Item item);

	public Item updateItem(Item item);

}
