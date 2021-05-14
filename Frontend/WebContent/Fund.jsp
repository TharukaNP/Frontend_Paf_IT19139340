<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/Fund.js"></script>

<meta charset="ISO-8859-1">
<title>Funding</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Fund Management</h1>        
				
				<form id="formCustomer" name="formCustomer" method="post" action="FundAPI">  
					Name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					Amount:  
					<input id="amount" name="amount" type="text" class="form-control form-control-sm">  
					
					<br>
					 NIC:  
					 <input id="nic" name="nic" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Address:  
					 <input id="address" name="address" type="text" class="form-control form-control-sm">  
					 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="submit" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="Fund.jsp"> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divItemsGrid">   
					<%    
						Fund fundObj = new Fund();
						out.print(fundObj.readFund());   
					%>  
					
					<br>
					<br>
					 <a href="Login.jsp" class="btn btn-success">Logout</a>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>