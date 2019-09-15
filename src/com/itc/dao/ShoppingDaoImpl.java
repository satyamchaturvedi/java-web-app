package com.itc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.itc.bean.Card;
import com.itc.bean.Item;
import com.itc.bean.Order;
import com.itc.bean.User;
import com.itc.credit.CardService;
import com.itc.crypto.PasswordHandler;

@Service
@Transactional
@Repository
public class ShoppingDaoImpl implements ShoppingDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Item> getItems() {
		try {
			String sql = "select item from Item item";
			TypedQuery<Item> query = entityManager.createQuery(sql, Item.class);
			return query.getResultList();
		} catch (Exception e) {
		}
		return null;

	}

	@Override
	public Item getItem(int gid) {
		try {
			Item item = null;
			item = entityManager.find(Item.class, gid);
			return item;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Order placeOrder(Order order) {
		try {
			Item item = entityManager.find(Item.class, order.getItemId());
			int quantity = order.getQuantity();
			int availableQuant = item.getQuantity();
			item.setQuantity(availableQuant - quantity);
			entityManager.merge(item);
			entityManager.persist(order);
			return order;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public User addUser(User user) {
		try {
			if (user != null) {
				String pwd = user.getPassword();
				PasswordHandler pwh = new PasswordHandler();
				pwd = pwh.encrypt(pwd);
				user.setPassword(pwd);
				user.setUserType("customer");
				entityManager.persist(user);
				return user;
			}

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public User findUser(User user) {
		try {
			if (user != null) {
				System.out.println("findUser");
				String pwd = user.getPassword();
				String uName = user.getUserName();
				PasswordHandler pwh = new PasswordHandler();
				user = entityManager.find(User.class, uName);
				if (user != null) {
					System.out.println("findUser Not Null");
					if (pwh.decrypt(user.getPassword()).equals(pwd)) {
						System.out.println("Pwd match");
						return user;
					}
				}
			}

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Card validateCard(String cardNo) {
		try {
			CardService cs = new CardService();
			Card c = new Card(cardNo, "", "", 0);
			if (cs.validateCard(c)) {
				return c;
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public User addAdmin(User user) {
		try {
			user.setUserType("admin");
			String pwd = user.getPassword();
			PasswordHandler pwh = new PasswordHandler();
			user.setPassword(pwh.encrypt(pwd));
			entityManager.persist(user);
			return user;
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public Item addItem(Item item) {
		try {
			if (item != null) {
				entityManager.persist(item);
				return item;
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public Item updateItem(Item item) {
		try {
			Item updateItem = entityManager.find(Item.class, item.getId());
			if (updateItem != null) {
				updateItem.setQuantity(updateItem.getQuantity() + item.getQuantity());
				entityManager.merge(updateItem);
				return updateItem;
			} else {
				return null;
			}
		} catch (Exception e) {

		}
		return null;
	}
}
