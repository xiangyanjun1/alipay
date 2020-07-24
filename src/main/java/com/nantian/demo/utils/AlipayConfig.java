package com.nantian.demo.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

	public static String app_id = "2016091900550621";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCltBLWjHT9HArXfsBqxMyX8MooKbEG4FGeCWbq0vAqW9N9UOmjSil46CBOkE2dzSKICcONc5ZLxxHEUmpL10qrfZs5lXqeL2BejPENvl/eW1nC0qDg6sqH7YUuU1EOAJ3kP7d2wsyqidPl0d8lCEdskhb/7cPVHVHax2vMtpdinf73iHXqmZvrzCuJTbnYyC2amYuUHI4EyC2PGz3AXSstjs2Ys8oiYnwyGnMojsKr51hRGkiOcpFDHRFHDo3yaWRBsiYV1WbjznvVSOnZIqNMsZaU+1L5D0CL/05goCU03VQzqmOmk6njfk64o3CAOQBke7RDfOZw1mDq1hejCe2hAgMBAAECggEBAJTRnFWaQfuMcuRFTzTU2NbhB/54Kyh80HB6Czd9CPtSCygyQhNIKOUQvOsySLMfqBK7iiiBFguqlvdV+WdxzPslzQIzkLN+4zMnq9rnDpzaOExz9M7rr8fSd1k6tMI5pZR38SK1IzFKpcdy7NEKlXMZ5ZQnC0lcksZFcjiiCGYlAqci2v9qFy/7X+pIuzSG7TD6g4Ftws5o0GdowSYUFdJes9TCh+dKj7kAttiHduh1jHU7SCytAexKig1z6sjJvWbmAr7e4hrVp7MUIUPmq6Ua3Dr8RXxLuPar0hY9wNdCY5jozMl5+5aToiP8ieP59Jq6y0wJVqtecrAj1bPF15ECgYEA4GNMDYlITeVtfogbIW4iyJaprRQBYA6OCt1OlvyF59uVkdVg2jLzIjJcuOVBGHoY19HdxNTzzDWy/h2XpQfATpsnh2fD/OpxQpOUc1DmF1JvURFGRz7MbYbdNVXKVUK7pdtUX+HABifCEGa/Ig0F0NSdes4NxrrNTwuCmdMK5OUCgYEAvQxH6mUYyU7DRMX5RLmI1V1ToPLCSlCOfhUqI4yjNuNA5+093+rU0FNgtCn9GFWMP4x5AQEVzJw91fmXTY7kBI8fZWzePUJlRaP3gLj+SZS5jT/a0FJCAEp/fRuxr53p3/LLMDJQiTixJSJhcEXaoCa96+fdCcu75OtDTcMkNg0CgYBKgm6EqCtsQlzjgUqRBUNjnDPnlxcF0I92KBqqpFvKq+20qUgp7HUzy8liVC4TdKe/Mp8TdyslikdPfoAWesZu07KCkgBQgYDufJb0284rvGHeZ4AQtFepnvjhdwVi3m/1GSmhVRMweA9G5vdc1TozKAbK+pU0gl8eelH5xkx9yQKBgQCBUQtInUstOUF9o+b1Sez99s8kA6s/5obyXEvs8WEcRKu7ep+esiZNMxutYiONJ8e+GLsVIsbkpePuoMMM/LehHr4dRTFouihIUPcXCHR5pNCgfJOTeRFI3a0DyZPsS5sV5CF1oJVSqIMUocYBZOc+MgIsgvjCoKiw2PW7FceJaQKBgQCJZRZvJPt6SjP0Mv2cdRL1HXl6+9oGJ1tTW0IYUr/e2uN24jVO7vgCG4dam4GPbNQAHUmhf5oYAPMhoL+PhosGSR6sI0tmh00TGL5eT0jKthLIMz0DBAgbHzowYfj4TSri0N8e8iD3ecpJAnxgdv/dTWi6WGcBt5TviIXj2oXSMA==";

	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn5YADHUKvaF4hxrUI++00HFXmzucoFAE+sjlOVZt/FbP1ftC61fx7Yhfh0rYW671oL8cdMnH27JQgE89+elL60jLsNOtV1SVMWV2GX8Pz4/OAj0oXGtvmo7KG8xLT79aJgUWOOcP79uUory3hfPJL2cHKLO4HfjMF+Carj55EE0ac5fgxR+Fv5EeRdlJPT9EoFT+/RRHqjQYoconhvgyOGw3dDz4j9RnBw+zQ6DMns5JtzIogGfCNytHyyusFybWOMBOPfZMfNIjIdM372CqS1p3MyibsRoY0kF+WFDn4MIxGVnptnA5l7UDUQDgIzBa5VL+JRFmI8QjHIs6vgvI9wIDAQAB";

	public static String notify_url = "http://27n6pe.natappfree.cc/alipay/notifyUrl";

	public static String return_url = "http://27n6pe.natappfree.cc/alipay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "D:\\";


	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 *
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
