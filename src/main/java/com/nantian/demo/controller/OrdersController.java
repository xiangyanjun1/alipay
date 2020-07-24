package com.nantian.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.nantian.demo.domain.Orders;
import com.nantian.demo.service.OrdersService;
import com.nantian.demo.utils.AlipayConfig;
import com.nantian.demo.utils.UUIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@RequestMapping("/add")
	public String add(Orders orders, Model model) {
		orders.setId(UUIdUtils.next());
		orders.setOrderNum(UUIdUtils.next());
		orders.setOrderStatus("10");
		Integer singlePrice = Integer.parseInt(orders.getProduct().getPrice());
		orders.setOrderAmount((singlePrice * orders.getBuyCounts()) + "");
		orders.setCreateTime(new Date());
		ordersService.add(orders);
		model.addAttribute("orders", orders);
		return "ordersInfo";
	}

	@ResponseBody
	@RequestMapping("/goAlipay")
	public String goAlipay(Orders orders, String ordersDesc) throws Exception {
		orders = ordersService.findById(orders.getId());
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = orders.getOrderNum();
		//付款金额，必填
		String total_amount = orders.getOrderAmount();
		//订单名称，必填
		String subject = orders.getProduct().getName();
		//商品描述，可空
		//String body = ordersDesc == null ? "" : ordersDesc;

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		String timeout_express = "1c";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				//+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		return result;
	}



	@RequestMapping("/getPayPage")
	public void getPayPage(String WIDout_trade_no,String WIDtotal_amount,String WIDsubject,String WIDbody,HttpServletResponse response) throws AlipayApiException, IOException {
		response.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView("alipay.trade.page.pay");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDout_trade_no +"\","
				+ "\"total_amount\":\""+ WIDtotal_amount +"\","
				+ "\"subject\":\""+ WIDsubject +"\","
				+ "\"body\":\""+ WIDbody +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(result);
		modelAndView.addObject("result",result);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
	}


	@RequestMapping("/query")
	public void query(String WIDTQout_trade_no,String WIDTQtrade_no,HttpServletResponse response) throws AlipayApiException, IOException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTQout_trade_no +"\","+"\"trade_no\":\""+ WIDTQtrade_no +"\"}");
		//请求
		String result = alipayClient.execute(alipayRequest).getBody();
		//TODO
		/*
		{
			"alipay_trade_query_response": {
			"code": "10000",
					"msg": "Success",
					"buyer_logon_id": "ijd***@sandbox.com",
					"buyer_pay_amount": "0.00",
					"buyer_user_id": "2088102176977130",
					"buyer_user_type": "PRIVATE",
					"invoice_amount": "0.00",
					"out_trade_no": "202072314449921",
					"point_amount": "0.00",
					"receipt_amount": "0.00",
					"total_amount": "0.01",
					"trade_no": "2020072322001477130501024273",
					"trade_status": "WAIT_BUYER_PAY"
		},
			"sign": "GFfHPAfskHE5yHv948K2C605E/W0ZYQUCECAVm+uCdosM+9zofNWRA9wqXEE6oKw6pQCXm20WHI2n6k1RYKCY9imqqAV9EcnP017oBcvU/BeDZ7Y7Vnql+/xcjrk+a0xS6LuUmMeheCpjpA8NTgj6iLZWRiy6PLu8mI/o3Eu33ca8C8t2HvXifbCTrHRmjReZ8LrlMfeQnna5FSdBrAFT35QzIfrHl02YIr0gnmpLWrf9l3jyKfmmpDHRX0gXEMQV+1zwnFWQPvD1kkeyDwE3Om+pfxg8i+MzJYeja4ri1RQUnlEcey+hKdsGCq1TPNMuR5oYlUWN8lWdZfOoG24Vw=="
		}
		*/
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
	}

	@RequestMapping("/refund")
	public void refund(String WIDTRout_trade_no,String WIDTRtrade_no,String WIDTRrefund_amount,String WIDTRrefund_reason,String WIDTRout_request_no,HttpServletResponse response) throws AlipayApiException, IOException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTRout_trade_no +"\","
				+ "\"trade_no\":\""+ WIDTRtrade_no +"\","
				+ "\"refund_amount\":\""+ WIDTRrefund_amount +"\","
				+ "\"refund_reason\":\""+ WIDTRrefund_reason +"\","
				+ "\"out_request_no\":\""+ WIDTRout_request_no +"\"}");

		//请求
		String result = alipayClient.execute(alipayRequest).getBody();

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
	}

	@RequestMapping("/refundQuery")
	public void refundQuery(String WIDRQout_trade_no,String WIDRQtrade_no,String WIDRQout_request_no,HttpServletResponse response) throws AlipayApiException, IOException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDRQout_trade_no +"\","
				+"\"trade_no\":\""+ WIDRQtrade_no +"\","
				+"\"out_request_no\":\""+ WIDRQout_request_no +"\"}");

		//请求
		String result = alipayClient.execute(alipayRequest).getBody();

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
	}

}
