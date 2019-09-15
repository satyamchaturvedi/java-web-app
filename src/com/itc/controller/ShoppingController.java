package com.itc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itc.bean.Card;
import com.itc.bean.Item;
import com.itc.bean.Order;
import com.itc.bean.User;
import com.itc.service.ShoppingService;

@Controller
public class ShoppingController {
	@Autowired
	private ShoppingService shopService;
	private List<Item> sList;

	@RequestMapping(value = "/items")
	public String getItems(Model model) {
		sList = shopService.getItems();
		model.addAttribute("slist", sList);
		return "buyItems";

	}

	@RequestMapping(value = "/buyItem")
	public String buyItem(Model model, @RequestParam("itemid") int gid) {
		Item item = shopService.getItem(gid);
		model.addAttribute("item", item);
		model.addAttribute("itemid", gid);
		model.addAttribute("quantity", item.getQuantity());
		return "buyForm";

	}

	@RequestMapping(value = "/confirmBuyItem")
	public String placeOrder(HttpSession session, Model model, @ModelAttribute(value = "item") @Valid Item item,
			@RequestParam("req") int req) {
		int count = 0;
		Order order = new Order();
		order.setItemId(item.getId());
		order.setQuantity(req);
		model.addAttribute("order", order);
		session.setAttribute("Uid", count);
		return "orderForm";

	}

	@RequestMapping(value = "/confirmorder", method = RequestMethod.POST)
	public String confirmOrder(Model model, @ModelAttribute(value = "order") @Valid Order order, BindingResult bResult,
			@RequestParam("card") String cardNo, HttpSession session) {
		if (bResult.hasErrors()) {
			return "orderForm";
		}

		else {

			Card card = shopService.validateCard(cardNo);
			if (card == null) {
				return "orderFail";
			} else {
				Order newOrder = shopService.placeOrder(order);
				if (newOrder == null) {
					return "orderFail";
				}
				session.setAttribute("UId", newOrder.getId());
				model.addAttribute("orderid", newOrder.getId());
				return "redirect:/order.obj";
			}

		}
	}

	@RequestMapping(value = "/order", params = { "orderid" })
	public String orderSuccess(Model model, @RequestParam("orderid") int id) {
		model.addAttribute("orderid", id);
		return "orderSuccess";
	}

	@RequestMapping(value = "/home")
	public String goHome() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "loginForm";

	}

	@RequestMapping(value = "/signUp")
	public String signUp(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signUpForm";
	}

	@RequestMapping(value = "/confirmsignup")
	public String confirmSignUp(HttpSession session, Model model, @ModelAttribute("user") User user) {
		try {
			User newUser = shopService.addUser(user);
			if (newUser != null) {
				session.setAttribute("user", user);
				return getItems(model);
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/confirmlogin")
	public String confirmLogIn(HttpSession session, Model model, @ModelAttribute("user") User user) {

		try {
			User newUser = shopService.findUser(user);
			if (newUser != null) {

				if (newUser.getUserType().equals("admin")) {
					session.setAttribute("user", user);
					User admin = new User();
					model.addAttribute("admin", admin);
					sList = shopService.getItems();
					System.out.println(sList);
					model.addAttribute("slist", sList);
					Item item = new Item();
					model.addAttribute("item", item);
					return "admin";
				} else {

					System.out.println("user not null");
					session.setAttribute("user", user);
					return getItems(model);

				}
			} else {
				session.invalidate();
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "index";
	}

	@ExceptionHandler(Exception.class)
	public String showError(Exception e) {
		System.out.println(e);
		return "error";
	}

	@RequestMapping(value = "/createadmin")
	public String addAdmin(Model model, @ModelAttribute(value = "admin") User admin, BindingResult bResult) {
		User newAdmin = shopService.addAdmin(admin);
		if (bResult.hasErrors()) {
			return "loginForm";
		}
		System.out.println(newAdmin.getName());
		if (newAdmin != null) {
			System.out.println("Admin created");
			String msg ="Admin created";
			model.addAttribute("msg", msg);
			return "redirect:/added.obj";
		} else {
			System.out.println("admin not created");
			return "error";
		}
	}

	@RequestMapping(value = "/addItem")
	public String addItem(Model model, @ModelAttribute(value = "item") Item item) {
		Item newItem = shopService.addItem(item);
		if (newItem != null) {
			String msg ="Added Item";
			model.addAttribute("msg", msg);
			return "redirect:/added.obj";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/updateItem")
	public String updateItem(Model model, @ModelAttribute(value = "item") Item item) {
		Item newItem = shopService.updateItem(item);
		if (newItem != null) {
			String msg ="Updated Item";
			model.addAttribute("msg", msg);
			return "redirect:/added.obj";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/added", params = { "msg" })
	public String success(Model model,@RequestParam("msg") String msg) {
		sList = shopService.getItems();
		System.out.println(sList);
		model.addAttribute("slist", sList);
		model.addAttribute("msg", msg);
		return "adminSuccess";
	}
	@RequestMapping(value = "/adminHome")
	public String adminHome(Model model){
		User admin = new User();
		model.addAttribute("admin", admin);
		sList = shopService.getItems();
		System.out.println(sList);
		model.addAttribute("slist", sList);
		Item item = new Item();
		model.addAttribute("item", item);
		return "admin";
	
	}

}
