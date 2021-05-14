$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateFundForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "FundAPI",
		type : t,
		data : $("#formFund").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onFundSaveComplete(response.responseText, status);
			console.log(response);
		}
	});
}); 

function onFundSaveComplete(response, status){
	if(status == "success")
	{
		console.log(response +  " "+status);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			console.log("dataaaaaa");
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	
	
	$("#hidFundIDSave").val("");
	$("#formFund")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val());     
	$("#Name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#Amount").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#NIC").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#Address").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "FundAPI",
		type : "DELETE",
		data : "FundID=" + $(this).data("fundid"),
		dataType : "text",
		complete : function(response, status)
		{
			onFundDeletedComplete(response.responseText, status);
		}
	});
});

function onFundDeletedComplete(response, status)
{
	if(status == "success")
	{
		console.log(response);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateFundForm() {  
	// NAME  
	if ($("#Name").val().trim() == "")  {   
		return "Insert fullName.";  
		
	} 
	
	 // Amount 
	if ($("#Amount").val().trim() == "")  {   
		return "Insert Email.";  
	} 
	
	
	// NIC  
	if ($("#NIC").val().trim() == "")  {   
		return "Insert NIC."; 
		 
	}
	 
	 // is numerical value  
	if  ($("#Address").val().trim() ==""); {   
		return "Insert address.";  
		
	}
	 
	
		

	 
	 return true; 
	 
}
