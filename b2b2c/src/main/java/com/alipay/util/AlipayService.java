package com.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.alipay.util.AlipayFunction;

/**
 *����alipay_service
 *���ܣ�֧�����ⲿ����ӿڿ���
 *��ϸ����ҳ�������������Ĵ����ļ�������Ҫ�޸�
 *�汾��3.1
 *�޸����ڣ�2010-11-01
 *˵��
  ���´���ֻ��Ϊ�˷����̻����Զ��ṩ��������룬�̻����Ը���Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
  �ô����ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ��ο���
 */

public class AlipayService {
	/**
	 * ���ܣ�����?�ύHTML
	 * @param partner ���������ID
	 * @param seller_email ǩԼ֧�����˺Ż����֧�����ʻ�
	 * @param return_url ��������ת��ҳ�� Ҫ�� ��http��ͷ��ʽ������·�����������?id=123�����Զ������
	 * @param notify_url ���׹���з�����֪ͨ��ҳ�� Ҫ�� ��http����ʽ������·�����������?id=123�����Զ������
	 * @param show_url ��վ��Ʒ��չʾ��ַ���������?id=123�����Զ������
	 * @param out_trade_no �������վ����ϵͳ�е�Ψһ������ƥ��
	 * @param subject ������ƣ���ʾ��֧��������̨��ġ���Ʒ��ơ����ʾ��֧�����Ľ��׹���ġ���Ʒ��ơ����б��
	 * @param body �������������ϸ��������ע����ʾ��֧��������̨��ġ���Ʒ������
	 * @param total_fee �����ܽ���ʾ��֧��������̨��ġ�Ӧ���ܶ��
	 * @param paymethod Ĭ��֧����ʽ���ĸ�ֵ��ѡ��bankPay(����); cartoon(��ͨ); directPay(���);  CASH(���֧��)
	 * @param defaultbank Ĭ�������ţ�����б��club.alipay.com/read.php?tid=8681379
	 * @param encrypt_key �5���ʱ���
	 * @param exter_invoke_ip ��ұ��ص��Ե�IP��ַ
	 * @param extra_common_param �Զ������ɴ���κ����ݣ���������ַ��⣩��������ʾ��ҳ����
	 * @param buyer_email Ĭ�����֧�����˺�
	 * @param royalty_type ������ͣ���ֵΪ�̶�ֵ��10������Ҫ�޸�
	 * @param royalty_parameters �����Ϣ��������Ҫ����̻���վ�������̬��ȡÿ�ʽ��׵ĸ�����տ��˺š������������˵�����ֻ������10��
	 * @param input_charset �ַ�����ʽ Ŀǰ֧�� GBK �� UTF-8
	 * @param key ��ȫУ����
	 * @param sign_type ǩ��ʽ �����޸�
	 * @param key ��ȫУ����
	 * @return �?�ύHTML�ı�
	 */
	public static String BuildForm(String partner,
			String seller_email,
			String return_url,
			String notify_url,
			String show_url,
			String out_trade_no,
			String subject,
			String body,
			String total_fee,
			String paymethod,
			String defaultbank,
			String anti_phishing_key,
			String exter_invoke_ip,
			String extra_common_param,
            String buyer_email,
			String royalty_type,
			String royalty_parameters,
            String input_charset,
            String key,
            String sign_type){
		Map sPara = new HashMap();
		sPara.put("service","create_direct_pay_by_user");
		sPara.put("payment_type","1");
		sPara.put("partner", partner);
		sPara.put("seller_email", seller_email);
		sPara.put("return_url", return_url);
		sPara.put("notify_url", notify_url);
		sPara.put("_input_charset", input_charset);
		sPara.put("show_url", show_url);
		sPara.put("out_trade_no", out_trade_no);
		sPara.put("subject", subject);
		sPara.put("body", body);
		sPara.put("total_fee", total_fee);
		sPara.put("paymethod", paymethod);
		sPara.put("defaultbank", defaultbank);
		sPara.put("anti_phishing_key", anti_phishing_key);
		sPara.put("exter_invoke_ip", exter_invoke_ip);
		sPara.put("extra_common_param", extra_common_param);
		sPara.put("buyer_email", buyer_email);
		sPara.put("royalty_type", royalty_type);
		sPara.put("royalty_parameters", royalty_parameters);
		
		Map sParaNew = AlipayFunction.ParaFilter(sPara); //��ȥ�����еĿ�ֵ��ǩ�����
		String mysign = AlipayFunction.BuildMysign(sParaNew, key);//���ǩ����
		
		StringBuffer sbHtml = new StringBuffer();
		List keys = new ArrayList(sParaNew.keySet());
		String gateway = "https://www.alipay.com/cooperate/gateway.do?";
		
		//GET��ʽ����
		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway + "_input_charset=" + input_charset + "\" method=\"get\">");
		//POST��ʽ���ݣ�GET��POST����ѡһ��
		//sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway + "_input_charset=" + input_charset + "\" method=\"post\">");
		
		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sParaNew.get(name);
			
			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
        sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"" + mysign + "\"/>");
        sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"" + sign_type + "\"/>");
        
        //submit��ť�ؼ��벻Ҫ����name����
        sbHtml.append("<input type=\"submit\" value=\"֧����ȷ�ϸ���\"></form>");
		
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
        
		return sbHtml.toString();
	}
}
