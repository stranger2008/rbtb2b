


<@s.form action="/portal/supply!luList.action">
	
	<#list testList as s>
	
		${s.supply_id?if_exists}=====${s.title?if_exists}====<br/>
	
	
	</#list>
	

<br/>
 	${pageBar?if_exists}
 
 </@s.form>
 