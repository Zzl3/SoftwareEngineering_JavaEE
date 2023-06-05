package com.example.studyx.controller;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.studyx.service.DonationmoneyService;
import com.example.studyx.utils.AliPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AliPayController {

//    @Autowired
//    DonationmoneyService donationmoneyService;
//    @CrossOrigin
//    @RequestMapping(value = "/api/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
//    public String pay(AliPay aliPay) {
//        AlipayTradePagePayResponse response;
//        try {
//            //  发起API调用（以创建当面付收款二维码为例）
//            response = Factory.Payment.Page()
//                    .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), aliPay.getTotalAmount(), "");
//        } catch (Exception e) {
//            System.err.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
//        return response.getBody();
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/api/notify")  // 注意这里必须是POST接口
//    public String payNotify(HttpServletRequest request) throws Exception {
//        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
//            System.out.println("=========支付宝异步回调========");
//
//            Map<String, String> params = new HashMap<>();
//            Map<String, String[]> requestParams = request.getParameterMap();
//            for (String name : requestParams.keySet()) {
//                params.put(name, request.getParameter(name));
//                // System.out.println(name + " = " + request.getParameter(name));
//            }
//
//            String tradeNo = params.get("out_trade_no");
//            String gmtPayment = params.get("gmt_payment");
//            String alipayTradeNo = params.get("trade_no");
//            // 支付宝验签
//            if (Factory.Payment.Common().verifyNotify(params)) {
//                // 验签通过
//                System.out.println("交易名称: " + params.get("subject"));
//                System.out.println("交易状态: " + params.get("trade_status"));
//                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
//                System.out.println("商户订单号: " + params.get("out_trade_no"));
//                System.out.println("交易金额: " + params.get("total_amount"));
//                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
//                System.out.println("买家付款时间: " + params.get("gmt_payment"));
//                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
//
//                // 捐赠记录写入数据库
//                System.out.println("userid"+tradeNo);
//                //donationmoneyService.addDonationmoney(Double.parseDouble(params.get("buyer_pay_amount")),Integer.parseInt(tradeNo));
//                //
//            }
//        }
//        return "success";
//    }
}
