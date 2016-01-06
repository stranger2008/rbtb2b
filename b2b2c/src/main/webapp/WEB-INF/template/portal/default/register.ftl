<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>会员注册-${cfg_webname?if_exists}</title>
		<link href="/templets/${templateStyle?if_exists}/css/regiter.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/include/css/common.css" type="text/css"></link>
		<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
		<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/register.js"></script>
		<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
		<script type="text/javascript">
	    $(document).ready(function(){ 
	         //加载第一级菜单,第一个参数为父级ID,第二个等级参数,第三个参数为所属模块参数值,到最后一级无分类后，又有属性，加载显示分类属性
			 cate_select("${cfg_topcatid?if_exists}",1,"company");	
			 //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
			 area_select("${cfg_topareaid?if_exists}");
			 //加载公司类型
			 //cust_type_select();		
			 
	    });		     
		 </script>
	</head>
	<body>
		<#include "/${templateRoute?if_exists}/top.html">
		<!--导航结束-->
		<div class="w960">
			<div class="cont">
				<div class="title">
					<h2 class="t_font">
						当前位置：
						<a href="/">首页</a> >> 会员注册
					</h2>
				</div>
				<div class="content">
					<form method="post" id="register" name="registerForm">
						<div class="m_bar">
							<div class="bar">
								<p class="bar_font">
									>>已经是会员？
									<a href="/login.html">请点这里登录</a> >>忘记了密码？
									<a href="/portal/memberuser!executepwd.action">请点这里找回</a>
								</p>
								<p>
									请认真、仔细地填写以下信息，严肃的商业信息有助于您获得别人的信任，结交潜在的商业伙伴，获取商业机会！
									<span class="star">*</span>为必填项
								</p>
							</div>
						</div>
						<div class="clear"></div>
						<div class="move">
							<div class="account">
								<h2 class="fonts">
									账户信息
								</h2>
							</div>
							<div class="clear"></div>
							<div class="ac_cont">
								<table width="814px;">
									<tr>
										<td class="registertd1">
											会员类型
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<span>
												<@s.radio id="mem_type" name="member.mem_type"
													list=r"#{'0':'企业','1':'个人'}" value="0"
													onclick="getRadioValue('member.mem_type');" /> </span>
										</td>
										<td></td>
									</tr>


									<tr>
										<td class="registertd1">
											用户名
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.textfield id="user_name" name="user_name" cssClass="comname_text" onblur="UserNameIsNull();"maxLength="20" onfocus="UserNameForm();"/>
										</td>
										<td>
											<span id="user_nameError"></span><span id="user_nameForm"></span>
											<@s.fielderror>
												<@s.param>user_name</@s.param>
											</@s.fielderror>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											密码
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.password id="passwd" name="passwd" cssClass="comname_text"
												onblur="PasswdIsNull();" onfocus="PasswordForm()" />
										</td>
										<td>
											<span id="passwdError"></span><span id="passwordForm"></span>
											<@s.fielderror>
												<@s.param>passwd</@s.param>
											</@s.fielderror>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											确认密码
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.password id="confirm_pwd" name="confirm_pwd"
												cssClass="comname_text" onblur="Confirm_pwdIsNull();" onfocus="Confirm_pwdForm()" />
										</td>
										<td>
											<span id="confirm_pwdError"></span><span id="confirmpwdForm"></span>
										</td>
									</tr>
								</table>

							</div>
						</div>
						<!--账户信息end-->
						<div class="clear"></div>
						<div class="move">
							<div class="account">
								<h2 class="fonts">
									联系信息
								</h2>
							</div>
							<div class="clear"></div>
							<div class="contact_cont">
								<table width="814px;">

									<tr>
										<td class="registertd1">
											联系人
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.textfield id="contact_name" name="member.contact_name"
												cssClass="comname_text" cssStyle="width:130px;" onblur="Contact_nameIsNull();" onfocus="Contact_nameForm()"/>
											<input type="radio" name="member.contact_sex" value="先生" checked/>先生
											<input type="radio" name="member.contact_sex" value="女士"/>女士
										</td>
										<td>
											<span id="contact_nameError"></span><span id="contactnameForm"></span>
											<@s.fielderror>
												<@s.param>member.contact_name</@s.param>
											</@s.fielderror>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											所在地区
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<div id="arealist"
												style="display: inline-block; padding-left: 15px;"></div>
										</td>
										<td>
											<span id="arealistError"></span><span id="areaForm"></span>
											<@s.fielderror>
												<@s.param>area_attr</@s.param>
											</@s.fielderror>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											邮箱
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.textfield id="email" name="member.email"
												cssClass="comname_text" onblur="EmailIsNull();" onfocus="EmailForm();" />
										</td>
										<td>
											<span id="emailError"></span><span id="emailForm"></span>
											<@s.fielderror>
												<@s.param>member.email</@s.param>
											</@s.fielderror>
										</td>
									</tr>
                                    <tr>
										<td class="registertd1">
											电话
										</td>
										<td class="registertd2">
											
										</td>
										<td class="registertd3">
											<@s.textfield id="phone" name="member.phone"
												cssClass="comname_text"	maxlength="15" onblur="PhoneIsNull();" onfocus="phoneForm();"/>
										</td>
										<td>
											<span id="phoneError"></span><span id="phoneForm"></span>
											<@s.fielderror>
												<@s.param>member.phone</@s.param>
											</@s.fielderror>
										</td>
									</tr>
									<tr>
										<td class="registertd1">
											手机
										</td>
										<td class="registertd2">
											
										</td>
										<td class="registertd3">
											<@s.textfield id="cellphone" name="member.contact_cellphone"
												cssClass="comname_text" maxlength="15" onblur="CellphoneIsNull();"  onfocus="CellphoneForm();"/>
										</td>
										<td>
											<span id="cellphoneError"></span><span id="cellphoneForm"></span>
											<@s.fielderror>
												<@s.param>member.contact_cellphone</@s.param>
											</@s.fielderror>
										</td>
									</tr>

								</table>


							</div>
						</div>
						<!--联系信息end-->
						<div class="clear"></div>
						<div id="bus" class="move">
							<div class="account">
								<h2 class="fonts">
									公司信息
								</h2>
							</div>
							<div class="clear"></div>


							<div class="com_info">

								<table width="814px;">
									<tr>
										<td class="registertd1">
											公司名称
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.textfield id="cust_name" name="member.cust_name"
												cssClass="comname_text" onblur="Cust_nameIsNull();" onfocus="Cust_nameForm();"/>

										</td>
										<td>
											<span id="cust_nameError"></span><span id="custnameForm"></span>
											<@s.fielderror>
												<@s.param>member.cust_name</@s.param>
											</@s.fielderror>

										</td>
									</tr>

									<tr>
										<td class="registertd1">
											公司类别
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.radio id="cust_rage" name="member.cust_rage"
												list=r"#{'0':'供应商','1':'采购商','2':'二者皆有'}" value="2"
												onclick="rage_select('member.cust_rage');" />
										</td>
										<td>
											<span id="cust_nameError"></span>
											<@s.fielderror>
												<@s.param>member.cust_name</@s.param>
											</@s.fielderror>

										</td>
									</tr>

									<tr>
										<td class="registertd1">
											供应产品
										</td>
										<td class="registertd2">
											<span id="supply_star" class="star">*</span>
										</td>
										<td class="registertd3">
											<span id="gongying"> <@s.textfield id="cust_supply"
													name="member.cust_supply" cssClass="comname_text" onblur="Cust_supplyIsNull();" onfocus="Cust_supplyForm();"/>
											</span>
										</td>
										<td>
											<span id="cust_supplyError"></span>
											<span id="cust_supplyForm"></span>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											采购产品
										</td>
										<td class="registertd2">
											<span id="buy_star" class="star">*</span>
										</td>
										<td class="registertd3">
											<span id="chanpin"><@s.textfield id="cust_stock"
													name="member.cust_stock" cssClass="comname_text" onblur="Cust_stockIsNull();" onfocus="Cust_stockForm();"/> </span>
										</td>
										<td>
											<span id="cust_stockError"></span>
											<span id="cust_stockForm"></span>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											公司类型
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<@s.select id="cust_type" name="member.cust_type" list="cust_typeList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" onblur="Cust_typeIsNull();" onfocus="Cust_typeForm();"/>
										</td>
										<td>
											<span id="cust_typeError"></span><span id="custtypeForm"></span>
											<@s.fielderror>
												<@s.param>member.cust_type</@s.param>
											</@s.fielderror>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											经营模式
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
										   <#list clientStrList as client>
										      <input type="checkbox" name="member.client_status" value="${(client.para_value)?if_exists}" onblur="StatusIsNull();" />${(client.para_key)?if_exists}
										      <#if client_index%2!=0><br/></#if>
										   </#list>
										</td>
										<td>
											<span id="statusError"></span><span id="statusForm"></span>
										</td>
									</tr>

									<tr>
										<td class="registertd1">
											所属行业
										</td>
										<td class="registertd2">
											<span class="star">*</span>
										</td>
										<td class="registertd3">
											<span id="divlist"
												style="display: inline-block; padding-left: 15px;"></span>
										</td>
										<td>
											<span id="divlistError"></span><span id="catForm"></span>
											<@s.fielderror>
												<@s.param>cate_attr</@s.param>
											</@s.fielderror>
										</td>
									</tr>

								</table>

							</div>
						</div>
						<!--公司信息end-->
						<div class="rands">
							<table width="816px">
								<tr>
									<td class="registertd1">
										验证码
									</td>
									<td class="registertd2">
										<span class="star">*</span>
									</td>
									<td class="registertd3">
										<@s.textfield maxlength="4" name="commentrand" id="rands"
											cssStyle="width: 50px;" onblur="RandsIsNull();"
											onfocusin="Catefouce();" onfocus="RandsForm()" />
										<img src="/imgrand.action" style="vertical-align: middle;"
											onclick="changeValidateCode(this)" />
									</td>
									<td>
										<span id="randsError"></span><span id="randForm"></span>
									</td>
								</tr>

							</table>

						</div>

						<div class="clear"></div>
						<div class="move">
							<div class="move_top">
								<div class="agreement">
									${cfg_serviceterms?if_exists}
								</div>
							</div>
						</div>

						<div class="regonclick">
							<img src="/templets/${templateStyle?if_exists}/images/hxz_but.gif"
								onclick="registerFrom();" style="cursor: pointer;" />
						</div>
					</div>
					<!-- 地区和分类隐藏字段 -->
					<input type="hidden" id="hidden_cat_value" name="hidden_cat_value" />
                    <input type="hidden" id="hidden_area_value" name="hidden_area_value"/>
				</form>
			</div>
		</div>

		<div class="clear"></div>
		
		
		<#include "/${templateRoute?if_exists}/bottom.html">
		
	</body>
	
</html>


