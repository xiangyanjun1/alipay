package com.nantian.demo.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

	@RequestMapping("/notifyUrl")
	public String notifyUrl(@RequestParam Map<String,String> params) {
		for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
			System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
		}
		return "notify_url";
	}
	@RequestMapping("/returnUrl")
	public void returnUrl(@RequestParam Map<String,String> params, HttpServletResponse response) throws AlipayApiException, IOException {
		boolean b = AlipaySignature.rsaCheckV2(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
		System.out.println(b);
		String sWord = AlipaySignature.getSignCheckContentV1(params);
		AlipayConfig.logResult(sWord);
		if (b){
			//商户订单号
			String out_trade_no = params.get("out_trade_no");

			//支付宝交易号
			String trade_no = params.get("trade_no");

			//付款金额
			String total_amount = params.get("total_amount");
			String ss="<h1 style='color:red'>付款成功！等待商城进一步操作！等待收货...</h1><br/>";
			String context="支付宝订单号："+trade_no+"<br/>商城订单号："+out_trade_no+"<br/>订单总价：:"+total_amount;
			context=ss+context;
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(context);
			response.getWriter().flush();
		}else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("验签失败");
			response.getWriter().flush();
		}
	}
}
