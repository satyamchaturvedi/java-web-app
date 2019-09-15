package com.itc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itc.bean.Card;
import com.itc.bean.Item;
import com.itc.bean.Order;
import com.itc.bean.User;
import com.itc.dao.ShoppingDao;


@Service
public class ShoppingServiceImpl implements ShoppingService{
	@Autowired
	private ShoppingDao sDao;

	@Override
	public List<Item> getItems() {
		return sDao.getItems();
	}

	@Override
	public Item getItem(int gid) {
		// TODO Auto-generated method stub
		return sDao.getItem(gid);
	}

	@Override
	public Order placeOrder(Order order) {
		// TODO Auto-generated method stub
		return sDao.placeOrder(order);
	}

	@Override
	public User addUser(User user) {
	return sDao.addUser(user);
		
	}

	@Override
	public User findUser(User user) {
		
		return sDao.findUser(user);
	}

	@Override
	public Card validateCard(String cardNo) {
		
		return sDao.validateCard(cardNo);
	}

	@Override
	public User addAdmin(User user) {
		// TODO Auto-generated method stub
		return sDao.addAdmin(user);
	}

	@Override
	public Item addItem(Item item) {
		return sDao.addItem(item);
		
	}

	@Override
	public Item updateItem(Item item) {
		
		return sDao.updateItem(item);
	}

}
